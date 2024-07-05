<!-- App.vue -->
<template>
  <div>
    <HeaderComponent :isLoggedIn="isLoggedIn" @logout="logout" />
    <div class="main-content">
      <router-view />
    </div>
    <FooterComponent />
  </div>
</template>

<script>
import HeaderComponent from "./components/HeaderComponent.vue"
import FooterComponent from './components/FooterComponent.vue'

export default {
  name: 'App',
  components: {
    HeaderComponent,
    FooterComponent
  },
  computed: {
    isLoggedIn() {
      return this.$store.state.isLoggedIn;
    }
  },
  methods: {
    logout() {
      this.$store.dispatch('logout');
    }
  },
  mounted() {
    this.$store.dispatch('checkLoginStatus');
  }
}
</script>

<style>
.main-content {
  padding-bottom: 100px; /* Footer의 높이만큼 하단 여백 추가 */
}</style>
