<template>
  <Header />
  <div class = "container">
    <EvenementsLieu />
    <LieuList />
  </div>
</template>

<script setup>

import Header from '@/components/Header.vue';
import EvenementsLieu  from "@/components/Evenements.vue";

import {computed, ref, onMounted} from "vue";
import LieuList from "@/components/LieuList.vue";

</script>



<style scoped>

</style>