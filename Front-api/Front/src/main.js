import { createApp } from 'vue';
import './assets/style.css';
import App from './App.vue'

// Importation de Vue Router
import { createRouter, createWebHistory } from 'vue-router';

// Importation des composants pour les routes
import LieuList from './components/LieuList.vue';
import EvenementsLieu from './components/Evenements.vue'; // Assurez-vous que le nom du fichier est correct

// Définition des routes
const routes = [
    { path: '/lieux', component: LieuList },
    { path: '/evenements', component: EvenementsLieu },
    { path: '/', component: App },
];

// Création du routeur avec l'historique du navigateur
const router = createRouter({
    history: createWebHistory(),
    routes,
});

// Création de l'application Vue
const app = createApp(App);

// Utilisation du routeur avec l'application Vue
app.use(router);

// Montage de l'application Vue
app.mount('#app');
