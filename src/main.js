// import './assets/main.css'

import { createApp } from "vue";
import { createPinia } from "pinia";
import { createNaverMap } from "vue3-naver-maps";

import App from "./App.vue";
import router from "./router";

import "bootstrap";
import "bootstrap/dist/css/bootstrap.min.css";

const app = createApp(App);

app.use(createPinia());
app.use(router);

app.use(createNaverMap, {
  clientId: import.meta.env.VITE_NAVER_MAP_CLIENT_ID,
});

app.mount("#app");
