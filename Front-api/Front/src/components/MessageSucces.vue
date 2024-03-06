<script setup>

const props = defineProps(['message']);

</script>

<template>
  <div class="alert alert-dismissible alert-success">
    <button type="button" class="btn-close" data-bs-dismiss="alert"></button>
    {{ message }}
  </div>
</template>

<style scoped>

</style>