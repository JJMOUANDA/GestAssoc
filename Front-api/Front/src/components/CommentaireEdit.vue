<template>
  <div class="container-sm m-4">
    <div class="d-flex justify-content-center align-items-center flex-column">
      <div>
        <h4>Par {{ auteur.prenom }} {{ auteur.nom }} pour l'événement {{ evenement.nom }}</h4>
        <label for="exampleTextarea" class="form-label mt-4"></label>
        <textarea class="form-control" id="exampleTextarea" rows="3"
                  v-model="nouveauTexte"></textarea>
      </div>
      <div class="d-flex justify-content-between mt-3">
        <button type="button" class="btn btn-danger me-2" @click="deleteComment">Supprimer</button>
        <button type="button"
                :class="isDisabled ? 'btn btn-secondary disabled' : 'btn btn-success'"
                :disabled="isDisabled"
                @click="editComment">Modifier
        </button>
      </div>
    </div>
  </div>
</template>

<script setup>
import {useRoute} from 'vue-router';
import CommentaireService from "@/services/CommentaireService.js";
import MembreService from "@/services/MembreService.js";
import EvenementService from "@/services/EvenementService.js";
import {computed, onMounted, ref} from "vue";
import router from "@/router/index.js";

const route = useRoute();
const commentaireId = route.params.id;

const commentaire = ref({});
const nouveauTexte = ref(''); // Initialiser avec une chaîne vide
const auteur = ref({});
const evenement = ref({});

onMounted(async () => {
  try {
    const response = await CommentaireService.getCommentaireById(commentaireId);
    commentaire.value = response.data;
    nouveauTexte.value = commentaire.value.texte;

    const auteurResponse = await MembreService.getMembreById(commentaire.value.auteurId);
    auteur.value = auteurResponse.data;

    const evenementResponse = await EvenementService.getEventById(commentaire.value.evenementId);
    evenement.value = evenementResponse.data;
  } catch (error) {
    console.error(error);
  }
});
onMounted(async () => {
  try {
    const response = await CommentaireService.getCommentaireById(commentaireId);
    commentaire.value = response.data;
    nouveauTexte.value = commentaire.value.texte; // Affecter le texte initial du commentaire à nouveauTexte
  } catch (error) {
    console.error(error);
  }
});

const deleteComment = async () => {
  try {
    await CommentaireService.deleteCommentaire(commentaireId);
    await router.push({path: '/commentaire', query: {message: 'Commentaire supprimé avec succès'}});
  } catch (error) {
    console.error(error);
  }
};

const editComment = async () => {
  try {
    await CommentaireService.updateCommentaire(commentaireId, nouveauTexte.value);
    await router.push({path: '/commentaire', query: {message: 'Commentaire modifié avec succès'}});
  } catch (error) {
    console.error(error);
  }
};

const isDisabled = computed(() => {
  return !nouveauTexte.value || nouveauTexte.value === commentaire.value.texte || nouveauTexte.value.trim() === '';
});

</script>

<style scoped>
</style>
