<script setup>
import {ref, onMounted, nextTick} from 'vue';
import LieuService from '../services/LieuService.js';
import L from 'leaflet';

import EvenementService from '../services/EvenementService.js';
import 'leaflet/dist/leaflet.css';

const lieux = ref([]);

const lieuForm = ref({
  nom: '',
  adresse: '',
  capacite_accueil: ''
});

const lieuIdEnEdition = ref(null);
const lieuEnEdition = ref({});

const evenements = ref([]);
const evenenementsLieu = ref([]);

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

async function supprimerLieu(idLieu) {
  console.log(`Tentative de suppression du lieu avec l'ID : ${idLieu}`);
  try {

   /* // recupere touts les evenements
    const evenements = await EvenementService.getAllEvent();
    // recupere les evenements qui ont le lieu a supprimer
    const evenementsLieu = evenements.data.filter(evenement => evenement.lieu.id === idLieu);
    // supprime les evenements qui ont le lieu a supprimer
    for (const evenement of evenementsLieu) {
      await EvenementService.deleteEvent(evenement.id);
    }*/
    /*TODO : Supprimer l'évenement lié au lieu à supprimer. */
    const response = await LieuService.deleteLieu(idLieu);

    lieux.value = lieux.value.filter(lieu => lieu.id !== idLieu);
  } catch (error) {
    console.error("Erreur lors de la suppression du lieu :", error);
  }
}




const commencerEdition = (lieu) => {
  lieuIdEnEdition.value = lieu.id;
  lieuEnEdition.value = { ...lieu }; // Crée une copie superficielle pour l'édition
};

const annulerEdition = () => {
  lieuIdEnEdition.value = null;
};

const modifierLieu = async (id) => {
  try {
    await LieuService.updateLieu(id, lieuEnEdition.value);
    // Met à jour l'état local si la modification a réussi
    const index = lieux.value.findIndex(lieu => lieu.id === id);
    if (index !== -1) {
      lieux.value[index] = { ...lieux.value[index], ...lieuEnEdition.value };
    }
    lieuIdEnEdition.value = null; // Termine l'édition
    alert('Lieu.java modifié avec succès');
  } catch (error) {
    console.error("Erreur lors de la modification du lieu :", error);
  }
};

</script>

<template>
  <div>
    <h2>Liste des Lieux</h2>
    <ul>
      <li v-for="lieu in lieux" :key="lieu.id">
        <transition name="expand">

        <div v-if="lieuIdEnEdition === lieu.id">
          <!-- Formulaire de modification pour le lieu actuel -->
          <form @submit.prevent="modifierLieu(lieu.id)">
            <input v-model="lieuEnEdition.nom" type="text" placeholder="Nom">
            <input v-model="lieuEnEdition.adresse" type="text" placeholder="Adresse">
            <input v-model.number="lieuEnEdition.capacite_accueil" type="number" placeholder="Capacité d'accueil">
            <button type="submit">Enregistrer</button>
            <button @click="annulerEdition">Annuler</button>
          </form>
        </div>

        <div v-else>
          <!-- Affichage du lieu -->
          {{ lieu.nom }} - {{ lieu.adresse }} - {{ lieu.capacite_accueil }}

        </div>
        </transition>
        <div :id="`map-${lieu.id}`" class="map-container"></div>
        <button @click="supprimerLieu(lieu.id)">Supprimer</button>
        <button @click="commencerEdition(lieu)">Modifier</button>
      </li>
    </ul>
  </div>



</template>

<style>

/* Style pour le conteneur de la carte */
.map-container {
  height: 300px; /* Hauteur du conteneur de la carte */
  width: 50%; /* Largeur du conteneur (peut être ajustée selon vos besoins) */
  margin-top: 20px; /* Marge au-dessus de la carte, ajustable selon votre mise en page */
}

/* Si vous utilisez un ID spécifique pour le conteneur de la carte dans votre template */
#map {
  height: 400px; /* Hauteur du conteneur de la carte */
  width: 100%; /* Largeur du conteneur (peut être ajustée selon vos besoins) */
  margin-top: 20px; /* Marge au-dessus de la carte, ajustable selon votre mise en page */
}
.expand-enter-active, .expand-leave-active {
  transition: all 0.3s ease;
}
.expand-enter, .expand-leave-to /* .expand-leave-active in <2.1.8 */ {
  transform: scaleY(0);
  opacity: 0;
}


</style>
