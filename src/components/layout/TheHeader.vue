<script setup>
import { RouterLink, useRouter } from "vue-router";
import { storeToRefs } from "pinia";
import { useMemberStore } from "@/stores/member";

import "@/assets/css/header.css";

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
  <header>
    <nav>
      <div class="logo-space">
        <RouterLink :to="{ name: 'home' }">
          <a href="#">
            <img src="/src/assets/img/icon/main-logo.svg" alt="main-logo" id="main-logo"/>
          </a>
        </RouterLink>
      </div>
      <div>
        <div class="menu-space" v-show="!isLogin">
          <RouterLink :to="{ name: 'member-login' }">
            <button v-show="!isLogin" class="light">Login</button>
          </RouterLink>
          <RouterLink :to="{ name: 'member-regist' }">
            <button v-show="!isLogin" class="dark">Sign up</button>
          </RouterLink>
        </div>
        <div class="menu-space" v-show="isLogin">
          <ul>
            <li
              v-show="isLogin"
              class="nav-item dropdown"
              style="list-style: none"
            >
              <a
                class="nav-link dropdown-toggle"
                href="#"
                id="navbarDropdown"
                role="button"
                data-bs-toggle="dropdown"
                aria-expanded="false"
              >
                <img
                  src="@/assets/img/icon/user-icon.svg"
                  alt=""
                  srcset=""
                  style="position: relative; height: 45px; width: auto"
                />
              </a>
              <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
                <li>
                  <RouterLink :to="{ name: 'member-info' }">
                    <a class="dropdown-item" href="#">마이 페이지</a>
                  </RouterLink>
                </li>
                <li>
                  <hr class="dropdown-divider" />
                </li>
                <li>
                  <RouterLink :to="{ name: 'team-list' }">
                    <a class="dropdown-item" href="#">팀 페이지</a>
                  </RouterLink>
                </li>
                <li>
                  <a href="#" @click.prevent="logout" class="dropdown-item"
                    >로그아웃</a
                  >
                </li>
              </ul>
            </li>
          </ul>
        </div>
      </div>
    </nav>
  </header>
</template>

<style scoped></style>
