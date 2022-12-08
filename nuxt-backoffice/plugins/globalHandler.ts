export default defineNuxtPlugin((nuxtApp) => {
    // 에러 처리
    nuxtApp.vueApp.config.errorHandler = (error, context) => {
        console.error("!!! global error handler start !!!");
        console.log("args.error =>>>>>> ", error);
        console.log("args.context =>>>>>> ", context);
        console.log("!!! global error handler end !!!");
    };
});
