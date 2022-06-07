<template>
    <v-layout justify-center>
      <v-flex sm12 md7>
            <mainQuiz></mainQuiz>
            <rec style="width:auto"></rec>
      </v-flex>
    </v-layout>
</template>

<script>
import mainQuiz from "@/components/MainQuiz";
import rec from "@/components/Rec.vue";
export default {
  components: {
    mainQuiz,
    rec
  },
};
</script>
