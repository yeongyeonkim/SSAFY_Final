<template>
    <v-layout justify-center>
        <v-flex sm12 md7 my-3>
            <div align="center" class="maintitle"> 면접 일정 </div>
          <v-row sm1 md4>
              <v-expansion-panels>
                  <v-expansion-panel class ="fonts">
                      <v-expansion-panel-header class="panheader" style="color:white">
                          <v-row align="center" justify="center">
                          <v-icon color="#f7b80b">mdi-lead-pencil</v-icon>
                          면접 일정 등록하기
                          </v-row>
                          </v-expansion-panel-header>
                        <v-expansion-panel-content class="mar">
                            <v-row>
                             <v-col vols="2"/>
              <v-col cols="4" md3>
                  <v-menu
                        v-model="menu2"
                        :close-on-content-click="false"
                        :nudge-right="40"
                        transition="scale-transition"
                        offset-y
                        min-width="290px"
                    >
                        <template v-slot:activator="{ on }">
                        <v-text-field
                            v-model="date"
                            label="면접 날짜를 선택해 주세요"
                            prepend-icon="event"
                            
                            v-on="on"
                            outlined
                        ></v-text-field>
                        </template>
                        <v-date-picker v-model="date" @input="menu2 = false"></v-date-picker>
                    </v-menu>
                   <v-text-field
                        label="기업명"
                        outlined
                        v-model="jcompany"
                    ></v-text-field>
                     <v-text-field
                        label="장소"
                        outlined
                        v-model="jplace"
                    ></v-text-field>
              </v-col>
              <v-col cols="4">
                  <v-textarea
                    v-model="jmemo"
                    outlined
                    label="메모"
                    counter="40"
                  >
                  </v-textarea>
                  <v-btn color="#0d47a1" style="color:white" large @click="registerInterviewSchedule()"> 등록 </v-btn>
              </v-col>
              <v-col cols="2"/>
                            </v-row>
                        </v-expansion-panel-content>
                  </v-expansion-panel>
              </v-expansion-panels>
             
          </v-row>
          <v-row align="center" justify="center" v-if="schedules.length != 0">
              <v-layout row wrap justify-center >
                  <v-flex md6 v-for="(sc,idx) in schedules" :key="idx">
                       <v-hover>
                    <template v-slot:default="{ hover }">
                    <v-card elevation="0" my-3>
                        <v-img src="@/assets/memo2.jpg" width="350px" class="imgsize">
                         <v-fade-transition>
                          <v-overlay v-if="hover" absolute color="blue darken-4">
                                <v-btn color="blue darken-4" @click="deleteinterview(sc.id)">일정 삭제</v-btn>
                          </v-overlay>
                        </v-fade-transition>
                            <v-card-text class="card"> {{sc.dates}} </v-card-text>
                            <v-card-text class="card-se"> {{sc.company}} </v-card-text>
                            <v-card-text class="card-se"> {{sc.place}} </v-card-text>
                            <div class="card-memo"> {{sc.memo}} </div>
                        </v-img>
                    </v-card>
                    </template>
                       </v-hover>
                  </v-flex>
              </v-layout>
        </v-row>
        <v-row v-if="schedules.length === 0">
            <v-layout justify-center align-center>
                  <v-card elevation="0" class="no">
                    등록된 면접 일정이 없습니다
                <v-img src="@/assets/no.png"/>
            </v-card>
              </v-layout>

        </v-row>
        </v-flex>
    </v-layout>
</template>

<style scoped>
.mar{
    margin: 0 auto;
    margin-top: 20px;
}
.panheader{
  background-color: #175bc2;
  font-size: 20px;
  font-family: "Do Hyeon", sans-serif;
  text-align: center;
}
.imgsize{
    align-self: center;
    justify-self: center;
    margin: 0 auto;
    margin-top: 50px;
}
.maintitle{
    font-family: "Do Hyeon", sans-serif;
    font-size: 50px;
    margin-top: 3%;
}
.no{
    font-family: "Do Hyeon", sans-serif;
    font-size: 30px;
    margin-top: 20%;
}
.fonts{
    font-family: "Do Hyeon", sans-serif;
}
.card{
    font-family: "Do Hyeon", sans-serif;
    padding: 15px;
    border-radius: 3px;
    cursor: pointer;
    transition: all .2s ease;
    margin-bottom: 0px;
    height: 60px;
    font-size: 30px;
    margin-top: 8%;
    text-align: center;
}

.card-se{
    font-family: "Do Hyeon", sans-serif;
    padding: 15px;
    border-radius: 3px;
    cursor: pointer;
    transition: all .2s ease;
    margin-bottom: 0px;
    height: 60px;
    font-size: 40px;
    margin-top: 3%;
    text-align: center;
}

.card-memo{
    font-family: "Do Hyeon", sans-serif;
    padding: 15px;
    border-radius: 3px;
    cursor: pointer;
    transition: all .2s ease;
    margin-bottom: 0px;
    height: 60px;
    font-size: 25px;
    margin-left: 5%;
    margin-top: 3%;
    margin-right: 7%;
    text-align: center;
}
</style>

<script>
import http from "../http-common.js";
  export default {
      data(){
          return {
              date: new Date().toISOString().substr(0, 10),
              menu: false,
              modal: false,
              menu2: false,
              schedules:[],
              jcompany:"",
              jplace:"",
              jmemo:"",
              uid:""
          }
      },
      methods:{
        registerInterviewSchedule(){

            http
            .post('/schedule/insert/' + this.uid,{
                company:this.jcompany,
                dates:this.date,
                place:this.jplace,
                memo:this.jmemo
            })
             .then(response =>{
                console.log(response.data)
                alert('질문 작성 완료!')
                this.$router.push('/mypage')
                
            })
            .catch(() => {
                alert('일정 등록 중 에러 발생!!')
                this.errored = true;
            })
            .finally(() => {
                this.loading = false;
            })
        },
        deleteinterview(id){
            http
            .delete('/schedule/delete/'+ id)
            .then(response=>{
                alert('일정이 삭제되었습니다')
                console.log(response.data)
                this.$router.push('/mypage')
            })
            .catch(() => {
                alert('삭제 실패!')
                this.errored = true;
            })
            .finally(() => {
                this.loading = false;
            })
        }
      },
      mounted(){
          this.uid=sessionStorage.getItem('id')
          http
          .get('/schedule/' + this.uid)
          .then(response=>{
              this.schedules = response.data
              console.log(this.schedules)
          })
          .catch(() => {
                this.errored = true;
            })
            .finally(() => {
                this.loading = false;
            })
      }
  }
</script>