<template>
  <v-content>
    <v-layout justify-center>
      <v-flex>
        <ProblemRegister/>
      </v-flex>
    </v-layout>
  </v-content>
</template>

<script>
import ProblemRegister from "@/components/ProblemRegisterPage.vue";

export default {
  components: {
    ProblemRegister,
  },
};
</script>