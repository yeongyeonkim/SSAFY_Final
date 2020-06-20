<template>
  <v-dialog v-model="dialog" persistent max-width="500px">
    <template v-slot:activator="{ on }">
      <v-btn color="white" dark v-on="on" text>
        <v-icon>mdi-chart-arc</v-icon>
      </v-btn>
    </template>
    <v-card>
      <v-card-title class="justify-center blue darken-4 white--text">
        <v-icon color="white" class="pr-6">mdi-chart-arc</v-icon>차트
        <v-spacer />
        <v-btn text @click="dialog = false">
          <v-icon color="white">mdi-close</v-icon>
        </v-btn>
      </v-card-title>
      <div>
        <chartjs-doughnut
          v-bind:labels="labels"
          v-bind:datasets="datasets"
          v-bind:option="option"
          style="color:white"
        ></chartjs-doughnut>
      </div>
    </v-card>
  </v-dialog>
</template>

<script>
export default {
  data() {
    return {
      dialog: false,
      labels: ["apples", "bananas", "grapes"],
      datasets: [
        {
          data: [20, 30, 50],
          backgroundColor: ["Red", "Yellow", "Purple"]
        }
      ],
      option: {
        legend: {
          labels: {
            fontColor: "white",
            fontSize: 10
          },
          display: false // 라벨 표시할때는 true로 바꾸면 됨
        },
        title: {
          text: "내가 많이 푼 문제 유형",
          display: true,
          fontColor: "white",
          fontSize: 15
        }
      }
    };
  }
};
</script>

<style scoped>
.v-card {
  font-family: "Nanum Gothic Coding", monospace;
}
</style>