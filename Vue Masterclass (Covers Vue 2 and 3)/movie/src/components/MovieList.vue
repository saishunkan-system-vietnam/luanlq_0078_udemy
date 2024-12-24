<template>
    <div class="my-14 mx-[203px]">
        <div class="text-white font-medium text-2xl">
            {{ title }}
        </div>
        <div class="mt-4 flex flex-row space-x-4">
            <slot></slot>
        </div>
    </div>
</template>

<script setup lang="ts">
defineProps<{ title: string }>();
</script>
