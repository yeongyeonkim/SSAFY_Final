<template>
  <v-content>
    <v-layout justify-center>
      <v-flex>
        <mylist/>
      </v-flex>
    </v-layout>
  </v-content>
</template>

<script>
import mylist from "@/components/MyQuestionList.vue";

export default {
  components: {
    mylist,
  },
};
</script>