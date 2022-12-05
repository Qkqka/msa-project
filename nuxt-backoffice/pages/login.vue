<script setup lang="ts">
import { FieldElement } from "ag-grid-community";
import * as common from "~~/utils/type";

// data
const formData = ref({
    form: false,
    pwShow: false,
    id: null,
    password: null,
    loading: false,
});

async function onSubmit() {
    if (!formData.value.form) return;

    //console.log(formData.value.id, formData.value.password);
    const d = await useFetch("/auth/login", {
        method: "get",
        params: {
            id: formData.value.id,
            password: formData.value.password,
        },
        server: false,
        cache: "no-cache",
    });
    if (d.pending.value) {
        console.log(1);
        formData.value.loading = true;
    }

    console.log(d);
    const result = toRaw(d.data.value);
    // if (!!result) {
    //     if (result.data.resultCode !== 200) {
    //         alert(result.data.resultMsg);
    //     }
    // }
    console.log(result);
    console.log(unref(d.data.value));

    setTimeout(() => {
        if (!d.pending.value) {
            formData.value.loading = false;
        }
    }, 2000);
}
const required = (v: FieldElement) => {
    return !!v || "Field is required";
};

definePageMeta({
    layout: false,
});
</script>

<template>
    <v-card class="mx-auto px-6 py-8" max-width="344">
        <v-form v-model="formData.form" @submit.prevent="onSubmit">
            <v-text-field
                v-model="formData.id"
                :readonly="formData.loading"
                :rules="[required]"
                class="mb-2"
                clearable
                label="ID"
                placeholder="Enter your ID"
            ></v-text-field>

            <v-text-field
                v-model="formData.password"
                :append-icon="formData.pwShow ? 'mdi-eye' : 'mdi-eye-off'"
                :readonly="formData.loading"
                :rules="[required]"
                :type="formData.pwShow ? 'text' : 'password'"
                clearable
                label="Password"
                placeholder="Enter your password"
                @click:append="formData.pwShow = !formData.pwShow"
            ></v-text-field>

            <br />

            <v-btn
                :disabled="!formData.form"
                :loading="formData.loading"
                block
                color="success"
                size="large"
                type="submit"
                variant="elevated"
            >
                Sign In
            </v-btn>
        </v-form>
    </v-card>
</template>
