<script setup lang="ts"></script>

<template>
    <v-app id="inspire">
        <Navigation></Navigation>
        <Header></Header>
        <v-main>
            <slot></slot>
        </v-main>
    </v-app>
</template>
