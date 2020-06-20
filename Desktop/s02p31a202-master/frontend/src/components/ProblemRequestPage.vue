<template>
<v-container>
  <v-layout justify-center align-center class="hidden-sm-and-down">
    <v-flex md7 my-5 class="fonts">
      <div align="center" class="maintitle">질문 승인</div>
      <v-expansion-panels v-if="notyet.length != 0" popout class="bottom">
        <v-expansion-panel v-for="n in notyet" :key="n" class="margin">
          <v-expansion-panel-header class="panheader" style="color:white">
            <v-row no-gutters>
              <v-col cols="12">
                <v-icon style="color:#f7b80b"> mdi-book-open-page-variant</v-icon>
                {{" "}} {{ n.company }} / {{ n.category }} / {{ n.fromwhen }} / {{ n.type }}
                </v-col>
            </v-row>
          </v-expansion-panel-header>
          <v-expansion-panel-content class="mar">
            <v-row no-gutters>
              <v-spacer></v-spacer>
              <v-col cols="12">
                <p v-html="n.question"></p>
              </v-col>
            </v-row>

            <v-card-actions>
              <v-spacer></v-spacer>
              <v-dialog v-model="dialog" width="500">
                <template v-slot:activator="{ on }">
                  <v-btn color="#175bc2" dark v-on="on">거절</v-btn>
                </template>

                <v-card>
                  <v-card-title
                    class="panheader"
                    primary-title
                    style="color:white"
                    
                  >
                    거절 사유
                  </v-card-title>
                  <v-divider></v-divider>
                  <v-select
                    :items="reasons"
                    label="거절 사유"
                    outline
                    v-model="qreason"
                    class="fonts"
                  ></v-select>
                  <v-card-actions>
                    <v-spacer></v-spacer>
                    <v-btn
                      color="#175bc2"
                      flat
                      @click="(dialog = false), reject(n.id)"
                      style="color:white"
                      class="fonts"
                    >
                      확인
                    </v-btn>
                  </v-card-actions>
                </v-card>
              </v-dialog>
              <v-btn color="#175bc2" @click="ok(n.id)" style="color:white">승인</v-btn>
            </v-card-actions>
          </v-expansion-panel-content>
        </v-expansion-panel>
      </v-expansion-panels>
      <v-row align="center" justify="center">
        <v-card v-if="notyet.length === 0" elevation="0" class="no">
          등록된 질문이 없습니다
          <v-img src="@/assets/no.png" />
        </v-card>
      </v-row>
    </v-flex>
  </v-layout>
  <v-layout justify-center align-center class="hidden-md-and-up">
    <v-flex sm12 my-5 class="fonts">
      <div align="center" class="maintitle">질문 승인</div>
      <v-expansion-panels v-if="notyet.length != 0" popout class="mbottom">
        <v-expansion-panel v-for="n in notyet" :key="n" class="mmargin">
          <v-expansion-panel-header class="mpanheader" style="color:white">
            <v-row no-gutters>
              <v-col cols="12">
                <v-icon style="color:#f7b80b"> mdi-book-open-page-variant</v-icon>
                {{" "}} {{ n.company }} 
              </v-col>
            </v-row>
          </v-expansion-panel-header>
          <v-expansion-panel-content class="mmar">
            <v-row>
              {{ n.category }} / {{ n.fromwhen }} / {{ n.type }}
            </v-row>
            <v-row>
              <v-divider/>
            </v-row>
            <v-row no-gutters>
              <v-spacer></v-spacer>
              <v-col cols="12">
                <p v-html="n.question"></p>
              </v-col>
            </v-row>

            <v-card-actions>
              <v-spacer></v-spacer>
              <v-dialog v-model="dialog" width="500">
                <template v-slot:activator="{ on }">
                  <v-btn color="#175bc2" dark v-on="on">거절</v-btn>
                </template>

                <v-card>
                  <v-card-title
                    class="panheader"
                    primary-title
                    style="color:white"
                  >
                    거절 사유
                  </v-card-title>
                  <v-divider></v-divider>
                  <v-select
                    :items="reasons"
                    label="거절 사유"
                    outline
                    v-model="qreason"
                    class="fonts"
                  ></v-select>
                  <v-card-actions>
                    <v-spacer></v-spacer>
                    <v-btn
                      color="#175bc2"
                      flat
                      @click="(dialog = false), reject(n.id)"
                      style="color:white"
                      class="fonts"
                    >
                      확인
                    </v-btn>
                  </v-card-actions>
                </v-card>
              </v-dialog>
              <v-btn color="#175bc2" @click="ok(n.id)" style="color:white">승인</v-btn>
            </v-card-actions>
          </v-expansion-panel-content>
        </v-expansion-panel>
      </v-expansion-panels>
      <v-row align="center" justify="center">
        <v-card v-if="notyet.length === 0" elevation="0" class="no">
          등록된 질문이 없습니다
          <v-img src="@/assets/no.png" />
        </v-card>
      </v-row>
    </v-flex>
  </v-layout>
</v-container>
</template>

<style scoped>
.mbottom{
  margin-bottom: 300px;
  width: 100%;
}
.mmargin{
  margin-bottom: 20px;
}
.mmar{
  margin-top: 20px;
}
.mpanheader{
  background-color: #175bc2;
  font-size: 15px;
  font-family: "Do Hyeon", sans-serif;
  text-align: center;
}
.bottom{
  margin: 0 auto;
  margin-bottom: 300px;
}
.margin{
  margin-bottom: 20px;
}
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
.maintitle {
  font-family: "Do Hyeon", sans-serif;
  font-size: 50px;
  margin-top: 30px;
  margin-bottom: 50px;

}
.no {
  font-family: "Do Hyeon", sans-serif;
  font-size: 30px;
  margin-top: 20%;
}
.fonts {
  font-family: "Do Hyeon", sans-serif;
}
</style>
<script>
import http from "../http-common.js";

export default {
  components: {},
  data() {
    return {
      reasons: ["중복된 질문", "부적절한 질문", "정보 부족", "불법 광고"],
      notyet: [],
      loading: true,
      errored: false,
      qid: "",
      qreason: "",
    };
  },
  mounted() {
    http.get("/interview/unapproved").then((response) => {
      this.notyet = response.data;
    });
  },
  methods: {
    ok(id) {
      http
        .put("/interview/approve/" + id)
        .then((response) => {
          alert("질문이 승인되었습니다");
          this.$router.go();
          console.log(response.data);
        })
        .catch(() => {
          this.errored = true;
        })
        .finally(() => {
          this.loading = false;
        });
    },
    reject(id) {
      http
        .put("/interview/reject", {
          id: id,
          reject_reason: this.qreason,
        })
        .then((response) => {
          alert("질문이 승인이 거절되었습니다");
          this.$router.go();
          console.log(response.data);
        })
        .catch(() => {
          this.errored = true;
        })
        .finally(() => {
          this.loading = false;
        });
    },
  },
};
</script>
