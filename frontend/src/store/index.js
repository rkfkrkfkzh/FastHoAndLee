// store/index.js
import Vue from 'vue'; // Vue 모듈을 import 합니다.
import Vuex from 'vuex';

Vue.use(Vuex); // Vue.use()를 사용하여 Vuex를 플러그인으로 등록합니다.

export default new Vuex.Store({
    state: {
        // 상태 정의
    },
    mutations: {
        // 상태 변경 메서드 정의
    },
    actions: {
        // 비동기 작업 처리 메서드 정의
    },
    modules: {
        // 필요한 경우에 모듈 추가
    }
});
