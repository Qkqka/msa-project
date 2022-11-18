// https://v3.nuxtjs.org/api/configuration/nuxt.config
// https://codybontecou.com/how-to-use-vuetify-with-nuxt-3.html
export default defineNuxtConfig({
    // my nuxt config
    css: [
        "vuetify/lib/styles/main.sass",
        "@mdi/font/css/materialdesignicons.min.css",
    ],
    build: {
        transpile: ["vuetify"],
    },
    vite: {
        define: {
            "process.env.DEBUG": false,
        },
    },
});
