<script setup>
import { reactive, ref, onMounted } from "vue";
import { useRoute, useRouter } from "vue-router";
import { storeToRefs } from "pinia";
import { useMemberStore } from "@/stores/member";

const route = useRoute();
const router = useRouter();
const memberStore = useMemberStore();

const text = ref("클릭하여 제목을 편집해주세요");
const isEditing = ref(false);
const clickedItem = ref(null); // 클릭된 아이템을 추적하기 위한 ref 생성

const startEditing = () => {
  isEditing.value = true;
};

const stopEditing = () => {
  isEditing.value = false;
};

const { userLogout, checkToken } = memberStore;
const { isLogin, isValidToken, userInfo } = storeToRefs(memberStore);

onMounted(() => {
  checkToken(sessionStorage.getItem("accessToken"));
  if (!isValidToken.value) {
    alert("토큰이 만료되었습니다.");
    router.replace({ name: "member-login" });
  }
});
</script>

<template>
  <div class="container">
    <div class="row">
      <!-- 사진 div (왼쪽) -->
      <div class="col-md-6 image-div">
        <img src="@/assets/img/slide/5.jpg" class="img-fluid" alt="" />
      </div>
      <!-- 개인정보 div (오른쪽) -->
      <div class="col-md-6">
        <div class="col">
          <!-- 계획 제목 -->
          <div class="plan-title">
            <div class="editable-text" v-if="!isEditing" @click="startEditing">
              <div v-if="text === ''">클릭하여 제목을 편집해주세요</div>
              <!-- 클릭하여 제목을 편집해주세요 -->
              {{ userInfo.userId }}
              {{ userInfo.userName }}
            </div>
            <input v-else v-model="text" @blur="stopEditing" placeholder="제목 입력" />
          </div>
          <!-- 계획 제목 -->
          <div class="plan-title">
            <div class="editable-text" v-if="!isEditing" @click="startEditing">
              <div v-if="text === ''">클릭하여 제목을 편집해주세요</div>
              클릭하여 제목을 편집해주세요
              <!-- {{ userInfo.userId }} -->
            </div>
            <input v-else v-model="text" @blur="stopEditing" placeholder="제목 입력" />
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<style scoped>
.plan-title {
  font-size: 30px;
  width: 480px; /* 고정된 너비 */
  height: 70%; /* 고정된 높이 */
  border-radius: 8px;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
  transition: box-shadow 0.3s ease;
  display: flex;
  align-items: center;
  justify-content: center;
  background: white;
}

.editable-text {
  cursor: pointer;
  padding: 8px;
  border-radius: 6px;
  transition: transform 0.2s ease, background-color 0.3s ease;
}

.editable-text:hover {
  transform: scale(1.05);
  background-color: #e8e8e8;
}

.input-field {
  width: 100%;
  border: 2px solid #aaa;
  border-radius: 6px;
  font-size: 16px;
  box-shadow: inset 0 2px 4px rgba(0, 0, 0, 0.1);
}
</style>
