// https://v3.nuxtjs.org/api/configuration/nuxt.config
// https://codybontecou.com/how-to-use-vuetify-with-nuxt-3.html
export default defineNuxtConfig({
    // my nuxt config
    css: [
        "vuetify/lib/styles/main.sass", // custom 할려면 주석, sass, scss 뭐하는지
        "@mdi/font/css/materialdesignicons.min.css",
        "ag-grid-community/styles/ag-grid.css", // Core grid CSS, always needed
        "ag-grid-community/styles/ag-theme-alpine.css", // Optional theme CSS
        "ag-grid-community/styles/ag-theme-balham.css", // Optional theme CSS
        // "@assets/style/main.scss", // custom 할려면 맨 위 vuetify sass 주석 후 작성, sass, scss 뭐하는지 알아보기
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
