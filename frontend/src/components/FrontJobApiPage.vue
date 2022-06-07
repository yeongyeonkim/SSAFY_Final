<template>
  <v-container>
    <v-layout justify-center class="hidden-sm-and-down">
      <v-flex md7 class="fonts">
        <div align="center" class="maintitle">채용 공고</div>
        <v-card class="elevation-0">
          <v-timeline>
            <v-timeline-item
              v-for="(n, idx) in 15"
              :key="idx"
              color="#0d47a1"
              class="timeline"
            >
              <template v-slot:opposite>
                <span>D-{{ idx }}</span>
              </template>
              <v-card class="elevation-2">
                <v-card v-for="(i, idxx) in jobs" :key="idxx">
                  <v-row>
                    <v-col v-if="getDday(i.dueDate) === n">
                      <ul class="iconList">
                        <li>
                          <a :href="i.link">
                            <div class="text">
                              {{ i.company }}
                              <small>#{{ i.position }}</small>
                            </div>
                            <v-img data-eva="flash-outline"></v-img>
                          </a>
                        </li>
                      </ul>
                    </v-col>
                  </v-row>
                </v-card>
              </v-card>
            </v-timeline-item>
          </v-timeline>
        </v-card>
      </v-flex>
    </v-layout>

    <v-layout justify-center class="hidden-md-and-up">
      <v-flex sm10>
        <h1 align="center" class="timeline">채용 공고</h1>
        <v-card class="elevation-0">
          <v-timeline dense>
            <v-timeline-item
              v-for="(n, idx) in 15"
              :key="idx"
              color="#0d47a1"
              class="timeline"
            >
              <v-card class="elevation-2">
                <v-card-text>D-{{ idx }}</v-card-text>
                <v-card v-for="(i, idxx) in jobs" :key="idxx">
                  <v-row>
                    <v-col v-if="getDday(i.dueDate) === n">
                      <ul class="iconList">
                        <li>
                          <a :href="i.link">
                            <div class="text">
                              {{ i.company }}
                              <small>#{{ i.position }}</small>
                            </div>
                            <v-img data-eva="flash-outline"></v-img>
                          </a>
                        </li>
                      </ul>
                    </v-col>
                  </v-row>
                </v-card>
              </v-card>
            </v-timeline-item>
          </v-timeline>
        </v-card>
      </v-flex>
    </v-layout>
  </v-container>
</template>

<style scoped>
.maintitle {
  font-family: "Do Hyeon", sans-serif;
  font-size: 50px;
  margin-top: 3%;
}
.fonts {
  font-family: "Do Hyeon", sans-serif;
}
.timeline {
  font-family: "Do Hyeon", sans-serif;
}
.iconList {
  margin: 0;
  padding: 0;
  list-style: none;
}
.iconList li {
  --color: var(--primary-1);
  margin: 0 0 20px 0;
  border: 1px solid var(--border);
  border-radius: 6px;
  padding: 12px;
}
.iconList li:last-child {
  margin-bottom: 0;
}
.iconList li a {
  display: -webkit-box;
  display: flex;
  -webkit-box-align: center;
  align-items: center;
  position: relative;
  text-decoration: none;
}

.iconList li a .icon svg {
  display: block;
  position: absolute;
  left: 50%;
  top: 50%;
  width: 20px;
  height: 20px;
  fill: var(--color);
  -webkit-transform: translate(-50%, -50%);
  transform: translate(-50%, -50%);
}
.iconList li a .text {
  color: black;
  font-weight: 600;
  font-size: 20px;
  font-family: "Do Hyeon", sans-serif;
}
.iconList li a .text small {
  display: block;
  font-size: 14px;
  font-weight: 500;
  opacity: 0.75;
  color: var(--text-color);
}
.iconList li a > svg {
  position: absolute;
  display: block;
  right: 0;
  top: 50%;
  -webkit-transform: translateY(-50%);
  transform: translateY(-50%);
  -webkit-transition: -webkit-transform 0.3s ease;
  transition: -webkit-transform 0.3s ease;
  transition: transform 0.3s ease;
  transition: transform 0.3s ease, -webkit-transform 0.3s ease;
  fill: var(--border-hover);
}
.iconList li a:hover > svg {
  -webkit-transform: translateY(-50%) translateX(2px);
  transform: translateY(-50%) translateX(2px);
}
</style>

<script>
import http from "../http-common.js";

export default {
  data() {
    return {
      jobs: [],
      loading: true,
    };
  },
  mounted() {
    http
      .get("/jobapi")
      .then((response) => {
        this.jobs = response.data;
      })
      .catch(() => {
        this.errored = true;
      })
      .finally(() => {
        this.loading = false;
      });
  },
  methods: {
    getDday(str) {
      var strArray = str.split("-");
      var Dday = new Date(strArray[0], (strArray[1] *= 1) - 1, strArray[2]);
      var now = new Date();
      var gap = now.getTime() - Dday.getTime();
      var result = Math.floor(gap / (1000 * 60 * 60 * 24)) * -1;

      return result;
    },
  },
};
</script>
