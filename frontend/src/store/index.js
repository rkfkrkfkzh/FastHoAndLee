import Vue from 'vue';
import Vuex from 'vuex';

Vue.use(Vuex);

export default new Vuex.Store({
    state: {
        isLoggedIn: false
    },
    mutations: {
        setLoginState(state, isLoggedIn) {
            state.isLoggedIn = isLoggedIn;
        }
    },
    actions: {
        login({ commit }, token) {
            localStorage.setItem('jwtToken', token);
            commit('setLoginState', true);
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
