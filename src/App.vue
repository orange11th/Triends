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
        <RouterLink :to="{ name: 'home' }">Home</RouterLink><br />
        <RouterLink :to="{ name: 'about' }">About</RouterLink><br />
        <RouterLink :to="{ name: 'attraction-list' }">Attraction</RouterLink><br />
        <RouterLink :to="{ name: 'board' }">Board</RouterLink><br />
        <RouterLink :to="{ name: 'member-login' }">Member</RouterLink><br />
        <RouterLink :to="{ name: 'member-regist' }">Regist</RouterLink><br />
        <RouterLink :to="{ name: 'team-list' }">Team</RouterLink><br />
      </nav>
    </div>
  </header>

  <RouterView />
  <TheFooter></TheFooter>
</template>

<style scoped></style>
