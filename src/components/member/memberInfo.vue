<script setup>
import { ref, onMounted } from "vue";
import { useRoute, useRouter } from "vue-router";
import { storeToRefs } from "pinia";
import { useMemberStore } from "@/stores/member";
import { userModify, memberDelete } from "@/api/member";
import memberInfoLeft from "./item/MemberInfoLeft.vue";

const route = useRoute();
const router = useRouter();
const memberStore = useMemberStore();

const { getUserInfo } = memberStore;

const startEditing = (no) => {
  isEditing.value[no] = true;
  changed.value = true;
};

const stopEditing = (no) => {
  isEditing.value[no] = false;
  changed.value = true;
};

const { userLogout, checkToken } = memberStore;
const { isLogin, isValidToken, userInfo } = storeToRefs(memberStore);

const isEditing = ref([false, false, false, false, false]);

const changed = ref(false);

const changeUserInfo = ref({
  userId: "",
  userPass: "",
  userName: "",
  email: "",
  phone: "",
});

function modify() {
  userModify(
    changeUserInfo.value,
    () => {
      getUserInfo(sessionStorage.getItem("accessToken"));
      changed.value = false;
    },
    (error) => console.log(error)
  );
  changeUserInfo.value.userPass = null;
}

onMounted(() => {
  changeUserInfo.value = { ...userInfo.value };
});


import Swal from 'sweetalert2'
const confirmDelete = (userId) => {
  Swal.fire({
    title: '정말로 탈퇴하시겠습니까?',
    text: "더이상 트렌즈와 함께 할 수 없습니다.",
    icon: 'warning',
    showCancelButton: true,
    confirmButtonColor: '#B3B3B3',
    cancelButtonColor: '#84B891',
    confirmButtonText: '트렌즈와 헤어지기',
    cancelButtonText: '트렌즈와 함께 하기'
  }).then((result) => {
    if (result.isConfirmed) {
      memberDelete(userId, (response) => {
        Swal.fire({
          title: '탈퇴 완료',
          text: '그동안 트렌즈와 함께 해서 행복했어요.',
          icon: 'success',
          confirmButtonColor: '#84B891', // 여기에 원하는 색상 코드를 입력하세요
        });
        userLogout();
        router.push({ name: "home" });
      }, (error) => {
        console.log(error);
      });
    }
  });
};


</script>

<template>
<!-- 드롭다운 컨테이너 추가 -->
<div class="dropdown-container">
  <a href="#" id="navbarDropdown" role="button" data-bs-toggle="dropdown" aria-expanded="false">
    <img class="invite-icon clickable-img" src="@/assets/img/icon/dot.svg" />
  </a>
  
  <li type="button" class="dropdown-menu" aria-labelledby="navbarDropdown" @click="confirmDelete(changeUserInfo.userId)">탈퇴하기</li>

</div>
<!-- 드롭다운 컨테이너 종료 -->


  
  <!--탈퇴-->





  <div class="container">
    <div class="row">
      <!-- 사진 div (왼쪽) -->
      <div class="col-md-6">
        <memberInfoLeft />
      </div>
      <!-- 개인정보 div (오른쪽) -->
      <div class="col-md-2">
            <h1 style="margin: 32px 0 32px 70px">ID</h1>
            <h1 style="margin: 32px 0 32px 70px">Name</h1>
            <h1 style="margin: 32px 0 32px 70px">c.Pass</h1>
            <h1 style="margin: 32px 0 32px 70px">Email</h1>
            <h1 style="margin: 32px 0 32px 70px">Phone</h1>
      </div>
      <div class="col-md-4">
        <div class="col">
          <!-- 계획 제목 -->
          <!-- <h1>My Info</h1> -->
          <div class="plan-title">
            <div style="padding: 8px">
              {{ changeUserInfo.userId }}
            </div>
            <!-- <input v-else v-model="changeUserInfo.userId" @blur="stopEditing(0)" placeholder="변경할 아이디" /> -->
          </div>
          <div class="plan-title">
            <div class="editable-text" v-if="!isEditing[1]" @click="startEditing(1)">
              {{ changeUserInfo.userName }}
            </div>
            <input
              v-else
              v-model="changeUserInfo.userName"
              @blur="stopEditing(1)"
              placeholder="변경할 이름"
            />
          </div>
          <div class="plan-title">
            <div class="editable-text" v-if="!isEditing[2]" @click="startEditing(2)">
              <span v-if="!changeUserInfo.userPass">비밀번호 변경</span>
              <span v-else>새 비밀번호: {{ changeUserInfo.userPass }}</span>
            </div>
            <input
              v-else
              v-model="changeUserInfo.userPass"
              @blur="stopEditing(2)"
              placeholder="새 비밀번호"
            />
          </div>
          <div class="plan-title">
            <div class="editable-text" v-if="!isEditing[3]" @click="startEditing(3)">
              {{ changeUserInfo.email }}
            </div>
            <input
              v-else
              v-model="changeUserInfo.email"
              @blur="stopEditing(3)"
              placeholder="변경할 이메일"
            />
          </div>
          <div class="plan-title">
            <div class="editable-text" v-if="!isEditing[4]" @click="startEditing(4)">
              {{ changeUserInfo.phone }}
            </div>
            <input
              v-else
              v-model="changeUserInfo.phone"
              @blur="stopEditing(4)"
              placeholder="변경할 전화번호"
            />
          </div>
          <button class="modify-button" v-if="changed" @click="modify">수정하기</button>
        </div>
      </div>
    </div>
  </div>
</template>

<style scoped>
.col {
  position: relative;
}
.modify-button {
  display: block;
  position: absolute;
  right: 14px;
  float: left;
  width: 120px;
  padding: 0;
  margin: 10px 20px 10px auto;
  font-weight: 600;
  text-align: center;
  line-height: 50px;
  color: #fff;
  border-radius: 5px;
  transition: all 0.2s;
  background: #5dc8cd;
  border-style: none;
}
.modify-button:hover {
  background: #01939a;
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

/* 메시지 아이콘과 드롭다운 메뉴를 포함하는 컨테이너 스타일 */
.dropdown-container {
  display: flex;
  justify-content: flex-end;
  position: relative;
}

/* 드롭다운 메뉴 스타일 */
.dropdown-menu {
  position: absolute;
  right: 0; /* 오른쪽 끝에 위치 */
  top: 100%; /* 아이콘 바로 아래에 위치 */
  width: 150px;
  padding: 0;
}

.invite-icon {
  width: 30px; 
  height: 30px; 
}

</style>
