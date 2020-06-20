<template>
  <v-content>
    <v-layout>
      <v-flex>
        <Solution />
      </v-flex>
    </v-layout>
  </v-content>
</template>

<script>
import Solution from "@/components/Solution.vue";

export default {
  name: "solution",
  components: {
    Solution
  }
};
</script>
