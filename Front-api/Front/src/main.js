import { createApp } from 'vue'

import './assets/style.css';
import 'bootstrap/dist/css/bootstrap.css'
import "../node_modules/bootswatch/dist/Flatly/bootstrap.min.css";
import App from './App.vue'
import router from "@/router/index.js";

createApp(App).use(router).mount('#app')