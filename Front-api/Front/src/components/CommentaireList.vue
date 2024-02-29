<script setup>

import {ref, onMounted} from 'vue';
import CommentaireService from '../services/CommentaireService.js';
import CommentaireCard from "@/components/CommentaireCard.vue";

const commentaires = ref([]);

onMounted(async () => {
  try {
    const response = await CommentaireService.getAllCommentaires();
    commentaires.value = response.data;
  } catch (error) {
    console.error(error);
  }
});

import {useRoute} from 'vue-router';
import MessageSucces from "@/components/MessageSucces.vue";

let message = "";
const route = useRoute();

if (route.query.message) {
  message = route.query.message;
}
console.log(message);

</script>

<template>
  <MessageSucces v-if="message" :message="message" />
  <div>
    <h2>Liste des Commentaires</h2>
    <ul>
      <template v-for="commentaire in commentaires" :key="commentaire.id">
        <CommentaireCard :id="commentaire.id" :auteur="commentaire.auteurId" :evenement="commentaire.evenementId"
                         :texte="commentaire.texte" :date="commentaire.date"/>
      </template>
    </ul>
  </div>
</template>

<style scoped>

</style>