<template>
    <div v-if="showDialog" class="fixed inset-0 z-50 flex items-center justify-center bg-black bg-opacity-50">
        <div class="absolute inset-0 z-0 bg-black bg-opacity-80"></div>
        <div v-if="isDetail">
            <ShowDetail :movie="movie" :handle-watch-click="handleWatchClick" @close="closeDialog" />
        </div>
        <div v-else>
            <PlayVideo :video-link="videoUrl" @close="closeDialog" />
        </div>
    </div>
</template>

<script setup lang="ts">
import { ref, watch } from 'vue';
import ShowDetail from './ShowDetailMovie.vue';
import PlayVideo from './PlayVideo.vue';
import type { Movie } from "../types/movie";

const props = defineProps<{
    movie?: Movie;
    showDialog: boolean;
    isDetail: boolean;
}>();

const videoUrl = ref<string>('');

watch(props, () => {
    if (!props.isDetail) {
        videoUrl.value = props.movie?.link_video ?? '';
    }
});

const emit = defineEmits(['watch', 'close'],);

const handleWatchClick = () => {
    emit('watch', props.movie, false);
};

const closeDialog = () => emit('close');

</script>