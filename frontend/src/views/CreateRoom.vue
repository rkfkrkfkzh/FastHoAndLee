<!-- views/CreateRoom.vue -->
<template>
  <div>
    <h1>Create Room</h1>
    <form @submit.prevent="createRoom">
      <label for="name">Room Name:</label>
      <input type="text" id="name" v-model="room.name">
      <button type="submit">Create</button>
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
        await this.$axios.post('/rooms', this.room)
        this.$router.push('/')
      } catch (error) {
        console.error('Error creating room:', error)
      }
    }
  }
}
</script>
