<template>
  <v-content>
    <v-layout justify-center>
      <v-flex>
        <Answer/>
      </v-flex>
    </v-layout>
  </v-content>
</template>

<script>
import Answer from "@/components/AnswerPage.vue";

export default {
  components: {
    Answer,
  },
};
</script>