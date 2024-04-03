<template>
  <div>
    <div class="container py-5">
      <h2 class="mb-4">환영합니다!</h2>
      <p class="lead">여기는 객실을 예약할 수 있는 웹사이트입니다. 로그인하거나 계정을 만들어 예약을 진행해주세요.</p>
      <h3 class="mb-3">최근 등록된 객실</h3>
      <div class="row">
        <div v-for="room in recentRooms" :key="room.id" class="col-lg-3 col-md-4 col-sm-6 mb-4">
          <div class="card h-100">
            <img :src="room.imageUrl" class="card-img-top" alt="Room Image">
            <div class="card-body d-flex flex-column">
              <h5 class="card-title">{{ room.name }}</h5>
              <p class="card-text mb-4">{{ room.description }}</p>
              <a href="#" class="btn btn-primary mt-auto">자세히 보기</a>
            </div>
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
      recentRooms: []
    }
  },
  mounted() {
    // Spring Boot API를 호출하여 최근 객실 데이터를 가져오는 메서드
    axios.get('/api/home') // Spring Boot API 엔드포인트에 대한 URL을 지정
        .then(response => {
          this.recentRooms = response.data;
        })
        .catch(error => {
          console.error('Error fetching recent rooms:', error);
        });
  }
}
</script>

<style scoped>
/* 필요한 스타일 추가 */
.card {
  border: 1px solid #dee2e6;
  border-radius: .25rem;
  box-shadow: 0 0.125rem 0.25rem rgba(0,0,0,.075);
}

.card-img-top {
  width: 100%;
  height: 15rem;
  object-fit: cover;
}
</style>
