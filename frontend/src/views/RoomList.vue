<template>
  <div>
    <div class="container mt-5">
      <div class="row">
        <div v-for="room in rooms" :key="room.id" class="col-md-4 d-flex align-items-stretch">
          <div class="card mb-4 shadow-sm flex-fill">
            <img class="card-img-top" :src="room.imageUrl" alt="Room Image" style="height: 200px; object-fit: cover;">
            <div class="card-body d-flex flex-column">
              <h5 class="card-title">{{ room.name }}</h5>
              <p class="card-text">금액: {{ formatPrice(room.price) }}원</p>
              <p class="card-text">{{ room.description }}</p>
              <router-link :to="`/rooms/${room.id}`" class="btn btn-primary mt-auto">자세히 보기</router-link>            </div>
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
    // axios 요청에 인증 헤더 추가
    axios.get('/rooms', )
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

