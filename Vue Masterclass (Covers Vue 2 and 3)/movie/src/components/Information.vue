<template>
    <PrimaryButton v-if="!isDetail" text="WATCH" customClass="bg-black border-none bg-opacity-70 mt-2"
        :onClick="handleWatchClick" />
    <div v-if="isDetail" class="flex flex-row ">
        <PrimaryButton text="WATCH" customClass="border-none bg-red text-white text-base" :onClick="handleWatchClick" />
        <PrimaryButton icon="reply" customClass="bg-red w-[48px] ml-5 border-none text-base text-white"
            :onClick="closeDialog" />
    </div>
    <div class="text-white font-semibold text-4xl mt-5">
        {{ movie?.name }}
    </div>
    <p class="mt-2 font-medium text-base" :class="isDetail ? 'w-[530px] text-gray-400' : 'w-[500px] text-white'">
        {{ movie?.describe }}
    </p>
    <div class="flex items-center space-x-4 text-[#ff020d] text-opacity-95 text-base mt-2">
        <div class="flex items-center">
            <span class="font-semibold">{{ movie?.type }}</span>
        </div>
        <div class="h-6 w-[2px] bg-white"></div>
        <div class="flex items-center">
            <span class="font-semibold">{{ movie?.category }}</span>
        </div>
        <div class="h-6 w-[2px] bg-white"></div>
        <div class="flex items-center">
            <span class="font-semibold">{{ movie?.duration }}</span>
        </div>
        <div class="h-6 w-[2px] bg-white"></div>
    </div>
</template>

<script setup lang="ts">
import type { Movie } from "../types/movie";
import PrimaryButton from './button/PrimaryButton.vue';

defineProps<{
    movie?: Movie;
    isDetail: boolean;
    handleWatchClick: Function;
}>();

const emit = defineEmits(['close']);

const closeDialog = () => emit('close');

</script>