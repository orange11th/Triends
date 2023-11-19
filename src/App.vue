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
    <nav>
      <RouterLink :to="{ name: 'member-info' }">MemberInfo</RouterLink><br />
      <RouterLink :to="{ name: 'team-list' }">Team</RouterLink><br />
    </nav>
  </div>
  <RouterView></RouterView>
  <footer>
    <TheFooter></TheFooter>
  </footer>
</template>

<style scoped></style>
