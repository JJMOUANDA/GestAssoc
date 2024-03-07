<template>
  <div class="card border-primary mb-3" style="max-width: 20rem;">
    <div class="card-header">Par {{ getAuteurName() }} pour l'événement {{ getEventName() }}</div>
    <div class="card-body">
      <p class="card-text">{{ texte }}</p>
    </div>
    <p class="text-body-tertiary">{{ formatDate(date) }}</p>
    <router-link :to="'/commentaire/' + id" class="btn btn-info">Éditer</router-link>
  </div>
</template>

<script setup>
import { formatDate } from '@/util/dateUtil.js';
import MembreService from '../services/MembreService.js';
import EvenementService from '../services/EvenementService.js';
import { defineProps, onMounted, ref } from 'vue';

const props = defineProps(['id', 'auteur', 'evenement', 'texte', 'date']);
let auteur = ref({});
let evenement = ref({});

const getAuteurName = () => {
  return auteur.value.prenom + " " + auteur.value.nom;
};

const getEventName = () => {
  return evenement.value.nom;
};

onMounted(async () => {
  try {
    const auteurResponse = await MembreService.getMembreById(props.auteur);
    auteur.value = auteurResponse.data;

    const eventResponse = await EvenementService.getEventById(props.evenement);
    evenement.value = eventResponse.data;
  } catch (error) {
    console.error(error);
  }
});
</script>

<style scoped>
</style>
