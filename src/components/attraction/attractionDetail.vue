<script setup>
import { ref } from "vue";
import { useRoute } from "vue-router";
import { attractionDetail } from "@/api/attraction";

const route = useRoute();

const attraction = ref({});

function getAttractionDetail() {
  attractionDetail(
    route.params.contentId,
    ({ data }) => {
      attraction.value = data;
    },
    console.error()
  );
}

getAttractionDetail();
</script>

<template>
  <div>
    <h2>{{ attraction.title }}</h2>
    <img :src="attraction.firstImage" alt="" />
    <p>{{ attraction.addr1 }}</p>
    <p>{{ attraction.tel }}</p>
    <p>{{ attraction.homepage }}</p>
    <p>{{ attraction.overview }}</p>
  </div>
</template>

<style scoped></style>
