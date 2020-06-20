<template>
  <v-content>
    <v-layout justify-center>
      <v-flex>
        <ProblemRequest/>
      </v-flex>
    </v-layout>
  </v-content>
</template>

<script>
import ProblemRequest from "@/components/ProblemRequestPage.vue";

export default {
  components: {
    ProblemRequest,
  },
};
</script>