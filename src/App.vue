<script setup>
import { RouterLink, RouterView } from "vue-router";
import TheHeader from "@/components/layout/TheHeader.vue";
import TheFooter from "@/components/layout/TheFooter.vue";
</script>

<template>
  <header>
    <TheHeader></TheHeader>
  </header>
  <div class="wrapper">
    <RouterView></RouterView>
  </div>
  <footer>
    <TheFooter></TheFooter>
  </footer>
</template>

<style scoped></style>
