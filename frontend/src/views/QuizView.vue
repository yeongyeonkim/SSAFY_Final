<template>
  <v-content>
    <v-layout justify-center>
      <v-flex>
        <Quiz />
      </v-flex>
    </v-layout>
  </v-content>
</template>

<script>
import Quiz from "@/components/QuizPage.vue";

export default {
  components: {
    Quiz
  }
};
</script>