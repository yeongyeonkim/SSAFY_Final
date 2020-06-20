<template>
  <v-container fluid class="pa-0 mt-5">
    <v-layout justify-center>
      <v-card raised width="600px" class="mx-12">
        <v-img
          class="align-center"
          src="../assets/background.jpg"
          height="400px"
        >
          <v-row class="ma-0 fill-height" align="center" justify="center">
            <v-card-title class="quiz_question mx-12">
              <p v-html="quiz.question" class="quiz_question mx-12"></p>
            </v-card-title>
          </v-row>
        </v-img>
      </v-card>

      <v-card raised width="600px" class="mx-12 hidden-sm-and-down">
        <v-card-title class="blue darken-4 white--text">
          문제 분석
          <v-spacer />
          <v-btn v-if="pick" class="orange--text" icon @click="togglePick()">
            <v-icon large>mdi-book-open-page-variant</v-icon>
          </v-btn>
          <v-btn v-else class="black--text" icon @click="togglePick()">
            <v-icon>mdi-book-open-page-variant</v-icon>
          </v-btn>
        </v-card-title>
        <v-snackbar top v-model="quizRegSnackbar" :timeout="timeout">
          {{ snackText }}
          <v-btn color="blue" text @click="quizRegSnackbar = false"
            >닫 기</v-btn
          >
        </v-snackbar>
        <v-snackbar top v-model="snackbar" :timeout="timeout">
          {{ text }}
          <v-btn color="blue" text @click="snackbar = false">닫 기</v-btn>
        </v-snackbar>
        <v-row align="center" justify="center">
          <v-col cols="12" sm="6">
            <v-text-field
              v-model="quiz.category"
              rounded
              outlined
              prepend-icon="mdi-desktop-classic"
              dense
              readonly
              single-line
            ></v-text-field>
            <v-text-field
              v-model="time"
              rounded
              outlined
              prepend-icon="mdi-calendar-multiple-check"
              dense
              readonly
              single-line
            ></v-text-field>
            <v-text-field
              v-model="quiz.company"
              rounded
              outlined
              prepend-icon="mdi-sim"
              dense
              readonly
              single-line
            ></v-text-field>
            <v-text-field
              v-model="type"
              rounded
              outlined
              prepend-icon="mdi-briefcase"
              dense
              readonly
              single-line
            ></v-text-field>
            <v-row justify="center">
              <v-chip class="mr-2">인턴</v-chip>
              <v-chip class="mr-2 white--text" color="blue darken-4"
                >신입</v-chip
              >
              <v-chip>경력</v-chip>
            </v-row>
          </v-col>
        </v-row>
        <v-spacer />
      </v-card>
    </v-layout>

    <v-layout>
      <v-col></v-col>
    </v-layout>

    <!-- 모바일 기준 -->
    <v-layout class="hidden-md-and-up">
      <v-col class="mt-3">
        <v-card raised width="600px">
          <v-card-title class="blue darken-4 white--text">
            문제 분석
            <v-spacer />
            <v-btn v-if="pick" class="orange--text" icon @click="togglePick()">
              <v-icon large>mdi-book-open-page-variant</v-icon>
            </v-btn>
            <v-btn v-else class="black--text" icon @click="togglePick()">
              <v-icon>mdi-book-open-page-variant</v-icon>
            </v-btn>
          </v-card-title>
          <v-snackbar top v-model="quizRegSnackbar" :timeout="timeout">
            {{ snackText }}
            <v-btn color="blue" text @click="quizRegSnackbar = false"
              >닫 기</v-btn
            >
          </v-snackbar>
          <v-snackbar top v-model="snackbar" :timeout="timeout">
            {{ text }}
            <v-btn color="blue" text @click="snackbar = false">닫 기</v-btn>
          </v-snackbar>

          <v-col>
            <v-text-field
              v-model="quiz.category"
              rounded
              outlined
              prepend-icon="mdi-desktop-classic"
              dense
              readonly
              single-line
            ></v-text-field>
            <v-text-field
              v-model="time"
              rounded
              outlined
              prepend-icon="mdi-calendar-multiple-check"
              dense
              readonly
              single-line
            ></v-text-field>
            <v-text-field
              v-model="quiz.company"
              rounded
              outlined
              prepend-icon="mdi-sim"
              dense
              readonly
              single-line
            ></v-text-field>
            <v-text-field
              v-model="type"
              rounded
              outlined
              prepend-icon="mdi-briefcase"
              dense
              readonly
              single-line
            ></v-text-field>
            <v-row class="mb-5" justify="center">
              <v-chip class="mr-2">인턴</v-chip>
              <v-chip class="mr-2 white--text" color="blue darken-4"
                >신입</v-chip
              >
              <v-chip>경력</v-chip>
            </v-row>
          </v-col>
        </v-card>
      </v-col>
    </v-layout>

    <v-divider class="my-5"></v-divider>

    <!--베스트답변-->
    <v-container fluid>
      <v-layout justify-center>
        <v-card raised width="1300px">
          <v-card-title class="blue darken-4 white--text justify-center"
            >답변 남기기</v-card-title
          >
          <v-card-text>
            <v-form @submit="regComment()">
              <v-textarea
                v-model="newContent"
                solo
                no-resize
                counter="500"
                label="문제에 관한 답변을 남겨주세요"
                style="fontSize:30px"
                prepend-inner-icon="mdi-comment"
              ></v-textarea>
              <v-row>
                <v-radio-group class="ml-12 mt-1" v-model="row" row>
                  <span class="mr-8 mt-1">
                    <span
                      style="text-decoration: wavy underline red; fontSize:20px"
                      >면접질문의 난이도</span
                    >
                  </span>
                  <v-row>
                    <v-radio label="쉬움" color="orange" value="1"></v-radio>
                    <v-radio
                      label="평이한 수준"
                      color="orange"
                      value="2"
                    ></v-radio>
                    <v-radio label="어려움" color="orange" value="3"></v-radio>
                  </v-row>
                </v-radio-group>
                <v-spacer />
                <v-card-actions>
                  <v-btn
                    v-if="$store.state.id"
                    color="blue darken-4"
                    right
                    class="white--text mr-3"
                    type="submit"
                    >등록</v-btn
                  >
                  <v-btn
                    v-else
                    color="blue darken-4"
                    right
                    class="white--text mr-3"
                    type="submit"
                    disabled
                    >등록</v-btn
                  >
                </v-card-actions>
              </v-row>
            </v-form>
          </v-card-text>
          <v-divider class="mb-5"></v-divider>

          <v-card v-if="this.bestOk" class="ma-3">
            <v-card-title class="blue-grey lighten-4">
              <v-row align="center" justify="center">
                <div class="text-effect">
                  <span class="mx-12">BEST</span>
                </div>
                <span class="highlight" style="fontSize:17px">
                  <span class="highlight"
                    >{{ bestComment.likes }}명이 좋아합니다</span
                  >
                </span>
                <v-spacer />
              </v-row>
            </v-card-title>
            <v-card-text class="mt-3">
              <v-row align="center">
                <span class="black--text mx-5" style="fontSize:20px">
                  {{ bestComment.writer }}
                </span>
                <v-spacer />
                <span class="mr-5" style="fontSize:15px">
                  {{ bestComment.regdate }}
                </span>
                <v-chip color="orange lighten-1">
                  <v-icon class="white--text">mdi-crown</v-icon>
                </v-chip>
              </v-row>
              <span class="mx-12">{{ bestComment.content }}</span>
            </v-card-text>
          </v-card>

          <v-divider />

          <v-card
            v-for="comment in commentList.slice().reverse()"
            :key="comment.id"
            class="ma-3"
          >
            <v-card-text>
              <v-row align="center">
                <span class="black--text mx-5" style="fontSize:20px">
                  {{ comment.writer }}
                </span>
                <v-spacer />
                <span class="mr-5" style="fontSize:15px">
                  {{ comment.regdate }}
                </span>
                <v-btn
                  color="blue darken-3"
                  icon
                  @click="updateLike(comment.id)"
                >
                  <v-icon>mdi-thumb-up</v-icon>
                </v-btn>
                <v-snackbar top v-model="quizLikeSnackbar" :timeout="timeout">
                  {{ likeText }}
                  <v-btn color="blue" text @click="quizLikeSnackbar = false"
                    >닫 기</v-btn
                  >
                </v-snackbar>
              </v-row>

              <span class="mx-12">{{ comment.content }}</span>
            </v-card-text>
          </v-card>
        </v-card>
      </v-layout>
    </v-container>

    <v-layout>
      <v-col></v-col>
    </v-layout>
  </v-container>
</template>

<script>
import axios from "axios";

export default {
  data() {
    return {
      quiz: [],
      commentList: [],
      category: null,
      time: null,
      company: null,
      type: "직무정보 없음",
      newContent: "",
      pick: null,
      row: "2",
      question: null,

      snackbar: false,
      quizRegSnackbar: false,
      quizLikeSnackbar: false,
      text: "로그인 후 이용가능한 서비스입니다.",
      snackText: "내 문제에 등록하였습니다.",
      likeText: "해당 댓글을 추천하였습니다.",

      timeout: 5000,
      bestOk: false,
      bestComment: {
        content: null,
        id: null,
        likes: null,
        likelist: null,
        regdate: null,
        writer: null,
      },
      like: false,
    };
  },
  created() {
    axios
      .get("/interview/detail/" + sessionStorage.getItem("quizId"))
      .then((response) => {
        this.quiz = response.data;
        this.commentList = this.quiz.comments;
        this.time = this.quiz.fromwhen + " " + this.quiz.type;
        // 베스트답변 띄우기
        var max = 0;
        for (var k = 0; k < this.commentList.length; k++) {
          if (max < this.commentList[k].likes) {
            max = this.commentList[k].likes;
            this.bestComment = this.commentList[k];
            this.bestOk = true;
          }
        }
      });

    axios
      .get("/user/findall/" + sessionStorage.getItem("id"))
      .then((response) => {
        for (var i = 0; i < response.data.length; i++) {
          if (response.data[i].id === sessionStorage.getItem("quizId")) {
            this.pick = true;
            break;
          }
        }
      });
  },

  methods: {
    regComment() {
      if (sessionStorage.getItem("id") === null) {
        console.error("로그인 X");
      } else {
        axios
          .post(
            "/comment/insert/" + this.quiz.id + "/" + this.$store.state.id,
            {
              content: this.newContent,
            }
          )
          .then((response) => {
            console.log(response.data);
            this.newContent = "";
            this.$router.replace({
              name: "Solution",
            });
          });
      }
    },

    togglePick() {
      if (sessionStorage.getItem("id") === null) {
        this.snackbar = true;
      } else {
        axios
          .put(
            "/user/dibs/" +
              sessionStorage.getItem("id") +
              "/" +
              sessionStorage.getItem("quizId")
          )
          .then((response) => {
            console.log(response.data.dibslist);
            var num = response.data.dibslist.split(" ");
            let check = !this.pick;

            for (var j = 0; j < num.length; j++) {
              // 찜 눌렀을 때
              if (num[j] === sessionStorage.getItem("quizId")) {
                check = true;
                this.pick = true;
                this.quizRegSnackbar = true;
                break;
              }

              if (!check) {
                this.pick = false;
                this.quizRegSnackbar = false;
              }
              console.log(this.pick);
            }
          })
          .catch((error) => {
            console.log(error);
            this.pick = !this.pick;
            this.quizRegSnackbar = !this.quizRegSnackbar;
          });
      }
    },
    updateLike(commentId) {
      if (sessionStorage.getItem("id") === null) {
        this.snackbar = true;
      } else {
        axios
          .put(
            "/comment/like/" + commentId + "/" + sessionStorage.getItem("id")
          )
          .then((response) => {
            console.log(response);
            var likeNum = this.commentList.likelist.split(" ");
            var flag = false;

            for (var a = 0; a < likeNum.length; a++) {
              // 좋아요 눌렀을 때 추천멘트
              if (likeNum[a] === sessionStorage.getItem("id")) {
                flag = true;
                this.quizLikeSnackbar = true;
                break;
              }

              if (!flag) {
                this.quizLikeSnackbar = false;
              }
            }
          })
          .catch((error) => {
            console.log(error);
            this.quizLikeSnackbar = !this.quizLikeSnackbar;
            this.$router.go();
          });
      }
    },
  },
};
</script>

<style scoped>
.quiz_question {
  font-family: "Do Hyeon", sans-serif;
  font-size: 40px;
}

.v-card {
  font-family: "Do Hyeon", sans-serif;
  font-size: 20px;
}

.highlight {
  background-image: url("https://andyhooke.co.uk/wp-content/uploads/2018/02/yellow-brushstroke.png");
  background-repeat: no-repeat;
  background-size: 100% 95%;
  padding: 8px 0;
}

.text-effect {
  font-family: "Do Hyeon", sans-serif;
  text-align: center;
  text-transform: uppercase;
  margin: 0 auto;
}
.text-effect span {
  color: #f53b57;
  font-size: 30px;
  text-shadow: 1px 1px 1px rgba(0, 0, 0, 0.8);
  display: inline-block;
  animation: iBounce 2.2s ease infinite;
}
.text-effect span:nth-child(1) {
  animation-delay: 0s;
}
.text-effect span:nth-child(2) {
  animation-delay: 0.2s;
}
.text-effect span:nth-child(3) {
  animation-delay: 0.4s;
}
.text-effect span:nth-child(4) {
  animation-delay: 0.6s;
}
.text-effect span:nth-child(5) {
  animation-delay: 0.8s;
}
.text-effect span:nth-child(6) {
  animation-delay: 1s;
}
.text-effect span:nth-child(7) {
  animation-delay: 1.3s;
}
.text-effect span:nth-child(8) {
  animation-delay: 1.5s;
}
.text-effect span:nth-child(9) {
  animation-delay: 1.7s;
}
.text-effect span:nth-child(10) {
  animation-delay: 1.9s;
}
@keyframes iBounce {
  0%,
  100%,
  15%,
  30%,
  45% {
    transform: translateY(0);
  }
  10% {
    transform: translateY(-30px);
  }
  25% {
    transform: translateY(-20px);
  }
  40% {
    transform: translateY(-10px);
  }
}
@media only screen and (max-width: 990px) {
  .text-effect span {
    font-size: 65px;
  }
}
@media only screen and (max-width: 767px) {
  .text-effect span {
    font-size: 50px;
  }
}
@media only screen and (max-width: 479px) {
  .text-effect span {
    font-size: 36px;
  }
}
@media only screen and (max-width: 359px) {
  .text-effect span {
    font-size: 27px;
  }
}
</style>
