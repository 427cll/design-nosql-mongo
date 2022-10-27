<script setup>
import { RouterView } from 'vue-router'
import Header from '../components/Header.vue'
</script>

<template>
    <div class="container">
        <Header />
        <RouterView />
    </div>
</template>

<style lang="less" scoped>

</style>