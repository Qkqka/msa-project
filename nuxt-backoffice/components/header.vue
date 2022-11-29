<script setup lang="ts">
const drawer = useDrawer();
</script>

<template>
    <v-app-bar>
        <v-app-bar-nav-icon @click="drawer = !drawer"></v-app-bar-nav-icon>
        <v-toolbar-title>Application</v-toolbar-title>
    </v-app-bar>
</template>
