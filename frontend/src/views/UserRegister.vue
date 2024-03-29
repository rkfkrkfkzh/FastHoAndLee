<template>
  <div class="register-container">
    <h1 class="register-title">회원가입</h1>
    <form @submit.prevent="register" class="register-form">
      <!-- 회원가입 폼 -->
      <div class="form-group">
        <label for="userId">사용자 ID:</label>
        <input type="text" id="userId" v-model="userId" @input="checkUserId" required class="form-control">
        <!-- 중복 아이디 에러 메시지 -->
        <p v-if="registerError" class="error">{{ registerErrorMessage }}</p>
      </div>
      <div class="form-group">
        <label for="userPwd">비밀번호:</label>
        <input type="password" id="userPwd" v-model="userPwd" required class="form-control">
      </div>
      <div class="form-group">
        <label for="confirmUserPwd">비밀번호 확인:</label>
        <input type="password" id="confirmUserPwd" v-model="confirmUserPwd" required class="form-control">
        <!-- 비밀번호 불일치 에러 메시지 -->
        <p v-if="passwordMismatch" class="error">비밀번호가 일치하지 않습니다.</p>
        <!-- 비밀번호 일치 메시지 -->
        <p v-else-if="passwordMatch" class="match">비밀번호가 일치합니다.</p>
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
      confirmUserPwd: '', // 비밀번호 확인을 위한 데이터 속성 추가
      userName: '',
      registerError: false,
      registerErrorMessage: '',
      passwordMismatch: false, // 비밀번호 불일치를 확인하기 위한 데이터 속성 추가
      passwordMatch: false, // 비밀번호 일치를 확인하기 위한 데이터 속성 추가
    }
  },
  watch: {
    // 비밀번호 또는 비밀번호 확인 필드가 변경될 때마다 실행
    userPwd() { // 여기서 value 매개변수를 제거했습니다.
      this.checkPasswordMatch();
    },
    confirmUserPwd() { // 여기서도 value 매개변수를 제거했습니다.
      this.checkPasswordMatch();
    }
  },

  methods: {
    checkUserId() {
      // 사용자 ID 필드 입력 시 중복 확인 로직
      // 예시로 간단한 조건을 추가했습니다. 실제로는 서버에 요청을 보내서 확인해야 합니다.
      if(this.userId === 'existingUserId') {
        this.registerError = true;
        this.registerErrorMessage = '이미 사용중인 아이디입니다.';
      } else {
        this.registerError = false;
      }
    },
    checkPasswordMatch() {
      // 비밀번호와 비밀번호 확인이 일치하는지 검사
      if (this.userPwd === this.confirmUserPwd && this.userPwd !== '') {
        this.passwordMatch = true;
        this.passwordMismatch = false;
      } else {
        this.passwordMatch = false;
        this.passwordMismatch = true;
      }
    },
    register() {
      // 비밀번호와 비밀번호 확인이 일치하는지 검사
      if(!this.passwordMismatch && !this.registerError) {
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
          });}
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
.match {
  color: green;
  margin-top: 10px;
}
</style>
