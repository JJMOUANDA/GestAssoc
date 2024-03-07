<script setup>
import {ref, onMounted} from 'vue';
import MembreService from '../services/MembreService.js';
import EvenementService from '../services/EvenementService.js';

const membres = ref([]);
const events = ref([]);
const selectedMembre = ref(null);
const selectedEvent = ref(null);

onMounted(async () => {
  try {
    const responseMembres = await MembreService.getAllMembres();
    membres.value = responseMembres.data;
    const responseEvents = await EvenementService.getAllEvent();
    events.value = responseEvents.data;
  } catch (error) {
    console.error(error);
  }
});

const register = () => {
};

</script>

<template>
  <form @submit.prevent="register">
    <div class="form-group">
      <label for="selectEvenement" class="form-label mt-4">Membre</label>
      <select class="form-select" v-model="selectedMembre">
        <option disabled value="">Choisir un membre</option>
        <option v-for="membre in membres" :key="membre.id" :value="membre.id">
          {{ membre.nom }}
        </option>
      </select>
    </div>
    <div class="form-group">
      <label for="selectEvenement" class="form-label mt-4">Événement</label>
      <select class="form-select" v-model="selectedEvent">
        <option disabled value="">Choisir un événement </option>
        <option v-for="event in events" :key="event.id" :value="event.id">
          {{ event.nom }}
        </option>
      </select>
    </div>
    <button type="submit" class="btn btn-success">Inscription</button>
  </form>
</template>