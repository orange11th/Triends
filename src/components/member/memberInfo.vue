<script setup>
import { reactive, ref, onMounted } from "vue";
import { useRoute, useRouter } from "vue-router";
import { storeToRefs } from "pinia";
import { useMemberStore } from "@/stores/member";

const route = useRoute();
const router = useRouter();
const memberStore = useMemberStore();

const clickedItem = ref(null); // 클릭된 아이템을 추적하기 위한 ref 생성

const startEditing = (no) => {
  isEditing.value[no] = true;
  changed.value=true;
};

const stopEditing = (no) => {
  isEditing.value[no] = false;
  changed.value=true;
};

const { userLogout, checkToken } = memberStore;
const { isLogin, isValidToken, userInfo } = storeToRefs(memberStore);

const isEditing = ref([false,false,false,false,false])

const changed = ref(false);

const changeUserInfo = ref({
  userId: "",
  userPass: "",
  userName: "",
  email: "",
  phone: ""
});

onMounted(() => {
  checkToken(sessionStorage.getItem("accessToken"));
  if (!isValidToken.value) {
    alert("토큰이 만료되었습니다.");
    router.replace({ name: "member-login" });
  }else{
    changeUserInfo.value = {...userInfo.value};
  }
});
</script>

<template>
  <div class="container">
    <div class="row">
      <!-- 사진 div (왼쪽) -->
      <div class="col-md-6 image-div">
        <img class="profile-image img-fluid" src="@/assets/img/slide/5.jpg" alt="" />
      </div>
      <!-- 개인정보 div (오른쪽) -->
      <div class="col-md-6">
        <div class="col">
          <!-- 계획 제목 -->
          <div class="plan-title">
            <div style="padding:8px;">
              {{ changeUserInfo.userId }}
            </div>
            <!-- <input v-else v-model="changeUserInfo.userId" @blur="stopEditing(0)" placeholder="변경할 아이디" /> -->
          </div>
          <div class="plan-title">  
            <div class="editable-text" v-if="!isEditing[1]" @click="startEditing(1)">
              {{ changeUserInfo.userName }}
            </div>
            <input v-else v-model="changeUserInfo.userName" @blur="stopEditing(1)" placeholder="변경할 이름" />
          </div>
          <div class="plan-title">  
            <div class="editable-text" v-if="!isEditing[2]" @click="startEditing(2)">
              <span v-if="!changeUserInfo.userPass">비밀번호 변경</span>
              <span v-else>변경할 비밀번호: {{changeUserInfo.userPass}}</span> 
            </div>
            <input v-else v-model="changeUserInfo.userPass" @blur="stopEditing(2)" placeholder="변경할 비밀번호" />
          </div>
          <div class="plan-title">  
            <div class="editable-text" v-if="!isEditing[3]" @click="startEditing(3)">
              {{ changeUserInfo.email }}
            </div>
            <input v-else v-model="changeUserInfo.email" @blur="stopEditing(3)" placeholder="변경할 이메일" />
          </div>
          <div class="plan-title">  
            <div class="editable-text" v-if="!isEditing[4]" @click="startEditing(4)">
              {{ changeUserInfo.phone }}
            </div>
            <input v-else v-model="changeUserInfo.phone" @blur="stopEditing(4)" placeholder="변경할 전화번호" />
          </div>
          <button v-if="changed">수정하기</button>
        </div>
      </div>
    </div>
  </div>
</template>

<style scoped>
.profile-image {
  border-radius: 100%;
}

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
  margin: 20px 0;
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
