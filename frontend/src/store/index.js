import Vue from 'vue';
import Vuex from 'vuex';

Vue.use(Vuex);

export default new Vuex.Store({
    state: {
        isLoggedIn: false,
    },
    mutations: {
        setLoginState(state, isLoggedIn) {
            state.isLoggedIn = isLoggedIn;
        },
    },
    actions: {
        login({ commit }, token ) { // 역할 정보도 함께 전달
            localStorage.setItem('jwtToken', token);
            commit('setLoginState', true);
            commit('setUserRole'); // 사용자 역할 정보 설정
        },
        logout({ commit }) {
            localStorage.removeItem('jwtToken');
            commit('setLoginState', false);
        },
        checkLoginStatus({ commit }) {
            const token = localStorage.getItem('jwtToken');
            commit('setLoginState', !!token);
        }
    }
});
