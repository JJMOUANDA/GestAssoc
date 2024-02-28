<script setup>
import {ref, onMounted, nextTick} from 'vue';
import LieuService from '../services/LieuService.js';
import L from 'leaflet';

import 'leaflet/dist/leaflet.css';

const lieux = ref([]);

onMounted(async () => {
  try {
    const response = await LieuService.getAllLieux(); // Récupération des lieux depuis le service
    lieux.value = response.data; // Mise à jour de la liste des lieux avec les données récupérées
    await nextTick(); // Attente de la mise à jour du DOM pour initialiser les cartes
    for (const lieu of lieux.value) {
      const coords = await getGeocoding(lieu.adresse);
      if (coords) {
        initMap(lieu, coords);
      }
    }
  } catch (error) {
    console.error(error);
  }
});

/*
* recupere la long et lat a partir de l'adresse */
async function getGeocoding(adresse) {
  try {
    const response = await fetch(`https://nominatim.openstreetmap.org/search?format=json&q=${adresse}`);
    const data = await response.json();
    if (data && data.length > 0) {
      const { lat, lon } = data[0];
      return [lat, lon];
    }
    return null;
  } catch (error) {
    console.error("Erreur de géocodage : ", error);
    return null;
  }
}

// Initialisation de la carte
function initMap(lieu, coords) {
  const map = L.map(`map-${lieu.id}`).setView(coords, 13); // Initialisation de la carte avec les coordonnées récupérées
  L.tileLayer('https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png', { // Ajout de la couche de tuiles OpenStreetMap à la carte
    attribution: '© OpenStreetMap contributors'
  }).addTo(map);

  L.marker(coords).addTo(map)
      .bindPopup(`${lieu.nom}`)
      .openPopup(); // Ajout d'un marqueur à la carte avec le nom du lieu
}
</script>

<template>
  <div>
    <h2>Liste des Lieux</h2>
    <ul>
      <li v-for="lieu in lieux" :key="lieu.id">
        {{ lieu.nom }} - {{ lieu.adresse }} - {{ lieu.capacite_accueil}}
        <div :id="`map-${lieu.id}`" class="map-container"></div> <!-- Ajout d'un conteneur pour chaque carte qui correspond à un lieu -->

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
