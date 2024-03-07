<template>
  <div class="card border-primary mb-3" style="max-width: 20rem;">
    <div class="card-header">Par {{ getAuteurName() }} pour l'événement {{ evenement }}</div>
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
import {defineProps, onMounted, ref} from 'vue';

const props = defineProps(['id', 'auteur', 'evenement', 'texte', 'date']);
let auteur = ref({});

const getAuteurName = () => {
  return auteur.value.prenom + " " + auteur.value.nom;
};

onMounted(async () => {
  try {
    const response = await MembreService.getMembreById(props.auteur);
    auteur.value = response.data;
  } catch (error) {
    console.error(error);
  }
});
</script>

<style scoped>
</style>
