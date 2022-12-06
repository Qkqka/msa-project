<script setup lang="ts">
import useDrawer from "~~/composables/commUtils";
import { AdminInfo, Result } from "~~/composables/type";

// data
const drawer = useDrawer();
const adminInfo = useAdmin() as AdminInfo;
const open = ref([]);
const codes = [
    ["목록", "mdi-list-box-outline", "code/codeList"],
    // ["Settings", "mdi-cog-outline"],
];
const admins = [
    ["목록", "mdi-list-box-outline"],
    // ["Settings", "mdi-cog-outline"],
];
const cruds = [
    ["Create", "mdi-plus-outline"],
    ["Read", "mdi-file-outline"],
    ["Update", "mdi-update"],
    ["Delete", "mdi-delete"],
];

// method
async function logout() {
    if (!adminInfo) {
        navigateTo("/login");
    }
    const d = await useFetch<Result>("/auth/logout", {
        method: "get",
        server: false,
        cache: "no-cache",
    });

    console.log(d);
    useState("adminInfo", () => null);
    //navigateTo("/login");
    console.log(window.__NUXT__);
}

// watch(open, (val) => {
//     console.log("group newVal: ", val);
// });
// const open = computed((val, newVal) => {
//     console.log(val);
//     return ["system", "product"];
// });
// console.log(open);
// onMounted(() => {
//     console.log(window.__NUXT__);
// });
</script>

<template>
    <v-navigation-drawer v-model="drawer" temporary>
        <v-list v-model="open">
            <v-list-item>안녕하세요. {{ adminInfo.adminId }} 님</v-list-item>
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

                <v-list-group value="Code">
                    <template v-slot:activator="{ props }">
                        <v-list-item
                            v-bind="props"
                            title="공통코드"
                        ></v-list-item>
                    </template>

                    <v-list-item
                        v-for="([title, icon, link], i) in codes"
                        :key="i"
                        :title="title"
                        :prepend-icon="icon"
                        :value="title"
                        :to="link"
                    ></v-list-item>
                </v-list-group>

                <v-list-group value="Admin">
                    <template v-slot:activator="{ props }">
                        <v-list-item
                            v-bind="props"
                            title="관리자"
                        ></v-list-item>
                    </template>

                    <v-list-item
                        v-for="([title, icon], i) in admins"
                        :key="i"
                        :title="title"
                        :prepend-icon="icon"
                        :value="title"
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
                <v-btn class="bg-deep-purple" @click="logout" block>
                    Logout
                </v-btn>
            </div>
        </template>
    </v-navigation-drawer>
</template>
