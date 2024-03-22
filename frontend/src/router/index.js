import Vue from 'vue';
import VueRouter from 'vue-router';

Vue.use(VueRouter);

const routes = [
    {
        path: '/',
        name: 'Home',
        component: () => import('../views/HomePage.vue')
    },
    {
        path: '/error',
        name: 'error',
        component: () => import('../views/ErrorPage.vue'),
    },
    {
        path: '/login',
        name: 'login',
        component: () => import('../views/LoginForm.vue'),
    },
    {
        path: '/rooms',
        name: 'RoomList',
        component: () => import('../views/RoomList.vue'),
    },
    {
        path: '/createRoom',
        name: 'CreateRoom',
        component: () => import('../views/CreateRoom.vue'),
    },
    {
        path: '/register',
        name: 'Register',
        component: () => import('../views/UserRegister.vue'),
    }
];

const routerInstance = new VueRouter({
    mode: 'history',
    base: process.env.BASE_URL,
    routes
});

export default routerInstance;
