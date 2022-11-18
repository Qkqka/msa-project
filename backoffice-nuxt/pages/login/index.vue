<!-- <template>
    <div class="login" style="border: 1px solid black">
        <h3>LOGIN PAGE</h3>

        <div class="loginForm">
            <input
                type="text"
                name="id"
                id="id"
                v-model="id"
                placeholder="id를 입력해주세요."
            />
            <input
                type="password"
                name="password"
                id="password"
                v-model="password"
                placeholder="password를 입력해주세요."
            />
            <button @click="login">LOGIN</button>
        </div>
    </div>
</template> -->
<template>
    <h3>LOGIN PAGE</h3>
    <v-form ref="form">
        <v-text-field
            v-model="id"
            label="ID"
            placeholder="id를 입력해주세요."
            required
        ></v-text-field>
        <v-text-field
            v-model="password"
            :append-icon="show1 ? 'mdi-eye' : 'mdi-eye-off'"
            :type="show1 ? 'text' : 'password'"
            label="PASSWORD"
            placeholder="PASSWORD를 입력해주세요."
            counter
            @click:append="show1 = !show1"
        ></v-text-field>

        <v-btn color="success" class="mr-4" @click="login"> LOGIN </v-btn>

        <v-btn color="error" class="mr-4" @click="logout"> LOGOUT </v-btn>
    </v-form>
</template>

<script setup lang="ts">
import { ref } from "vue";

const id = ref("");
const password = ref("");
const show1 = ref(false);

function login() {
    console.log(id.value, password.value);
    const d = useFetch("/auth/login", {
        method: "get",
        params: {
            id: id.value,
            password: password.value,
        },
        server: false,
        initialCache: false,
    });
    console.log(d);
}

function logout() {
    const d = useFetch("/auth/logout", {
        method: "get",
        server: false,
        initialCache: false,
    });
    console.log(d);
}
</script>
