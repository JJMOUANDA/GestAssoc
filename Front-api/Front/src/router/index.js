import { createRouter, createWebHistory} from "vue-router";
import Evenement from "@/pages/Evenement.vue";
import Commentaire from "@/pages/Commentaire.vue";
import CommentaireEdit from "@/pages/CommentaireEdit.vue";

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
        component: CommentaireEdit
    }
];

const router = createRouter({
    history: createWebHistory(),
    routes
});

export default router;