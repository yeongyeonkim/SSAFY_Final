<template>
  <v-dialog v-model="dialog" max-width="800px">
    <template v-slot:activator="{ on }">
      <v-btn x-large color="blue darken-4" class="ma-12" v-on="on">스낵퀴즈</v-btn>
    </template>
    <v-card>
      <v-expansion-panels multiple focusable>
        <v-expansion-panel v-for="(quiz, i) in quizList" :key="i">
          <v-expansion-panel-header class="quiz_Header">
            <v-row align="center">
              <v-icon>mdi-pencil</v-icon>
              <p class="mt-5 ml-2" v-html="quiz.question"></p>
            </v-row>
            <h5 align="right" class="orange--text mr-5">{{ quiz.category }}</h5>
          </v-expansion-panel-header>
          <v-expansion-panel-content class="quiz_Content mt-3">
            {{
            quiz.comments[0].content
            }}
          </v-expansion-panel-content>
        </v-expansion-panel>
      </v-expansion-panels>
    </v-card>
  </v-dialog>
</template>

<script>
import axios from "axios";

export default {
  data() {
    return {
      dialog: false,
      quizList: []
    };
  },
  created() {
    axios
      .get("/interview/random5")
      .then(response => {
        for (var i = 0; i < 5; i++) {
          this.quizList.push(response.data[i]);
        }
      })
      .catch(e => {
        console.log("error : ", e);
      });
  }
};
</script>

<style scoped>
@import url("https://fonts.googleapis.com/css2?family=Do+Hyeon&display=swap");
.quiz_Header {
  font-family: "Do Hyeon", sans-serif;
  font-size: 20px;
}

.quiz_Content {
  font-family: "Nanum Gothic Coding", monospace;
  font-size: 15px;
}
</style>
