<template>
  <div>
    <div v-if="loading"><Loader/></div>
    <template v-else-if="commentaireExiste">
      <CommentaireEdit :commentaireId="commentaireId"/>
    </template>
    <template v-else>
      <CommentaireIntrouvable/>
    </template>
  </div>
</template>

<script setup>
import {useRoute} from 'vue-router';
import CommentaireService from "@/services/CommentaireService.js";
import {onMounted, ref} from "vue";

import CommentaireEdit from "@/components/CommentaireEdit.vue";
import CommentaireIntrouvable from "@/components/CommentaireIntrouvable.vue";
import Loader from "@/components/Loader.vue";

const route = useRoute();
const commentaireId = route.params.id;
const loading = ref(true);
const commentaireExiste = ref(false);

onMounted(async () =>
    {
      try {
        await CommentaireService.getCommentaireById(commentaireId);
        commentaireExiste.value = true;
      } catch (error) {
        commentaireExiste.value = false;
      } finally {
        loading.value = false;
      }
    }
);
</script>

<style scoped>
</style>
