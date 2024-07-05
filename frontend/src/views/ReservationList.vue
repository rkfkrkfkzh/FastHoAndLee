<template>
  <div class="reservation-list">
    <h1>{{ userName }}님의 객실 예약 목록</h1>
    <div v-if="reservations.length === 0">
      <p>예약한 객실이 없습니다.</p>
    </div>
    <div v-else>
      <div class="reservation-grid">
        <div v-for="reservation in reservations" :key="reservation.id" class="reservation-item">
          <img :src="getImageUrl(reservation.room.imageUrl)" alt="Room Image" class="room-image"/>
          <div class="reservation-details">
            <h2>{{ reservation.room.name }}</h2>
            <p>Check-in: {{ reservation.checkIn }}</p>
            <p>Check-out: {{ reservation.checkOut }}</p>
            <button @click="cancelReservation(reservation.id)">예약 취소</button>
          </div>
        </div>
      </div>
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
    },
    cancelReservation(id) {
      const token = localStorage.getItem('jwtToken');
      axios.delete(`/reservations/cancel/${id}`, {headers: {'Authorization': `Bearer ${token}`}})
          .then(response => {
            alert(response.data);
            this.reservations = this.reservations.filter(reservation => reservation.id !== id);
          })
          .catch(error => {
            console.error('Error cancelling reservation:', error);
            alert('예약 취소 중 오류가 발생했습니다.');
          });
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

.reservation-grid {
  display: grid;
  grid-template-columns: repeat(auto-fit, minmax(280px, 1fr)); /* 최소 280px, 최대 화면 크기에 따라 열 수 조정 */
  gap: 20px; /* 카드 간격 */
}

.reservation-item {
  display: flex;
  align-items: center;
  padding: 10px;
  border: 1px solid #ddd;
  border-radius: 8px;
  background-color: #fafafa;
}

.room-image {
  width: 80px;
  height: 80px;
  border-radius: 8px;
  object-fit: cover;
  margin-right: 20px;
}

.reservation-details {
  display: flex;
  flex-direction: column;
  flex-grow: 1;
}

.reservation-details h2 {
  margin: 0;
  font-size: 1.2em;
  color: #333;
}

.reservation-details p {
  margin: 5px 0;
  color: #666;
}

button {
  align-self: flex-end;
  padding: 8px 16px;
  border: none;
  border-radius: 8px;
  background-color: #ff6666;
  color: white;
  cursor: pointer;
}

button:hover {
  background-color: #ff3333;
}
</style>
