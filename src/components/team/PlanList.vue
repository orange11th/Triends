<template>
  <div class="playground">
    <div v-for="(item, idx) in state.lists" :key="item.id" class="col"
         @drop.prevent="onDrop($event, idx)"
         @dragover.prevent>
      <div v-for="(numItem, numIdx) in item.numberList" :key="numIdx" class="box"
           draggable="true"
           @dragstart="startDrag($event, numItem, idx)">
        <p>{{ numItem.content }}</p>
        <p>{{ numItem.date }}</p>
      </div>
      <button @click="addItem(idx)">+</button>
    </div>
  </div>
  <!-- 새로운 아이템 입력 필드 -->
  <div class="new-item-input">
    <input type="text" v-model="newItem.content" placeholder="내용 입력" />
    <input type="date" v-model="newItem.date" />
    <button @click="register">아이템 추가</button>
  </div>
</template>


  
<script setup>
  import { reactive } from 'vue';

  
  const state = reactive({
    lists: [
      {
        id: 1,
        numberList: [ { content: 1 }, { content: 2 } ]
      },
      {
        id: 2,
        numberList: [ {content: 3}, {content: 4}, {content: 5}, {content: 6} ]
      },
      {
        id: 3,
        numberList: [ {content: 7}, {content: 8}, {content: 9} ]
      }
    ]
  });

// 새 항목 추가 함수
const addItem = (colNum) => {
  const newItem = {
    content: state.lists[colNum].numberList.length + 1
  };
  state.lists[colNum].numberList.push(newItem);
};

  const startDrag = (event, item) => {
    event.dataTransfer.dropEffect = "move";
    event.dataTransfer.effectAllowed = "move";
    event.dataTransfer.setData("selectedItem", item.content);
  };
  
  const onDrop = (event, colNum) => {
    const selectedItem = Number(event.dataTransfer.getData("selectedItem"));
    let targetIdx;
    let targetItem;
  
    state.lists.forEach((obj, index) => {
      obj.numberList.forEach((ob) => {
        if(ob.content === selectedItem) {
          targetIdx = index;
          targetItem = ob;
        }
      });
    });
  
    state.lists[colNum].numberList.push(targetItem);
    state.lists[targetIdx].numberList.splice(state.lists[targetIdx].numberList.indexOf(targetItem), 1);
};
// 새 컬럼 추가 함수
const register = () => {
    if (newItem.content && newItem.date) {
      const newColumn = {
        id: state.lists.length + 1, // 다음 ID를 생성
        numberList: [{ content: newItem.content, date: newItem.date }]
      };
      state.lists.push(newColumn);

      // 입력 필드 초기화
      newItem.content = '';
      newItem.date = '';
    }
  };

const newItem = reactive({
    content: '',
    date: ''
  });

  const addNewItem = () => {
    if (newItem.content && newItem.date) {
      state.lists.forEach(list => {
        list.numberList.push({ ...newItem });
      });
      newItem.content = '';
      newItem.date = '';
    }
  };

  const deleteItem = (colNum, itemIndex) => {
    state.lists[colNum].numberList.splice(itemIndex, 1);

    const addItem = (colNum) => {
    state.lists[colNum].numberList.push({ ...newItem });
    newItem.content = '';
    newItem.date = '';
  };

  const addNewItem = () => {
    state.lists.push({
      id: Date.now(),
      numberList: [{ ...newItem }]
    });
    newItem.content = '';
    newItem.date = '';
  };
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
  </style>
  