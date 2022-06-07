<template>
  <v-container fluid>
    <v-layout justify-center class="hidden-sm-and-down">
      <v-flex md9>
        <v-row>
          <v-col cols="3">
            <v-row>
              <v-card raised height="200px" width="200px" class="mar">
                <v-card-title
                  class="white--text justify-center font font-size-title"
                >
                  <v-icon left color="#f7b80b"> mdi-lead-pencil </v-icon>
                  내가 푼 문제
                </v-card-title>
                <v-card class="ma-3" elevation="0">
                  <v-row align="center" justify="center">
                    <v-card-title class="menu-font">
                      {{ this.mylist.length }} / {{ this.interviewlist.length }}
                    </v-card-title>
                  </v-row>
                </v-card>
              </v-card>
            </v-row>
            <v-row>
              <v-card raised height="200px" width="200px" class="mar">
                <v-card-title
                  class="white--text justify-center font font-size-title"
                >
                  <v-icon left color="#f7b80b"> mdi-folder-star</v-icon>
                  내가 찜한 문제
                </v-card-title>
                <v-card class="ma-3" elevation="0">
                  <v-row align="center" justify="center">
                    <v-card-title class="menu-font">
                      {{ this.jjim.length }} 개
                    </v-card-title>
                  </v-row>
                </v-card>
              </v-card>
            </v-row>
          </v-col>
          <v-col cols="6">
            <v-card raised height="450px" width="400px" class="mar">
              <v-card-title
                class="white--text justify-center font font-size-title"
              >
                <v-icon left color="#f7b80b">
                  mdi-badge-account-horizontal-outline
                </v-icon>
                내 프로필
              </v-card-title>
              <v-card class="ma-3" elevation="0">
                <v-row align="center" justify="center">
                  <v-card-title class="profile">
                    {{ this.userid }}
                  </v-card-title>
                </v-row>
                <v-row>
                  <v-avatar color="white" size="150" class="profile">
                    <v-img src="@/assets/profile2.png" />
                  </v-avatar>
                </v-row>
                <v-row>
                  <v-card-subtitle class="profile">
                    {{ this.uemail }}
                  </v-card-subtitle>
                </v-row>
                <v-row>
                  <v-btn
                    class="btnal"
                    style="color:white"
                    color="#175bc2"
                    large
                    @click="gotomylist()"
                  >
                    내가 등록한 문제
                  </v-btn>
                </v-row>
              </v-card>
            </v-card>
          </v-col>
          <v-col cols="3">
            <v-card raised height="430px" width="200px" class="mar">
              <v-card-title
                class="white--text justify-center font font-size-title"
              >
                <v-icon left color="#f7b80b"> mdi-calendar-account </v-icon>
                면접 일정
              </v-card-title>
              <v-card class="ma-3" elevation="0">
                <v-row align="center" justify="center">
                  <v-col cols="12">
                    <v-row align="center" justify="center">
                      <v-card-text class="day">
                        {{
                          this.schedules[idx] && this.schedules[idx].dates
                        }}</v-card-text
                      >
                    </v-row>
                    <v-row align="center" justify="center">
                      <v-card-text class="company">
                        {{ this.schedules[idx] && this.schedules[idx].company }}
                      </v-card-text>
                    </v-row>
                    <v-row align="center" justify="center">
                      <v-card-text class="place">
                        {{ this.schedules[idx] && this.schedules[idx].place }}
                      </v-card-text>
                    </v-row>
                    <v-row align="center" justify="center">
                      <v-card-text class="dday" style="color:red">
                        D - {{ this.dday }}
                      </v-card-text>
                    </v-row>
                    <v-card-title>
                      <v-btn
                        class="btnal"
                        style="color:white"
                        color="#175bc2"
                        large
                        @click="gotoschedule()"
                      >
                        일정관리
                      </v-btn>
                    </v-card-title>
                  </v-col>
                </v-row>
              </v-card>
            </v-card>
          </v-col>
        </v-row>
        <v-row>
          <v-col cols="6">
            <v-card raised height="470px" width="400px" class="mar2">
              <v-card-title
                class="white--text justify-center font font-size-title"
              >
                <v-icon left color="#f7b80b"> mdi-chart-areaspline </v-icon>
                내가 많이 푼 문제 유형
              </v-card-title>
              <v-card class="ma-3" elevation="0">
                <chartjs-doughnut
                  v-bind:labels="labels"
                  v-bind:datasets="datasets"
                  v-bind:option="option"
                  style="color:white"
                  width="200px"
                  height="200px"
                ></chartjs-doughnut>
              </v-card>
            </v-card>
          </v-col>
          <v-col cols="6">
            <v-card
              raised
              height="450px"
              width="400px"
              elevation="0"
              class="mar2"
            >
              <v-expansion-panels class="font">
                <v-expansion-panel>
                  <v-expansion-panel-header
                    class="panheader"
                    style="color:white"
                  >
                    <v-row align="center" justify="center">
                      <v-icon left color="#f7b80b"> mdi-lock-reset </v-icon>
                      비밀번호 변경
                    </v-row>
                  </v-expansion-panel-header>
                  <v-expansion-panel-content class="pass">
                    <v-text-field
                      label="email"
                      outlined
                      v-model="cemail"
                    ></v-text-field>
                    <v-text-field
                      label="현재 비밀번호"
                      outlined
                      v-model="cnow"
                      :type="showPassword ? 'text' : 'password'"
                      :append-icon="showPassword ? 'mdi-eye' : 'mdi-eye-off'"
                      autocomplete="off"
                      prepend-icon="mdi-lock"
                      @click:append="showPassword = !showPassword"
                    ></v-text-field>
                    <v-text-field
                      label="새 비밀번호"
                      outlined
                      v-model="cnew1"
                      :type="showPassword2 ? 'text' : 'password'"
                      :append-icon="showPassword2 ? 'mdi-eye' : 'mdi-eye-off'"
                      autocomplete="off"
                      prepend-icon="mdi-lock"
                      @click:append="showPassword2 = !showPassword2"
                    ></v-text-field>
                    <v-text-field
                      label="새 비밀번호 확인"
                      outlined
                      v-model="cnew2"
                      :type="showPassword3 ? 'text' : 'password'"
                      :append-icon="showPassword3 ? 'mdi-eye' : 'mdi-eye-off'"
                      autocomplete="off"
                      prepend-icon="mdi-lock"
                      @click:append="showPassword3 = !showPassword3"
                    ></v-text-field>

                    <v-btn
                      color="#0d47a1"
                      style="color:white"
                      large
                      @click="changePassword()"
                      class="epcolor"
                    >
                      비밀번호 변경
                    </v-btn>
                  </v-expansion-panel-content>
                </v-expansion-panel>
              </v-expansion-panels>
            </v-card>
          </v-col>
        </v-row>
      </v-flex>
    </v-layout>
    <!-- 모바일 화면 -->
    <v-layout justify-center class="hidden-md-and-up">
      <v-flex sm12>
        <v-row align="center">
          <v-col cols="12">
            <v-card raised height="460px" width="400px" class="mar">
              <v-card-title
                class="white--text justify-center font font-size-title"
              >
                <v-icon left color="#f7b80b">
                  mdi-badge-account-horizontal-outline
                </v-icon>
                내 프로필
              </v-card-title>
              <v-card class="ma-3" elevation="0">
                <v-row align="center" justify="center">
                  <v-card-title class="profile">
                    {{ this.userid }}
                  </v-card-title>
                </v-row>
                <v-row>
                  <v-avatar color="white" size="150" class="profile">
                    <v-img src="@/assets/profile2.png" />
                  </v-avatar>
                </v-row>
                <v-row>
                  <v-card-subtitle class="profile">
                    {{ this.uemail }}
                  </v-card-subtitle>
                </v-row>
                <v-row>
                  <v-btn
                    class="btnal"
                    style="color:white"
                    color="#175bc2"
                    large
                    @click="gotomylist()"
                  >
                    내가 등록한 문제
                  </v-btn>
                </v-row>
              </v-card>
            </v-card>
          </v-col>
        </v-row>
        <v-row align="center">
          <v-col cols="12">
            <v-card raised elevation="0" class="mar">
              <v-expansion-panels class="font">
                <v-expansion-panel>
                  <v-expansion-panel-header
                    class="panheader"
                    style="color:white"
                  >
                    <v-row align="center" justify="center">
                      <v-icon left color="#f7b80b"> mdi-lock-reset </v-icon>
                      비밀번호 변경
                    </v-row>
                  </v-expansion-panel-header>
                  <v-expansion-panel-content class="pass">
                    <v-text-field
                      label="email"
                      outlined
                      v-model="cemail"
                    ></v-text-field>
                    <v-text-field
                      label="현재 비밀번호"
                      outlined
                      v-model="cnow"
                      :type="showPassword ? 'text' : 'password'"
                      :append-icon="showPassword ? 'mdi-eye' : 'mdi-eye-off'"
                      autocomplete="off"
                      prepend-icon="mdi-lock"
                      @click:append="showPassword = !showPassword"
                    ></v-text-field>
                    <v-text-field
                      label="새 비밀번호"
                      outlined
                      v-model="cnew1"
                      :type="showPassword2 ? 'text' : 'password'"
                      :append-icon="showPassword2 ? 'mdi-eye' : 'mdi-eye-off'"
                      autocomplete="off"
                      prepend-icon="mdi-lock"
                      @click:append="showPassword2 = !showPassword2"
                    ></v-text-field>
                    <v-text-field
                      label="새 비밀번호 확인"
                      outlined
                      v-model="cnew2"
                      :type="showPassword3 ? 'text' : 'password'"
                      :append-icon="showPassword3 ? 'mdi-eye' : 'mdi-eye-off'"
                      autocomplete="off"
                      prepend-icon="mdi-lock"
                      @click:append="showPassword3 = !showPassword3"
                    ></v-text-field>

                    <v-btn
                      color="#0d47a1"
                      style="color:white"
                      large
                      @click="changePassword()"
                      class="epcolor"
                    >
                      비밀번호 변경
                    </v-btn>
                  </v-expansion-panel-content>
                </v-expansion-panel>
              </v-expansion-panels>
            </v-card>
          </v-col>
        </v-row>
        <v-row align="center">
          <v-col cols="12">
            <v-card raised height="200px">
              <v-card-title
                class="white--text justify-center font font-size-title"
              >
                <v-icon left color="#f7b80b"> mdi-lead-pencil </v-icon>
                내가 푼 문제
              </v-card-title>
              <v-card class="mcontent" elevation="0">
                <v-row align="center" justify="center">
                  <v-card-title class="menu-font">
                    {{ this.mylist.length }} / {{ this.interviewlist.length }}
                  </v-card-title>
                </v-row>
              </v-card>
            </v-card>
          </v-col>
        </v-row>
        <v-row align="center" justify="center">
          <v-col cols="12">
            <v-card raised height="200px">
              <v-card-title
                class="white--text justify-center font font-size-title"
              >
                <v-icon left color="#f7b80b"> mdi-folder-star</v-icon>
                내가 찜한 문제
              </v-card-title>
              <v-card class="ma-3 mcontent" elevation="0">
                <v-row align="center" justify="center">
                  <v-card-title class="menu-font">
                    {{ this.jjim.length }} 개
                  </v-card-title>
                </v-row>
              </v-card>
            </v-card>
          </v-col>
        </v-row>
        <v-row align="center">
          <v-col cols="12">
            <v-card raised class="mar">
              <v-card-title
                class="white--text justify-center font font-size-title"
              >
                <v-icon left color="#f7b80b"> mdi-calendar-account </v-icon>
                면접 일정
              </v-card-title>
              <v-card class="ma-3" elevation="0">
                <v-row align="center" justify="center">
                  <v-col cols="12">
                    <v-row align="center" justify="center">
                      <v-card-text class="day">
                        {{
                          this.schedules[idx] && this.schedules[idx].dates
                        }}</v-card-text
                      >
                    </v-row>
                    <v-row align="center" justify="center">
                      <v-card-text class="company">
                        {{ this.schedules[idx] && this.schedules[idx].company }}
                      </v-card-text>
                    </v-row>
                    <v-row align="center" justify="center">
                      <v-card-text class="place">
                        {{ this.schedules[idx] && this.schedules[idx].place }}
                      </v-card-text>
                    </v-row>
                    <v-row align="center" justify="center">
                      <v-card-text class="dday" style="color:red">
                        D - {{ this.dday }}
                      </v-card-text>
                    </v-row>
                    <v-card-title>
                      <v-btn
                        class="btnal"
                        style="color:white"
                        color="#175bc2"
                        x-large
                        @click="gotoschedule()"
                      >
                        일정관리
                      </v-btn>
                    </v-card-title>
                  </v-col>
                </v-row>
              </v-card>
            </v-card>
          </v-col>
        </v-row>
        <v-row align="center">
          <v-col cols="12">
            <v-card raised class="mar">
              <v-card-title
                class="white--text justify-center font font-size-title"
              >
                <v-icon left color="#f7b80b"> mdi-chart-areaspline </v-icon>
                내가 많이 푼 문제 유형
              </v-card-title>
              <v-card class="ma-3" elevation="0">
                <chartjs-doughnut
                  v-bind:labels="labels"
                  v-bind:datasets="datasets"
                  v-bind:option="option"
                  style="color:white"
                  width="150px"
                  height="150px"
                ></chartjs-doughnut>
              </v-card>
            </v-card>
          </v-col>
        </v-row>
      </v-flex>
    </v-layout>
  </v-container>
</template>

<script>
import http from "../http-common.js";
import randomColor from "randomcolor";

export default {
  components: {},
  data() {
    return {
      model: null,
      selectedTab: null,

      backgroundColor: [
        "Red",
        "Orange",
        "Yellow",
        "Green",
        "Blue",
        "Purple",
        "pink",
        "grey",
      ],
      schedules: [],
      interviewlist: [],
      dday: "",
      uid: "",
      idx: "",
      userid: "",
      mylist: [],
      jjim: [],

      cemail: "",
      cnow: "",
      cnew1: "",
      cnew2: "",
      result: "",
      uemail: "",
      showPassword: false,
      showPassword2: false,
      showPassword3: false,
      labels: [
        "알고리즘",
        "컴퓨터구조",
        "자료구조",
        "데이터베이스",
        "기타",
        "네트워크",
        "운영체제",
        "웹",
      ],
      datasets: [
        {
          data: [],
          backgroundColor: [
            randomColor(),
            randomColor(),
            randomColor(),
            randomColor(),
            randomColor(),
            randomColor(),
            randomColor(),
            randomColor(),
          ],
          hoverBackgroundColor: "#6895e8",
        },
      ],
      option: {
        legend: {
          labels: {
            fontColor: "white",
            fontSize: 10,
          },
          display: false, // 라벨 표시할때는 true로 바꾸면 됨
        },
        title: {
          text: "내가 많이 푼 문제 유형",
          display: false,
          fontColor: "pink",
          fontSize: 15,
        },
      },
    };
  },
  methods: {
    selectTab() {
      this.selectedTab = this.tab.title;
      console.log(123);
    },
    gotomylist() {
      this.$router.push("/MyQuestionList");
    },
    gotoschedule() {
      this.$router.push("/interviewSchedule");
    },
    changePassword() {
      http
        .get("/user/changecheck/" + this.cemail + "/" + this.cnow)
        .then((response) => {
          this.result = response.data;
          if (response.data === true) {
            if (this.cnew1 === this.cnew2) {
              http
                .put("/user/update", {
                  id: this.uid,
                  uid: this.userid,
                  email: this.cemail,
                  password: this.cnew1,
                })
                .then((response) => {
                  console.log(response.data);
                  alert("비밀번호가 변경되었습니다.");
                  this.$router.go();
                })
                .catch(() => {
                  alert("비밀 번호 변경 실패");
                  this.errored = true;
                })
                .finally(() => {
                  this.loading = false;
                });
            } else {
              alert("비밀번호가 일치하지 않습니다.");
            }
          } else {
            alert("이메일 혹은 비밀번호를 확인해 주세요");
          }
        })
        .catch(() => {
          alert("질문 불러오기 실패");
          this.errored = true;
        })
        .finally(() => {
          this.loading = false;
        });
    },
    updateChart() {
      this.$refs.skills_chart.update();
    },
    updateAmount(amount, index) {
      this.chartData.datasets[0].data.splice(index, 1, amount);
      this.updateChart();
    },
    updateName(text, index) {
      this.chartData.labels.splice(index, 1, text);
      this.updateChart();
    },
  },
  mounted() {
    this.uid = sessionStorage.getItem("id");
    this.userid = sessionStorage.getItem("userId");
    this.uemail = sessionStorage.getItem("email");
    http
      .get("/solvecount/" + this.userid)
      .then((response) => {
        this.datasets[0].data = response.data;
      })
      .catch(() => {
        alert("많이푼 유형 불러오기 실패");
        this.errored = true;
      })
      .finally(() => {
        this.loading = false;
      });
    console.log(this.datasets);
    http
      .get("/schedule/" + this.uid)
      .then((response) => {
        this.schedules = response.data;
        var result = 0;
        for (var i = 0; i < this.schedules.length; i++) {
          var strArray = this.schedules[i].dates.split("-");
          var Dday = new Date(strArray[0], (strArray[1] *= 1) - 1, strArray[2]);
          var now = new Date();
          var gap = now.getTime() - Dday.getTime();
          result = Math.floor(gap / (1000 * 60 * 60 * 24)) * -1;
          if (result >= 0) {
            console.log(result);
            this.idx = i;
            break;
          }
        }
        this.dday = result;
      })
      .catch(() => {
        alert("면접 일정을 불러올 수 없습니다");
        this.errored = true;
      })
      .finally(() => {
        this.loading = false;
      }),
      http
        .get("/interview/list")
        .then((response) => {
          this.interviewlist = response.data;
        })
        .catch(() => {
          alert("질문 불러오기 실패");
          this.errored = true;
        })
        .finally(() => {
          this.loading = false;
        }),
      http
        .get("/interview/isolve/" + this.uid)
        .then((response) => {
          this.mylist = response.data;
        })
        .catch(() => {
          alert("내가 푼 문제 불러오기 실패");
          this.errored = true;
        })
        .finally(() => {
          this.loading = false;
        }),
      http
        .get("/user/findall/" + this.uid)
        .then((response) => {
          this.jjim = response.data;
        })
        .catch(() => {
          alert("내가 찜한 문제 불러오기 실패");
          this.errored = true;
        })
        .finally(() => {
          this.loading = false;
        });
  },
  created() {},
};
</script>

<style scoped>
.mcontent {
  margin: 0 auto;
  align-self: center;
  justify-self: center;
}
.mtitle {
  margin-top: 10px;
  margin: 0 auto;
  font-family: "Do Hyeon", sans-serif;
  font-size: 25px;
}
.panheader {
  background-color: #175bc2;
  font-size: 20px;
  font-family: "Do Hyeon", sans-serif;
  text-align: center;
}
.pass {
  margin: 0 auto;
  margin-top: 10px;
}
.chartsize {
  height: 350px;
  width: 350px;
  margin: 0 auto;
}
.profile {
  font-family: "Do Hyeon", sans-serif;
  margin: 0 auto;
  font-size: 30px;
  margin-bottom: 15px;
}
.epcolor {
  font-family: "Do Hyeon", sans-serif;
  margin: 0 auto;
}
.btnal {
  margin: 0 auto;
  font-family: "Do Hyeon", sans-serif;
}
.mar {
  margin: auto;
  margin-top: 15px;
  margin-bottom: 15px;
}
.mar2 {
  margin: auto;
  margin-top: 15px;
  margin-bottom: 15px;
  margin-left: 15px;
  margin-right: 15px;
}
.font-card {
  font-family: "Do Hyeon", sans-serif;
  font-size: 20px;
}
.day {
  font-family: "Do Hyeon", sans-serif;
  font-size: 30px;
  margin: 0 auto;
  align-content: center;
  justify-self: center;
  text-align: center;
}
.place {
  font-family: "Do Hyeon", sans-serif;
  font-size: 25px;
  margin: 0 auto;
  align-content: center;
  justify-self: center;
  text-align: center;
}
.company {
  font-family: "Do Hyeon", sans-serif;
  font-size: 40px;
  margin: 0 auto;
  align-content: center;
  justify-self: center;
  text-align: center;
}
.dday {
  font-family: "Do Hyeon", sans-serif;
  font-size: 45px;
  margin: 0 auto;
  align-content: center;
  justify-self: center;
  text-align: center;
}
.menu-font {
  font-family: "Do Hyeon", sans-serif;
  font-size: 40px;
  margin-top: 10%;
}
.font {
  font-family: "Do Hyeon", sans-serif;
  background-color: #175bc2;
}

.font-size-title {
  font-size: 25px;
}

body {
  font-family: "Roboto", sans-serif;
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

.marginl {
  margin-left: 5px;
}

.margind {
  margin-bottom: 3px;
}

.tab-item {
  background: white;
  border: 0px #d5dadf solid;
  border-left: 5px solid #d5dadf;
  box-shadow: 0 2px 3px rgba(213, 218, 223, 0.35);
  padding: 15px;
  border-radius: 3px;
  cursor: pointer;
  transition: all 0.2s ease;
  margin-bottom: 0px;
  height: 60px;
}

.tab-item:hover,
.tab-item.active {
  box-shadow: 0px 3px 3px 2px rgba(213, 218, 223, 0.35);
  border-left: 5px solid #0d47a1;
}

.tab-item__heading {
  font-weight: bold;
  font-size: 19px;
  line-height: 1.2em;
  letter-spacing: 0.02em;
  color: #175bc2;
  margin: -5px;
  font-family: "Do Hyeon", sans-serif;
}

.tab-item__subheading {
  font-size: 18px;
  color: #333;
  margin: 0px;
}

.tab-content__header {
  color: #175bc2;
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
  transition: all 0.3s ease;
  text-align: center;
  line-height: 1;
  border: 2px solid;
  border-radius: 3px;
  background-color: transparent;
  box-shadow: 0 2px 3px rgba(213, 218, 223, 0.35);
  color: #175bc2;
  fill: #175bc2;
  border-color: #175bc2;
}

.tab-content__btn:hover {
  color: #175bc2;
  text-decoration: none;
  box-shadow: 0px 3px 3px 2px rgba(213, 218, 223, 0.35);
}

.tab-content__testimonial {
  margin-bottom: 15px;
  font-size: 1em;
  color: rgba(0, 0, 0, 0.75);
  font-style: italic;
}

.tab-content__testimonial-author {
  margin-bottom: 5px;
  font-size: 1em;
  color: rgba(0, 0, 0, 0.75);
  font-weight: bold;
}
</style>
