<template>
  <Header />
  <div class = "container">
    <Evenements />
    <LieuList />
    <CommentaireList />
  </div>
</template>

<script setup>

import Header from '@/components/Header.vue';
import Evenements  from "@/components/Evenements.vue";

import {computed, ref, onMounted} from "vue";
import LieuList from "@/components/LieuList.vue";
import CommentaireList from "@/components/CommentaireList.vue";

</script>



<style scoped>

</style>