<template>
  <div>
    <h1>Commentaire ID: {{ commentaireId }}</h1>
  </div>
</template>

<script setup>
import { useRoute } from 'vue-router';

const route = useRoute();
const commentaireId = route.params.id;
</script>

<style scoped>
/* Styles spécifiques au composant */
</style>
