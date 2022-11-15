/* app.vue는 모든 페이지의 진입점이 되는 컴포넌트 역할을 하기 때문에 js,
css파일을 적용하고 싶다면 app.vue에 적용해도 됨 */
<template>
    <div class="text-lg" style="border: 1px solid black">
        <h1>BACKOFFICE - NUXT</h1>
        <!-- layouts/ 폴더 -->
        <NuxtLayout :name="layout">
            <!-- pages/ 폴더 -->
            <NuxtPage />
        </NuxtLayout>
    </div>
</template>

<script setup>
// layout, middleware 파일 이름은 kebab-case!!!!!!!!!!!!!
// vue.js 컴포넌트 <template> 영역안에선 kebab-case로 작성 (camel 자동 변환)
// script 영역안에선 camel 사용 가능
const layout = "bo-default";
</script>
