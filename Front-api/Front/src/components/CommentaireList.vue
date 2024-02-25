<script setup>

import { ref, onMounted } from 'vue';
import CommentaireService from '../CommentaireService';

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
      {{ commentaires }}
    </ul>
  </div>

</template>

<style scoped>

</style>