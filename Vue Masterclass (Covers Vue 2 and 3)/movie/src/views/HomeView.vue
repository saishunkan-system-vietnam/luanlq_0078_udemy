<template>
  <Content />
  <Loading :is-loading="isLoading" />
</template>

<script setup lang="ts">
import Content from './home/MainContent.vue'
import Loading from '../components/Loading.vue';
import { useAuthStore } from '../stores/appStore';

const { isLoading } = useAuthStore();
</script>