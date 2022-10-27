<script setup>
import Home from './views/Home.vue'
</script>

<template>

  <div class="app">
    <n-notification-provider>
      <Home />
    </n-notification-provider>
  </div>
</template>

<style scoped>
.app {
  -ms-user-select: none;
  /*IE 10+*/
  user-select: none;
}
</style>
