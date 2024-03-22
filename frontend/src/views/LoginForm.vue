<template>
  <div class="login-container">
    <h1 class="login-title">로그인</h1>
    <form @submit.prevent="login" class="login-form">
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
      <p v-if="loginError" class="error">{{ loginErrorMessage }}</p>
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
import axios from 'axios';

export default {
  data() {
    return {
      userId: '',
      userPwd: '',
      loginError: false,
      loginErrorMessage: '',
      csrfToken: '' // CSRF 토큰을 저장할 변수 추가
    }
  },
  methods: {
    login() {
      // 서버에서 CSRF 토큰을 받아온다고 가정
      const csrfToken = ''; // CSRF 토큰을 받아와서 저장
      axios.post('/api/users/login', {userId: this.userId, userPwd: this.userPwd},{
        headers: {
          'X-CSRF-TOKEN': csrfToken  // CSRF 토큰을 요청 헤더에 포함
        }
      })
          .then(() => {
            // 필요한 로직 구현
          })
          .catch(error => {
            // 로그인 실패 시
            console.error('로그인 실패:', error.response.data);
            this.loginError = true;
            this.loginErrorMessage = error.response ? error.response.data : '서버 오류가 발생했습니다.'; // 서버에서 받은 에러 메시지를 표시합니다.
          });
    }
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
