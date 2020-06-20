<template>
  <v-container fluid fill-height>
    <v-layout justify-center>
      <v-flex sm12>
        <v-row>
          <v-col>
            <SlideMenu />
          </v-col>
        </v-row>
        <v-row align="center">
          <v-col cols="12">
            <myTab />
          </v-col>
        </v-row>
      </v-flex>
    </v-layout>
  </v-container>
</template>

<script>
import SlideMenu from "@/components/SlideComponents.vue";
import myTab from "@/components/Mytab.vue";

export default {
  components: {
    SlideMenu,
    myTab,
  },
  data() {
    return {};
  },
  methods: {},
  mounted() {},
};
</script>
