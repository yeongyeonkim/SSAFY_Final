<template>
  <v-content>
    <v-layout class="hidden-sm-and-down" justify-center>
      <v-flex>
        <interWeb/>
      </v-flex>
    </v-layout>
    <v-layout class="hidden-md-and-up" justify-center>
      <v-flex>
        <interMobile/>
      </v-flex>
    </v-layout>
  </v-content>
</template>

<script>
import interWeb from "@/components/JobInterviewWeb.vue";
import interMobile from "@/components/JobInterviewMobile.vue";

export default {
  components: {
      interWeb,
      interMobile
  },
};
</script>