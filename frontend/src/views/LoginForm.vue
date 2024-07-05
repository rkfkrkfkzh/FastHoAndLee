<template>
  <div class="login-container">
    <h1 class="login-title">로그인</h1>
    <form @submit.prevent="attemptLogin" class="login-form">
      <!-- 로그인 폼 -->
      <div class="form-group">
        <label for="userId">사용자 ID:</label>
        <input type="text" id="userId" v-model="userId" required class="form-control">
      </div>
      <div class="form-group">
        <label for="userPwd">비밀번호:</label>
        <input type="password" id="userPwd" v-model="userPwd" required class="form-control">
      </div>
      <button type="submit" class="btn btn-primary">로그인</button>
      <p v-if="loginError" class="error" v-html="formattedErrorMessage"></p>
    </form>
    <!-- 회원가입 링크 -->
    <div class="register-link">
      <p>계정이 없으신가요?
        <router-link to="/register" class="btn btn-link">회원가입</router-link>
      </p>
    </div>
  </div>
</template>

<script>
import { mapActions } from 'vuex';
import axios from "axios";

export default {
  data() {
    return {
      userId: '',
      userPwd: '',
      loginError: false,
      loginErrorMessage: '',
    }
  },
  computed: {
    formattedErrorMessage() {
      return this.loginErrorMessage.replace(/\n/g, '<br>');
    }
  },
  methods: {
    ...mapActions(['login']), // Vuex 액션을 매핑, logout 제거
    attemptLogin() {
      axios.post('/auth/login', {
        userId: this.userId,
        userPwd: this.userPwd
      })
          .then(response => {
            const token = response.data.token; // 수정된 서버 응답 구조에 맞게 토큰 값을 추출
            this.login(token); // Vuex의 login 액션을 호출
            this.$router.push('/');
          })
          .catch(error => {
            console.error('로그인 실패:', error);
            this.loginError = true;

            if (error.response) {
              // 서버가 응답을 반환한 경우
              if (error.response.status === 401) {
                // 인증 실패 (예: 아이디 또는 비밀번호가 틀린 경우)
                this.loginErrorMessage = error.response.data.message.replace('\\n', '\n');
              } else {
                // 다른 서버 오류
                this.loginErrorMessage = '서버 오류가 발생했습니다. 다시 시도해주세요.';
              }
            } else if (error.request) {
              // 요청이 만들어졌으나 응답을 받지 못한 경우
              this.loginErrorMessage = '서버와 통신할 수 없습니다. 인터넷 연결을 확인해주세요.';
            } else {
              // 그 외의 에러
              this.loginErrorMessage = '알 수 없는 오류가 발생했습니다. 다시 시도해주세요.';
            }
            console.log('로그인 에러 메시지:', this.loginErrorMessage);
          });
    },
  }
}
</script>


<style scoped>
.login-container {
  max-width: 400px;
  margin: auto;
  padding: 20px;
}

.login-title {
  text-align: center;
}

.login-form {
  background-color: #f8f9fa;
  padding: 20px;
  border-radius: 5px;
}

.form-group {
  margin-bottom: 15px;
}

.form-control {
  width: 100%;
  padding: 10px;
  border: 1px solid #ced4da;
  border-radius: 3px;
}

.btn-primary {
  width: 100%;
  padding: 10px;
  background-color: #007bff;
  border: none;
  border-radius: 3px;
  color: #fff;
}

.btn-primary:hover {
  background-color: #0056b3;
}

.error {
  color: red;
  margin-top: 10px;
}
</style>
