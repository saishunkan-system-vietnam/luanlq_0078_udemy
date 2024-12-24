<template>
    <div class="flex flex-row transform">
        <img :src="movie!.poster" alt="Movie Poster" class="w-[870px] pr-7" />
        <div class="flex flex-col justify-center">
            <Information :isDetail="true" :movie="movie" :handleWatchClick="handleWatchClick" @close="closeDialog" />
        </div>
    </div>
</template>

<script setup lang="ts">
import type { Movie } from "../types/movie";
import Information from './Information.vue';

defineProps<{
    movie?: Movie;
    handleWatchClick: Function
}>();

const emit = defineEmits(['watch', 'close']);

const closeDialog = () => emit('close');

</script>