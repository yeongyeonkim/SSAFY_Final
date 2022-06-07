<template>
  <v-container fluid class="pa-0">
    <v-layout class="hidden-sm-and-down">
      <v-img
        class="align-center"
        src="../assets/main7.jpg"
        height="350px"
      ></v-img>
    </v-layout>

    <v-layout class="hidden-md-and-up">
      <v-img
        class="align-center"
        src="../assets/main7.jpg"
        height="150px"
      ></v-img>
    </v-layout>
    <!-- 기술면접 필수코스 -->
    <v-container fluid class="grey lighten-3">
      <!-- 웹 기준 -->
      <v-layout class="hidden-sm-and-down" justify-center>
        <v-card
          width="700px"
          height="350px"
          elevation="0"
          color="grey lighten-3"
        >
          <v-row align="center" justify="center">
            <v-img
              class="align-center"
              src="@/assets/maintitle.jpg"
              height="350px"
            />
          </v-row>
        </v-card>
      </v-layout>

      <!-- 모바일 기준 -->
      <v-layout class="hidden-md-and-up" justify-center>
        <v-card height="150px" elevation="0" color="grey lighten-3">
          <v-row align="center" justify="center">
            <v-img
              class="align-center"
              src="@/assets/maintitle.jpg"
              height="150px"
            />
          </v-row>
        </v-card>
      </v-layout>
    </v-container>
    <v-container fluid class="white">
      <v-layout justify-center align-center>
        <div class="hidden-sm-and-down">
          <v-card raised="" height="430px" width="680px" class="mar">
            <youtube
              :video-id="videoId"
              :player-vars="playerVars"
              @playing="playing"
              width="720px"
              height="430px"
            ></youtube>
          </v-card>
        </div>
        <div class="hidden-md-and-up">
          <v-card raised="" class="mar">
            <youtube
              :video-id="videoId"
              :player-vars="playerVars"
              @playing="playing"
              width="auto"
              height="auto"
            ></youtube>
          </v-card>
        </div>
      </v-layout>
    </v-container>
    <!-- 면접질문 -->
    <v-container fluid class="grey lighten-3">
      <!-- 웹 기준 -->
      <v-layout class="hidden-sm-and-down" justify-center align-center>
        <v-card
          width="700px"
          height="400px"
          elevation="0"
          color="grey lighten-3"
        >
          <v-row align="center" justify="center">
            <v-img
              class="align-center"
              src="@/assets/mainques.jpg"
              height="400px"
            />
          </v-row>
        </v-card>
      </v-layout>

      <!-- 모바일 기준 -->
      <v-layout class="hidden-md-and-up" justify-center align-center>
        <v-card height="200px" elevation="0" color="grey lighten-3">
          <v-row align="center" justify="center">
            <v-img
              class="align-center"
              src="@/assets/mainques.jpg"
              height="200px"
            />
          </v-row>
        </v-card>
      </v-layout>
    </v-container>
    <v-container fluid class="white">
      <v-layout justify-center align-center class="hidden-sm-and-down">
        <v-card flat color="white" height="350px" width="800px" elevation="0">
          <v-img src="@/assets/maincontent.png" wdith="350px" />
        </v-card>
      </v-layout>
      <v-layout justify-center align-center class="hidden-md-and-up">
        <v-card flat color="white" height="150px" width="300px" elevation="0">
          <v-img src="@/assets/maincontent.png" wdith="150px" />
        </v-card>
      </v-layout>
    </v-container>
  </v-container>
</template>

<script>
export default {
  components: {},
  data() {
    return {
      videoId: "NXoluDh4eUA",
      playerVars: {
        autoplay: 0,
      },
    };
  },
  methods: {
    playing() {
      console.log(" we are watching!!!");
    },
  },
};
</script>

<style scoped>
@import url("https://fonts.googleapis.com/css2?family=Do+Hyeon&display=swap");
.a {
  font-family: "Do Hyeon", sans-serif;
}

.b {
  font-family: "Do Hyeon", sans-serif;
  font-size: 30px;
}
.mar {
  margin: 0 auto;
}
</style>
