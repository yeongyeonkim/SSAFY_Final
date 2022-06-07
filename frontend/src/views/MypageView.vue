<template>
  <v-content>
    <v-layout justify-center>
      <v-flex>
        <MypageWeb/>
      </v-flex>
    </v-layout>
  </v-content>
</template>

<script>
import MypageWeb from "@/components/MypageWeb.vue";


export default {
  name: "mypage",
  components: {
    MypageWeb
  },
};
</script>
