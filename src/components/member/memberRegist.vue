<script setup>
import { reactive, ref, watch } from "vue";
import { useRouter } from "vue-router";
import { memberRegist } from "@/api/member";

const router = useRouter();

const param = reactive({
  userId: "",
  userPass: "",
  userName: "",
  email: "",
  phone: "",
});

const userPassCheck = ref("");
const diffPass = ref(false);

watch([param, userPassCheck], ([nV1, nV2], [oV1, oV2]) => {
  diffPass.value = nV1.userPass !== nV2;
});

function regist() {
  memberRegist(
    param,
    (response) => {
      alert("회원가입 성공");
      console.log("성공");
      moveLogin();
    },
    (error) => {
      alert("회원가입 실패!");
      console.error();
    }
  );
}

function moveLogin() {
  router.push({ name: "member-login" });
}
</script>

<template>
  <div class="container text-center">
    <div>
      <h1>회원가입</h1>
    </div>
    <div>
      <div>
        <input class="regist-input" type="text" placeholder="아이디" v-model="param.userId" />
      </div>
      <div>
        <input
          class="regist-input"
          type="password"
          placeholder="비밀번호"
          v-model="param.userPass"
        />
      </div>
      <div>
        <input
          class="regist-input"
          type="password"
          placeholder="비밀번호확인"
          v-model="userPassCheck"
          style="margin: 15px 15px 0px 15px"
        />
      </div>
      <p class="diff-pass" :style="[diffPass ? '' : 'visibility:hidden']">비밀번호가 다릅니다</p>
      <div>
        <input
          class="regist-input"
          type="text"
          placeholder="닉네임"
          v-model="param.userName"
          style="margin: 13.5px 15px 15px 15px"
        />
      </div>
      <div>
        <input class="regist-input" type="text" placeholder="이메일" v-model="param.email" />
      </div>
      <div>
        <input class="regist-input" type="text" placeholder="전화번호" v-model="param.phone" />
      </div>
      <div>
        <button class="regist-button" @click="regist">Register</button>
      </div>
    </div>
  </div>
</template>
<style scoped>
h1 {
  margin: 50px;
}
.diff-pass {
  font-size: 11px;
  color: red;
  margin: 0px;
}
.regist-input {
  width: 350px;
  height: 45px;
  background-color: rgb(231, 231, 231);
  border: 0px;
  padding-left: 15px;
  border-radius: 15px;
  margin: 15px;
}

.regist-button {
  color: white;
  width: 350px;
  height: 45px;
  background-color: rgb(50, 62, 62);
  border: 0px;
  padding-left: 15px;
  border-radius: 15px;
  margin: 10px;
}
</style>
