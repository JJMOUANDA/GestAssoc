<template>
  <div class="card border-primary mb-3" style="max-width: 20rem;">
    <div class="card-header">{{id}}</div>
    <div class="card-body">
      <p class="card-text">{{ nom }}</p>
    </div>
    <p class="text-body-tertiary">{{ formatDate(date) }}</p>
    <router-link :to="'/evenement/' + id" class="btn btn-info">Éditer</router-link>
  </div>
</template>

<script setup>
import {formatDate} from '@/util/dateUtil.js';

const props = defineProps(['id', 'nom', 'dateHeureDebut', 'dateHeureFin', 'maxParticipants', 'lieuId']);

</script>

<style scoped>
/* Styles spécifiques au composant */
</style>
