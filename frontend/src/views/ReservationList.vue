<template>
  <div>
    <h1>객실 예약 목록</h1>
      <div v-if="reservations.length === 0">
        <p>예약한 객실이 없습니다.</p>
      </div>
      <div v-else>
        <ul>
          <li v-for="reservation in reservations" :key="reservation.id">
            Room ID: {{ reservation.room.id }}, Check-in: {{ reservation.checkIn }}, Check-out: {{ reservation.checkOut }}
          </li>
        </ul>
      </div>
    </div>
</template>

<script>
import axios from 'axios';

export default {
  name: "ReservationList",
  data() {
    return {
      reservations: [],
    };
  },
  mounted() {
    // axios 요청에 인증 헤더 추가
    axios.get('/reservations/list', )
        .then(response => {
          console.log(response.data); // 응답 데이터를 콘솔에 출력하여 확인
          this.rooms = response.data;
        })
        .catch(error => {
          console.error('Error fetching rooms:', error);
        });
  },
}
</script>

<style scoped>
/* 필요에 따라 스타일을 추가하세요 */
</style>
