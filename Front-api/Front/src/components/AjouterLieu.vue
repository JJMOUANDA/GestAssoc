<template>
  <h2>Ajouter un Nouveau Lieu</h2>
  <form @submit.prevent="ajouterLieu">
    <div>
      <label for="nom">Nom:</label>
      <input id="nom" v-model="lieuForm.nom" type="text" required>
    </div>
    <div>
      <label for="adresse">Adresse:</label>
      <input id="adresse" v-model="lieuForm.adresse" type="text" required>
    </div>
    <div>
      <label for="capacite">Capacité d'accueil:</label>
      <input id="capacite" v-model.number="lieuForm.capacite_accueil" type="number" required>
    </div>
    <button type="submit">Ajouter le Lieu</button>
  </form>
</template>


<script setup>
import {nextTick, onMounted, ref} from 'vue';
import LieuService from '../services/LieuService';

import  { useRoute } from 'vue-router';

const router = useRoute();

const lieuForm = ref({
  nom: '',
  adresse: '',
  capacite_accueil: ''
});

const ajouterLieu = async () => {
  try {
    const response = await LieuService.createLieu(lieuForm.value);
    alert('Lieu.java ajouté avec succès');
    lieuForm.value = { nom: '', adresse: '', capacite_accueil: '' }; // Réinitialiser le formulaire
    router.push('/evenement');
  }
  catch (error) {
    console.error("Erreur lors de l'ajout du lieu :", error);
  }
}


</script>
