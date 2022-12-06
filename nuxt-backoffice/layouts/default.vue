<script setup lang="ts">
import { AdminInfo } from "~~/composables/type";

const adminInfo = useAdmin() as AdminInfo;
console.log(adminInfo);
if (!adminInfo) {
    navigateTo("login");
}
</script>

<template>
    <v-app id="inspire">
        <Navigation></Navigation>
        <Header></Header>
        <v-main>
            <slot></slot>
        </v-main>
    </v-app>
</template>
