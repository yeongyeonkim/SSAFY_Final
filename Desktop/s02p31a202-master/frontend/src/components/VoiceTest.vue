<template>
  <v-container>
    <audio id="audio" muted src="../assets/test_voice.mp3"></audio>
    <v-btn @click="audio()"></v-btn>

    <v-btn v-if="voice_flag" to="/"> 시작하기 </v-btn>
  </v-container>
</template>
<script>
export default {
  data() {
    return {
      voice_flag: false,
    };
  },
  methods: {
    audio() {
      var audio = document.getElementById("audio");

      audio.play();
      this.voice_flag = true;
    },
  },
};
</script>
