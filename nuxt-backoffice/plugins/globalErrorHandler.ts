/* 
    defineNuxtplugin : npl(자동완성)
*/
export default defineNuxtPlugin((nuxtApp) => {
    nuxtApp.vueApp.config.errorHandler = (error, context) => {
        console.error("!!! global error handler !!!");
        console.log("args =>>>>>> ", error, context);
        console.log("!!! global error handler !!!");
    };
});
