import { createRouter, createWebHistory} from "vue-router";
import Evenement from "@/pages/Evenement.vue";
import Commentaire from "@/pages/Commentaire.vue";
import ModificationCommentaire from "@/pages/ModificationCommentaire.vue";

const routes = [
    {
        path: "/",
        name: "Evenement",
        component: Evenement
    },
    {
        path: "/commentaire",
        name: "Commentaires",
        component: Commentaire
    },
    {
        path: "/commentaire/:id",
        name: "Commentaire",
        component: ModificationCommentaire
    }
];

const router = createRouter({
    history: createWebHistory(),
    routes
});

export default router;