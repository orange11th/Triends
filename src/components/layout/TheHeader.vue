<script setup>
import { RouterLink, useRouter } from "vue-router";
import { storeToRefs } from "pinia";
import { useMemberStore } from "@/stores/member";

const memberStore = useMemberStore();

const { userLogout } = memberStore;
const { isLogin, isValidToken } = storeToRefs(memberStore);

const router = useRouter();

function logout() {
  userLogout();
  console.log("로그아웃", isLogin.value);
  router.push({ name: "member-login" });
}
</script>

<template>
  <div class="header">
    <nav class="navbar navbar-expand-lg">
      <div class="container-fluid">
        <RouterLink :to="{ name: 'home' }"><a class="navbar-brand" href="#">Triends</a></RouterLink>
        <div class="collapse navbar-collapse" id="navbarSupportedContent">
          <ul class="navbar-nav me-auto mb-2 mb-lg-0"></ul>
          <RouterLink :to="{ name: 'member-login' }"
            ><button v-show="!isLogin" class="login-button">Login</button></RouterLink
          ><br />
          <RouterLink :to="{ name: 'member-regist' }"
            ><button v-show="!isLogin" class="signup-button">Sign up</button></RouterLink
          ><br />
          <li v-show="isLogin" class="nav-item dropdown">
            <a
              class="nav-link dropdown-toggle"
              href="#"
              id="navbarDropdown"
              role="button"
              data-bs-toggle="dropdown"
              aria-expanded="false"
            >
              사람 아이콘
            </a>
            <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
              <li><a class="dropdown-item" href="#">Action</a></li>
              <li><a class="dropdown-item" href="#">Another action</a></li>
              <li>
                <hr class="dropdown-divider" />
              </li>
              <li><a class="dropdown-item" href="#">Something else here</a></li>
            </ul>
          </li>
          <button v-show="isLogin" class="logout-button" @click.prevent="logout">Log Out</button>
        </div>
      </div>
    </nav>
  </div>
</template>

<style scoped>
.header {
  background-color: #b0d9b1;
}

a {
  text-decoration: none;
}

button {
  margin: 0 10px;
  padding: 4px 10px;
  border-radius: 10%;
  font-family: Impact, Haettenschweiler, "Arial Narrow Bold", sans-serif;
}

.login-button {
  background-color: #b0d9b1;
}

.signup-button,
.logout-button {
  color: #b0d9b1;
  background-color: black;
  margin-right: 10vw;
}
</style>
