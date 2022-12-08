<script setup lang="ts">
import { userInfo } from "os";
import { AdminInfo, CommonCode, Result } from "~~/composables/type";

definePageMeta({
    layout: false,
});

// data
const formData = ref({
    form: false,
    pwShow: false,
    id: null,
    password: null,
    loading: false,
});
const required = (v: any) => {
    return !!v || "Field is required";
};

// method
async function onSubmit() {
    if (!formData.value.form) return;

    //console.log(formData.value.id, formData.value.password);
    const d = await useFetch<Result>("/auth/login", {
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

    const resultData = d.data.value?.resultData;
    if (resultData !== null && resultData?.data !== null) {
        useState<AdminInfo>("adminInfo", () => resultData?.data);
        navigateTo("/");
    }

    setTimeout(() => {
        if (!d.pending.value) {
            formData.value.loading = false;
        }
    }, 2000);
}
</script>

<template>
    <v-app>
        <v-main>
            <v-card-title class="text-center" primary-title>
                BACKOFFICE
            </v-card-title>
            <v-card class="mx-auto my-16 px-6 py-8" max-width="344">
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
                        :append-icon="
                            formData.pwShow ? 'mdi-eye' : 'mdi-eye-off'
                        "
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
        </v-main>
    </v-app>
</template>
