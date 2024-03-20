<template>
  <div class="container mt-5">
    <h1 class="display-4">문제가 발생했습니다...</h1>
    <p class="lead">죄송합니다. 문제가 발생했습니다. 우리는 이 문제를 해결하기 위해 노력하고 있으며 최대한 빨리 문제를 해결하도록 하겠습니다.</p>

    <div class="alert alert-danger" role="alert">
      <h4 class="alert-heading">오류 정보</h4>
      <p>에러 메시지: <span>{{ errorMessage }}</span></p>
      <!-- 기타 필요한 에러 정보 추가 -->
    </div>

    <router-link to="/" class="btn btn-primary">홈</router-link>
  </div>
</template>

<script>
export default {
  data() {
    return {
      errorMessage: "아이디 중복으로 회원가입이 안됬거나 흠..." // 에러 메시지 데이터
    }
  }
}
</script>

<style scoped>
/* 스타일 작성 */
</style>
