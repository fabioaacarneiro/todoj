<script setup lang="ts">
import { RouterLink, RouterView } from 'vue-router'
import TheTaskList from '@/components/TheTaskList.vue'
import TheHead from '@/components/TheHead.vue'
import TheTaskForm from './components/TheTaskForm.vue'
</script>

<template>
    <TheHead />
    <TheTaskList />
</template>
