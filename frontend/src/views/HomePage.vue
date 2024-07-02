<template>
  <div>
    <div class="container py-5">
      <h2 class="mb-4">인기 객실</h2>

      <div class="row">
        <div v-for="room in recentRooms" :key="room.id" class="col-lg-3 col-md-4 col-sm-6 mb-4">
          <div class="card h-100">
            <img class="card-img-top" :src="room.imageUrl" alt="Room Image" style="height: 200px; object-fit: cover;">
            <div class="card-body d-flex flex-column">
              <h5 class="card-title">{{ room.name }}</h5>
              <p class="card-text mb-4">{{ room.description }}</p>
              <router-link :to="`/rooms/detail/${room.id}`" class="btn btn-primary mt-auto">자세히 보기</router-link>
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
    axios.get('/home') // Spring Boot API 엔드포인트에 대한 URL을 지정
        .then(response => {
          console.log(response.data); // 응답 데이터를 콘솔에 출력하여 확인
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
