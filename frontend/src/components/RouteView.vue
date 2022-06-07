<template>
  <v-content>
    <div id="app-view" style="height: 100%;">
      <div>
        <v-overlay :value="overlay" color="blue-grey darken-2">
          <simpleQuiz />
          <v-btn x-large color="blue darken-4" to="/mainquiz">모의면접</v-btn>
          <v-btn class="ml-3" icon @click="overlay = false">
            <v-icon>mdi-close</v-icon>
          </v-btn>
        </v-overlay>
        <v-overlay :value="overlay2">
          <v-row>
            <v-text-field
              label="검색어를 입력하세요"
              type="text"
              autocomplete="off"
              append-icon="mdi-magnify"
              rounded
              dense
              filled
              color="blue darken-4"
              background-color="grey"
            ></v-text-field>
            <v-btn class="mt-3 ml-3" icon @click="overlay2 = false">
              <v-icon>mdi-close</v-icon>
            </v-btn>
          </v-row>
        </v-overlay>
      </div>
      <v-fade-transition mode="in-out">
        <router-view></router-view>
      </v-fade-transition>
    </div>
  </v-content>
</template>

<script>
import { EventBus } from "@/util/eventBus";
import simpleQuiz from "@/components/SimpleQuiz";

export default {
  components: {
    simpleQuiz
  },
  data() {
    return {
      overlay: false,
      overlay2: false
    };
  },

  created() {
    EventBus.$on("showOverlay", overlay => {
      this.overlay = overlay;
    });

    EventBus.$on("searchOverlay", overlay2 => {
      this.overlay2 = overlay2;
    });
  }
};
</script>

<style scoped>
.v-btn {
  font-family: "Nanum Gothic Coding", monospace;
}

.v-text-field__slot input {
  color: black;
}
</style>
