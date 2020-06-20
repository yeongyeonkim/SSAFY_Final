<template>
  <v-layout justify-center class="my-12">
    <v-flex sm10 md7>
      <div class="ar">
        <audio-recorder
          upload-url="some url"
          :attempts="10"
          :time="10"
          :before-recording="callback"
          :after-recording="callback"
          :before-upload="callback"
          :successful-upload="callback"
          :failed-upload="callback"
        />
      </div>
    </v-flex>
  </v-layout>
</template>

<style scoped>
.ar {
  width: auto;
}
</style>
<script>
export default {
  data() {
    return {};
  },
  methods: {
    callback(msg) {
      console.debug("Event: ", msg);
    }
  }
};
</script>