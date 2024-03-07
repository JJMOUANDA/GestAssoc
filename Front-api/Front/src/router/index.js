import { createRouter, createWebHistory} from "vue-router";
import Evenement from "@/pages/Evenement.vue";
import Commentaire from "@/pages/Commentaire.vue";
import ModificationCommentaire from "@/pages/ModificationCommentaire.vue";
import Membres from "@/pages/Membre.vue";
import AjouterLieu from "@/components/AjouterLieu.vue";
import AjouterEvenement from "@/components/EvenementAdd.vue";
import Inscription from "@/components/Inscription.vue";

const routes = [
    {
        path: "/ajouterEvenement",
        name: "AjouterEvenement",
        component: AjouterEvenement
    },
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
    },
    {
        path: "/inscription",
        name: "Inscription",
        component: Inscription
    }
];

const router = createRouter({
    history: createWebHistory(),
    routes
});

export default router;
