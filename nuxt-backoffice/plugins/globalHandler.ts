export default defineNuxtPlugin((nuxtApp) => {
    // 에러 처리
    nuxtApp.vueApp.config.errorHandler = (error, context) => {
        console.error("!!! global error handler !!!");
        console.log("args =>>>>>> ", error, context);
        console.log("!!! global error handler !!!");
    };
});
