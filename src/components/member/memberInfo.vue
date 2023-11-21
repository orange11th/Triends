<script setup>
import { reactive, ref, onMounted } from "vue";
import { useRoute, useRouter } from "vue-router";
import { storeToRefs } from "pinia";
import { useMemberStore } from "@/stores/member";

const route = useRoute();
const router = useRouter();
const memberStore = useMemberStore();

const { userLogout, checkToken } = memberStore;
const { isLogin, isValidToken, userInfo } = storeToRefs(memberStore);

onMounted(() => {
  checkToken(sessionStorage.getItem("accessToken"));
  if (!isValidToken.value) {
    alert("토큰이 만료되었습니다.");
    router.replace({ name: "member-login" });
  }
});
</script>
<template>
  <div>
    {{ userInfo }}
  </div>
</template>

<style scoped></style>
