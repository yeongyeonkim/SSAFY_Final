<template>
  <v-toolbar color="blue darken-4" height="128px" class="fonts">
    <!-- 페이지 로고 -->
    <router-link to="/">
      <v-toolbar-title>
        <v-col>
          <v-img src="../assets/logo.png" height="100px" width="120px" />
        </v-col>
      </v-toolbar-title>
    </router-link>

    <!-- 툴바 메뉴 -->
    <v-toolbar-items class="hidden-sm-and-down">
      <v-col class="mt-7">
        <v-btn text color="white" @click="showOverlay">기술면접</v-btn>
        <v-btn
          v-for="(item, index) in menu"
          :key="index"
          :to="item.link"
          text
          color="white"
          >{{ item.title }}</v-btn
        >
        <v-btn v-if="admin" text color="white" to="/proreq">질문승인</v-btn>
      </v-col>
    </v-toolbar-items>
    <v-spacer />

    <v-col>
      <v-text-field
        v-model="text"
        class="hidden-sm-and-down mt-5"
        rounded
        label="검색어를 입력하세요"
        single-line
        light
        solo
        autocomplete="off"
        prepend-inner-icon="mdi-magnify"
        @keypress.enter="gotosearch()"
      >
        <template v-slot:append>
          <v-progress-circular
            v-if="loading"
            size="24"
            color="teal"
            indeterminate
          />
        </template>
      </v-text-field>
    </v-col>
    <v-spacer />

    <!-- 로그인 버튼 & 모바일 전환 시 햄버거 -->
    <v-toolbar-items>
      <v-row>
        <v-col class="mt-7">
          <v-row>
            <form @submit.prevent="gotosearch()" class="hidden-md-and-up">
              <label :data-state="state" for="search">
                <input
                  type="text"
                  placeholder="Search"
                  @click="state = 'open'"
                  @blur="state = 'close'"
                  v-model="text"
                />
                <i
                  class="fa fa-search"
                  aria-hidden="true"
                  style="color:white"
                ></i>
              </label>
            </form>
            <loginModal></loginModal>
            <v-menu offset-y>
              <template v-slot:activator="{ on }">
                <v-btn class="hidden-md-and-up" text color="white" v-on="on">
                  <v-icon>mdi-menu</v-icon>
                </v-btn>
              </template>
              <v-list rounded>
                <v-list-item>
                  <v-list-item-icon>
                    <v-icon>mdi-account</v-icon>
                  </v-list-item-icon>
                  <v-list-item-title @click="showOverlay"
                    >기술면접</v-list-item-title
                  >
                </v-list-item>
                <v-list-item
                  v-for="(item, index) in menu"
                  :key="index"
                  :to="item.link"
                >
                  <v-list-item-icon>
                    <v-icon>{{ item.icon }}</v-icon>
                  </v-list-item-icon>
                  <v-list-item-title>{{ item.title }}</v-list-item-title>
                </v-list-item>
                <v-list-item v-if="admin" to="/proreq">
                  <v-list-item-icon>
                    <v-icon>mdi-checkbox-marked-circle-outline</v-icon>
                  </v-list-item-icon>
                  <v-list-item-title>질문승인</v-list-item-title>
                </v-list-item>
              </v-list>
            </v-menu>
          </v-row>
        </v-col>
      </v-row>
    </v-toolbar-items>
  </v-toolbar>
</template>

<script>
import { EventBus } from "@/util/eventBus";
import loginModal from "@/components/LoginModal";

export default {
  components: {
    loginModal,
  },
  data() {
    return {
      text: "",
      search: "search",
      state: "close",
      overlay: false,
      overlay2: false,
      menu: [
        {
          title: "문제은행",
          icon: "mdi-book-open-page-variant",
          link: "/quiz",
        },
        {
          title: "채용정보",
          icon: "mdi-calendar-multiple-check",
          link: "/frontjobapi",
        },
      ],
      loading: false,
      keyword: "",
      admin: sessionStorage.getItem("userId") === "admin" || null,
    };
  },
  methods: {
    showOverlay: function() {
      this.overlay = true;

      EventBus.$emit("showOverlay", this.overlay);
    },

    searchOverlay: function() {
      this.overlay2 = true;

      EventBus.$emit("searchOverlay", this.overlay2);
    },

    gotosearch() {
      localStorage.setItem(this.search, this.text);
      this.text = "";
      if (this.$route.path != "/search") {
        this.$router.push("/search");
      } else {
        this.$router.go();
      }
    },
  },
};
</script>

<style scoped>
.fonts {
  font-family: "Do Hyeon", sans-serif;
}
.v-btn {
  font-family: "Nanum Gothic Coding", monospace;
}

.v-text-field {
  width: 500px;
  font-family: "Do Hyeon", sans-serif;
}

@import url("https://maxcdn.bootstrapcdn.com/font-awesome/4.4.0/css/font-awesome.min.css");
body {
  display: -webkit-box;
  display: flex;
  -webkit-box-align: center;
  align-items: center;
  -webkit-box-pack: center;
  justify-content: center;
  height: 100vh;
  background-color: #f0ad4e;
}

label {
  position: relative;
  display: inline-block;
  background-color: #0d47a1;
  padding: 5px 12px;
  -webkit-transition: all 1s ease;
  transition: all 1s ease;
  border-radius: 0;
  box-shadow: 1px 1px 5px #0d47a1;
}
label::after {
  content: "";
  display: block;
  height: 2px;
  width: 80%;
  background-color: white;
  -webkit-transition: all 1s ease 0.5s;
  transition: all 1s ease 0.5s;
}
label input {
  -webkit-transition: width 1s ease, opacity 0.5s ease 0.5s;
  transition: width 1s ease, opacity 0.5s ease 0.5s;
  opacity: 1;
  width: 180px;
  height: 25px;
  border: 0;
  outline: none;
  color: #b06d0f;
}
label i {
  position: absolute;
  top: 11px;
  right: 11px;
  color: #333;
  cursor: pointer;
}
label[data-state="close"] {
  border-radius: 30px;
  padding: 5px 5px;
  -webkit-transition: all 1s ease;
  transition: all 1s ease;
}
label[data-state="close"]::after {
  width: 0%;
  -webkit-transition: all 0.3s ease;
  transition: all 0.3s ease;
}
label[data-state="close"] i {
  pointer-events: none;
}
label[data-state="close"] input {
  width: 28px;
  height: 25px;
  opacity: 0;
  cursor: pointer;
  -webkit-transition: opacity 0.5s ease, width 1s ease;
  transition: opacity 0.5s ease, width 1s ease;
  -webkit-appearance: none;
}
</style>
