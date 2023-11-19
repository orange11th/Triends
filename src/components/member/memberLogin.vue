<script setup>
import { reactive, ref } from "vue";
import { useRouter } from "vue-router";
import { storeToRefs } from "pinia";
import { useMemberStore } from "@/stores/member";

const router = useRouter();
const memberStore = useMemberStore();

const { isLogin } = storeToRefs(memberStore);
const { userLogin, getUserInfo } = memberStore;

const loginInfo = ref({
  userId: "",
  userPass: "",
});

const login = async () => {
  await userLogin(loginInfo.value);
  if (isLogin.value) {
    getUserInfo(sessionStorage.getItem("accessToken"));
    router.push("/");
  } else {
    alert("로그인 실패")
  }
};

function moveRegist() {
  router.push({ name: "member-regist" });
}
</script>

<template>
  <div class="container text-center">
    <h1>로그인</h1>
    <div>
      <span>아이디가 없나요? 지금 바로 </span>
      <a href="" @click.prevent="moveRegist">회원가입</a>
      <div>
        <input class="login-input" type="text" placeholder="ID" v-model="loginInfo.userId" />
      </div>
      <div>
        <input class="login-input" type="password" placeholder="Password" v-model="loginInfo.userPass" />
      </div>
      <div>
        <button class="login-button" @click="login">Login</button>
      </div>
    </div>
  </div>
</template>

<style scoped>
h1 {
  margin: 50px;
}

.login-input {
  width: 350px;
  height: 45px;
  background-color: rgb(231, 231, 231);
  border: 0px;
  padding-left: 15px;
  border-radius: 15px;
  margin: 15px;
}

.login-button {
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
