<script setup>
import { ref } from 'vue';
import { attractionList } from '@/api/attraction';
import { useRouter } from "vue-router";

const router = useRouter()

const sidoCode = ref(1);
const contentTypeId = ref(12);
const attractions = ref([]);

function getAttractionList() {
  attractionList(sidoCode.value, contentTypeId.value, ({ data }) => { attractions.value = data }, (error) => console.error())
}

function moveDetail(contentId){
  router.push({name: "attraction-detail", params: {contentId}})
}

</script>
<template>
  <div>
    <h2>관광지 목록</h2>
    <div class="tmp">
      <select name="selectSidoCode" id="selectSidoCode" v-model="sidoCode">
        <optgroup label="시도코드">
          <option value=1>서울</option>
          <option value=2>인천</option>
          <option value=3>대전</option>
          <option value=4>대구</option>
          <option value=5>광주</option>
          <option value=6>부산</option>
          <option value=7>울산</option>
          <option value=8>세종특별자치시</option>
          <option value=31>경기도</option>
          <option value=32>강원도</option>
          <option value=33>충청북도</option>
          <option value=34>충청남도</option>
          <option value=35>경상북도</option>
          <option value=36>경상남도</option>
          <option value=37>전라북도</option>
          <option value=38>전라남도</option>
          <option value=39>제주도</option>
        </optgroup>
      </select>
    </div>
    <div class="tmp">
      <select name="selectContentTypeId" id="selectContentTypeId" v-model="contentTypeId">
        <optgroup label="관광지 타입">
          <option value=12>일반 관광지</option>
          <option value=14>문화생활</option>
          <option value=15>축제</option>
          <option value=28>유흥</option>
          <option value=32>숙박</option>
          <option value=38>시장</option>
          <option value=39>식당</option>
        </optgroup>
      </select>
    </div>
    <div class="tmp">
      <button @click="getAttractionList">검색</button>
    </div>
    <ul>
      <li v-for="attraction in attractions" :key="attraction.contentId">
        <p><a href="" @click.prevent="moveDetail(attraction.contentId)">{{ attraction.title }}</a></p>
        <img :src="attraction.firstImage" alt="">
        <p>{{ attraction.addr1}}</p>
        <p>전화번호: {{ attraction.tel}}</p>
      </li>
    </ul>
  </div>
</template>

<style scoped>
.tmp {
  margin: 10px;
}
</style>
시도코드 값				   타입 아이디 값
1	  서울				     12	그냥 여행지? 잘모르겠다 산도있고 해변도있고 절도있고
2	  인천				     14	문화원, 박물관, 예술회관 등
3	  대전				     15	축제
4	  대구				     25	컨셉 여행지? (얘는 빼야겠어요...join하면 결과가 0개라서)
5	  광주				     28	스키장, 클럽, 카지노, 저수지 등 유흥시설
6	  부산				     32	호텔, 리조트 등 숙박시설
7	  울산				     38	시장
8	  세종특별자치시		39 식당
31	경기도
32	강원도
33	충청북도
34	충청남도
35	경상북도
36	경상남도
37	전라북도
38	전라남도
39	제주도