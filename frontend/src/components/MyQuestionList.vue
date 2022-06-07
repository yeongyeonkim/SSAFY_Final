<template>
<v-container>
    <v-layout justify-center class="hidden-sm-and-down">
        <v-flex sm12 md7 my-5 class="fonts">
            <div align="center" class="maintitle"> 나의 질문 </div>
            <v-expansion-panels v-if="mylist.length != 0" class="enmar" popout>
            <v-expansion-panel v-for="n in mylist" :key="n" class="mar">
                <v-expansion-panel-header color="#175bc2" style="color:white" class="htitle">
                    <v-row no-gutters >
                        <v-icon color="#f7b80b" left>mdi-lightbulb-on</v-icon>
                        {{n.company}} / {{n.category}} / {{n.fromwhen}} / {{n.type}}
                    </v-row>
                </v-expansion-panel-header>
                <v-expansion-panel-content class="mar">
                    <v-row no-gutters>
                    <v-spacer></v-spacer>  
                    <v-col cols="12">
                        <p v-html="n.question" class="title"></p> 
                    </v-col>
                    </v-row>
                    <v-row >
                        <v-col v-if="n.approval === 'yes'" cols="6">
                            <div> 승인 상태 : 승인 </div>
                        </v-col>
                        <v-col v-if="n.approval === 'yes'" cols="6">
                                <router-link :to="{ name: 'Solution', params: { id: n.id }}">
                                    <v-btn color="#175bc2" @click="go(n.id)" style="color:white" class="btn"> 자세히 </v-btn>
                                </router-link>
                        </v-col>
                        <v-col v-if="n.approval === 'no'">
                            <div> 승인 상태 : 거절 </div>
                            <div> 거절 이유 : {{n.reject_reason}} </div>
                        </v-col>
                        <v-col v-if="n.approval === 'notyet'">
                            <div> 승인 상태 : 진행중 </div>
                        </v-col>
                    </v-row>
                </v-expansion-panel-content>
                </v-expansion-panel>
            </v-expansion-panels>
            <v-row align="center" justify="center">
            <v-card v-if="mylist.length === 0" elevation="0" class="no">
                등록된 질문이 없습니다
                <v-img src="@/assets/no.png"/>
            </v-card>
            </v-row>
        </v-flex>
    </v-layout>
    <v-layout justify-center class="hidden-md-and-up">
        <v-flex sm12 md7 my-5 class="fonts">
            <div align="center" class="maintitle"> 나의 질문 </div>
            <v-expansion-panels v-if="mylist.length != 0" class="enmar" popout>
            <v-expansion-panel v-for="n in mylist" :key="n" class="mar">
                <v-expansion-panel-header color="#175bc2" style="color:white" class="htitle">
                    <v-row no-gutters >
                        <v-icon color="#f7b80b" left>mdi-lightbulb-on</v-icon>
                        {{n.company}}
                    </v-row>
                </v-expansion-panel-header>
                <v-expansion-panel-content class="mar">
                    <v-row>
                        {{n.category}} / {{n.fromwhen}} / {{n.type}}
                    </v-row>
                    <v-row class="mar2">
                    <v-divider/>
                    </v-row>
                    <v-row no-gutters>
                    <v-spacer></v-spacer>  
                    <v-col cols="12">
                        <p v-html="n.question" class="title"></p>  
                    </v-col>
                    </v-row>
                    <v-row>
                        <v-col v-if="n.approval === 'yes'" cols="6">
                            <div> 승인 상태 : 승인 </div>
                        </v-col>
                        <v-col v-if="n.approval === 'yes'" cols="6">
                                <router-link :to="{ name: 'Solution', params: { id: n.id }}">
                                    <v-btn color="#175bc2" @click="go(n.id)" style="color:white" class="btn"> 자세히 </v-btn>
                                </router-link>
                        </v-col>
                        <v-col v-if="n.approval === 'no'">
                            <div> 승인 상태 : 거절 </div>
                            <div> 거절 이유 : {{n.reject_reason}} </div>
                        </v-col>
                        <v-col v-if="n.approval === 'notyet'">
                            <div> 승인 상태 : 진행중 </div>
                        </v-col>
                    </v-row>
                </v-expansion-panel-content>
                </v-expansion-panel>
            </v-expansion-panels>
            <v-row align="center" justify="center">
            <v-card v-if="mylist.length === 0" elevation="0" class="no">
                등록된 질문이 없습니다
                <v-img src="@/assets/no.png"/>
            </v-card>
            </v-row>
        </v-flex>
    </v-layout>
</v-container>
</template>

<style scoped>
.maincontent{
    font-size: 50px;
}
.htitle{
    font-family: "Do Hyeon", sans-serif;
    font-size: 20px;
}
.mar{
    margin-top: 30px;
}
.mar2{
    margin-bottom: 30px;
}
.enmar{
    margin-top: 30px;
    margin-bottom: 300px;
}
.btn{
    align-self: center;
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
</style>
<script>
import http from "../http-common.js";

export default {
    components : { 

    },
    data() { 
        return {
            mylist:[],
            loading:true,
            errored:false,
            qid:"",
            qreason:"",
            uid:"",
        } 
    },
    mounted(){
        this.uid=sessionStorage.getItem('id')
        console.log(this.uid)
        http
        .get("/interview/my/" + this.uid)
        .then(response=>{
            this.mylist = response.data
            console.log(this.mylist)
        })
    },
    methods:{
        go(idx){
            console.log(idx)
            sessionStorage.setItem("quizId", idx);
        }
    }
}; 
</script>