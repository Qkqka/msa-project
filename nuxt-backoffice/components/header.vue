<script setup lang="ts">
import { useTheme } from "vuetify";
import useDrawer from "~~/composables/commUtils";

const drawer = useDrawer();
const theme = useTheme();
const mode = ref("Dark");

watch(mode, (val) => {
    theme.global.name.value = theme.global.current.value.dark
        ? "light"
        : "dark";
});
</script>

<template>
    <v-app-bar>
        <v-app-bar-nav-icon @click="drawer = !drawer"></v-app-bar-nav-icon>
        <v-toolbar-title>Backoffice</v-toolbar-title>
        <div class="pr-4">
            <v-switch
                inset
                v-model="mode"
                hide-details
                true-value="Dark"
                false-value="Light"
                :label="`${mode} Mode`"
            ></v-switch>
        </div>
    </v-app-bar>
</template>
