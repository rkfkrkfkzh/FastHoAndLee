<template>
  <div class="register-container">
    <h1 class="register-title">회원가입</h1>
    <form @submit.prevent="register" class="register-form">
      <!-- 회원가입 폼 -->
      <div class="form-group">
        <label for="userId">사용자 ID:</label>
        <input type="text" id="userId" v-model="userId" required class="form-control">
        <!-- 중복 아이디 에러 메시지 -->
        <p v-if="registerError" class="error">{{ registerErrorMessage }}</p>
      </div>
      <div class="form-group">
        <label for="userPwd">비밀번호:</label>
        <input type="password" id="userPwd" v-model="userPwd" required class="form-control">
      </div>
      <div class="form-group">
        <label for="userName">이름:</label>
        <input type="text" id="userName" v-model="userName" required class="form-control">
      </div>
      <button type="submit" class="btn btn-primary">가입하기</button>
    </form>
  </div>
</template>

<script>
import axios from 'axios';

export default {
  data() {
    return {
      userId: '',
      userPwd: '',
      userName: '',
      registerError: false,
      registerErrorMessage: ''
    }
  },
  methods: {
    register() {
      axios.post('/api/users/register', {userId: this.userId, userPwd: this.userPwd, userName: this.userName})
          .then(() => {
            // 회원가입 성공 시 로그인 페이지로 이동합니다.
            this.$router.push('/users/login');
            alert('회원가입이 완료되었습니다.'); // 회원가입이 성공했음을 알림창으로 표시합니다.
          })
          .catch(error => {
            console.error('회원가입 실패:', error.response.data);
            this.registerError = true;
            // 서버로부터의 에러 응답을 기반으로 적절한 에러 메시지를 설정합니다.
            this.registerErrorMessage = error.response && error.response.data && error.response.data.message
                ? error.response.data.message
                : '사용중인 아이디입니다.';
          });
    }
  }
}
</script>

<style scoped>
.register-container {
  max-width: 400px;
  margin: auto;
  padding: 20px;
}

.register-title {
  text-align: center;
}

.register-form {
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
