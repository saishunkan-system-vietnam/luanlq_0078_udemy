<template>
    <div ref="scrollContainer" class="h-screen overflow-y-auto">
        <Popular v-if="listPopular.length > 0" :movie-popular="listPopular[0]" @watch="handleClickButton" />
        <MovieList title="Popular">
            <div v-for="item in listPopular" :key="item.id"
                class="relative overflow-hidden w-[340px] rounded-lg cursor-pointer"
                @click="handleClickButton(item, true)">
                <img :src="item.thumbnail" alt="Movie Thumbnail"
                    class="w-full h-full object-cover transform hover:scale-110" />
            </div>
        </MovieList>
        <MovieList title="Originals">
            <div v-for="item in listOriginals" :key="item.id"
                class="relative overflow-hidden w-[340px] rounded-lg cursor-pointer"
                @click="handleClickButton(item, true)">
                <img :src="item.thumbnail" alt="Movie Thumbnail"
                    class="w-full h-full object-cover transform hover:scale-110" />
            </div>
        </MovieList>
        <ShowDialog :movie="movie" :show-dialog="showDialogDetail" :is-detail="true" @watch="handleClickButton"
            @close="closeDialog" />
        <ShowDialog :movie="movie" :show-dialog="showDialogMovie" :is-detail="false" @close="closeDialog" />
        <Footer />
    </div>
</template>

<script setup lang="ts">
import { onMounted, ref, onUnmounted } from 'vue';
import Popular from '../../components/Popular.vue';
import MovieList from '../../components/MovieList.vue';
import MovieService from '../../shared/service/movie.service';
import Footer from '../home/Footer.vue';
import type { Movie } from "../../types/movie";
import { useAuthStore } from '../../stores/appStore';
import ShowDialog from '@/components/ShowDialog.vue';

const listPopular = ref<Movie[]>([]);
const listOriginals = ref<Movie[]>([]);
const { setLoading, setAtTop } = useAuthStore();
const movie = ref<Movie>();
const showDialogDetail = ref<boolean>(false);
const showDialogMovie = ref<boolean>(false);
const scrollContainer = ref<HTMLDivElement | null>(null)


const getDataMovie = async () => {
    listPopular.value = await MovieService.getDataPopular();
    listOriginals.value = await MovieService.getDataOriginals();
};

const handleScroll = () => {
    if (scrollContainer.value) {
        setAtTop(scrollContainer.value.scrollTop < 50);
    }
};

const handleClickButton = (item: Movie, detail: boolean) => {
    setLoading(true);
    movie.value = item;
    if (detail) {
        showDialogDetail.value = true;
    } else {
        showDialogMovie.value = true;
    }
    setTimeout(() => setLoading(false), 2000);
};

const closeDialog = () => {
    if (showDialogMovie.value) {
        showDialogMovie.value = false;
    } else {
        showDialogDetail.value = false;
    }
};

onMounted(() => {
    setLoading(true);
    getDataMovie();
    scrollContainer.value?.addEventListener("scroll", handleScroll);
    setInterval(() => setLoading(false), 2000);
});

onUnmounted(() => {
    scrollContainer.value?.removeEventListener("scroll", handleScroll);
});
</script>
