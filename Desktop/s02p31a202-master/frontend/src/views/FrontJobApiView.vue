<template>
  <v-content>
    <v-layout justify-center>
      <v-flex>
        <FrontJobApi />
      </v-flex>
    </v-layout>
  </v-content>
</template>

<script>
import FrontJobApi from "@/components/FrontJobApiPage.vue";

export default {
  components: {
    FrontJobApi,
  },
};
</script>
