<template>
  <v-layout justify-center>
    <v-flex sm12 md7 my-5 class="fonts">
      <div align="center" class="maintitle">질문 등록</div>
      <v-card elevation="0">
        <v-layout justify-center>
          <v-flex sm10 md8 ma-1 pa-1>
            <v-row justify="center">
              <v-col>
                <v-select :items="items" label="기업형태" v-model="qcompanytype" outlined></v-select>
              </v-col>
            </v-row>
            <v-row>
              <v-col>
                <v-text-field label="기업명" outlined v-model="qcompany"></v-text-field>
              </v-col>
            </v-row>
            <v-row>
              <v-col>
                <v-select :items="category" label="카테고리" v-model="qcategory" outlined></v-select>
              </v-col>
            </v-row>
            <v-row>
              <v-col>
                <v-select :items="years" label="년도" v-model="qfromwhen" outlined></v-select>
              </v-col>
              <v-col>
                <v-select :items="kinds" label="종류" v-model="qtype" outlined></v-select>
              </v-col>
            </v-row>
            <v-row>
              <v-col>
                <vue-editor v-model="qquestion"></vue-editor>
              </v-col>
            </v-row>
          </v-flex>
        </v-layout>
      </v-card>
      <v-layout justify-center>
        <v-flex sm12 md7>
          <v-row justify="center">
            <v-btn color="primary" @click="addQuestion()">요청하기</v-btn>
          </v-row>
        </v-flex>
      </v-layout>
    </v-flex>
  </v-layout>
</template>

<style scoped>
.maintitle {
  font-family: "Do Hyeon", sans-serif;
  font-size: 50px;
  margin-top: 3%;
}
.fonts {
  font-family: "Do Hyeon", sans-serif;
}
</style>
<script>
// @ is an alias to /src
import http from "../http-common.js";

export default { 
    components : { 

    },
    data() { 
        return {
            items: ["대기업", "중견기업", "중소기업", "강소기업", "공기업","기타"],
            category:["운영체제", "네트워크", "알고리즘", "자료구조", "컴퓨터구조", "데이터베이스", "웹", "기타"],
            years:["2010","2011","2012","2013","2014","2015","2016","2017","2018","2019","2020"],
            kinds:["상반기","하반기","특별채용","수시채용","기타"],
            qcompany:"",
            qcompanytype:"",
            qfromwhen:"",
            qtype:"",
            qcategory:"",
            qquestion:"",
            errored:false,
            loading:true,
            uid:""
        } 
    },
    methods:{
        addQuestion(){           
            http
            .post('/interview/insert/' + this.uid,{
                company: this.qcompany,
                companytype: this.qcompanytype,
                fromwhen: this.qfromwhen,   //  코드 내용
                type: this.qtype,    //  코드 문법이 모드 형태로 들어감 ex) text/x-c++src
                category: this.qcategory,
                question: this.qquestion
                      //  태그 입력할 땐 문자열 형태로 들어감
            })
            .then(response =>{
                if(response.data.question != null){
                    alert('질문 작성 완료!')
                    this.$router.push('/quiz')
                }
            })
            .catch(() => {
                alert('질문 작성 중 에러 발생!!')
                this.errored = true;
            })
            .finally(() => {
                this.loading = false;
            })

        }
    },
    mounted(){
        this.uid=sessionStorage.getItem('id')
    }
  }
</script>