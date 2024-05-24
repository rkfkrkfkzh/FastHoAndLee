<template>
  <div class="container mt-5">
    <div v-if="room" class="card mb-4">
      <img class="card-img-top" :src="room.imageUrl" alt="Room Image" style="height: 400px; object-fit: cover;">
      <div class="card-body">
        <h5 class="card-title">{{ room.name }}</h5>
        <p class="card-text">금액: {{ formatPrice(room.price) }}원</p>
        <p class="card-text">{{ room.description }}</p>
        <!-- 체크인, 체크아웃 날짜 선택 -->
        <div>
          <label for="checkIn">체크인:</label>
          <input type="date" id="checkIn" v-model="checkIn">
        </div>
        <div>
          <label for="checkOut">체크아웃:</label>
          <input type="date" id="checkOut" v-model="checkOut">
        </div>
        <button class="btn btn-primary" @click="reserveRoom">객실 예약하기</button>
      </div>
    </div>
  </div>
</template>
<script>
import axios from 'axios';
import {mapState} from 'vuex';

export default {
  data() {
    return {
      room: null,
      checkIn: '',
      checkOut: '',
    };
  },
  computed: {
    ...mapState(['isLoggedIn']),
    userId() {
      // 여기서는 예시로 1을 반환합니다. 실제로는 로그인 시 사용자 정보에서 ID를 가져와야 합니다.
      return 1;
    },
  },
  mounted() {
    const roomId = this.$route.params.id;
    axios.get(`/rooms/${roomId}`)
        .then(response => {
          this.room = response.data;
        })
        .catch(error => {
          console.error('Error fetching room detail:', error);
        });
  },
  methods: {
    formatPrice(price) {
      return price.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
    },
    reserveRoom() {
      // 사용자가 로그인했는지 확인
      if (!this.isLoggedIn) {
        alert('로그인이 필요합니다.');
        this.$router.push('/login');
        return;
      }

      const reservationData = {
        roomId: this.room.id,
        userId: this.userId,
        checkIn: this.checkIn,
        checkOut: this.checkOut
      };
      axios.post('/reservations/create', reservationData) // 변경된 부분
          .then(response => {
            console.log('Reservation successful:', response);
            alert('예약이 완료되었습니다.');
            this.$router.push('/'); // 임시 리다이렉트
          })
          .catch(error => {
            if (error.response && error.response.data && error.response.data.error) {
              alert(`예약 실패: ${error.response.data.error}`);
            } else {
              console.error('Error creating reservation:', error);
              alert('예약 중 알 수 없는 오류가 발생했습니다.');
            }
          });
    }
  }
}
</script>
