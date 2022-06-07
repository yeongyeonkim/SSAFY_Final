import Vue from "vue";
import App from "./App.vue";
import router from "./router";
import store from "./store";
import vuetify from "./plugins/vuetify";

import VueParallaxJs from "vue-parallax-js";
import "chart.js";
import "hchs-vue-charts";

import "vuetify/dist/vuetify.min.css";

import VueEditor from "vue2-editor";
import AudioRecorder from "vue-audio-recorder";
import moment from "moment";
import VueMomentJs from "vue-momentjs";
import VueYoutube from "vue-youtube";

Vue.use(VueYoutube);
Vue.config.productionTip = false;
Vue.use(VueParallaxJs);

Vue.use(window.VueCharts);

Vue.use(VueEditor);
Vue.use(AudioRecorder);
Vue.use(VueMomentJs, moment);

new Vue({
  router,
  store,
  vuetify,
  render: (h) => h(App),
}).$mount("#app");
