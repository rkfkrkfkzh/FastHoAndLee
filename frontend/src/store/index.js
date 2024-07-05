import Vue from 'vue';
import Vuex from 'vuex';

Vue.use(Vuex);

export default new Vuex.Store({
    state: {
        isLoggedIn: false,
        userId: null,
        userName: null, // userName 상태 추가
    },
    mutations: {
        setLoginState(state, { isLoggedIn, userId, userName }) {
            state.isLoggedIn = isLoggedIn;
            state.userId = userId;
            state.userName = userName; // userName 상태 업데이트
        },
    },
    actions: {
        login({ commit }, token ) {
            const { userId, userName } = extractUserInfoFromToken(token);

            if (userId) {
                localStorage.setItem('jwtToken', token);
                commit('setLoginState', { isLoggedIn: true, userId, userName });
            } else {
                console.error('유효한 사용자 정보를 추출하지 못했습니다.');
            }
        },
        logout({ commit }) {
            localStorage.removeItem('jwtToken');
            commit('setLoginState', { isLoggedIn: false, userId: null, userName: null });
        },
        checkLoginStatus({ commit }) {
            const token = localStorage.getItem('jwtToken');
            if (token) {
                const { userId, userName } = extractUserInfoFromToken(token);
                if (userId) {
                    commit('setLoginState', { isLoggedIn: true, userId, userName });
                } else {
                    commit('setLoginState', { isLoggedIn: false, userId: null, userName: null });
                }
            } else {
                commit('setLoginState', { isLoggedIn: false, userId: null, userName: null });
            }
        }
    }
});

// JWT 토큰에서 사용자 정보를 추출하는 함수
function extractUserInfoFromToken(token) {
    if (!token) return { userId: null, userName: null };

    try {
        const base64Url = token.split('.')[1];
        if (!base64Url) {
            console.error('토큰에서 페이로드 부분을 찾지 못했습니다.');
            return { userId: null, userName: null };
        }
        const base64 = base64Url.replace(/-/g, '+').replace(/_/g, '/');
        const jsonPayload = decodeURIComponent(atob(base64).split('').map((c) => {
            return '%' + ('00' + c.charCodeAt(0).toString(16)).slice(-2);
        }).join(''));

        const payload = JSON.parse(jsonPayload);
        return { userId: payload.id || null, userName: payload.username || null };

    } catch (error) {
        console.error('토큰을 파싱하는 중에 오류가 발생했습니다.', error);
        return { userId: null, userName: null };
    }
}
