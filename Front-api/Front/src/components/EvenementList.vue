<script setup>
import {ref, onMounted} from 'vue';
import EvenementService from '../services/EvenementService.js';

const events = ref([]);

onMounted(async () => {
  try {
    const response = await EvenementService.getAllEvent()();
    events.value = response.data;
  } catch (error) {
    console.error(error);
  }
});

import {useRoute} from 'vue-router';
import MessageSucces from "@/components/MessageSucces.vue";
import Evenement from "@/pages/Evenement.vue";

let message = "";
const route = useRoute();

if (route.query.message) {
  message = route.query.message;
}
console.log(message);

</script>

<template>
  <div>
    <h2>Liste des Evenement</h2>
    <ul>
      <template v-for="event in events" :key="commentaire.id">
        <EvenementCard :id="event.id" :auteur="event.nom" :evenement="commentaire.evenementId"
                         :texte="commentaire.texte" :date="commentaire.date"/>
      </template>
    </ul>
  </div>
</template>

<style scoped>

</style>
