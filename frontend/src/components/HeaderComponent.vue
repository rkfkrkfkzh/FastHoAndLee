<template>
  <header>
    <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
      <div class="container">
        <router-link to="/" class="navbar-brand">호텔 앤 리조또</router-link>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav"
                aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
          <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarNav">
          <ul class="navbar-nav ml-auto">
            <li class="nav-item">
              <router-link to="/" class="nav-link">홈</router-link>
            </li>
            <li class="nav-item" v-if="!isLoggedIn">
              <router-link to="/users/login" class="nav-link">로그인</router-link>
            </li>
            <li class="nav-item" v-if="isLoggedIn">
              <a href="#" class="nav-link" @click="logout">로그아웃</a>
            </li>
            <li class="nav-item">
              <router-link to="/reservations/list" class="nav-link">예약 목록</router-link>
            </li>
            <li class="nav-item">
              <router-link to="/rooms" class="nav-link">객실 목록</router-link>
            </li>
            <li class="nav-item">
              <router-link to="/users/list" class="nav-link">사용자 목록</router-link>
            </li>
          </ul>
        </div>
      </div>
    </nav>
    <!-- 로그인 컴포넌트 조건부 렌더링 -->
    <Login v-if="!isLoggedIn && $route.path === '/users/login'" />
  </header>
</template>

<script>
import Login from '@/views/LoginForm.vue';

export default {
  components: {
    Login
  },
  props: {
    isLoggedIn: {
      type: Boolean,
      default: false
    }
  },
  methods: {
    logout() {
      this.$emit('logout');
    }
  }
};
</script>

<style scoped>
/* 기존 스타일 유지 */
.navbar {
  background-color: #222;
}

.navbar-brand {
  color: #fff;
  font-weight: bold;
}

.navbar-toggler {
  border: none;
}

.nav-link {
  color: #fff;
}

.navbar-nav .nav-link:hover {
  color: #ddd;
}

/* 추가된 스타일: 네비게이션 바 아이템들을 가로로 정렬 */
.navbar-nav {
  display: flex;
  flex-direction: row; /* 가로 정렬을 위한 설정 */
  padding-left: 0; /* 필요에 따라 조정 */
  margin-bottom: 0; /* 필요에 따라 조정 */
  list-style: none;
}

.nav-item {
  margin-right: 1rem; /* 아이템 간격 조정 */
}

.nav-item:last-child {
  margin-right: 0; /* 마지막 아이템의 오른쪽 간격 제거 */
}
</style>
