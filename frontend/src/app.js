// app.js
import Vue from 'vue'
import HeaderComponent from './components/HeaderComponent.vue'
import FooterComponent from './components/FooterComponent.vue'

new Vue({
    el: '#app',
    components: {
        HeaderComponent,
        FooterComponent
    },
    data() {
        return {
            recentRooms: [
                { id: 1, name: 'Room 1', description: 'Description for Room 1', imageUrl: 'room1.jpg' },
                { id: 2, name: 'Room 2', description: 'Description for Room 2', imageUrl: 'room2.jpg' },
                // 나머지 객실 정보를 추가하세요.
            ]
        }
    }
})
