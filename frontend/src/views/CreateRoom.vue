<!--<template>-->
<!--  <div class="room-registration">-->
<!--    <h1 class="login-title">객실 등록</h1>-->
<!--    <form @submit.prevent="createRoom" class="login-form">-->
<!--      <div class="form-group">-->
<!--        <label for="name">객실 이름:</label>-->
<!--        <input type="text" id="name" v-model="room.name" required class="form-control">-->
<!--      </div>-->
<!--      <div class="form-group">-->
<!--        <label for="description">설명:</label>-->
<!--        <textarea id="description" v-model="room.description" class="form-control"></textarea>-->
<!--      </div>-->
<!--      <div class="form-group">-->
<!--        <label for="price">가격:</label>-->
<!--        <input type="number" id="price" v-model.number="room.price" class="form-control">-->
<!--      </div>-->
<!--      <button type="submit" class="btn btn-primary">등록</button>-->
<!--    </form>-->
<!--  </div>-->
<!--</template>-->

<!--<script>-->
<!--export default {-->
<!--  data() {-->
<!--    return {-->
<!--      room: {-->
<!--        name: '',-->
<!--        description: '',-->
<!--        price: null,-->
<!--        imageUrl: '/images/room.png', // 기본 이미지 경로-->
<!--      },-->
<!--      imagePreview: null-->
<!--    }-->
<!--  },-->
<!--  methods: {-->
<!--    async createRoom() {-->
<!--      try {-->
<!--        const response = await this.$axios.post('/rooms/create', this.room, {-->
<!--          headers: {-->
<!--            'Content-Type': 'application/json'-->
<!--          }-->
<!--        });-->
<!--        console.log('Room created:', response.data);-->
<!--        this.$router.push('/rooms');-->
<!--      } catch (error) {-->
<!--        console.error('Error creating room:', error);-->
<!--      }-->
<!--    },-->
<!--    previewImage(event) {-->
<!--      const file = event.target.files[0];-->
<!--      this.room.imageUrl = URL.createObjectURL(file);-->
<!--      this.imagePreview = this.room.imageUrl;-->
<!--    }-->
<!--  }-->
<!--}-->
<!--</script>-->

<!--<style scoped>-->
<!--.room-registration {-->
<!--  max-width: 400px;-->
<!--  margin: auto;-->
<!--  padding: 20px;-->
<!--}-->

<!--.login-title {-->
<!--  text-align: center;-->
<!--}-->

<!--.login-form {-->
<!--  background-color: #f8f9fa;-->
<!--  padding: 20px;-->
<!--  border-radius: 5px;-->
<!--}-->

<!--.form-group {-->
<!--  margin-bottom: 15px;-->
<!--}-->

<!--.form-control {-->
<!--  width: 100%;-->
<!--  padding: 10px;-->
<!--  border: 1px solid #ced4da;-->
<!--  border-radius: 3px;-->
<!--}-->

<!--.btn-primary {-->
<!--  width: 100%;-->
<!--  padding: 10px;-->
<!--  background-color: #007bff;-->
<!--  border: none;-->
<!--  border-radius: 3px;-->
<!--  color: #fff;-->
<!--}-->

<!--.btn-primary:hover {-->
<!--  background-color: #0056b3;-->
<!--}-->
<!--</style>-->
