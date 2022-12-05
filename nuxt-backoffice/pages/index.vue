<script setup lang="ts">
const foo = useFoo();
const foo2 = 1; //useFoo2();
const test = testFoo();
// if (true) {
//     throw createError({ statusCode: 404, statusMessage: "Page Not Found" });
// }

// const { data: mountain } = useAsyncData(() => useFoo());
</script>

<template>
    <div class="d-flex align-center flex-column">
        <v-icon large color="green darken-2">mdi-domain</v-icon>
        <v-btn color="primary" elevation="2" outlined rounded>Click</v-btn>
        <span>{{ foo }}</span>
        <span>{{ foo2 }}</span>
        <span>{{ test }}</span>
    </div>
</template>
