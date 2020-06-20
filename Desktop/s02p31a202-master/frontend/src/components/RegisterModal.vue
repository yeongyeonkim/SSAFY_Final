<template>
  <v-dialog v-model="dialog" persistent max-width="500px">
    <template v-slot:activator="{ on }">
      <v-btn color="blue darken-1" text v-on="on">회원가입</v-btn>
    </template>
    <v-card>
      <v-card-title class="justify-center blue darken-4 white--text">
        <v-icon color="white" class="pr-6">mdi-laptop-chromebook</v-icon>회원가입
        <v-spacer />
        <v-btn text @click="closeModal">
          <v-icon color="white">mdi-close</v-icon>
        </v-btn>
      </v-card-title>

      <v-form
        ref="form"
        v-model="valid"
        lazy-validation
        @submit="register({ uid, email, password })"
      >
        <v-card-text class="pb-0">
          <v-container class="pb-0">
            <v-row>
              <v-col class="pb-0">
                <v-text-field
                  v-model="uid"
                  type="text"
                  label="아이디"
                  autocomplete="off"
                  prepend-icon="mdi-account"
                  :counter="12"
                  :rules="[rule.required, rule.uid]"
                  required
                />
                <v-text-field
                  v-model="password"
                  :type="showPassword ? 'text' : 'password'"
                  :append-icon="showPassword ? 'mdi-eye' : 'mdi-eye-off'"
                  prepend-icon="mdi-lock"
                  hint="비밀번호는 8글자 이상이어야 합니다."
                  :rules="[rule.required, rule.password]"
                  required
                  label="비밀번호"
                  autocomplete="off"
                  @click:append="showPassword = !showPassword"
                />
                <v-text-field
                  v-model="passwordConfirm"
                  :type="showPasswordConfirm ? 'text' : 'password'"
                  :append-icon="showPasswordConfirm ? 'mdi-eye' : 'mdi-eye-off'"
                  prepend-icon="mdi-check"
                  label="비밀번호 확인"
                  :rules="[rule.required, passwordConfirmRule]"
                  autocomplete="off"
                  required
                  @click:append="showPasswordConfirm = !showPasswordConfirm"
                />
                <v-text-field
                  v-model="email"
                  type="text"
                  label="이메일"
                  autocomplete="off"
                  prepend-icon="mdi-email"
                  :rules="[rule.required, rule.email]"
                  required
                />
              </v-col>
            </v-row>
          </v-container>
        </v-card-text>
        <v-card-actions>
          <v-col>
            <v-btn
              type="submit"
              :disabled="!valid"
              block
              color="blue darken-4 white--text"
              @click="validate"
            >가 입 하 기</v-btn>
          </v-col>
        </v-card-actions>
      </v-form>
    </v-card>
  </v-dialog>
</template>

<script>
import { mapActions } from "vuex";

export default {
  data() {
    return {
      dialog: false,
      valid: true,
      uid: "",
      password: "",
      passwordConfirm: "",
      email: "",
      showPassword: false,
      showPasswordConfirm: false,
      rule: {
        required: v => !!v || "필수 입력 사항입니다.",
        uid: v => (v && v.length <= 12) || "12글자 이하여야합니다.",
        password: v => 8 <= v.length || "최소 8글자 이상이어야 합니다.",
        email: v => /.+@.+/.test(v) || "올바른 이메일 형식이 아닙니다."
      }
    };
  },
  computed: {
    passwordConfirmRule() {
      return (
        this.password === this.passwordConfirm ||
        "비밀번호가 일치하지 않습니다."
      );
    }
  },
  created() {},
  methods: {
    ...mapActions(["register"]),
    init() {
      (this.uid = ""),
        (this.password = ""),
        (this.passwordConfirm = ""),
        (this.email = "");
    },
    validate() {
      this.$refs.form.validate();
    },
    closeModal() {
      this.dialog = false;
      this.$refs.form.reset();
    }
  }
};
</script>

<style scoped>
.v-card {
  font-family: "Nanum Gothic Coding", monospace;
}
</style>