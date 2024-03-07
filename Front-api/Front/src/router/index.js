import { createRouter, createWebHistory} from "vue-router";
import Evenement from "@/pages/Evenement.vue";
import Commentaire from "@/pages/Commentaire.vue";
import ModificationCommentaire from "@/pages/ModificationCommentaire.vue";
import Membres from "@/pages/Membre.vue";
import AjouterLieu from "@/components/AjouterLieu.vue";

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
    },

    {
        path: "/ajouterLieu",
        name: "AjouterLieu",
        component: AjouterLieu
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