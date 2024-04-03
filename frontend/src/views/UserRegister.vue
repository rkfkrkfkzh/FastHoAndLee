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
      <div class="form-group">
        <label for="email">이메일:</label>
        <input type="email" id="email" v-model="email" required class="form-control">
      </div>
      <div class="form-group">
        <label for="phoneNumber">연락처:</label>
        <input type="text" id="phoneNumber" v-model="phoneNumber" required class="form-control">
      </div>
      <div class="form-group row">
        <div class="col-sm-8">
          <input type="text" v-model="postcode" placeholder="우편번호" class="form-control">
        </div>
        <div class="col-sm-4">
          <input type="button" @click="execDaumPostcode()" value="우편번호 찾기" class="btn btn-secondary">
        </div>
      </div>
      <div class="form-group">
        <input type="text" id="address" v-model="address" placeholder="주소" class="form-control mb-2">
      </div>
      <div class="form-group">
        <input type="text" id="detailAddress" v-model="detailAddress" placeholder="상세주소" class="form-control mb-2">
      </div>
      <div class="form-group">
        <input type="text" id="extraAddress" v-model="extraAddress" placeholder="참고항목" class="form-control mb-2">
      </div>
      <div class="form-group">
        <label for="birthDate">생일:</label>
        <input type="date" id="birthDate" v-model="birthDate" required class="form-control">
      </div>
      <div class="form-group">
        <label for="gender">성별:</label>
        <select id="gender" v-model="gender" required class="form-control">
          <option disabled value="">선택해주세요</option>
          <option value="male">남성</option>
          <option value="female">여성</option>
        </select>
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
      email: '',
      phoneNumber: '',
      postcode: "",
      address: "",
      detailAddress: "",
      extraAddress: "",
      birthDate: null,
      gender: '',
      ageLimit: 19, // 나이 제한 설정
      addressOptions: [], // 주소 검색 결과를 저장할 배열
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
      if (this.userId === 'existingUserId') {
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
    checkAge() {
      const birthDate = new Date(this.birthDate);
      const today = new Date();
      let age = today.getFullYear() - birthDate.getFullYear();
      const m = today.getMonth() - birthDate.getMonth();
      if (m < 0 || (m === 0 && today.getDate() < birthDate.getDate())) {
        age--;
      }
      return age >= this.ageLimit;
    },
    execDaumPostcode() {
      new window.daum.Postcode({
        oncomplete: (data) => {
          if (this.extraAddress !== "") {
            this.extraAddress = "";
          }
          if (data.userSelectedType === "R") {
            // 사용자가 도로명 주소를 선택했을 경우
            this.address = data.roadAddress;
          } else {
            // 사용자가 지번 주소를 선택했을 경우(J)
            this.address = data.jibunAddress;
          }

          // 사용자가 선택한 주소가 도로명 타입일때 참고항목을 조합한다.
          if (data.userSelectedType === "R") {
            // 법정동명이 있을 경우 추가한다. (법정리는 제외)
            // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
            if (data.bname !== "" && /[동|로|가]$/g.test(data.bname)) {
              this.extraAddress += data.bname;
            }
            // 건물명이 있고, 공동주택일 경우 추가한다.
            if (data.buildingName !== "" && data.apartment === "Y") {
              this.extraAddress +=
                  this.extraAddress !== ""
                      ? `, ${data.buildingName}`
                      : data.buildingName;
            }
            // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
            if (this.extraAddress !== "") {
              this.extraAddress = `(${this.extraAddress})`;
            }
          } else {
            this.extraAddress = "";
          }
          // 우편번호를 입력한다.
          this.postcode = data.zonecode;
        },
      }).open();
    },
    register() {
      // 비밀번호 불일치, 등록 오류 검사, 나이 제한 검사를 모두 통과해야 합니다.
      if (this.passwordMismatch || this.registerError || !this.checkAge()) {
        if (!this.checkAge()) {
          alert('19세 미만은 가입할 수 없습니다.');
        }
        return; // 여기서 함수 종료
      }

      // 나이 제한을 만족하면 회원가입 로직 진행
      axios.post('/api/users/register', {
        userId: this.userId,
        userPwd: this.userPwd,
        userName: this.userName,
        email: this.email,
        phoneNumber: this.phoneNumber,
        detailAddress: this.detailAddress, // 상세 주소 추가
        address: this.address,
        extraAddress: this.extraAddress,
        postcode: this.postcode,
        birthDate: this.birthDate,
        gender: this.gender
      })
          .then(() => {
            // 회원가입 성공 시 로그인 페이지로 이동합니다.
            this.$router.push('/login');
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

.match {
  color: green;
  margin-top: 10px;
}
</style>
