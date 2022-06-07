<template>
  <v-dialog v-model="dialog" persistent max-width="600px">
    <template v-slot:activator="{ on }">
      <v-btn color="blue darken-1" text v-on="on">비밀번호 찾기</v-btn>
    </template>
    <v-card>
      <v-card-title class="justify-center blue darken-4 white--text">
        <v-icon color="white" class="pr-6">mdi-magnify</v-icon>비밀번호 찾기
      </v-card-title>

      <v-form @submit.prevent="findPassword(email, id)">
        <v-card-text class="pb-0">
          <v-container class="pb-0">
            <v-row>
              <v-col class="pb-0">
                <v-text-field
                  v-model="email"
                  label="가입시 등록한 이메일을 입력하세요"
                  type="text"
                  autocomplete="off"
                  prepend-icon="mdi-email"
                ></v-text-field>
                <v-text-field
                  v-model="id"
                  label="아이디를 입력하세요"
                  type="text"
                  autocomplete="off"
                  prepend-icon="mdi-account"
                ></v-text-field>
                <transition name="fade">
                  <v-text-field
                    v-if="result === 1"
                    v-model="password"
                    class="mt-2 text-center"
                    solo
                    type="text"
                    light
                    readonly
                    prepend-inner-icon="mdi-check"
                  />
                  <v-text-field
                    v-if="result === 2"
                    class="mt-2 text-center"
                    solo
                    dark
                    type="text"
                    label="잘못된 계정정보입니다."
                    prepend-inner-icon="mdi-alert"
                  />
                </transition>
              </v-col>
            </v-row>
          </v-container>
        </v-card-text>
        <v-card-actions>
          <v-spacer></v-spacer>
          <v-btn color="blue darken-1" text @click="closeModal">닫 기</v-btn>
          <v-btn type="submit " color="blue darken-1" text>찾 기</v-btn>
        </v-card-actions>
      </v-form>
    </v-card>
  </v-dialog>
</template>

<script>
import axios from "axios";

export default {
  data: () => ({
    dialog: false,
    email: null,
    id: null,
    password: null,
    result: 0,
  }),
  methods: {
    closeModal() {
      (this.dialog = false),
        (this.email = null),
        (this.id = null),
        (this.result = 0);
    },
    findPassword(email, id) {
      axios.get("/user/findPass/" + email + "/" + id).then((response) => {
        if (response.data !== "") {
          this.result = 1;
          this.password = response.data;
        } else this.result = 2;
      });
    },
  },
};
</script>

<style scoped>
.v-card {
  font-family: "Nanum Gothic Coding", monospace;
}
</style>
