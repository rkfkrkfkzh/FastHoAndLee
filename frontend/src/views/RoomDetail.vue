<template>
  <div class="container mt-5">
    <div v-if="room" class="card mb-4">
      <img class="card-img-top" :src="getImageUrl(room.imageUrl)" alt="Room Image"
           style="height: 300px; object-fit: cover; width: 100%;">
      <div class="card-body">
        <h5 class="card-title">{{ room.name }}</h5>
        <p class="card-text">금액: {{ formatPrice(room.price) }}원</p>
        <p class="card-text">{{ room.description }}</p>
        <!-- 체크인, 체크아웃 날짜 선택 -->
        <div class="form-group">
          <label for="checkIn">체크인:</label>
          <input type="date" id="checkIn" v-model="checkIn" class="form-control" :min="today">
        </div>
        <div class="form-group">
          <label for="checkOut">체크아웃:</label>
          <input type="date" id="checkOut" v-model="checkOut" class="form-control" :min="checkIn || today">
        </div>
        <button class="btn btn-primary btn-lg btn-block mt-3" @click="reserveRoom">객실 예약하기</button>
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
      today: new Date().toISOString().split('T')[0] // 오늘 날짜를 'YYYY-MM-DD' 형식으로 설정
    };
  },
  computed: {
    ...mapState(['isLoggedIn', 'userId']),
  },
  mounted() {
    const roomId = this.$route.params.id;
    axios.get(`/rooms/detail/${roomId}`) // 백틱(`)을 사용하여 템플릿 문자열을 올바르게 작성
        .then(response => {
          if (response.data) {
            console.log(response.data); // 응답 데이터를 콘솔에 출력하여 확인

            this.room = response.data;
            console.log('Room data:', this.room);

          } else {
            console.error('이미지 URL이 없습니다.');
          }
        })
        .catch(error => {
          console.error('객실 세부정보를 가져오는 중에 오류가 발생했습니다.\n', error);
        });
  },
  methods: {
    getImageUrl(filename) {
      return `/${filename}`;
    },
    formatPrice(price) {
      if (price === undefined || price === null) {
        return '가격 정보 없음';
      }
      return price.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
    },
    reserveRoom() {
      if (!this.isLoggedIn) {
        alert('로그인이 필요합니다.');
        return;
      }
      // userId가 올바르게 설정되어 있는지 확인
      if (!this.userId) {
        console.error("userId가 정의되지 않았습니다.");
        alert('로그인이 필요합니다.');
        return;
      }

      const reservationData = {
        roomId: this.room.id,
        userId: this.userId,
        checkIn: this.checkIn,
        checkOut: this.checkOut
      };

      console.log("Reservation Data: ", reservationData); // 디버깅용 로그

      axios.post('/reservations/create', reservationData)
          .then(response => {
            console.log("예약 성공 : ", response.data);
            alert("예약이 성공적으로 완료되었습니다.");
          })
          .catch(error => {
            console.error("에러 : ", error);
            alert("예약 중 오류가 발생했습니다. 다시 시도해 주세요.");
          });
    }
  }
}
</script>
<style>
/* 고객중심지향적으로 디자인을 개선하기 위한 스타일 추가 */
.card-body {
  padding: 20px;
}

.card-title {
  font-size: 1.5rem;
  font-weight: bold;
}

.card-text {
  margin-bottom: 1rem;
}

.form-group {
  margin-bottom: 1rem;
}

.btn-lg {
  font-size: 1.25rem;
  padding: 0.75rem 1.25rem;
}

.btn-block {
  display: block;
  width: 100%;
}
</style>