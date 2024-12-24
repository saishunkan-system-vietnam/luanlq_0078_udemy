<template>
    <div v-show="isLoading" class="fixed inset-0 bg-gray-700 bg-opacity-60 flex justify-center items-center z-50">
        <img :src="urleLoading" alt="Loading" class="w-80">
    </div>
</template>

<script setup lang="ts">
import { ref, watchEffect } from 'vue';

const props = defineProps<{
    isLoading: boolean;
}>();

const urleLoading = ref<string>('');

watchEffect(() => {
    if (props.isLoading) {
        urleLoading.value = 'https://media.tenor.com/miBRlkp03LkAAAAj/netflix.gif';
    } else {
        urleLoading.value = '';
    }
});
</script>