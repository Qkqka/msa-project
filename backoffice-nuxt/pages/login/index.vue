<template>
    <div class="login" style="border: 1px solid black">
        <h3>LOGIN PAGE</h3>
        <button @click="count++">You Clicked me {{ count }} times.</button
        ><br />
        <button @click="login">{{ msg }}</button>
        <button @click="counts++">{{ counts }}</button>

        <div class="loginForm">
            <input
                type="text"
                name="id"
                id="id"
                placeholder="id를 입력해주세요."
            />
            <input
                type="password"
                name="password"
                id="password"
                placeholder="password를 입력해주세요."
            />
            <button @click="login">LOGIN</button>
        </div>
    </div>
</template>

<script setup>
import { ref } from "vue";
console.log("login page script setup");

const msg = "login page!!!";

function login() {
    console.log("login!!!");
}

const { data, error, pending, refresh } = await useFetch(
    "http://localhost:9100/auth/login",
    {
        method: "post",
        params: {
            id: document.getElementById("id").value,
            password: document.getElementById("password").value,
        },
    }
);
console.log(hello);

const counts = ref(0);
</script>
<script>
console.log("login page1");
export default {
    data() {
        return {
            count: 0,
        };
    },
};
</script>
