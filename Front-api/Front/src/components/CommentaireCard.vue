<template>
  <div class="card border-primary mb-3" style="max-width: 20rem;">
    <div class="card-header">Par {{ auteur }} pour l'événement {{ evenement }}</div>
    <div class="card-body">
      <p class="card-text">{{ texte }}</p>
    </div>
    <p class="text-body-tertiary">{{ formatDate(date) }}</p>
    <router-link :to="'/commentaire/' + id" class="btn btn-info">Éditer</router-link>
  </div>
</template>

<script setup>
import {formatDate} from '@/util/dateUtil.js';

const props = defineProps(['id', 'auteur', 'evenement', 'texte', 'date']);

</script>

<style scoped>
/* Styles spécifiques au composant */
</style>
