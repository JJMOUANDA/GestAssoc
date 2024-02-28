<script setup>
import { ref, onMounted } from 'vue';
import LieuService from '../services/LieuService.js';

const lieux = ref([]);

onMounted(async () => {
  try {
    const response = await LieuService.getAllLieux();
    lieux.value = response.data;
  } catch (error) {
    console.error(error);
  }
});

</script>

<template>
  <div>
    <h2>Liste des Lieux</h2>
    <ul>
      <li v-for="lieu in lieux" :key="lieu.id">
        {{ lieu.nom }} - {{ lieu.adresse }} - {{ lieu.capacite_accueil}}
      </li>
    </ul>
  </div>
</template>

<style>

/* Styles pour EvenementsList ici */
ul {
  list-style-type: none;
  padding: 0;
}

li {
  margin-bottom: 20px;
}

h3 {
  margin: 0 0 10px 0;
}

p {
  margin: 0;
}
</style>
