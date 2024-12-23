"use strict";(self["webpackChunkfrontend"]=self["webpackChunkfrontend"]||[]).push([[868],{5868:function(t,e,o){o.r(e),o.d(e,{default:function(){return u}});var r=function(){var t=this,e=t._self._c;return e("div",{staticClass:"container mt-5"},[t.room?e("div",{staticClass:"card mb-4"},[e("img",{staticClass:"card-img-top",staticStyle:{height:"300px","object-fit":"cover",width:"100%"},attrs:{src:t.getImageUrl(t.room.imageUrl),alt:"Room Image"}}),e("div",{staticClass:"card-body"},[e("h5",{staticClass:"card-title"},[t._v(t._s(t.room.name))]),e("p",{staticClass:"card-text"},[t._v("금액: "+t._s(t.formatPrice(t.room.price))+"원")]),e("p",{staticClass:"card-text"},[t._v(t._s(t.room.description))]),e("div",{staticClass:"form-group"},[e("label",{attrs:{for:"checkIn"}},[t._v("체크인:")]),e("input",{directives:[{name:"model",rawName:"v-model",value:t.checkIn,expression:"checkIn"}],staticClass:"form-control",attrs:{type:"date",id:"checkIn",min:t.today},domProps:{value:t.checkIn},on:{input:function(e){e.target.composing||(t.checkIn=e.target.value)}}})]),e("div",{staticClass:"form-group"},[e("label",{attrs:{for:"checkOut"}},[t._v("체크아웃:")]),e("input",{directives:[{name:"model",rawName:"v-model",value:t.checkOut,expression:"checkOut"}],staticClass:"form-control",attrs:{type:"date",id:"checkOut",min:t.checkIn||t.today},domProps:{value:t.checkOut},on:{input:function(e){e.target.composing||(t.checkOut=e.target.value)}}})]),e("button",{staticClass:"btn btn-primary btn-lg btn-block mt-3",on:{click:t.reserveRoom}},[t._v("객실 예약하기")])])]):t._e()])},a=[],s=(o(4114),o(2257)),c=o(1910),n={data(){return{room:null,checkIn:"",checkOut:"",today:(new Date).toISOString().split("T")[0]}},computed:{...(0,c.aH)(["isLoggedIn","userId"])},mounted(){const t=this.$route.params.id;s.A.get(`/rooms/detail/${t}`).then((t=>{t.data?(console.log(t.data),this.room=t.data,console.log("Room data:",this.room)):console.error("이미지 URL이 없습니다.")})).catch((t=>{console.error("객실 세부정보를 가져오는 중에 오류가 발생했습니다.\n",t)}))},methods:{getImageUrl(t){return`/${t}`},formatPrice(t){return void 0===t||null===t?"가격 정보 없음":t.toString().replace(/\B(?=(\d{3})+(?!\d))/g,",")},reserveRoom(){if(!this.isLoggedIn)return void alert("로그인이 필요합니다.");if(!this.userId)return console.error("userId가 정의되지 않았습니다."),void alert("로그인이 필요합니다.");const t={roomId:this.room.id,userId:this.userId,checkIn:this.checkIn,checkOut:this.checkOut};console.log("Reservation Data: ",t),s.A.post("/reservations/create",t).then((t=>{console.log("예약 성공 : ",t.data),alert("예약이 성공적으로 완료되었습니다."),this.$router.push("/")})).catch((t=>{console.error("에러 : ",t),alert("예약 중 오류가 발생했습니다. 다시 시도해 주세요.")}))}}},i=n,l=o(1656),d=(0,l.A)(i,r,a,!1,null,null,null),u=d.exports}}]);
//# sourceMappingURL=868.f6618516.js.map