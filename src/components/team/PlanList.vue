<template>
  
  <div class="playground">
    
    <div v-for="(item, idx) in state.lists" :key="item.id" class="col"
         @drop.prevent="onDrop($event, idx)"
         @dragenter.prevent
         @dragover.prevent>
         <div class="box-title">{{ item.date }}</div>
             <!-- 박스 삭제 버튼 추가: id가 1보다 큰 경우에만 표시 -->
      <div v-if="item.id > 1" class="delete-box-button">
        <button @click="deleteBox(idx)">X</button>
      </div>
      <div v-if="item.id === 1" class="travel-destination">
        후보 여행지
        <!-- 새로운 아이템 입력 필드 -->
      <div class="new-item-input">
        <!-- <input type="date" v-model="newItem.date" /> -->
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
      />
      <button @click="searchAttractionList">검색</button>
<!--       
      <input type="text" v-model="newItem.content" placeholder="관광지 검색" />
      
      
      <div>이런 형태로 관광지들이 쫘르륵 뜨게!</div>
      

      <button @click="register">검색</button> -->
    </div>
      </div>
      

      <div v-for="(numItem, numIdx) in item.numberList" :key="numIdx" class="box" draggable="true" @dragstart="startDrag($event, numItem, idx)">
      <div class="content-with-delete-button">
        <p>{{ numItem.title }}</p>
        <button @click="deleteItem(numItem, idx)">X</button>
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
const attractions = ref([]);
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


// const searchAttractionList = () => {
//   console.log("검색합니다.", param.value);
//   searchList(
//     param.value,
//     ({ data }) => {
//       console.log(data);
//       // 데이터 변환
//       const transformedData = data.map(item => ({
//         content: item.contentId,
//         // contentId: item.contentId,
//         // title: item.title,
//         // 기타 필요한 필드
//       }));

//       // index=1번 요소의 numberList에 데이터 추가
//       const firstItem = state.lists.find(item => item.id === 1);
//       if (firstItem) {
//         firstItem.numberList.push(...transformedData);
//       }
//     },
//     (error) => {
//       console.log(error);
//     }
//   );
// };


const addToStateLists = (newData) => {
  // newData는 변환된 관광지 정보 배열
  newData.forEach(item => {
    state.lists.push({
      id: state.lists.length + 1, // 혹은 적절한 고유 ID
      content: item,
      // 기타 필요한 필드
    });
  });
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

// const getPlanList = () => {
//   planList(route.params.teamId,
//     ({ data }) => {
//       data.forEach(plan => {
//         addPlanToStateLists(plan);
//       });
//       state.lists.sort((a, b) => {
//       const dateA = new Date(a.date);
//       const dateB = new Date(b.date);
//       return dateA - dateB;
//     });
//       // state.lists.sort((a, b) => new Date(a.date) - new Date(b.date));
//       // sortStateListsByDate(); // 정렬 실행
//     },
//     (error) => {
//       console.log(error);
//     }
//   );
// };

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
      title: place.title      // title 속성 추가
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



//todo : like 눌렀으면 눌렀따고 표시하기
//todo : 게시물 삭제

  const state = reactive({
    lists: [
      {
        id: 1,
        numberList: [] //[ { content: 125266 }, { content: 125405 }, { content: 125406 }, { content: 125407 } ]
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


// 새 컬럼 추가 함수
// const register = () => {
//     if (newItem.content) {
//       const newBoxItem = {
//         content: newItem.content,
//       };
//       const firstColumn = state.lists.find(column => column.id === 1);
//       if (firstColumn) firstColumn.numberList.push(newBoxItem);
//       newItem.content = '';
//     }
//   };
const newItem = reactive({
    content: '',
    date: ''
});

const startDrag = (event, item, listIndex) => {
  // 드래그할 아이템의 정보와 원래 위치(리스트 인덱스)를 설정
  event.dataTransfer.setData('draggedItem', JSON.stringify(item));
  event.dataTransfer.setData('fromListIndex', listIndex.toString());
};
const onDrop = (event, toListIndex) => {
  // 드래그된 아이템과 그 원래 위치를 데이터 전송 객체에서 가져옴
  const draggedItem = JSON.parse(event.dataTransfer.getData('draggedItem'));
  const fromListIndex = parseInt(event.dataTransfer.getData('fromListIndex'));

  // 드래그된 아이템이 원래 있던 리스트에서 제거
  const fromList = state.lists[fromListIndex];
  const itemIndex = fromList.numberList.findIndex(item => item.content === draggedItem.content);
  if (itemIndex > -1) {
    fromList.numberList.splice(itemIndex, 1);
  }

  // 드래그된 아이템을 새 위치에 추가
  const toList = state.lists[toListIndex];
  toList.numberList.push(draggedItem);
};

// const savePlans = () => {
//   const plan = state.lists
//     .filter(item => item.id >= 2)
//     .map(item => {
//       return {
//         date: item.date,
//         place: item.numberList.map(numItem => numItem.content)
//       };
//     });
    
//   console.log(plan); // 저장된 계획 확인

  
// };



// const savePlans = () => {
//   const newPlans = state.lists
//     .filter(item => item.id >= 2 && !plans.value.some(p => p.date === item.date))
//     .map(item => {
//       const formattedDate = item.date.replace(/-/g, '');
//       return {
//         date: formattedDate,
//         places: item.numberList.map(numItem => numItem.content)
//       };
//     });


//   saveNewPlan(route.params.teamId, newPlans);

//   console.log("저장");
// };


const savePlans = () => {
  const newPlans = state.lists
    .filter(item => item.id >= 2 && !plans.value.some(p => p.date === item.date))
    .map(item => {
      const formattedDate = item.date.replace(/-/g, '');
      const places = item.numberList.map(numItem => ({
        title: numItem.title,  // 제목 추가
        content: numItem.content // 콘텐츠 ID
      }));
      return {
        date: formattedDate,
        places: places
      };
    });
    saveNewPlan(route.params.teamId, newPlans);
  // 서버에 newPlans 전송
  // 예: axios.post('/api/save/' + teamId, newPlans)
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
  .playground {
    display: flex;
    flex-direction: row; /* 박스들을 수평으로 나열 */
  }
  
  .col {
    display: flex;
    flex-direction: column;
    align-items: center;
    height: 500px;
    width: 150px;
    background-color: #fff;
    border: 1px solid lightgrey;
    margin-left: 5px;
    border-radius: 8px;
  }
  
  .content-with-delete-button {
  display: flex;
  justify-content: space-between;
  align-items: center;
}


.box p {
  margin: 0; /* p 태그의 기본 마진 제거 */
  font-size: 1.1em; /* 폰트 크기 증가 */
  color: white; /* 폰트 색상 */
}

.attraction-image {
  width: 180px; /* 이미지 크기 조정 */
  height: auto; /* 이미지 높이 자동 조정 */
}
.box {
  width: 200px; /* 박스 너비 설정 */
  min-height: 50px; /* 박스 최소 높이 설정 */
  margin-top: 10px; /* 박스 간의 상단 마진 */
  padding: 10px; /* 내부 패딩 */
  background-color: #7b71b7; /* 박스 배경색 */
  border-radius: 5px; /* 박스 테두리 둥글기 */
  box-sizing: border-box; /* 패딩을 포함한 박스 크기 계산 */

  /* 콘텐츠가 많아지면 박스 높이가 자동으로 늘어남 */
  display: flex;
  flex-direction: column;
}

.box p {
  margin: 0 0 10px 0; /* 아래쪽 마진 추가 */
  word-wrap: break-word;
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
  max-height: 500px; /* 최대 높이 설정 */
  overflow-y: auto; /* 세로 스크롤바가 필요할 때 나타남 */
}


.playground {
  display: flex;
  flex-direction: row;
  overflow-x: auto; /* 가로 스크롤바 설정 */
  /* 기타 스타일 */
}

.col {
  flex-shrink: 0; /* 컬럼 축소 방지 */
  width: 150px; /* 혹은 적절한 너비 */
  max-height: 500px; /* 세로 최대 높이 */
  overflow-y: auto; /* 세로 스크롤바 설정 */
  /* 기타 스타일 */
}



  </style>
  