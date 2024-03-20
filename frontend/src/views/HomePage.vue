<template>
  <div>
    <HeaderComponent />
    <div class="container">
      <h2>환영합니다!</h2>
      <p>여기는 객실을 예약할 수 있는 웹사이트입니다. 로그인하거나 계정을 만들어 예약을 진행해주세요.</p>
      <h3>최근 등록된 객실</h3>
      <ul class="list-group">
        <li v-for="room in recentRooms" :key="room.id" class="list-group-item">
          <div class="row">
            <div class="col-2">
              <img :src="room.imageUrl" class="img-fluid" alt="Room Image" style="max-width: 100px;">
            </div>
            <div class="col">
              <h5>{{ room.name }}</h5>
              <p>{{ room.description }}</p>
            </div>
          </div>
        </li>
      </ul>
    </div>
    <FooterComponent />
  </div>
</template>

<script>
import HeaderComponent from '../components/HeaderComponent.vue'
import FooterComponent from '../components/FooterComponent.vue'
import axios from 'axios';

export default {
  components: {
    HeaderComponent,
    FooterComponent
  },
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
