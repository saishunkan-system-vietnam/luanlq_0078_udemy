import { defineStore } from "pinia";
import { ref, computed } from "vue";

export const useAuthStore = defineStore("appStore", () => {
  const loading = ref<boolean>(false);
  const atTop = ref<boolean>(true);

  const setLoading = (flag: boolean) => (loading.value = flag);

  const isLoading = computed(() => loading);

  const setAtTop = (flag: boolean) => (atTop.value = flag);

  const isAtTop = computed(() => atTop);

  return {
    isLoading,
    setLoading,
    setAtTop,
    isAtTop,
  };
});

