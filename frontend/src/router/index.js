// src/router/index.js
import Vue from 'vue'
import VueRouter from 'vue-router'


Vue.use(VueRouter)

const routes = [
    {
        path: '/',
        name: 'Home',
        component:() => import('../views/HomePage.vue')
    },
    {
        path: '/error',
        name: 'error`',
        component: () => import('../views/ErrorPage'),
    },
    // 필요한 라우트들을 추가
]

const router = new VueRouter({
    mode: 'history',
    base: process.env.BASE_URL,
    routes
})

export default router
