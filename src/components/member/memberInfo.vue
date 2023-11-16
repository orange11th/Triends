<script setup>
import { reactive, ref } from "vue";
import { useRoute, useRouter } from "vue-router";
import { storeToRefs } from "pinia";
import { useMemberStore } from "@/stores/member";

const route = useRoute();
const router = useRouter();
const memberStore = useMemberStore();

const { getUserInfo } = memberStore;

getUserInfo(sessionStorage.getItem("accessToken"));

</script>
<template>
  <div>
  </div>
</template>

<style scoped></style>
