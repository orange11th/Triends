<script setup>
import { onMounted, ref } from "vue";
import { attractionList } from "@/api/attraction";
import { useRouter } from "vue-router";
import { NaverMap, NaverMarker, NaverInfoWindow } from "vue3-naver-maps";

const router = useRouter();

const sidoCode = ref(1);
const contentTypeId = ref(12);
const attractions = ref([]);
const currentAttraction = ref({});

// onMounted(() => {
//   getAttractionList();
// });

function getAttractionList() {
  attractionList(
    sidoCode.value,
    contentTypeId.value,
    ({ data }) => {
      console.dir(data);
      attractions.value = data;
    },
    (error) => console.error()
  );
}

function moveDetail(contentId) {
  router.push({ name: "attraction-detail", params: { contentId } });
}

const map = ref();
const mapOptions = {
  latitude: 37.5666, // 지도 중앙 위도
  longitude: 126.9784, // 지도 중앙 경도
  zoom: 15,
  zoomControl: false,
  zoomControlOptions: { position: "TOP_RIGHT" },
};
const initLayers = [
  "BACKGROUND",
  "BACKGROUND_DETAIL",
  "POI_KOREAN",
  "TRANSIT",
  "ENGLISH",
];

const onLoadMap = (mapObject) => {
  map.value = mapObject;
};

const marker = ref();
const infoWindow = ref();
const isOpen = ref(false);

const onLoadMarker = (index, markerObject) => {
  console.log(index, markerObject);
  attractions.value[index].marker = markerObject;
  // marker.value = markerObject;
};
const onShowInfoWindow = (index) => {
  console.log(index);
  console.log(attractions.value[index].marker);
  isOpen.value = !isOpen.value;
  // console.log(markerObject);
  // marker.value = attractions.value[index].marker;
  currentAttraction.value = attractions.value[index];
};
const onLoadInfoWindow = (infoWindowObject) => {
  console.log(infoWindowObject);
  infoWindow.value = infoWindowObject;
};
</script>

<template>
  <div>
    <h2>관광지 목록</h2>

    <div class="map">
      <naver-map
        style="width: 100%; height: 600px"
        :mapOptions="mapOptions"
        :initLayers="initLayers"
        @onLoad="onLoadMap($event)"
      >
        <div
          v-for="(attraction, index) in attractions"
          :key="attraction.contentId"
        >
          <naver-marker
            :latitude="attraction.latitude"
            :longitude="attraction.longitude"
            @onLoad="onLoadMarker(index, $event)"
            @mouseover="onShowInfoWindow(index)"
            @mouseout="isOpen = false"
            @click="moveDetail(attraction.contentId)"
          >
          </naver-marker>
        </div>
        <naver-info-window
          :marker="currentAttraction.marker"
          :open="isOpen"
          @onLoad="onLoadInfoWindow($event)"
        >
          <div class="infoWindow-content" style="width: 250px">
            <img
              :src="currentAttraction.firstImage"
              alt=""
              style="width: 100%"
            />
            <h4>{{ currentAttraction.title }}</h4>
            <p>{{ currentAttraction.addr1 }}</p>
            <p
              style="
                overflow: hidden;
                text-overflow: ellipsis;
                white-space: nowrap;
              "
            >
              {{ currentAttraction.overview }}
            </p>
          </div>
        </naver-info-window>
      </naver-map>
    </div>

    <div class="tmp">
      <select name="selectSidoCode" id="selectSidoCode" v-model="sidoCode">
        <optgroup label="시도코드">
          <option value="1">서울</option>
          <option value="2">인천</option>
          <option value="3">대전</option>
          <option value="4">대구</option>
          <option value="5">광주</option>
          <option value="6">부산</option>
          <option value="7">울산</option>
          <option value="8">세종특별자치시</option>
          <option value="31">경기도</option>
          <option value="32">강원도</option>
          <option value="33">충청북도</option>
          <option value="34">충청남도</option>
          <option value="35">경상북도</option>
          <option value="36">경상남도</option>
          <option value="37">전라북도</option>
          <option value="38">전라남도</option>
          <option value="39">제주도</option>
        </optgroup>
      </select>
    </div>
    <div class="tmp">
      <select
        name="selectContentTypeId"
        id="selectContentTypeId"
        v-model="contentTypeId"
      >
        <optgroup label="관광지 타입">
          <option value="12">관광지</option>
          <option value="14">문화시설</option>
          <option value="15">축제</option>
          <option value="28">레저</option>
          <option value="32">숙박</option>
          <option value="38">쇼핑</option>
          <option value="39">식당</option>
        </optgroup>
      </select>
      <button @click="getAttractionList">검색</button>
    </div>
    <ul>
      <li v-for="attraction in attractions" :key="attraction.contentId">
        <p>
          <a href="" @click.prevent="moveDetail(attraction.contentId)">{{
            attraction.title
          }}</a>
        </p>
        <img :src="attraction.firstImage" alt="" />
        <p>{{ attraction.addr1 }}</p>
        <p>전화번호: {{ attraction.tel }}</p>
      </li>
    </ul>
  </div>
</template>

<style scoped>
.tmp {
  margin: 10px;
}
</style>
<!-- 시도코드 값				   타입 아이디 값
1	  서울				     12	관광지
2	  인천				     14	문화시설
3	  대전				     15	축제
4	  대구				     25	x
5	  광주				     28	레저
6	  부산				     32 숙박
7	  울산				     38	상점
8	  세종특별자치시		  39 식당
31	경기도
32	강원도
33	충청북도
34	충청남도
35	경상북도
36	경상남도
37	전라북도
38	전라남도
39	제주도 -->
