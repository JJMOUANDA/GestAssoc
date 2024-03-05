<script setup>
import CommentaireService from '../services/CommentaireService.js';
import router from "@/router/index.js";

let idEvenement = '';
let idAuteur = '';
let texteCommentaire = '';

const handleSubmit = async (event) => {
  event.preventDefault();

  try {
    await CommentaireService.createCommentaire(idEvenement, idAuteur, texteCommentaire);
    idEvenement = '';
    idAuteur = '';
    texteCommentaire = '';
    await router.push({path: '/commentaire', query: {message: 'Commentaire créé avec succès'}});
  } catch (error) {
    console.error(error);
  }
};
</script>

<template>
  <form @submit="handleSubmit">
    <fieldset>
      <legend>Ajouter un commentaire</legend>
      <div class="form-group">
        <label for="exampleSelect1" class="form-label mt-4">Evénement</label>
        <select v-model="idEvenement" class="form-select" id="selectEvenement">
          <option>1</option>
          <option>2</option>
          <option>3</option>
          <option>4</option>
          <option>5</option>
        </select>
      </div>
      <div class="form-group">
        <label for="exampleSelect1" class="form-label mt-4">Auteur</label>
        <select v-model="idAuteur" class="form-select" id="selectAuteur">
          <option>1</option>
          <option>2</option>
          <option>3</option>
          <option>4</option>
          <option>5</option>
        </select>
      </div>
      <div class="form-group">
        <label for="exampleTextarea" class="form-label mt-4">Commentaire</label>
        <textarea v-model="texteCommentaire" class="form-control" id="commentaire" rows="3"></textarea>
      </div>
      <button type="submit" class="btn btn-primary">Envoyer</button>
    </fieldset>
  </form>
</template>

<style scoped>

</style>