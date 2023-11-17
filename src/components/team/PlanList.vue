<template>
  <div class="playground">
    <div v-for="(item, idx) in state.lists" :key="item.id" class="col"
         @drop.prevent="onDrop($event, idx)"
         @dragenter.prevent
         @dragover.prevent
    >
      <div v-for="(numItem, numIdx) in item.numberList" :key="numIdx"
           class="box"
           @dragstart="startDrag($event, numItem)"
           draggable="true"
      >
        <p>{{ numItem.content }}</p>
      </div>
      <!-- 드래그 오버 이벤트를 추가한 추가 버튼 -->
      <button @click="addItem(idx)" @dragover.prevent>+</button>
    </div>
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
  