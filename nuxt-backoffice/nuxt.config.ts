// https://nuxt.com/docs/api/configuration/nuxt-config
export default defineNuxtConfig({
    app: {
        head: {
            title: "Backoffice",
        },
    },
    css: [
        "@mdi/font/css/materialdesignicons.min.css",
        "ag-grid-community/styles/ag-grid.css", // Core grid CSS, always needed
        "ag-grid-community/styles/ag-theme-balham.css", // Optional theme CSS
        "~/assets/styles/vuetify.scss", // vuetify custom scss
    ],
    build: {
        transpile: ["vuetify"], // css 자동 세팅 할 때 필요함.
    },
    vite: {
        define: {
            "process.env.DEBUG": false,
        },
    },
});
