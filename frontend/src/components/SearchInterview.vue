<template>
  <v-container fluid class="pa-0">
    <v-container fluid class="pa-0">
      <v-img class="align-center" src="../assets/tablet.jpg" height="490px">
        <v-row class="ma-0 fill-height" align="center" justify="center">
          <v-card-title class="search_title mx-12">"{{text}}" 로 검색한 결과</v-card-title>
        </v-row>

        <v-row class="ma-0 fill-height" align="center" justify="center">
          <v-card-subtitle class="my-5 search_subtitle">{{cnt}} 개의 문제가 존재합니다</v-card-subtitle>
        </v-row>
      </v-img>
    </v-container>

    <v-container fluid v-if="cnt">
      <v-row align="center">
        <v-col v-for="quiz in quizList" :key="quiz.quesion" cols="12" sm="3">
          <v-hover>
            <template v-slot:default="{ hover }">
              <v-card class="quiz_title" width="380px">
                <v-img src="../assets/background.jpg" height="300">
                  <v-row class="ma-0 fill-height" align="center" justify="center">
                    <p class="quiz mx-5" v-html="quiz.question"></p>
                  </v-row>
                </v-img>
                <v-fade-transition>
                  <v-overlay v-if="hover" absolute color="blue darken-4">
                    <div class="hover_text">
                      <v-icon class="mb-1">mdi-desktop-classic</v-icon>
                      <span class="ml-3">{{ quiz.category }}</span>
                      <br />
                      <br />
                      <v-icon class="mb-1">mdi-sim</v-icon>
                      <span class="ml-3">{{ quiz.company }}</span>
                      <br />
                      <br />
                      <v-icon class="mb-1">mdi-comment-text</v-icon>
                      <span class="ml-3">{{ quiz.comments.length }}</span>
                      <br />
                      <br />
                      <v-row align="center" justify="center">
                        <router-link :to="{ name: 'Solution', params: { id: quiz.id }}">
                          <v-btn color="blue darken-4" @click="storeQuiz(quiz.id)">확인</v-btn>
                        </router-link>
                      </v-row>
                    </div>
                  </v-overlay>
                </v-fade-transition>
              </v-card>
            </template>
          </v-hover>
        </v-col>
      </v-row>
    </v-container>

    <v-container v-else fulid>
      <v-card flat height="300px">
        <v-row>
          <v-col class="my-12"></v-col>
        </v-row>
      </v-card>
    </v-container>
  </v-container>
</template>

<style scoped>
@import url("https://fonts.googleapis.com/css2?family=Do+Hyeon&display=swap");

.card {
  display: block;
  margin: 20px 0;
  padding: 12px;
  background-color: #0d47a1;
  border-radius: 6px;
  transition: transform 0.2s cubic-bezier(0.42, 0, 0.33, 1.52);
  color: #557243;
  text-decoration-color: white;
}
.card:hover {
  background-color: rgb(103, 112, 192);
  transform: scale(1.05);
}
.search_title {
  font-family: "Do Hyeon", sans-serif;
  font-size: 30px;
}

.search_subtitle {
  font-family: "Do Hyeon", sans-serif;
  font-size: 25px;
}

.quiz_title {
  font-family: "Do Hyeon", sans-serif;
  font-size: 20px;
}

.hover_text {
  font-family: "Do Hyeon", sans-serif;
  font-size: 20px;
}
</style>

<script>
import http from "../http-common.js";

export default {
  data() {
    return {
      text: "",
      cnt: 0,
      select: [],
      items: ["기업형태", "기업이름", "문제분류"],
      model: null,
      overlay: false,
      quizList: [],
      accordions: {
        Experience: { isOpen: false }
      }
    };
  },
  methods: {
    storeQuiz(quizId) {
      sessionStorage.setItem("quizId", quizId);
    },
    toggleAll() {
      const newIsOpen = this.showExpandAll;
      Object.keys(this.accordions).forEach(key => {
        this.accordions[key].isOpen = newIsOpen;
      });
    },
    toggleAccordion(label) {
      const accordion = this.accordions[label];

      if (accordion) {
        accordion.isOpen = !accordion.isOpen;
      } else {
        console.error(`Could not find accordion '${label}'`);
      }
    },
    isOpen(label) {
      const accordion = this.accordions[label];
      return accordion ? accordion.isOpen : false;
    }
  },

  mounted() {
    this.text = localStorage.getItem("search");
    http
      .get("/interview/search/" + this.text)
      .then(resopnse => {
        localStorage.removeItem("search");
        this.quizList = resopnse.data;
        this.cnt = this.quizList.length;
      })
      .catch(() => {
        localStorage.removeItem("search");
        this.errored = true;
      })
      .finally(() => {
        this.loading = false;
      });
  }
};
</script>

<style scoped></style>
