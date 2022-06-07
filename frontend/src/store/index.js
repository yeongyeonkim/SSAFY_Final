import Vue from "vue";
import Vuex from "vuex";
import axios from "axios";
import router from "@/router/index";
Vue.use(Vuex);

export default new Vuex.Store({
  /* [state]: 여러 컴포넌트간의 공유되는 데이터 */
  state: {
    isLogin: sessionStorage.getItem("isLogin") || false,
    id: sessionStorage.getItem("id") || null,
    userId: sessionStorage.getItem("userId") || null,
    email: sessionStorage.getItem("email") || null,
  },

  /* [mutations]: state 값을 변화시키는 로직, 동기적 처리 */
  mutations: {
    // 로그인 후 변이
    setUserInfo(state, userInfo) {
      (state.isLogin = true),
        (state.userId = userInfo.userId),
        sessionStorage.setItem("isLogin", userInfo.isLogin);
      sessionStorage.setItem("id", userInfo.id);
      sessionStorage.setItem("userId", userInfo.userId);
      sessionStorage.setItem("email", userInfo.email);
    },

    // 로그아웃 후 변이
    clearUserInfo(state) {
      (state.isLogin = false),
        (state.id = null),
        (state.userId = null),
        sessionStorage.clear();
    },
  },

  /* [actions]: 비즈니스 로직, 비동기적 처리 */
  actions: {
    register({ commit }, registerObj) {
      axios
        .post(`/user/signup`, {
          uid: registerObj.uid,
          email: registerObj.email,
          password: registerObj.password,
        })
        .then((response) => {
          let userInfo = {
            isLogin: true,
            userId: registerObj.uid,
            id: response.data.data.id,
            email: response.data.data.email,
          };

          // mutations 호출
          commit("setUserInfo", userInfo);

          // 페이지 이동
          router.go();
        })
        .catch(() => {
          alert("아이디와 이메일을 확인해주세요");
        });
    },

    // 로그인
    login({ commit }, loginObj) {
      axios
        .post(`/user/signin`, {
          userid: loginObj.userid,
          password: loginObj.password,
        })
        .then((response) => {
          let userInfo = {
            isLogin: true,
            userId: loginObj.userid,
            id: response.data.data.id,
            email: response.data.data.email,
          };
          // alert(response.data.data.message);
          // mutations 호출
          commit("setUserInfo", userInfo);
          router.go();
        })
        .catch((error) => {
          alert(error);
          alert("아이디와 비밀번호를 확인해주세요.");
        });
    },

    // 로그아웃
    logout({ commit }) {
      // mutations 호출
      commit("clearUserInfo");

      router.push("/logout");
    },
  },
});
