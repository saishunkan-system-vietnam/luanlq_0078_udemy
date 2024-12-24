<template>
    <footer class="bg-black text-white py-10 px-36 h-[350px]">
        <div class="container mx-auto px-6 md:px-12">
            <div class="flex flex-col md:flex-row justify-between items-center md:items-start space-y-8 md:space-y-0">
                <div class="flex flex-col items-center md:items-start">
                    <a href="/">
                        <img src="https://upload.wikimedia.org/wikipedia/commons/thumb/0/08/Netflix_2015_logo.svg/799px-Netflix_2015_logo.svg.png"
                            alt="Netflix Logo" class="w-44" />
                    </a>
                </div>

                <div class="text-center md:text-left">
                    <h4 class="font-bold text-lg">Quick Navigation</h4>
                    <ul class="space-y-2 mt-2 text-base font-medium">
                        <li v-for="item in menuItems" :key="item.path">
                            <a :href="item.path"
                                class="text-gray-400 hover:text-red-500 hover:bg-transparent transition"> {{
                                    item.name }}</a>
                        </li>
                    </ul>
                </div>

                <div class="text-center md:text-left">
                    <h4 class="font-bold text-lg">Contact Us</h4>
                    <ul class="space-y-2 mt-2 text-base font-medium">
                        <li v-for="(item, index) in contact" :key="index">
                            <a href="#"
                                class="text-gray-400 hover:text-red-500 hover:bg-transparent transition cursor-pointer">
                                {{ item }}</a>
                        </li>
                    </ul>
                </div>

                <div>
                    <h4 class="font-bold text-lg ml-2 pb-2">Social Media</h4>
                    <div class="flex justify-center md:justify-start">
                        <a v-for="(item, index) in social" :key="index" href="#"
                            class="p-2 rounded-full hover:opacity-80">
                            <img :src="item.image" :alt="item.name" class="w-9" />
                        </a>
                    </div>
                </div>
            </div>
            <div class="mt-10 text-center text-base text-white">
                Copyright © 2020 My Company. All rights reserved.
            </div>
        </div>
    </footer>
</template>

<script setup lang="ts">
import type { MenuItem, Social } from "../../types/menu";

const menuItems: MenuItem[] = [
    { name: 'Home', path: '/' },
    { name: 'About', path: '/about' },
    { name: 'Movies', path: '' },
    { name: 'Music', path: '' },
    { name: 'TV Shows', path: '' },
];

const contact: string[] = ['hello@demo.com', '123456789'];

const social: Social[] = [
    {
        name: "Facebook",
        image: "https://uploads-ssl.webflow.com/5eda5c5540fcfa1f83e1a322/5eda5c5540fcfa5c5be1a338_fb.png"
    },
    {
        name: "Instagram",
        image: "https://uploads-ssl.webflow.com/5eda5c5540fcfa1f83e1a322/5eda5c5540fcfa160ee1a33a_IG.png"
    },
    {
        name: "Twitter",
        image: "https://uploads-ssl.webflow.com/5eda5c5540fcfa1f83e1a322/5eda5c5540fcfa76bfe1a339_twitt.png"
    },
    {
        name: "LinkedIn",
        image: "https://uploads-ssl.webflow.com/5eda5c5540fcfa1f83e1a322/5eda5c5540fcfa86dfe1a33c_linked.png"
    }
];
</script>