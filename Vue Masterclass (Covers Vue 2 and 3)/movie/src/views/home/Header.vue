<template>
    <div ref="scrollableElement"
        class="fixed flex justify-between items-center top-0 left-0 h-16 z-50 w-full text-white px-56 py-11"
        :class="isAtTop ? '' : 'bg-black bg-opacity-50'">
        <div class="flex items-center">
            <a href="/">
                <img src="https://upload.wikimedia.org/wikipedia/commons/thumb/0/08/Netflix_2015_logo.svg/799px-Netflix_2015_logo.svg.png"
                    alt="Movie Poster" class="w-44" />
            </a>
        </div>
        <nav>
            <ul class="flex space-x-6 text-lg font-medium">
                <li v-for="item in menuItems" :key="item.path">
                    <a :href="item.path" class="hover:text-red-500 hover:bg-transparent transition">
                        {{ item.name }}
                    </a>
                </li>
            </ul>
        </nav>
    </div>
</template>

<script setup lang="ts">
import { useAuthStore } from '../../stores/appStore';
import type { MenuItem } from "../../types/menu";

const { isAtTop } = useAuthStore();

const menuItems: MenuItem[] = [
    { name: 'Home', path: '/' },
    { name: 'About', path: '/about' },
    { name: 'Movies', path: '' },
    { name: 'Music', path: '' },
    { name: 'TV Shows', path: '' },
];
</script>