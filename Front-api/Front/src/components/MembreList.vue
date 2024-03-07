<script setup>
import { ref, onMounted } from 'vue';
import MembreService from '../services/MembreService.js';


const membres = ref([]);

const membreIdEnEdition = ref(null);
const membreEnEdition = ref({});

onMounted(async () => {
  try {
    const response = await MembreService.getAllMembres();
    membres.value = response.data;
  } catch (error) {
    console.error(error);
  }
});

const handleDelete = async (id) => {
  try {
    await MembreService.deleteMembre(id);
    // Refresh the member list after deletion
    const response = await MembreService.getAllMembres();
    membres.value = response.data;
  } catch (error) {
    console.error(error);
  }
};

const commencerEdition = (membre) => {
  membreIdEnEdition.value = membre.id;
  membreEnEdition.value = { ...membre }; // Crée une copie superficielle pour l'édition
};

const annulerEdition = () => {
  membreIdEnEdition.value = null;
};

const modifierMembre = async (id) => {
  try {
    await MembreService.updateMembre(id, membreEnEdition.value);
    // Met à jour l'état local si la modification a réussi
    const index = membres.value.findIndex(membre => membre.id === id);
    if (index !== -1) {
      membres.value[index] = { ...membres.value[index], ...membreEnEdition.value };
    }
    membreIdEnEdition.value = null; // Termine l'édition
    alert('Membre modifié avec succès');
  } catch (error) {
    console.error("Erreur lors de la modification du membre :", error);
  }
};
</script>

<template>
  <div>
    <h2>Liste des Membres</h2>
    <table>
      <thead>
      <tr>
        <th>Nom</th>
        <th>Prénom</th>
        <th>Date de Naissance</th>
        <th>Adresse</th>
        <th>Mail</th>
        <th>Actions</th>
      </tr>
      </thead>
      <tbody>
      <tr v-for="membre in membres" :key="membre.id">
        <td v-if="membreIdEnEdition === membre.id">
            <input v-model="membreEnEdition.nom" type="text" placeholder="Nom">
        </td>
        <td v-else>
          {{ membre.nom }}
        </td>
        <td v-if="membreIdEnEdition === membre.id">
            <input v-model="membreEnEdition.prenom" type="text" placeholder="Prénom">
        </td>
        <td v-else>
          {{ membre.prenom }}
        </td>
        <td v-if="membreIdEnEdition === membre.id">
            <input v-model="membreEnEdition.dateNaissance" type="date" placeholder="Date de Naissance">
        </td>
        <td v-else>
          {{ membre.dateNaissance }}
        </td>
        <td v-if="membreIdEnEdition === membre.id">
            <input v-model="membreEnEdition.adresse" type="text" placeholder="Adresse">
        </td>
        <td v-else>
          {{ membre.adresse }}
        </td>
        <td v-if="membreIdEnEdition === membre.id">
            <input v-model="membreEnEdition.mail" type="email" placeholder="Mail">
        </td>
        <td v-else>
          {{ membre.mail }}
        </td>
        <td>
          <button v-if="membreIdEnEdition === membre.id" @click="modifierMembre(membre.id)">Enregistrer</button>
          <button v-if="membreIdEnEdition === membre.id" @click="annulerEdition">Annuler</button>
          <button v-else @click="commencerEdition(membre)">Modifier</button>
          <button @click="handleDelete(membre.id)">Supprimer</button>
        </td>
      </tr>
      </tbody>
    </table>
  </div>
</template>

<style scoped>
table {
  width: 100%;
  border-collapse: collapse;
}

th, td {
  border: 1px solid #ddd;
  padding: 8px;
  text-align: left;
}

thead {
  background-color: #24b897; /* Couleur de fond verte */
  border-top-left-radius: 10px; /* Coin supérieur gauche arrondi */
  border-top-right-radius: 10px; /* Coin supérieur droit arrondi */
}

td {
  border-bottom: 2px solid #888; /* Ligne inférieure grise */
}

form {
  display: flex;
  align-items: center;
}

input {
  width: 100%;
  padding: 5px;
  border: 1px solid #ccc;
  border-radius: 5px;
  margin-right: 5px;
}

button {
  background-color: #24b897; /* Couleur de fond verte */
  color: white;
  border: none;
  padding: 5px 10px;
  border-radius: 5px;
  cursor: pointer;
  margin-right: 5px;
}

button:hover {
  background-color: #1f8e76; /* Couleur de fond verte foncée au survol */
}
</style>