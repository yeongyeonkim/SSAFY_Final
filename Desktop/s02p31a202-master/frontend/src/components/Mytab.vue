<template>
    <v-container>
        <v-card raised height="300px">
          <v-card-title class="blue darken-4 white--text justify-center font font-size-title">내 정보</v-card-title>
            <div v-for="(tab, idx) in tabs" :key="idx" @click="selectedTab = tab.title" :class="{ active : selectedTab == tab.title }" class="tab-item">
            <v-row>
              <v-col cols="2">
                <v-icon class="tab-item__heading margind" large > {{tab.icon}} </v-icon>
              </v-col>
              <v-col cols="10">
                <div class="tab-item__heading marginl" @click="goEachMenu(tab.link)">{{ tab.title }}</div>
                <p> </p>
              </v-col>
            </v-row>
          </div>
        </v-card>
    </v-container>
</template>


<style scoped>
.font{
  font-family: "Do Hyeon", sans-serif;
}

.font-size-title{
  font-size: 25px;
}

body {
  font-family: 'Roboto', sans-serif;
  padding: 50px;
  background: #fcfcfc;
  height: 50vh;
}

.pen-heading {
  font-weight: bold;
  font-size: 3em;
  text-align: center;
  margin-bottom: 40px;
  color: #3333;
}

.marginl{
  margin-left: 5px;
}

.margind{
  margin-bottom: 3px;
}

.tab-item {
  background: white;
  border: 0px #D5DADF solid;
  border-left: 5px solid #D5DADF;
  box-shadow: 0 2px 3px rgba(213,218,223,0.35);
  padding: 15px;
  border-radius: 3px;
  cursor: pointer;
  transition: all .2s ease;
  margin-bottom: 0px;
  height: 60px;
}

.tab-item:hover, .tab-item.active {
  box-shadow: 0px 3px 3px 2px rgba(213,218,223,0.35);
  border-left: 5px solid #0d47a1;
}

.tab-item__heading {
  font-weight: bold;
  font-size: 19px;
  line-height: 1.2em;
  letter-spacing: 0.02em;
  color: #314f8d;
  margin: -5px;
  font-family: "Do Hyeon", sans-serif;
}

.tab-item__subheading {
  font-size: 18px;
  color: #333;
  margin: 0px;
}

.tab-content__header {
  color: #314f8d;
  font-weight: bold;
   margin: 0px 0px 30px;
  font-size: 36px;
  line-height: 1.3em;
  letter-spacing: 0.02em;
}

.tab-content__text {
  margin: 0px 0px 30px;
  font-size: 1.25em;
}

.tab-content__btn {
  display: inline-block;
  margin-bottom: 30px;
  padding: 16px 30px;
  cursor: pointer;
  text-decoration: none;
  font-size: 14px;
  text-transform: uppercase;
  font-weight: 900;
  position: relative;
  transition: all .3s ease;
  text-align: center;
  line-height: 1;
  border: 2px solid;
  border-radius: 3px;
  background-color: transparent;
  box-shadow: 0 2px 3px rgba(213,218,223,0.35);
  color: #0d47a1;
  fill: #0d47a1;
  border-color: #0d47a1;
}

.tab-content__btn:hover {
  color: #0d47a1;
  text-decoration: none;
  box-shadow: 0px 3px 3px 2px rgba(213,218,223,0.35);
}

.tab-content__testimonial {
  margin-bottom: 15px;
  font-size: 1em;
  color: rgba(0,0,0,.75);
  font-style: italic;
}

.tab-content__testimonial-author {
  margin-bottom: 5px;
  font-size: 1em;
  color: rgba(0,0,0,.75);
  font-weight: bold;
}
</style>
<script>
export default {
  data() {
    return{
      tabs:[
        {
          title: "내 정보 수정",
          icon: "mdi-account",
          
        },
        {
          title: "비밀번호 변경",
          icon:"mdi-key"
        },
        {
          title: "내가 등록한 질문",
          icon:"mdi-star",
          link:"MyQuestionList"
        },
        {
          title: "면접 일정 등록 및 확인",
          icon:"mdi-calendar-edit",
          link:"InterviewSchedule"
        },

      ],
      model: null,
      selectedTab: null,
      
    }
  },
  methods:{
   selectTab() {
      this.selectedTab = this.tab.title;
    },
    gotoday(){
      this.$router.push('/initial')
    },
    goEachMenu(link){
      this.$router.push(link)
    }
  },
  mounted(){
    
  }
}
</script>