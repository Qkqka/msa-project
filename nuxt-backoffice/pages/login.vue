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

    formData.value.loading = true;

    console.log(formData.value.id, formData.value.password);
    const d = await useFetch("/auth/login", {
        method: "get",
        params: {
            id: formData.value.id,
            password: formData.value.password,
        },
        server: false,
        initialCache: false,
    });
    console.log(d);
    console.log(toRaw(formData));

    setTimeout(() => (formData.value.loading = false), 2000);
}
const required = (v: FieldElement) => {
    return !!v || "Field is required";
};

// function getStudentMoreInfo(studentId: number): common.CommonCode {
//     return {
//         adminSeq: 10,
//         compSeq: 10,
//         adminId: "admin",
//         adminStatCd: "정상",
//         adminTypeCd: "",
//         lastLoginDt: "",
//         pwChgDt: "",
//         regDt: "",
//         modDt: "",
//         regSeq: 1,
//         modSeq: 1,
//         adminGrpIds: "",
//         adminMenuList: [
//             {
//                 adminMenuSeq: 10,
//                 url: "/",
//             },
//         ],
//     };
// }
// console.log(getStudentMoreInfo(10));

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
                label="Email"
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
