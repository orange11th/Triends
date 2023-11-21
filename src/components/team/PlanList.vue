<template>
  <div class="playground">
    <div v-for="(item, idx) in state.lists" :key="item.id" class="col"
    @drop.prevent="onDrop($event, idx)"
    @dragenter.prevent
    @dragover.prevent>
    <div class="title-and-delete-container">
    <div class="box-title">{{ item.date }}</div>
      <div v-if="item.id > 1" class="delete-box-button">
        <button class="delete-button delete-box-button-position" @click="deleteBox(idx)">
            <svg viewBox="0 0 22 22">
                <circle cx="11" cy="11" r="10"></circle>
            </svg>
            <div class="cross"></div>
        </button>
        
        <!-- <button @click="deleteBox(idx)">X</button> -->
      </div>
    </div>
      <div v-if="item.id === 1" class="travel-destination">
      <div class="new-item-input">
        <select
        name="selectSidoCode"
        id="selectSidoCode"
        v-model="sidoCode"
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
      <input
      type="text"
      v-model="param.keyword"
      placeholder="키워드를 입력하세요!"
      @keyup.enter="searchAttractionList"
      />
      <button @click="searchAttractionList">검색</button>


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
      <div v-for="(numItem, numIdx) in item.numberList" :key="numIdx" class="box" draggable="true" @dragstart="startDrag($event, numItem, idx)">
      <div class="content-with-delete-button">
        <div class="content-container">
        <img :src="numItem.image" class="box-image">
        <p>{{ numItem.title }}</p>
        </div>
        <!-- <button class="delete-button" @click="deleteItem(numItem, idx)">X</button> -->
        <button class="delete-button delete-item-button-position" @click="deleteItem(numItem, idx)">
            <svg viewBox="0 0 22 22">
                <circle cx="11" cy="11" r="10"></circle>
            </svg>
            <div class="cross"></div>
        </button>
      
      </div>
    </div>
    </div>
  </div>
  <div class="new-item-input">
    <div>여기서 새로운 날짜 선택해서 계획박스 추가할 수 있음</div>
  <input type="date" v-model="newItem.date" placeholder="날짜 선택" />
  <button @click="addNewBox">추가</button>
  <div>이때 최종저장 시키는 것</div>
  <button @click="savePlans">저장</button>
  <li v-for="team in plans" :key="team.teamId" class="team-item">
    <h1>{{team}}</h1>
  </li>

</div>
</template>


  
<script setup>
import { reactive, ref, onMounted, watch } from 'vue';
import { planList, placeList, saveNewPlan } from "@/api/team";
import { searchList } from "@/api/attraction";
import { useRoute } from 'vue-router';

const route = useRoute();
console.log(route.params.teamId); // teamId 정보 가져옴

const plans = ref([]);

const sidoCode = ref(1);
watch(sidoCode, (newVal) => {
  param.value.sido = newVal;
});
const param = ref({
  sido: sidoCode.value,
  keyword: "",
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

const searchAttractionList = () => {
  console.log("검색합니다.", param.value);
  const firstItem = state.lists.find(item => item.id === 1);
  if (firstItem) {
    firstItem.numberList = [];
  }
  searchList(
    param.value,
    ({ data }) => {
      console.log(data);
      const transformedData = data.map(item => ({
        title: item.title,
        address: item.addr1,
        image: item.firstImage,
        content: item.contentId,
      }));

      const firstItem = state.lists.find(item => item.id === 1);
      if (firstItem) {
        firstItem.numberList.push(...transformedData);
      }
    },
    (error) => {
      console.log(error);
    }
  );
};


onMounted(() => {
  getPlanList();
});

const getPlanList = () => {
  planList(route.params.teamId,
    ({ data }) => {
      data.forEach(plan => {
          addPlanToStateLists(plan);
      });
    },
      (error) => {
      console.log(error)
      }
  );
}

const sortStateListsByDate = () => {
  state.lists.sort((a, b) => new Date(a.date) - new Date(b.date));
};

const addPlanToStateLists = (plan) => {
  // YYYYMMDD 형식에서 YYYY, MM, DD 추출
  const year = plan.date.substring(0, 4);
  const month = plan.date.substring(4, 6);
  const day = plan.date.substring(6, 8);

  // YYYY-MM-DD 형식으로 조합
  const formattedDate = `${year}-${month}-${day}`;
  console.log(formattedDate);
  // 빈 numberList로 초기화
  const newBox = {
    id: state.lists.length + 1,
    date: formattedDate,
    numberList: [] 
  };
  // state.lists = state.lists.filter(item => item.id === 1);
  // placeList 함수를 호출하여 관광지 데이터 가져오기
  placeList(plan.planId,
    ({ data }) => {
      newBox.numberList = data.map(place => ({
      content: place.placeId, // placeId (혹은 적절한 속성 이름)를 content로 설정
      title: place.title,      // title 속성 추가
      image: place.image
    }));

      // newBox.numberList = data.map(place => ({ content: place.placeId }));
      state.lists.push(newBox);
      state.lists.sort((a, b) => new Date(a.date) - new Date(b.date));
    },
    (error) => {
      console.error('Error fetching place list:', error);
    }
    );
};

const state = reactive({
  lists: [
    {
      id: 1,
      numberList: []
    },
  ]
});

const addNewBox = () => {
  if (newItem.date) {
    const newBox = {
      id: state.lists.length + 1,
      date: newItem.date,
      numberList: []
    };
    state.lists.push(newBox); // 새 박스를 리스트에 추가
    newItem.date = ''; // 날짜 입력 필드 초기화

    // 리스트를 날짜 기준으로 오름차순 정렬
    state.lists.sort((a, b) => new Date(a.date) - new Date(b.date));
  }
};

const newItem = reactive({
    content: '',
    date: ''
});

const startDrag = (event, item, listIndex) => {
  event.dataTransfer.setData('draggedItem', JSON.stringify(item));
  event.dataTransfer.setData('fromListIndex', listIndex.toString());
};
const onDrop = (event, toListIndex) => {
  const draggedItem = JSON.parse(event.dataTransfer.getData('draggedItem'));
  const fromListIndex = parseInt(event.dataTransfer.getData('fromListIndex'));

  const fromList = state.lists[fromListIndex];
  const itemIndex = fromList.numberList.findIndex(item => item.content === draggedItem.content);
  if (itemIndex > -1) {
    fromList.numberList.splice(itemIndex, 1);
  }

  const toList = state.lists[toListIndex];
  toList.numberList.push(draggedItem);
};

const savePlans = () => {
  const newPlans = state.lists
  .filter(item => item.id >= 2 && !plans.value.some(p => p.date === item.date))
  .map(item => {
    const formattedDate = item.date.replace(/-/g, '');
    const places = item.numberList.map(numItem => ({
      title: numItem.title,  // 제목 추가
      content: numItem.content, // 콘텐츠 ID
      image: numItem.image,
    }));
    console.log(places);
      return {
        date: formattedDate,
        places: places
      };
    });
    saveNewPlan(route.params.teamId, newPlans);
};

const deleteItem = (item, listIndex) => {
  const list = state.lists[listIndex];
  const itemIndex = list.numberList.findIndex(numItem => numItem.content === item.content);
  if (itemIndex > -1) {
    list.numberList.splice(itemIndex, 1);
  }
};

const deleteBox = (index) => {
  state.lists.splice(index, 1);
};



  </script>
  
  <style scoped>
.content-with-delete-button {
  display: flex;
  justify-content: space-between;
  align-items: center;
}

.box p {
  margin: 0; /* p 태그의 기본 마진 제거 */
  font-size: 1.1em; /* 폰트 크기 증가 */
  color: darkslategray; /* 폰트 색상 */
}


.box {
  width: 200px; /* 박스 너비 설정 */
  min-height: 250px; /* 박스 최소 높이 설정 */
  margin-top: 10px; /* 박스 간의 상단 마진 */
  padding: 15px; /* 내부 패딩 증가 */
  background-color: #fff; /* 박스 배경색을 흰색으로 변경 */
  border-radius: 10px; /* 박스 테두리 둥글기 증가 */
  box-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0.2), 0 6px 20px 0 rgba(0, 0, 0, 0.19); /* 그림자 효과 추가 */
  box-sizing: border-box; /* 패딩을 포함한 박스 크기 계산 */
  display: flex;
  flex-direction: column;
  justify-content: center; /* 세로 방향 중앙 정렬 */
  align-items: center; /* 가로 방향 중앙 정렬 */
  transition: transform 0.3s; /* 부드러운 변환 효과 추가 */
}

.box:hover {
  transform: scale(1.05); /* 마우스 오버 시 약간 확대 */
}

.col {
  display: flex;
  flex-direction: column;
  align-items: center;
  width: 150px; /* 혹은 적절한 너비 */
  margin-left: 5px;
  background-color: #fff;
  border: 1px solid lightgrey;
  border-radius: 8px;
  max-height: 600px; /* 최대 높이 설정 */
  overflow-y: auto; /* 세로 스크롤바가 필요할 때 나타남 */
  flex: 0 0 300px;
}

.playground {
  display: flex;
  flex-direction: row;
  overflow-x: auto;
}



.box-image {
  width: 150px; /* 이미지 너비 고정 */
  height: 150px; /* 이미지 높이 고정 */
  object-fit: cover; /* 이미지를 컨테이너에 맞추고 필요한 경우 잘라냄 */
  object-position: center; /* 이미지의 중앙 부분을 항상 표시 */
  display: block;
  margin: 30px auto 0; /* 상단 여백 30px, 나머지는 자동으로 조정 */
  border-radius: 10px;
}


.playground {
  display: flex;
  flex-direction: row;
  overflow-x: auto; /* 요소들이 넘칠 때 가로 스크롤바 생성 */
}

.content-container {
  display: flex; /* Flexbox 레이아웃 사용 */
  flex-direction: column; /* 자식 요소들을 세로 방향으로 정렬 */
  justify-content: center; /* 세로 방향 중앙 정렬 */
  align-items: center; /* 가로 방향 중앙 정렬 */
  width: 100%;
  text-align: center; /* 텍스트 중앙 정렬 */
}

.box {
  position: relative; /* 상대적 위치 설정 */
  /* 기존 스타일 유지 */
}


 .delete-button {
  position: relative;
  width: 50px;
  height: 50px;
  border-radius: 50%;
  cursor: pointer;
  background: transparent;
  border: none;
  opacity: 0.5;
  filter: gray;
  -webkit-filter: grayscale(100%);
  transition: all 0.3s ease;
}

.delete-button svg {
  display: block;
  width: 100%;
  fill: none;
  height: 100%;
  stroke: #F26175;
  stroke-width: 2;
}
.delete-button svg circle {
  stroke-dasharray: 64;
  transition: all 0.3s ease;
}

.delete-button .cross {
  position: absolute;
  left: 50%;
  top: 50%;
  transform: translate(-50%, -50%) rotate(135deg);
  transition: all 0.3s ease;
}

.delete-button .cross:before, .delete-button .cross:after {
  content: "";
  position: absolute;
  background: #F26175; /* 십자가 색상 */
  left: 50%;
  top: 50%;
  transform: translate(-50%, -50%) rotate(0deg); /* 수직 교차로 X 모양 생성 */
  transition: all 0.3s ease;
}

.delete-button .cross:before {
  width: 15px; /* 가로선 길이 */
  height: 3px; /* 가로선 두께 */
}

.delete-button .cross:after {
  width: 3px; /* 세로선 두께 */
  height: 15px; /* 세로선 길이 */
}


.delete-button:hover {
  filter: none;
  -webkit-filter: grayscale(0%);
  opacity: 1;
} 
/* .delete-button {
  position: absolute; 
  top: 10px; 
  right: 10px; 
 } */
 .box-title {
  font-size: 1.4em; /* 폰트 크기를 증가시켜 제목처럼 보이게 함 */
  font-weight: bold; /* 굵은 글씨체로 표시 */
  color: #333; /* 제목의 색상 */
  margin-bottom: 15px; /* 제목 아래에 여백 추가 */
  text-align: center; /* 중앙 정렬 */
  width: 100%; /* 전체 너비 사용 */
}
/* 첫 번째 delete-button 위치 */
/* .delete-box-button-position {
  position: absolute;
  top: 10px;
  right: 10px;
} */


/* 두 번째 delete-button 위치 */
.delete-item-button-position {
  position: absolute;
  top: 2px;
  right: 2px;
}
.title-and-delete-container {
  display: flex;
  align-items: center;
  width: 100%;
  position: relative; /* 상대적 위치 설정 */
}

.box-title {
  font-size: 1.4em;
  font-weight: bold;
  color: #333;
  margin-bottom: 15px;
  margin-top: 20px; /* 상단 여백 추가 */
  text-align: center;
  width: 100%;
}


.delete-box-button {
  position: absolute;
  top: 10px;
  right: 10px;
}
.playground {
  display: flex;
  flex-direction: row;
  overflow-x: auto; /* 요소들이 넘칠 때 가로 스크롤바 생성 */
}

/* col 클래스에 스크롤바 스타일 적용 */
.col {
  /* 기존 스타일 유지 */
  overflow-y: scroll; /* 스크롤바 활성화 */
}

.col::-webkit-scrollbar {
  width: .4em; 
}

.col::-webkit-scrollbar,
.col::-webkit-scrollbar-thumb {
  overflow: visible;
  border-radius: 4px;
}

.col::-webkit-scrollbar-thumb {
  background: rgba(0,0,0,.2); 
}

/* 스크롤바 숨김 효과를 위한 커버 스타일 */
.col-cover {
  position: absolute;
  background: #fff;
  height: 100%;  
  top: 0;
  right: 0;
  width: .4em;
  transition: all .5s;
  opacity: 1;
}

/* 마우스 오버하지 않았을 때 스크롤바 숨김 */
.col::-webkit-scrollbar-thumb {
  background-color: transparent; /* 투명한 스크롤바 */
}

/* 마우스 오버 시 스크롤바 표시 */
.col:hover::-webkit-scrollbar-thumb {
  background-color: rgba(0, 0, 0, 0.2); /* 스크롤바 색상 설정 */
}

/* 가로 스크롤바가 있는 요소에 대한 기본 스타일 */
.playground {
  display: flex;
  flex-direction: row;
  overflow-x: auto; /* 가로 스크롤바 활성화 */
}

/* 웹킷 브라우저용 스크롤바 스타일 */
.playground::-webkit-scrollbar {
  height: .4em; /* 스크롤바 높이 설정 */
}

/* 기본 상태에서 스크롤바 숨김 */
.playground::-webkit-scrollbar-thumb {
  background-color: transparent; /* 투명한 스크롤바 */
}

/* 마우스 오버 시 스크롤바 표시 */
.playground:hover::-webkit-scrollbar-thumb {
  background-color: rgba(0, 0, 0, 0.2); /* 스크롤바 색상 설정 */
}
.search-space {
  background-color: white;
  border: solid 1px;
  border-color: var(--gray);
  border-radius: 100px;
  padding: 5px 10px;
  display: flex;
  height: 40px;
  margin-top: 10px;
  flex-direction: row;
}
.search-space input {
  border: none;
}
.search-space input:focus {
  outline: none;
}
.search-space img {
  width: 20px;
  margin: 5px;
}
  </style>
  