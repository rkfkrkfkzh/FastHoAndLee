<template>
  <div>
    <h1>객실 등록</h1>
    <form @submit.prevent="createRoom">
      <label for="name">객실 이름:</label>
      <input type="text" id="name" v-model="room.name" required>
      <button type="submit">등록</button>
    </form>
  </div>
</template>

<script>
export default {
  data() {
    return {
      room: {
        name: ''
      }
    }
  },
  methods: {
    async createRoom() {
      try {
        await this.$axios.post('/rooms/create', this.room)
        this.$router.push('/rooms')
      } catch (error) {
        console.error('Error creating room:', error)
      }
    }
  }
}
</script>
