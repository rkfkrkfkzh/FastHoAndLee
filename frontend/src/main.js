// main.js
import Vue from 'vue'; // Vue import 수정
import App from './App.vue';
import router from './router';
import axios from 'axios';
import store from './store'; // 중괄호 없이 import
import 'bootstrap/dist/css/bootstrap.css';


// axios.defaults.baseURL = 'http://localhost:8080';
axios.defaults.baseURL = '/api';
Vue.prototype.$axios = axios; // globalProperties 대신 Vue.prototype 사용

axios.interceptors.request.use(function (config) {
    const token = localStorage.getItem('jwtToken');
    config.headers.Authorization = token ? `Bearer ${token}` : '';
    return config;
});

new Vue({
    el: '#app',
    router,
    store,
    render: h => h(App) // mount 대신 render 사용
});

axios.interceptors.response.use(function (response) {
    return response;
}, function (error) {
    if (error.response && error.response.status === 401) {
        // 로그인 페이지로 리다이렉트
        router.push('/login');
    }
    return Promise.reject(error);
});


