import Vue from 'vue';
import Vuex from 'vuex';

Vue.use(Vuex);

export default new Vuex.Store({
    state: {
        isLoggedIn: false,
        userId: null, // userId가 아닌, 사용자 기본키를 저장할 변수
    },
    mutations: {
        setLoginState(state, { isLoggedIn, userId }) {
            state.isLoggedIn = isLoggedIn;
            state.userId = userId;
        },
    },
    actions: {
        login({ commit }, token ) { // 역할 정보도 함께 전달
            // JWT 토큰에서 사용자 ID를 추출하는 로직
            const userId = extractUserIdFromToken(token);

            if (userId) {
                localStorage.setItem('jwtToken', token);
                commit('setLoginState', { isLoggedIn: true, userId });
            } else {
                console.error('유효한 사용자 ID를 추출하지 못했습니다.');
            }
        },
        logout({ commit }) {
            localStorage.removeItem('jwtToken');
            commit('setLoginState', { isLoggedIn: false, userId: null });
        },
        checkLoginStatus({ commit }) {
            const token = localStorage.getItem('jwtToken');
            if (token) {
                const userId = extractUserIdFromToken(token);
                if (userId) {
                    commit('setLoginState', { isLoggedIn: true, userId });
                } else {
                    commit('setLoginState', { isLoggedIn: false, userId: null });
                }
            } else {
                commit('setLoginState', { isLoggedIn: false, userId: null });
            }
        }
    }
});

// JWT 토큰에서 사용자 ID를 추출하는 함수
function extractUserIdFromToken(token) {
    if (!token) return null;

    try {
        const base64Url = token.split('.')[1];
        if (!base64Url) {
            console.error('토큰에서 페이로드 부분을 찾지 못했습니다.');
            return null;
        }
        const base64 = base64Url.replace(/-/g, '+').replace(/_/g, '/');
        const jsonPayload = decodeURIComponent(atob(base64).split('').map((c) => {
            return '%' + ('00' + c.charCodeAt(0).toString(16)).slice(-2);
        }).join(''));

        const payload = JSON.parse(jsonPayload);
        if (!payload.id) {
            console.error('페이로드에 id가 없습니다.');
            return null;
        }
        return payload.id;

    } catch (error) {
        console.error('토큰을 파싱하는 중에 오류가 발생했습니다.', error);
        return null;
    }
}
// user.userId <- 로그인 ID
// function extractUserIdFromToken(token) {
//     if (!token) return null;
//
//     try {
//         const base64Url = token.split('.')[1];
//         if (!base64Url) {
//             console.error('토큰에서 페이로드 부분을 찾지 못했습니다.');
//             return null;
//         }
//         const base64 = base64Url.replace(/-/g, '+').replace(/_/g, '/');
//         const jsonPayload = decodeURIComponent(atob(base64).split('').map((c) => {
//             return '%' + ('00' + c.charCodeAt(0).toString(16)).slice(-2);
//         }).join(''));
//
//         const payload = JSON.parse(jsonPayload);
//         if (!payload.userId) {
//             console.error('페이로드에 userId가 없습니다.');
//             return null;
//         }
//         return payload.userId;
//
//     } catch (error) {
//         console.error('토큰을 파싱하는 중에 오류가 발생했습니다.', error);
//         return null;
//     }
// }