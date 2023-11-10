<script setup></script>

<template>
  <div>
    <div>
      <input class="login-input" type="text" placeholder="아이디" />
    </div>
    <div><input class="login-input" type="password" placeholder="패스워드" /></div>
    <div>
      <button class="login-button">로그인</button>
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
}
</style>
