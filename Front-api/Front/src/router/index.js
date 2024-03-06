import { createRouter, createWebHistory} from "vue-router";
import Evenement from "@/pages/Evenement.vue";
import Commentaire from "@/pages/Commentaire.vue";
import ModificationCommentaire from "@/pages/ModificationCommentaire.vue";
import Membres from "@/pages/Membre.vue";

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
    ,
    {
        path: "/membres",
        name: "Membres",
        component: Membres
    }
];

const router = createRouter({
    history: createWebHistory(),
    routes
});

export default router;