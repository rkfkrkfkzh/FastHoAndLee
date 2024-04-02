<template>
  <div>
    <div class="row">
      <div v-for="room in rooms" :key="room.id" class="col-md-4">
        <div class="card mb-4 shadow-sm">
          <img class="card-img-top" :src="room.imageUrl" alt="Room Image">
          <div class="card-body">
            <h5 class="card-title">{{ room.name }}</h5>
            <p class="card-text">금액 : {{ formatPrice(room.price) }}원</p>
            <p class="card-text">{{ room.description }}</p>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import axios from 'axios';

export default {
  data() {
    return {
      rooms: []
    };
  },
  mounted() {
    // 로컬 스토리지에서 토큰 가져오기
    const token = localStorage.getItem('token');

    // axios 요청에 인증 헤더 추가
    axios.get('/api/rooms', {
      headers: {
        'Authorization': `Bearer ${token}`
      }
    })
        .then(response => {
          this.rooms = response.data;
        })
        .catch(error => {
          console.error('Error fetching rooms:', error);
        });
  },
  methods: {
    formatPrice(price) {
      // 가격을 형식화하여 쉼표를 추가합니다.
      return price.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
    }
  }
}
</script>

