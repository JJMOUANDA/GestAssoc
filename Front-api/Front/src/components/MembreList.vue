<script setup>
import {ref, onMounted} from 'vue';
import MembreService from '../services/MembreService.js';
import L from 'leaflet';

import 'leaflet/dist/leaflet.css';

const membres = ref([]);

onMounted(async () => {
  try {
    const response = await MembreService.getAllMembres();
    membres.value = response.data;
    console.log(response.data)
  } catch (error) {
    console.error(error);
  }
});
</script>

<template>
  <div>
    <h2>Liste des Membres</h2>
    <ul>
      <li v-for="membre in membres" :key="membre.id">
        {{membre.nom}} {{membre.prenom}} {{membre.date_naissance}} {{membre.adresse}} {{membre.mail}}
      </li>
    </ul>
  </div>
</template>