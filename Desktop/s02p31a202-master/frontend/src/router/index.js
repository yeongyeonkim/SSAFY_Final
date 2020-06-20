import Vue from "vue";
import VueRouter from "vue-router";
import Home from "../views/Home.vue";
import Mypage from "../views/MypageView.vue";
import Quiz from "../views/QuizView.vue";
import Answer from "../views/AnswerView.vue";
import Solution from "../views/Solution.vue";
import ProblemRegister from "../views/ProblemRegisterView.vue";
import ProblemRequest from "../views/ProblemRequestView.vue";
import FrontJobApi from "../views/FrontJobApiView.vue";
import MainQuiz from "../views/MainQuizView.vue";
import VoiceTest from "../views/VoiceTest.vue";
import InterviewSchedule from "../views/InterviewScheduleView.vue";
import MyQuestionList from "../views/MyQuestionListView.vue";
import SearchInterview from "../views/SearchView.vue";
import LogoutPage from "../views/LogoutPage.vue";

Vue.use(VueRouter);

const routes = [
  {
    path: "/",
    name: "Home",
    component: Home,
  },
  {
    path: "/mypage",
    name: "Mypage",
    component: Mypage,
  },
  {
    path: "/quiz",
    name: "quiz",
    component: Quiz,
  },
  {
    path: "/solution",
    name: "Solution",
    component: Solution,
  },
  {
    path: "/answer",
    name: "Answer",
    component: Answer,
  },
  {
    path: "/proregi",
    name: "ProblemRegister",
    component: ProblemRegister,
  },
  {
    path: "/proreq",
    name: "ProblemRequest",
    component: ProblemRequest,
  },
  {
    path: "/frontjobapi",
    name: "FrontJobApi",
    component: FrontJobApi,
  },
  {
    path: "/mainquiz",
    component: MainQuiz,
  },
  {
    path: "/voicetest",
    name: "VoiceTest",
    component: VoiceTest,
  },
  {
    path: "/interviewSchedule",
    name: "InterviewSchedule",
    component: InterviewSchedule,
  },
  {
    path: "/MyQuestionList",
    name: "MyQuestionList",
    component: MyQuestionList,
  },
  {
    path: "/search",
    name: "SearchInterview",
    component: SearchInterview,
  },
  {
    path: "/logout",
    name: "LogoutPage",
    component: LogoutPage,
  },
];

const router = new VueRouter({
  mode: "history",
  // base: process.env.BASE_URL,

  routes,
});

/* 라우터 네비게이션 가드 */
router.beforeEach((to, from, next) => {
  if (to.name === "LogoutPage" && sessionStorage.getItem("isLogin") !== null) {
    next("/");
  } else {
    next();
  }
});

export default router;
