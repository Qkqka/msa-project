<template>
    <!-- <h1>이건 nav</h1> -->
    <div style="border: 1px solid blue">
        <nav>
            <ul>
                <!-- nuxt-link 컴포넌트는 a태그 대신 사용, 라우팅이 훨씬 빠름(바뀐 부분만 렌더링) -->
                <li><nuxt-link to="/">HOME</nuxt-link></li>
                <li><nuxt-link to="/login">Login</nuxt-link></li>
                <li><NuxtLink to="/code">CodeList</NuxtLink></li>
            </ul>
        </nav>
    </div>
</template>
