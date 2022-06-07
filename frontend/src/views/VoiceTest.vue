<template>
  <v-content>
    <v-layout>
      <v-flex>
        <VoiceTest />
      </v-flex>
    </v-layout>
  </v-content>
</template>

<script>
import VoiceTest from "@/components/VoiceTest.vue";

export default {
  name: "voicetest",
  components: {
    VoiceTest,
  },
};
</script>
