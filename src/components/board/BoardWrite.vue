<script setup>
import { ref, onMounted } from "vue";
import { storeToRefs } from "pinia";
import { useRouter } from "vue-router";
import { useMemberStore } from "@/stores/member";

import BoardFormItem from "./item/BoardFormItem.vue";

const router = useRouter();
const memberStore = useMemberStore();
const { checkToken } = memberStore;
const { isValidToken, userInfo } = storeToRefs(memberStore);

const userId = ref();

onMounted(() => {
  userId.value = userInfo.value.userId;
});
</script>

<template>
  <div class="container">
    <div class="row justify-content-center">
      <div class="col-lg-10">
        <h2 class="my-3 py-3 shadow-sm bg-light text-center">
          <mark class="sky">글!쓰!기</mark>
        </h2>
      </div>
      <div class="col-lg-10 text-start">
        <BoardFormItem type="regist" :userId="userId" />
      </div>
    </div>
  </div>
</template>

<style scoped></style>
