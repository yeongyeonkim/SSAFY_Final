<template>
  <v-card flat tile class="mt-12">
    <v-window v-model="onboarding" vertical>
      <v-window-item v-for="(quiz, i) in quizList" :key="i">
        <v-card raised color="grey lighten-4" height="300">
          <v-row
            class="fill-height pa-0 quiz_header"
            align="center"
            justify="center"
            tag="v-card-text"
          >
            <p v-html="quiz.question" class="mx-10 quiz_header">
              {{ quiz.question }}
            </p>
          </v-row>
        </v-card>
      </v-window-item>
    </v-window>

    <v-card-actions class="justify-space-between">
      <v-btn text @click="prev">
        <v-icon>mdi-arrow-left-drop-circle</v-icon>
      </v-btn>
      <v-item-group
        v-model="onboarding"
        class="text-center hidden-sm-and-down"
        mandatory
      >
        <v-item
          v-for="n in length"
          :key="`btn-${n}`"
          v-slot:default="{ active, toggle }"
        >
          <v-btn :input-value="active" icon @click="toggle">
            <v-icon color="blue darken-4">mdi-pencil-circle</v-icon>
          </v-btn>
        </v-item>
      </v-item-group>
      <v-btn text @click="next">
        <v-icon>mdi-arrow-right-drop-circle</v-icon>
      </v-btn>
    </v-card-actions>
  </v-card>
</template>

<script>
import axios from "axios";

export default {
  data: () => ({
    length: 10,
    onboarding: 0,
    quizList: [],
  }),
  created() {
    axios
      .get("/interview/random10")
      .then((response) => {
        for (var i = 0; i < 10; i++) {
          this.quizList.push(response.data[i]);
        }
      })
      .catch((e) => {
        console.log("error : ", e);
      });
  },
  methods: {
    next() {
      this.onboarding =
        this.onboarding + 1 === this.length ? 0 : this.onboarding + 1;
    },
    prev() {
      this.onboarding =
        this.onboarding - 1 < 0 ? this.length - 1 : this.onboarding - 1;
    },
  },
};
</script>

<style scoped>
@import url("https://fonts.googleapis.com/css2?family=Do+Hyeon&display=swap");
.quiz_header {
  font-family: "Do Hyeon", sans-serif;
  font-size: 30px;
}
</style>
