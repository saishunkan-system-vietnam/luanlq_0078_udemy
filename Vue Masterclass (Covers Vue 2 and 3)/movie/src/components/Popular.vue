<template>
    <div class="relative w-full">
        <div class="w-full h-screen">
            <img :src="moviePopular.poster" alt="Movie Poster" class="w-full h-full object-cover" />
            <div class="absolute inset-0 bg-gradient-to-b from-black/80 via-black/30 to-black/100" />
        </div>
        <div class="absolute inset-0 top-52 left-[203px] z-10">
            <img :src="moviePopular.logo" alt="Movie Logo" class="w-[400px]" />
            <Information :isDetail="false" :movie="moviePopular" :handleWatchClick="handleWatchClick" />
        </div>
    </div>
</template>

<script setup lang="ts">
import type { Movie } from "../types/movie";
import Information from './Information.vue';


const props = defineProps<{
    moviePopular: Movie;
}>();

const emit = defineEmits(['watch']);

const handleWatchClick = () => {
    emit('watch', props.moviePopular, false);
};
</script>