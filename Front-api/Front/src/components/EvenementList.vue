<script setup>
import {ref, onMounted} from 'vue';
import EvenementService from '../services/EvenementService.js';
import CommentaireService from "@/services/CommentaireService.js";
import CommentaireCard from "@/components/CommentaireCard.vue";
import {formatDate} from '@/util/dateUtil.js';

const events = ref([]);
const comments = ref([]);
const isModalOpen = ref(false);
let currentEvent = ref({
  nom: '',
  lieu: {
    id: 0,
    nom: '',
    adresse: '',
    capaciteAccueil: 0
  },
  maxParticipants: 0,
  dateHeureDebut: '',
  dateHeureFin: ''
});

onMounted(async () => {
  await fetchEvents();
  await fetchComments();
});

async function fetchComments() {
  try {
    for (let event of events.value) {
      const response = await CommentaireService.getCommentaireByEvenementId(event.id);
      comments.value[event.id] = response.data;
    }
  } catch (error) {
    console.error(error);
  }
}

async function fetchEvents() {
  try {
    const response = await EvenementService.getAllEvent();
    events.value = response.data;
  } catch (error) {
    console.error(error);
  }
}

function showUpdateForm(event) {
  currentEvent = {...event};
  isModalOpen.value = true;
}

async function deleteEvent(id) {
  try {
    await EvenementService.deleteEvent(id);
    await fetchEvents(); // Recharger la liste après suppression
  } catch (error) {
    console.error(error);
  }
}

async function updateEvent(id, eventDetails) {
  try {
    await EvenementService.updateEvent(id, eventDetails);
    isModalOpen.value = false;
    await fetchEvents(); // Recharger la liste après la mise à jour
  } catch (error) {
    console.error(error);
  }
}


import {useRoute} from 'vue-router';
import MessageSucces from "@/components/MessageSucces.vue";
import Evenement from "@/pages/Evenement.vue";

let message = "";
const route = useRoute();

if (route.query.message) {
  message = route.query.message;
}
console.log(message);

</script>

<template>
  <div>
    <h2>Liste des Evenements</h2>
    <div v-for="event in events" :key="event.id" class="event">
      <h3>{{ event.nom }}</h3>
      <p>Lieu: {{ event.lieu.nom }}</p>
      <p>Adresse: {{ event.lieu.adresse }} </p>
      <p>Nombre de participants: {{ event.maxParticipants }}</p>
      <p>Début: {{ formatDate(event.dateHeureDebut) }}</p>
      <p>Fin: {{ formatDate(event.dateHeureFin) }}</p>
      <button @click="deleteEvent(event.id)">Supprimer</button>
      <button @click="showUpdateForm(event)">Modifier</button>
      <div v-for="comment in comments[event.id]" :key="comment.id">
        <CommentaireCard :id = "comment.id" :auteur = "comment.auteurId" :evenement = "comment.evenementId"
                         :texte = "comment.texte" :date = "comment.date"/>
      </div>
    </div>
    <p></p>
    <nav>
      <router-link to="/ajouterEvenement">Ajouter un nouvel événement</router-link>
    </nav>

    <div v-if="isModalOpen" class="modal">
      <!-- Modal content here -->
      <form @submit.prevent="updateEvent(currentEvent.id, currentEvent)">
        <input type="text" v-model="currentEvent.nom" placeholder="Nom de l'événement"/>
        <input type="text" v-model="currentEvent.lieu.nom" placeholder="Lieu de l'événement"/>
        <input type="number" v-model="currentEvent.maxParticipants" placeholder="Nombre de participants"/>
        <input type="datetime-local" v-model="currentEvent.dateHeureDebut" placeholder="Date et heure de debut"/>
        <input type="datetime-local" v-model="currentEvent.dateHeureFin" placeholder="Date et heure de fin"/>
        <button type="submit">Mettre à jour</button>
        <button @click="isModalOpen = false">Annuler</button>
      </form>
    </div>
  </div>
</template>
<style scoped>
.modal {
  display: block; /* ou flex, selon votre mise en page */
  visibility: visible;
  opacity: 1;
}
</style>
