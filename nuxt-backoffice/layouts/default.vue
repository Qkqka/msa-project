<script setup lang="ts">
const adminInfo = useState("adminInfo");
console.log(adminInfo);
// if (!adminInfo.value) {
//     console.log(toRaw(adminInfo));
//     navigateTo("login");
// }
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
