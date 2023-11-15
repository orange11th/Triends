<script setup>
import { reactive, ref } from "vue";
import { useRouter } from "vue-router";
import { memberLogin } from "@/api/member";

const router = useRouter();

const param = reactive({
  userId: "",
  userPass: "",
});

const memberInfo = ref({});

function login() {
  memberLogin(
    param,
    ({ data }) => {
      if (data.userId) {
        memberInfo.value.userId = data.userId;
        memberInfo.value.userName = data.userName;
        memberInfo.value.email = data.email;
        memberInfo.value.phone = data.phone;
        moveMemberInfo();
      }
    },
    (error) => {
      console.log(error);
    }
  );
}

function moveMemberInfo() {
  router.replace({
    name: "member-info",
    params: { memberInfo: JSON.stringify(memberInfo.value) },
  });
}

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
        <input
          class="login-input"
          type="text"
          placeholder="ID"
          v-model="param.userId"
        />
      </div>
      <div>
        <input
          class="login-input"
          type="password"
          placeholder="Password"
          v-model="param.userPass"
        />
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
