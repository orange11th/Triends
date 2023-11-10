<script setup>
import { RouterLink, RouterView } from "vue-router";
import TheHeader from "@/components/layout/TheHeader.vue";
import TheFooter from "@/components/layout/TheFooter.vue";
</script>

<template>
  <header>
    <TheHeader></TheHeader>
    <div class="wrapper">
      <nav>
        <RouterLink :to="{ name: 'home' }">Home</RouterLink>
        <RouterLink :to="{ name: 'about' }">About</RouterLink>
        <RouterLink :to="{ name: 'board' }">Board</RouterLink>
        <RouterLink :to="{ name: 'member' }">Member</RouterLink>
      </nav>
    </div>
  </header>

  <RouterView />
  <TheFooter></TheFooter>
</template>

<style scoped></style>
