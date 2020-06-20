<template>
  <v-dialog v-model="dialog" persistent max-width="500px">
    <template v-slot:activator="{ on: modal }">
      <!-- 웹 로그인 OK -->
      <div>
        <v-menu
          v-model="menu"
          :close-on-content-click="false"
          :nudge-width="150"
        >
          <template v-slot:activator="{ on: menu }">
            <v-btn
              v-if="$store.state.isLogin"
              class="hidden-sm-and-down"
              color="white"
              dark
              text
              v-on="{ ...menu }"
              @click="myQuiz()"
            >
              <v-icon large>mdi-account</v-icon>
            </v-btn>
            <v-btn
              v-else
              class="hidden-sm-and-down"
              color="white"
              dark
              text
              v-on="{ ...modal }"
              >로 그 인</v-btn
            >
          </template>
          <v-card>
            <v-list>
              <v-list-item>
                <v-icon x-large>mdi-account-circle</v-icon>
                <v-list-item-content>
                  <v-list-item-title class="mx-5">
                    {{ $store.state.userId }} 님
                  </v-list-item-title>
                  <v-list-item-subtitle class="ml-5"
                    >오늘도 응원합니다</v-list-item-subtitle
                  >
                </v-list-item-content>

                <!-- 유저 찜 개수 -->
                <v-badge
                  :value="hover"
                  color="blue darken-4"
                  left
                  transition="slide-x-transition"
                >
                  <span slot="badge">{{ myInterview }}문제</span>
                  <v-hover v-model="hover">
                    <v-icon color="orange" large
                      >mdi-book-open-page-variant</v-icon
                    >
                  </v-hover>
                </v-badge>
              </v-list-item>
            </v-list>
            <v-divider></v-divider>
            <v-list>
              <v-list-item to="/mypage">
                <v-list-item-icon>
                  <v-icon>mdi-wrench</v-icon>
                </v-list-item-icon>
                <v-list-item-title>내 정보</v-list-item-title>
              </v-list-item>
              <v-list-item @click="logout(), (menu = false)">
                <v-list-item-icon>
                  <v-icon>mdi-logout</v-icon>
                </v-list-item-icon>
                <v-list-item-title>로그아웃</v-list-item-title>
              </v-list-item>
            </v-list>
          </v-card>
        </v-menu>
      </div>

      <!-- 모바일 로그인 OK-->
      <div>
        <v-menu
          v-model="menu"
          :close-on-content-click="false"
          :nudge-width="150"
        >
          <template v-slot:activator="{ on: menu }">
            <v-btn
              v-if="$store.state.isLogin"
              class="hidden-md-and-up"
              dark
              text
              v-on="{ ...menu }"
            >
              <v-icon large>mdi-account</v-icon>
            </v-btn>
            <!-- 모바일 로그인 X-->
            <v-btn
              v-else
              class="hidden-md-and-up"
              v-on="{ ...modal }"
              text
              dark
            >
              <v-icon>mdi-login</v-icon>
            </v-btn>
          </template>
        </v-menu>
      </div>
    </template>

    <!-- 로그인 입력 폼-->
    <v-card>
      <v-card-title class="justify-center blue darken-4 white--text">
        <v-icon color="white" class="pr-6">mdi-laptop-chromebook</v-icon>로그인
        <v-spacer />
        <v-btn text @click="closeModal">
          <v-icon color="white">mdi-close</v-icon>
        </v-btn>
      </v-card-title>

      <v-form ref="form" @submit="login({ userid, password }), closeModal()">
        <v-card-text class="pb-0">
          <v-container class="pb-0">
            <v-row>
              <v-col class="pb-0">
                <v-text-field
                  v-model="userid"
                  label="아이디"
                  type="text"
                  autocomplete="off"
                  prepend-icon="mdi-account"
                ></v-text-field>
                <v-text-field
                  v-model="password"
                  :type="showPassword ? 'text' : 'password'"
                  :append-icon="showPassword ? 'mdi-eye' : 'mdi-eye-off'"
                  label="비밀번호"
                  autocomplete="off"
                  prepend-icon="mdi-lock"
                  @click:append="showPassword = !showPassword"
                ></v-text-field>
              </v-col>
            </v-row>
          </v-container>
        </v-card-text>
        <v-card-actions>
          <v-col>
            <v-btn type="submit" block dark color="blue darken-4">
              <v-icon>mdi-login</v-icon>
              <v-divider vertical class="mx-5" />로 그 인
            </v-btn>
          </v-col>
        </v-card-actions>
      </v-form>
      <v-card-actions class="pt-0">
        <v-spacer />
        <searchIdModal />
        <searchPwModal />
      </v-card-actions>

      <v-col class="pt-0">
        <v-row>
          <v-flex xs5>
            <v-divider class="mt-2" />
          </v-flex>
          <v-flex xs2 class="text-center">또는</v-flex>
          <v-flex xs5>
            <v-divider class="mt-2" />
          </v-flex>
        </v-row>
        <v-layout row wrap align-center>
          <v-spacer />
          <v-spacer />
          <span class="subtitle-2 font-weight-black" />아직 계정이 없으신가요?
          <v-spacer />
          <registerModal />
        </v-layout>
      </v-col>
    </v-card>
  </v-dialog>
</template>

<script>
import { mapActions } from "vuex";
import axios from "axios";
import registerModal from "@/components/RegisterModal";
import searchIdModal from "@/components/SearchIdModal";
import searchPwModal from "@/components/SearchPwModal";

export default {
  components: {
    registerModal,
    searchIdModal,
    searchPwModal,
  },
  data() {
    return {
      dialog: false,
      userid: "",
      password: "",
      showPassword: false,
      menu: false,
      hover: false,
      offset: true,

      // 유저 인터뷰 개수
      id: sessionStorage.getItem("id") || null,
      myInterview: 0,
    };
  },

  methods: {
    ...mapActions(["login"]),
    ...mapActions(["logout"]),
    myQuiz() {
      axios
        .get("/user/findall/" + this.id)
        .then((response) => {
          this.myInterview = response.data.length;
        })
        .catch((error) => {
          console.log(error);
        });
    },
    closeModal() {
      this.dialog = false;
      this.$refs.form.reset();
    },
  },
};
</script>

<style scoped>
.v-card {
  font-family: "Nanum Gothic Coding", monospace;
}
</style>
