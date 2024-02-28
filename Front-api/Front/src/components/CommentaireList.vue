<script setup>

import { ref, onMounted } from 'vue';
import CommentaireService from '../services/CommentaireService.js';
import {formatDate} from "@/util/dateUtil.js";

const commentaires = ref([]);

onMounted(async () => {
  try {
    const response = await CommentaireService.getAllCommentaires();
    commentaires.value = response.data;
  } catch (error) {
    console.error(error);
  }
});

</script>

<template>
  <div>
    <h2>Liste des Commentaires</h2>
    <ul>
      <li v-for="commentaire in commentaires" :key="commentaire.id">
        {{ commentaire.texte }} - {{ formatDate(commentaire.date) }}
      </li>
    </ul>
  </div>

</template>

<style scoped>

</style>