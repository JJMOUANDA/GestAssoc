<template>
  <div>
    <h2>Événements à venir</h2>
    <ul>
      <li v-for="evenement in evenements" :key="evenement.id">
        <h3>{{ evenement.nom }}</h3>
        <p>Date: {{ evenement.date }} - Heure: {{ evenement.heure }}</p>
        <p>Lieu: {{ evenement.lieu }} - Capacité: {{ evenement.capacite }}</p>
        <p>Participants: {{ evenement.participants }}/{{ evenement.capacite }}</p>
      </li>
    </ul>
  </div>
</template>

<script setup>
import { ref } from 'vue'

const evenements = ref([
        // Exemple d'événements, à remplacer par des données dynamiques
        { id: 1, nom: 'Événement 1', date: '2024-03-01', heure: '18:00', lieu: 'Salle A', capacite: 50, capaciteMax: 100, participants: 35 },
        { id: 2, nom: 'Événement 2', date: '2024-03-15', heure: '16:00', lieu: 'Salle B', capacite: 30, capaciteMax: 50, participants: 20 },
        { id: 3, nom: 'Événement 3', date: '2024-04-05', heure: '19:30', lieu: 'Salle C', capacite: 40, capaciteMax: 60, participants: 10 },
]);

</script>

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
