<script setup>
import { onMounted, ref, watch } from "vue";
import { attractionList, searchList } from "@/api/attraction";
import { useRouter } from "vue-router";
import { NaverMap, NaverMarker, NaverInfoWindow } from "vue3-naver-maps";

// import heart from "@/components/attraction/item/heart.vue";

import "@/assets/css/attraction/map.css";
import "@/assets/css/attraction/list.css";

const router = useRouter();

onMounted(() => {
  getAttractionList();
});

const sidoCode = ref(1);
const contentTypeId = ref(12);
const attractions = ref([]);
const currentAttraction = ref({});

watch(sidoCode, (newVal) => {
  param.value.sido = newVal;
});

const selectSido = ref([
  { text: "서울특별시", value: "1" },
  { text: "인천광역시", value: "2" },
  { text: "대전광역시", value: "3" },
  { text: "대구광역시", value: "4" },
  { text: "광주광역시", value: "5" },
  { text: "부산광역시", value: "6" },
  { text: "울산광역시", value: "7" },
  { text: "세종특별자치시", value: "8" },
  { text: "경기도", value: "31" },
  { text: "강원도", value: "32" },
  { text: "충청북도", value: "33" },
  { text: "충청남도", value: "34" },
  { text: "경상북도", value: "35" },
  { text: "경상남도", value: "36" },
  { text: "전라북도", value: "37" },
  { text: "전라남도", value: "38" },
  { text: "제주도", value: "39" },
]);
const selectContentType = ref([
  { text: "관광지", value: "12" },
  { text: "문화시설", value: "14" },
  { text: "축제", value: "15" },
  { text: "레저", value: "28" },
  { text: "숙박", value: "32" },
  { text: "상점", value: "38" },
  { text: "식당", value: "39" },
]);

function getAttractionList() {
  attractionList(
    sidoCode.value,
    contentTypeId.value,
    ({ data }) => {
      console.log();
      attractions.value = data;
    },
    (error) => console.error()
  );
}

const param = ref({
  sido: sidoCode.value,
  keyword: "",
});
const searchAttractionList = () => {
  console.log("검색합니다.", param.value);
  searchList(
    param.value,
    ({ data }) => {
      attractions.value = data;
    },
    (error) => {
      console.log(error);
    }
  );
};

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

const infoWindow = ref();
const isOpen = ref(false);

const onLoadMarker = (index, markerObject) => {
  attractions.value[index].marker = markerObject;
};
const onShowInfoWindow = (index) => {
  isOpen.value = !isOpen.value;
  currentAttraction.value = attractions.value[index];
};
const onLoadInfoWindow = (infoWindowObject) => {
  infoWindow.value = infoWindowObject;
};
</script>

<template>
  <div class="main">
    <div class="map">
      <naver-map
        style="width: 100%; height: 700px"
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
            <p class="addr">{{ currentAttraction.addr1 }}</p>
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

    <div class="condition-space">
      <h2>여행지 탐색</h2>
      <div class="input-space">
        <div class="select-space">
          <select
            name="selectSidoCode"
            id="selectSidoCode"
            v-model="sidoCode"
            @change="getAttractionList()"
          >
            <optgroup label="시/도 선택">
              <option
                v-for="option in selectSido"
                :key="option.value"
                :value="option.value"
              >
                {{ option.text }}
              </option>
            </optgroup>
          </select>
          <select
            name="selectContentTypeId"
            id="selectContentTypeId"
            v-model="contentTypeId"
            @change="getAttractionList()"
          >
            <optgroup label="카테고리 선택">
              <option
                v-for="option in selectContentType"
                :key="option.value"
                :value="option.value"
              >
                {{ option.text }}
              </option>
            </optgroup>
          </select>
        </div>
        <div class="search-space">
          <input
            type="text"
            v-model="param.keyword"
            placeholder="키워드를 입력하세요!"
            @keyup.enter="searchAttractionList"
          />
          <a @click="searchAttractionList">
            <img src="@/assets/img/icon/search-icon.svg" alt="검색" />
          </a>
        </div>
      </div>
    </div>

    <div class="list">
      <div
        class="list-item"
        v-for="attraction in attractions"
        :key="attraction.contentId"
      >
        <a class="detail">
          <div
            class="text-space"
            @click.prevent="moveDetail(attraction.contentId)"
          >
            <h5>{{ attraction.title }}</h5>
            <p class="addr">{{ attraction.addr1 }}</p>
            <p
              class="overview"
              style="
                white-space: nowrap;
                overflow: hidden;
                text-overflow: ellipsis;
              "
            >
              {{ attraction.overview }}
            </p>
          </div>
          <!-- <heart></heart> -->
          <img
            id="attraction-img"
            :src="attraction.firstImage"
            @click.prevent="moveDetail(attraction.contentId)"
            alt="사진"
          />
        </a>
        <hr />
      </div>
    </div>
  </div>
</template>

<style scoped></style>
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
