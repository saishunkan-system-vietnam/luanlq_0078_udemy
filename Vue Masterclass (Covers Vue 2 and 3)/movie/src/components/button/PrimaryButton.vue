<template>
    <button class="border-2 text-red-600 h-11 w-28 rounded-lg text-lg font-bold transition duration-300"
        :class="props.customClass" @click="handleClick">
        <div v-if="text"> {{ text }}</div>
        <div v-else class="material-icons" style="font-size: 27px;">{{ icon }}</div>
    </button>
</template>


<script setup lang="ts">
const props = defineProps<{
    text?: string;
    icon?: string;
    customClass?: string,
    onClick: Function,
}>();

const handleClick = () => {
    if (typeof props.onClick === "function") {
        props.onClick();
    }
};
</script>