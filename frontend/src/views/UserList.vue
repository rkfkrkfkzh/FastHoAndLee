<template>
  <div class="container">
    <h1>고객 명단</h1>
    <ul class="user-list">
      <li v-for="user in users" :key="user.id" class="user-card">
        <p><strong><i class="fas fa-user"></i> 이름: </strong> {{ user.userName }}</p>
        <p><strong><i class="fas fa-envelope"></i> 이메일: </strong> {{ user.email }}</p>
        <p><strong><i class="fas fa-map-marker-alt"></i> 주소: </strong>
          <span v-if="user.address">{{ user.address }}</span>
          <span v-else>주소가 없습니다.</span>
        </p>
        <p><strong><i class="fas fa-calendar-alt"></i> 가입 날짜: </strong> {{ formatDate(user.signUpDate) }}</p>
      </li>
    </ul>
  </div>
</template>

<script>
import axios from 'axios';

export default {
  name: 'UserList',
  data() {
    return {
      users: []
    };
  },
  created() {
    this.fetchUsers();
  },
  methods: {
    async fetchUsers() {
      try {
        const response = await axios.get('/users/list');
        this.users = response.data;
      } catch (error) {
        console.error('유저 목록을 가져오는 중 오류가 발생했습니다: ', error);
      }
    },
    formatDate(date) {
      const options = { year: 'numeric', month: '2-digit', day: '2-digit', hour: '2-digit', minute: '2-digit', second: '2-digit' };
      return new Date(date).toLocaleDateString('ko-KR', options);
    }
  }
};
</script>

<style scoped>
@import url('https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css');

.container {
  max-width: 1200px;
  margin: 0 auto;
  padding: 20px;
}

h1 {
  color: #333;
  text-align: center;
  margin-bottom: 30px;
}

.user-list {
  list-style-type: none;
  padding: 0;
  display: flex;
  flex-wrap: wrap;
  justify-content: space-between;
}

.user-card {
  background: #fff;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
  margin: 10px;
  padding: 15px;
  border-radius: 8px;
  transition: transform 0.3s ease;
  flex: 0 0 calc(33.333% - 20px);
  box-sizing: border-box;
}

.user-card:hover {
  transform: translateY(-5px);
}

p {
  margin: 8px 0;
  font-size: 14px;
}

p strong {
  font-weight: 600;
  color: #555;
}

p i {
  margin-right: 8px;
  color: #777;
}

@media (max-width: 900px) {
  .user-card {
    flex: 0 0 calc(50% - 20px);
  }
}

@media (max-width: 600px) {
  .user-card {
    flex: 0 0 100%;
    padding: 10px;
  }

  p {
    font-size: 12px;
  }
}
</style>