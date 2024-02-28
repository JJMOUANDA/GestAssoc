import { createRouter, createWebHistory} from "vue-router";
import Evenement from "@/pages/Evenement.vue";
import Commentaire from "@/pages/Commentaire.vue";

const routes = [
    {
        path: "/",
        name: "Evenement",
        component: Evenement
    },
    {
        path: "/commentaire",
        name: "Commentaire",
        component: Commentaire
    }
];

const router = createRouter({
    history: createWebHistory(),
    routes
});

export default router;