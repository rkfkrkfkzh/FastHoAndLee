<template>
  <div class="register-container">
    <h1 class="register-title">회원가입</h1>
    <form @submit.prevent="register" class="register-form">
      <!-- 회원가입 폼 -->
      <div class="form-group">
        <label for="userId">사용자 ID:</label>
        <input type="text" id="userId" v-model="userId" required class="form-control">
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
      <p v-if="registerError" class="error">{{ registerErrorMessage }}</p>
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
            // 회원가입 성공 시 필요한 로직을 여기에 구현합니다.
            alert('회원가입이 완료되었습니다.'); // 예시: 회원가입이 성공했음을 알림창으로 표시합니다.
          })
          .catch(error => {
            // 회원가입 실패 시
            console.error('회원가입 실패:', error.response.data);
            this.registerError = true;
            this.registerErrorMessage = error.response ? error.response.data : '서버 오류가 발생했습니다.'; // 서버에서 받은 에러 메시지를 표시합니다.
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
