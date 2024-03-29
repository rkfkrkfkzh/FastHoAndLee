import Vue from 'vue';
import Vuex from 'vuex';
import axios from 'axios';

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
        async login({ commit }, { userId, userPwd }) {
            try {
                const response = await axios.post('/api/auth/login', { userId, userPwd });
                const token = response.data;
                localStorage.setItem('jwtToken', token); // 토큰을 로컬 스토리지에 저장
                commit('setLoginState', true); // 로그인 상태를 true로 변경
                return true; // 로그인 성공
            } catch (error) {
                console.error('로그인 실패:', error.response ? error.response.data : '서버 오류가 발생했습니다.');
                return false; // 로그인 실패
            }
        },
        logout({ commit }) {
            localStorage.removeItem('jwtToken'); // 토큰 삭제
            commit('setLoginState', false); // 로그인 상태를 false로 변경
        },
        checkLoginStatus({ commit }) {
            const token = localStorage.getItem('jwtToken');
            commit('setLoginState', !!token);
        }
    }

});
