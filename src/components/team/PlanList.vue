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
        <input type="text" v-model="newItem.content" placeholder="관광지 검색" />
        <!-- <input type="date" v-model="newItem.date" /> -->
        <button @click="register">검색</button>
        <div>이런 형태로 관광지들이 쫘르륵 뜨게!</div>
      </div>
      </div>
      

      <div v-for="(numItem, numIdx) in item.numberList" :key="numIdx" class="box" draggable="true" @dragstart="startDrag($event, numItem, idx)">
      <div class="content-with-delete-button">
        <p>{{ numItem.content }}</p>
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
import { reactive, ref, onMounted } from 'vue';
import { planList, placeList, saveNewPlan } from "@/api/team";
import { useRoute } from 'vue-router';

const route = useRoute();
console.log(route.params.teamId); // teamId 정보 가져옴

const plans = ref([]);

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
      newBox.numberList = data.map(place => ({ content: place.placeId }));
      state.lists.push(newBox);
      state.lists.sort((a, b) => new Date(a.date) - new Date(b.date));
    },
    (error) => {
      console.error('Error fetching place list:', error);
    }
    );
};



//todo : 저장 누를시에 리스트 보내서 그대로 저장
//todo : 관광지 검색결과를 드래그앤드롭
//todo : 가져온 정보 바탕으로 화면에 뿌리기
//todo : like 눌렀으면 눌렀따고 표시하기
//todo : 게시물 삭제

  const state = reactive({
    lists: [
      {
        id: 1,
        numberList: [ { content: 125266 }, { content: 125405 }, { content: 125406 }, { content: 125407 } ]
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
const register = () => {
    if (newItem.content) {
      const newBoxItem = {
        content: newItem.content,
      };
      const firstColumn = state.lists.find(column => column.id === 1);
      if (firstColumn) firstColumn.numberList.push(newBoxItem);
      newItem.content = '';
    }
  };
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



const savePlans = () => {
  const newPlans = state.lists
    .filter(item => item.id >= 2 && !plans.value.some(p => p.date === item.date))
    .map(item => {
      const formattedDate = item.date.replace(/-/g, '');
      return {
        date: formattedDate,
        places: item.numberList.map(numItem => numItem.content)
      };
    });


  saveNewPlan(route.params.teamId, newPlans);

  console.log("저장");
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
  
  .box {
    width: 130px;
    height: 50px;
    background-color: #7b71b7;
    margin-top: 10px;
    border-radius: 5px;
  }
  
  .box p {
    text-align: center;
    line-height: 50px;
    color: white;
  }
  .content-with-delete-button {
  display: flex;
  justify-content: space-between;
  align-items: center;
}

.box p {
  margin: 0;
  /* 기타 필요한 스타일 */
}

  </style>
  