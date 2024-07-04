<template>
  <div class="reservation-list">
    <h1>{{ userName }}님의 객실 예약 목록</h1>
    <div v-if="reservations.length === 0">
      <p>예약한 객실이 없습니다.</p>
    </div>
    <div v-else>
      <ul>
        <li v-for="reservation in reservations" :key="reservation.id" class="reservation-item">
          <img :src="getImageUrl(reservation.room.imageUrl)" alt="Room Image" class="room-image"/>
          <div class="reservation-details">
            <h2>{{ reservation.room.name }}</h2>
            <p>Check-in: {{ reservation.checkIn }}</p>
            <p>Check-out: {{ reservation.checkOut }}</p>
          </div>
        </li>
      </ul>
    </div>
  </div>
</template>

<script>
import axios from 'axios';
import { mapState } from 'vuex';

export default {
  name: "ReservationList",
  data() {
    return {
      reservations: [],
    };
  },
  computed: {
    ...mapState(['userId', 'userName']),
  },
  mounted() {
    const token = localStorage.getItem('jwtToken');
    axios.get('/reservations/list', { headers: { 'Authorization': `Bearer ${token}` } })
        .then(response => {
          console.log(response.data); // 응답 데이터를 콘솔에 출력하여 확인
          this.reservations = response.data;
        })
        .catch(error => {
          console.error('Error fetching reservations:', error);
        });
  },
  methods: {
    getImageUrl(filename) {
      return `/${filename}`;
    }
  }
}
</script>

<style scoped>
.reservation-list {
  font-family: Arial, sans-serif;
  padding: 20px;
}

h1 {
  text-align: center;
  color: #333;
}

p {
  text-align: center;
  color: #666;
}

ul {
  list-style: none;
  padding: 0;
}

.reservation-item {
  display: flex;
  align-items: center;
  margin-bottom: 20px;
  padding: 10px;
  border: 1px solid #ddd;
  border-radius: 8px;
  background-color: #fafafa;
}

.room-image {
  width: 100px;
  height: 100px;
  border-radius: 8px;
  object-fit: cover;
  margin-right: 20px;
}

.reservation-details {
  display: flex;
  flex-direction: column;
}

.reservation-details h2 {
  margin: 0;
  font-size: 1.5em;
  color: #333;
}

.reservation-details p {
  margin: 5px 0;
  color: #666;
}
</style>
