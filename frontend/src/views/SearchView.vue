<template>
  <v-content>
    <v-layout justify-center>
      <v-flex>
        <SearchInterview/>
      </v-flex>
    </v-layout>
  </v-content>
</template>

<script>
import SearchInterview from "@/components/SearchInterview.vue";

export default {
  components: {
    SearchInterview,
  },
};
</script>