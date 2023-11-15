<script setup>
import { ref } from "vue";
import { NaverMarker, NaverInfoWindow } from "vue3-naver-maps";

const marker = ref();
const infoWindow = ref();
const isOpen = ref(true);

const onLoadMarker = (markerObject) => {
  marker.value = markerObject;
};

const onLoadInfoWindow = (infoWindowObject) => {
  infoWindow.value = infoWindowObject;
};
</script>

<template>
  <naver-marker
    :latitude="36.3587"
    :longitude="127.3438"
    @onLoad="onLoadMarker($event)"
    @mouseover="isOpen = !isOpen"
    @mouseout="isOpen = !isOpen"
  >
  </naver-marker>
  <naver-info-window
    :marker="marker"
    :open="!isOpen"
    @onLoad="onLoadInfoWindow($event)"
  >
    <div class="infoWindow-content">
      <h4>용용선생 대전봉명점</h4>
      <p>042-826-8716</p>
      <p>대전광역시 유성구 봉명동 612-3</p>
      <p>중화요리 안주가 맛있는 홍콩주점</p>
    </div>
  </naver-info-window>
</template>

<style scoped></style>
