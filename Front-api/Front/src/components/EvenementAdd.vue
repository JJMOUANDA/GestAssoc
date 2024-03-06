<script setup>
import { ref } from 'vue';
import EvenementService from '../services/EvenementService';
import router from "@/router/index.js";

const nouvelEvenement = ref({
  nom: '',
  lieu: {
    nom: '',
    adresse: '',
    capaciteAccueil: 0,
  },
  maxParticipants: 0,
  dateHeureDebut: '',
  dateHeureFin: '',
});

async function ajouterEvenement() {
  try {
    await EvenementService.createEvent(nouvelEvenement.value);
    // Rediriger l'utilisateur après l'ajout
    this.$router.push({ path: 'localhost:5173' });
  } catch (error) {
    console.error(error);
  }
}

function retour() {
  router.back();
}
</script>

<template>
  <div>
  <form @submit.prevent="ajouterEvenement">
    <input type="text" v-model="nouvelEvenement.nom" placeholder="Nom de l'événement" required>
    <input type="text" v-model="nouvelEvenement.lieu.nom" placeholder="Nom du lieu" required>
    <input type="text" v-model="nouvelEvenement.lieu.adresse" placeholder="Adresse du lieu" required>
    <input type="number" v-model="nouvelEvenement.maxParticipants" placeholder="Nombre maximum de participants" required>
    <input type="datetime-local" v-model="nouvelEvenement.dateHeureDebut" required>
    <input type="datetime-local" v-model="nouvelEvenement.dateHeureFin" required>
    <button type="submit">Ajouter l'événement</button>
    <button @click="retour">Retour</button>
  </form>
  </div>
</template>

<style scoped>

</style>
