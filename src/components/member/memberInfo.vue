<script setup>
import { reactive, ref, onMounted } from "vue";
import { useRoute, useRouter } from "vue-router";
import { storeToRefs } from "pinia";
import { useMemberStore } from "@/stores/member";

const route = useRoute();
const router = useRouter();
const memberStore = useMemberStore();

const { userLogout, getUserInfo } = memberStore;
const { isLogin, isValidToken, userInfo } = storeToRefs(memberStore);

function logout() {
  userLogout();
  router.push({ name: "member-login" });
}

onMounted(() => {
  getUserInfo(sessionStorage.getItem("accessToken"));
  if (!isValidToken.value) {
    alert("토큰이 만료되었습니다.");
    router.replace({ name: "member-login" });
  }
});
</script>
<template>
  <div>
    {{ userInfo }}
    <button @click="logout">로그아웃</button>
  </div>
</template>

<style scoped></style>
