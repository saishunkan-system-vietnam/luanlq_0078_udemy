<template>
    <div class="absolute inset-1 flex items-center justify-center z-10">
        <iframe :src="videoUrl" title="YouTube video player" allow="autoplay; encrypted-media" allowfullscreen
            class="w-4/5 md:w-2/3 lg:w-1/2 aspect-video rounded-lg shadow-lg border-0"></iframe>
    </div> <button @click="closeDialog" class="absolute z-20 top-11 right-10 text-white text-xl" aria-label="Close">
        <span class="material-icons">close</span>
    </button>
</template>

<script setup lang="ts">
import { ref, watchEffect } from 'vue';

const props = defineProps<{
    videoLink: string;
}>();

const videoUrl = ref<string>();

watchEffect(() => {
    const newVideoLink = props.videoLink;

    if (newVideoLink.includes('youtube.com') || newVideoLink.includes('youtu.be')) {
        const videoId =
            newVideoLink.includes('youtu.be')
                ? newVideoLink.split('/').pop()
                : new URLSearchParams(new URL(newVideoLink).search).get('v');
        videoUrl.value = `https://www.youtube.com/embed/${videoId}`;
    } else if (newVideoLink.includes('vimeo.com')) {
        const videoId = newVideoLink.split('/').pop();
        videoUrl.value = `https://player.vimeo.com/video/${videoId}`;
    } else {
        videoUrl.value = '';
    }
}
);


const emit = defineEmits(['close']);

const closeDialog = () => emit('close');

</script>