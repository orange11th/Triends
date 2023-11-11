<script setup>
import { reactive, ref } from "vue";
import { useRouter } from "vue-router";
import { memberLogin } from "@/api/member";

const router = useRouter()

const param = reactive({
  userId: "",
  userPass: "",
})

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
      console.log(error)
    }
  )
}

function moveMemberInfo() {
  router.replace({name: "member-info", params: {memberInfo: JSON.stringify(memberInfo.value)}})
}

function moveRegist(){
  router.push({name: "member-regist"})
}

</script>

<template>
  <h2>로그인</h2>
  <div>
    <div>
      <input class="login-input" type="text" placeholder="아이디" v-model="param.userId" />
    </div>
    <div><input class="login-input" type="password" placeholder="패스워드" v-model="param.userPass" /></div>
    <div>
      <button class="move-regist-button" @click="moveRegist">회원가입</button>
      <button class="login-button" @click="login">로그인</button>
    </div>
  </div>
</template>
<style scoped>
.login-input {
  width: 350px;
  height: 45px;
  background-color: rgb(231, 231, 231);
  border: 0px;
  padding-left: 15px;
  border-radius: 15px;
  margin: 10px;
}

.login-button {
  margin: 10px;
}
</style>
