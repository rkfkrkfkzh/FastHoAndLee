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
              <router-link to="/login" class="nav-link">로그인</router-link>
            </li>
            <li class="nav-item" v-if="isLoggedIn">
              <a href="#" class="nav-link" @click.prevent="logout">로그아웃</a>
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
            <li class="nav-item">
              <router-link to="/rooms/create" class="nav-link">객실 등록</router-link>
            </li>
          </ul>
        </div>
      </div>
    </nav>
    <!-- 로그인 컴포넌트 조건부 렌더링 -->
    <Login v-if="!isLoggedIn && $route.path === '/users/login'" @login-success="handleLoginSuccess" />
  </header>
</template>

<script>
export default {
  computed: {
    isLoggedIn() {
      return this.$store.state.isLoggedIn;
    }
  },
  created() {
    this.$store.dispatch('checkLoginStatus');
  },
  methods: {
    logout() {
      this.$store.dispatch('logout');
    }
  }
};
</script>


<style scoped>
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

.navbar-nav {
  display: flex;
  flex-direction: row;
  padding-left: 0;
  margin-bottom: 0;
  list-style: none;
}

.nav-item {
  margin-right: 1rem;
}

.nav-item:last-child {
  margin-right: 0;
}
</style>
