import { createApp } from "vue";
import { createPinia } from "pinia";
import "material-icons/iconfont/material-icons.css";

import App from "./App.vue";
import router from "./router";
import "@/css/app.css";

const app = createApp(App);

app.use(createPinia());
app.use(router);

app.mount("#app");

