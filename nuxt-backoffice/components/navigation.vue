<script setup lang="ts">
const drawer = useDrawer();
// const group = ref(null);

const open = ref([]);
const admins = [
    ["Management", "mdi-account-multiple-outline"],
    ["Settings", "mdi-cog-outline"],
];
const cruds = [
    ["Create", "mdi-plus-outline"],
    ["Read", "mdi-file-outline"],
    ["Update", "mdi-update"],
    ["Delete", "mdi-delete"],
];

// watch(open, (val) => {
//     console.log("group newVal: ", val);
// });
// const open = computed((val, newVal) => {
//     console.log(val);
//     return ["system", "product"];
// });
// console.log(open);
onMounted(() => {
    console.log(window.__NUXT__);
});
</script>

<template>
    <v-navigation-drawer v-model="drawer" temporary>
        <v-list v-model="open">
            <v-list-item>{{ open }}</v-list-item>
            <v-list-item
                prepend-icon="mdi-home"
                title="Home"
                to="/"
            ></v-list-item>

            <v-list-group value="system">
                <template v-slot:activator="{ props }">
                    <v-list-item
                        v-bind="props"
                        title="시스템관리"
                    ></v-list-item>
                    <!-- prepend-icon="mdi-account-circle" -->
                </template>

                <v-list-group value="Admin">
                    <template v-slot:activator="{ props }">
                        <v-list-item v-bind="props" title="Admin"></v-list-item>
                    </template>

                    <v-list-item
                        v-for="([title, icon], i) in admins"
                        :key="i"
                        :title="title"
                        :prepend-icon="icon"
                        :value="title"
                    ></v-list-item>
                </v-list-group>

                <v-list-group value="Actions">
                    <template v-slot:activator="{ props }">
                        <v-list-item
                            v-bind="props"
                            title="Actions"
                        ></v-list-item>
                    </template>

                    <v-list-item
                        v-for="([title, icon], i) in cruds"
                        :key="i"
                        :value="title"
                        :title="title"
                        :prepend-icon="icon"
                    ></v-list-item>
                </v-list-group>
            </v-list-group>
            <v-list-group value="product">
                <template v-slot:activator="{ props }">
                    <v-list-item v-bind="props" title="상품관리"></v-list-item>
                </template>
            </v-list-group>
        </v-list>

        <template v-slot:append>
            <div class="pa-2">
                <v-btn class="bg-deep-purple" block> Logout </v-btn>
            </div>
        </template>
    </v-navigation-drawer>
</template>
