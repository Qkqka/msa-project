/* 
    defineNuxtplugin : npl(자동완성)
*/
export default defineNuxtPlugin((nuxtApp) => {
    nuxtApp.vueApp.config.errorHandler = (...args) => {
        console.log(args);

        console.log("global error handler");
    };
});
