<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>호텔 앤 리조또</title>
  <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div id="app">
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
              <router-link to="/rooms/list" class="nav-link">객실 목록</router-link>
            </li>
            <li class="nav-item">
              <router-link to="/users/list" class="nav-link">사용자 목록</router-link>
            </li>
          </ul>
        </div>
      </div>
    </nav>
  </header>
</div>

<script src="https://cdn.jsdelivr.net/npm/vue@2"></script>
<script src="https://unpkg.com/vue-router@3"></script>
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.3/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<script>
  const Home = { template: '<div>홈 페이지 컨텐츠</div>' }
  const Login = { template: '<div>로그인 페이지 컨텐츠</div>' }
  const ReservationList = { template: '<div>예약 목록 페이지 컨텐츠</div>' }
  const RoomList = { template: '<div>객실 목록 페이지 컨텐츠</div>' }
  const UserList = { template: '<div>사용자 목록 페이지 컨텐츠</div>' }

  const routes = [
    { path: '/', component: Home },
    { path: '/users/login', component: Login },
    { path: '/reservations/list', component: ReservationList },
    { path: '/rooms/list', component: RoomList },
    { path: '/users/list', component: UserList }
  ]

  const router = new VueRouter({
    routes
  })

  new Vue({
    el: '#app',
    router,
    data() {
      return {
        isLoggedIn: false // 로그인 여부에 따라 보이는 메뉴 항목을 조절할 수 있습니다.
      }
    },
    methods: {
      logout() {
        // 로그아웃 로직을 작성하세요.
        // 예시로 isLoggedIn을 false로 설정하여 간단하게 표현했습니다.
        this.isLoggedIn = false;
      }
    }
  })
</script>
</body>
</html>
