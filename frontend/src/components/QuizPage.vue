<template>
  <v-container>
    <v-card>
      <v-tabs
        class="tab_title"
        background-color="white"
        color="blue darken-3"
        grow
      >
        <v-tab>
          <h1 class="font-weight-thin">ALL</h1>
        </v-tab>
        <v-tab>
          <h1 class="font-weight-thin">기업별</h1>
        </v-tab>
        <v-tab>
          <h1 class="font-weight-thin">기술별</h1>
        </v-tab>

        <v-tab-item v-for="n in 3" :key="n">
          <v-container fluid>
            <!-- All -->
            <div v-if="n === 1">
              <v-row>
                <v-chip class="mx-3 my-5" color="blue darken-4" label large>
                  <v-icon left color="white" large>mdi-desktop-classic</v-icon>
                  <span class="tab_body white--text">전체문제</span>
                </v-chip>
                <v-spacer />
                <v-btn
                  v-if="$store.state.isLogin"
                  color="orange darken-1"
                  class="white--text mt-5 mr-7"
                  large
                  to="/proregi"
                  fab
                >
                  <v-icon large>mdi-plus</v-icon>
                </v-btn>
              </v-row>
              <v-divider />
              <v-row align="center">
                <v-col v-for="quiz in quizList" :key="quiz.id" cols="12" sm="3">
                  <v-hover>
                    <template v-slot:default="{ hover }">
                      <v-card width="380px">
                        <v-img src="../assets/background.jpg" height="300">
                          <v-row
                            class="ma-0 fill-height"
                            align="center"
                            justify="center"
                          >
                            <p class="quiz mx-5" v-html="quiz.question"></p>
                          </v-row>
                        </v-img>
                        <v-fade-transition>
                          <v-overlay
                            v-if="hover"
                            absolute
                            color="blue darken-4"
                          >
                            <div class="hover_text">
                              <v-icon class="mb-1">mdi-desktop-classic</v-icon>
                              <span class="ml-3">{{ quiz.category }}</span>
                              <br />
                              <br />
                              <v-icon class="mb-1">mdi-sim</v-icon>
                              <span class="ml-3">{{ quiz.company }}</span>
                              <br />
                              <br />
                              <v-icon class="mb-1">mdi-comment-text</v-icon>
                              <span class="ml-3">{{
                                quiz.comments.length
                              }}</span>
                              <br />
                              <br />
                              <v-row align="center" justify="center">
                                <router-link
                                  :to="{
                                    name: 'Solution',
                                    params: { id: quiz.id },
                                  }"
                                >
                                  <v-btn
                                    color="blue darken-4"
                                    @click="storeQuiz(quiz.id)"
                                    >확인</v-btn
                                  >
                                </router-link>
                              </v-row>
                            </div>
                          </v-overlay>
                        </v-fade-transition>
                      </v-card>
                    </template>
                  </v-hover>
                </v-col>
              </v-row>
            </div>

            <!-- 기업별 -->
            <div v-if="n === 2">
              <v-row>
                <v-chip class="mx-3 my-5" color="blue darken-4" label large>
                  <v-icon left color="white" large>mdi-sim</v-icon>
                  <span class="tab_body white--text">대기업</span>
                </v-chip>
                <v-chip class="mx-3 my-5" color="blue darken-4" label large>
                  <v-icon left color="white" large>mdi-sim</v-icon>
                  <span class="tab_body white--text">중견기업</span>
                </v-chip>
                <v-chip class="mx-3 my-5" color="blue darken-4" label large>
                  <v-icon left color="white" large>mdi-sim</v-icon>
                  <span class="tab_body white--text">중소기업</span>
                </v-chip>
                <v-chip class="mx-3 my-5" color="blue darken-4" label large>
                  <v-icon left color="white" large>mdi-sim</v-icon>
                  <span class="tab_body white--text">공기업</span>
                </v-chip>
                <v-chip class="mx-3 my-5" color="blue darken-4" label large>
                  <v-icon left color="white" large>mdi-sim</v-icon>
                  <span class="tab_body white--text">ETC</span>
                </v-chip>
                <v-spacer />
                <v-btn
                  v-if="$store.state.isLogin"
                  color="orange darken-1"
                  class="white--text mt-5 mr-7"
                  large
                  to="/proregi"
                  fab
                >
                  <v-icon large>mdi-plus</v-icon>
                </v-btn>
              </v-row>
              <v-divider />
              <v-col></v-col>
              <span class="mx-5 tab_body2">대기업</span>
              <v-row align="center">
                <v-col
                  v-for="quiz in companyQuizList1"
                  :key="quiz.id"
                  cols="12"
                  sm="3"
                >
                  <v-hover>
                    <template v-slot:default="{ hover }">
                      <v-card width="380px">
                        <v-img src="../assets/background.jpg" height="300">
                          <v-row
                            class="ma-0 fill-height"
                            align="center"
                            justify="center"
                          >
                            <p class="quiz mx-5" v-html="quiz.question"></p>
                          </v-row>
                        </v-img>
                        <v-fade-transition>
                          <v-overlay
                            v-if="hover"
                            absolute
                            color="blue darken-4"
                          >
                            <div class="hover_text">
                              <v-icon class="mb-1">mdi-desktop-classic</v-icon>
                              <span class="ml-3">{{ quiz.category }}</span>
                              <br />
                              <br />
                              <v-icon class="mb-1">mdi-sim</v-icon>
                              <span class="ml-3">{{ quiz.company }}</span>
                              <br />
                              <br />
                              <v-icon class="mb-1">mdi-comment-text</v-icon>
                              <span class="ml-3">{{
                                quiz.comments.length
                              }}</span>
                              <br />
                              <br />
                              <v-row align="center" justify="center">
                                <router-link
                                  :to="{
                                    name: 'Solution',
                                    params: { id: quiz.id },
                                  }"
                                >
                                  <v-btn
                                    color="blue darken-4"
                                    @click="storeQuiz(quiz.id)"
                                    >확인</v-btn
                                  >
                                </router-link>
                              </v-row>
                            </div>
                          </v-overlay>
                        </v-fade-transition>
                      </v-card>
                    </template>
                  </v-hover>
                </v-col>
              </v-row>
              <v-divider />
              <v-col></v-col>
              <span class="mx-5 tab_body2">중견기업</span>
              <v-row align="center">
                <v-col
                  v-for="quiz in companyQuizList2"
                  :key="quiz.id"
                  cols="12"
                  sm="3"
                >
                  <v-hover>
                    <template v-slot:default="{ hover }">
                      <v-card width="380px">
                        <v-img src="../assets/background.jpg" height="300">
                          <v-row
                            class="ma-0 fill-height"
                            align="center"
                            justify="center"
                          >
                            <p class="quiz mx-5" v-html="quiz.question"></p>
                          </v-row>
                        </v-img>
                        <v-fade-transition>
                          <v-overlay
                            v-if="hover"
                            absolute
                            color="blue darken-4"
                          >
                            <div class="hover_text">
                              <v-icon class="mb-1">mdi-desktop-classic</v-icon>
                              <span class="ml-3">{{ quiz.category }}</span>
                              <br />
                              <br />
                              <v-icon class="mb-1">mdi-sim</v-icon>
                              <span class="ml-3">{{ quiz.company }}</span>
                              <br />
                              <br />
                              <v-icon class="mb-1">mdi-comment-text</v-icon>
                              <span class="ml-3">{{
                                quiz.comments.length
                              }}</span>
                              <br />
                              <br />
                              <v-row align="center" justify="center">
                                <router-link
                                  :to="{
                                    name: 'Solution',
                                    params: { id: quiz.id },
                                  }"
                                >
                                  <v-btn
                                    color="blue darken-4"
                                    @click="storeQuiz(quiz.id)"
                                    >확인</v-btn
                                  >
                                </router-link>
                              </v-row>
                            </div>
                          </v-overlay>
                        </v-fade-transition>
                      </v-card>
                    </template>
                  </v-hover>
                </v-col>
              </v-row>

              <v-divider />
              <v-col></v-col>
              <span class="mx-5 tab_body2">중소기업</span>
              <v-row align="center">
                <v-col
                  v-for="quiz in companyQuizList3"
                  :key="quiz.id"
                  cols="12"
                  sm="3"
                >
                  <v-hover>
                    <template v-slot:default="{ hover }">
                      <v-card width="380px">
                        <v-img src="../assets/background.jpg" height="300">
                          <v-row
                            class="ma-0 fill-height"
                            align="center"
                            justify="center"
                          >
                            <p class="quiz mx-5" v-html="quiz.question"></p>
                          </v-row>
                        </v-img>
                        <v-fade-transition>
                          <v-overlay
                            v-if="hover"
                            absolute
                            color="blue darken-4"
                          >
                            <div class="hover_text">
                              <v-icon class="mb-1">mdi-desktop-classic</v-icon>
                              <span class="ml-3">{{ quiz.category }}</span>
                              <br />
                              <br />
                              <v-icon class="mb-1">mdi-sim</v-icon>
                              <span class="ml-3">{{ quiz.company }}</span>
                              <br />
                              <br />
                              <v-icon class="mb-1">mdi-comment-text</v-icon>
                              <span class="ml-3">{{
                                quiz.comments.length
                              }}</span>
                              <br />
                              <br />
                              <v-row align="center" justify="center">
                                <router-link
                                  :to="{
                                    name: 'Solution',
                                    params: { id: quiz.id },
                                  }"
                                >
                                  <v-btn
                                    color="blue darken-4"
                                    @click="storeQuiz(quiz.id)"
                                    >확인</v-btn
                                  >
                                </router-link>
                              </v-row>
                            </div>
                          </v-overlay>
                        </v-fade-transition>
                      </v-card>
                    </template>
                  </v-hover>
                </v-col>
              </v-row>

              <v-divider />
              <v-col></v-col>
              <span class="mx-5 tab_body2">공기업</span>
              <v-row align="center">
                <v-col
                  v-for="quiz in companyQuizList4"
                  :key="quiz.id"
                  cols="12"
                  sm="3"
                >
                  <v-hover>
                    <template v-slot:default="{ hover }">
                      <v-card width="380px">
                        <v-img src="../assets/background.jpg" height="300">
                          <v-row
                            class="ma-0 fill-height"
                            align="center"
                            justify="center"
                          >
                            <p class="quiz mx-5" v-html="quiz.question"></p>
                          </v-row>
                        </v-img>
                        <v-fade-transition>
                          <v-overlay
                            v-if="hover"
                            absolute
                            color="blue darken-4"
                          >
                            <div class="hover_text">
                              <v-icon class="mb-1">mdi-desktop-classic</v-icon>
                              <span class="ml-3">{{ quiz.category }}</span>
                              <br />
                              <br />
                              <v-icon class="mb-1">mdi-sim</v-icon>
                              <span class="ml-3">{{ quiz.company }}</span>
                              <br />
                              <br />
                              <v-icon class="mb-1">mdi-comment-text</v-icon>
                              <span class="ml-3">{{
                                quiz.comments.length
                              }}</span>
                              <br />
                              <br />
                              <v-row align="center" justify="center">
                                <router-link
                                  :to="{
                                    name: 'Solution',
                                    params: { id: quiz.id },
                                  }"
                                >
                                  <v-btn
                                    color="blue darken-4"
                                    @click="storeQuiz(quiz.id)"
                                    >확인</v-btn
                                  >
                                </router-link>
                              </v-row>
                            </div>
                          </v-overlay>
                        </v-fade-transition>
                      </v-card>
                    </template>
                  </v-hover>
                </v-col>
              </v-row>

              <v-divider />
              <v-col></v-col>
              <span class="mx-5 tab_body2">ETC</span>
              <v-row align="center">
                <v-col
                  v-for="quiz in companyQuizList5"
                  :key="quiz.id"
                  cols="12"
                  sm="3"
                >
                  <v-hover>
                    <template v-slot:default="{ hover }">
                      <v-card width="380px">
                        <v-img src="../assets/background.jpg" height="300">
                          <v-row
                            class="ma-0 fill-height"
                            align="center"
                            justify="center"
                          >
                            <p class="quiz mx-5" v-html="quiz.question"></p>
                          </v-row>
                        </v-img>
                        <v-fade-transition>
                          <v-overlay
                            v-if="hover"
                            absolute
                            color="blue darken-4"
                          >
                            <div class="hover_text">
                              <v-icon class="mb-1">mdi-desktop-classic</v-icon>
                              <span class="ml-3">{{ quiz.category }}</span>
                              <br />
                              <br />
                              <v-icon class="mb-1">mdi-sim</v-icon>
                              <span class="ml-3">{{ quiz.company }}</span>
                              <br />
                              <br />
                              <v-icon class="mb-1">mdi-comment-text</v-icon>
                              <span class="ml-3">{{
                                quiz.comments.length
                              }}</span>
                              <br />
                              <br />
                              <v-row align="center" justify="center">
                                <router-link
                                  :to="{
                                    name: 'Solution',
                                    params: { id: quiz.id },
                                  }"
                                >
                                  <v-btn
                                    color="blue darken-4"
                                    @click="storeQuiz(quiz.id)"
                                    >확인</v-btn
                                  >
                                </router-link>
                              </v-row>
                            </div>
                          </v-overlay>
                        </v-fade-transition>
                      </v-card>
                    </template>
                  </v-hover>
                </v-col>
              </v-row>
            </div>

            <!-- 기술별 -->
            <div v-if="n === 3">
              <v-row>
                <v-chip class="mx-3 my-5" color="blue darken-4" label large>
                  <v-icon left color="white" large>mdi-linux</v-icon>
                  <span class="tab_body white--text">운영체제</span>
                </v-chip>
                <v-chip class="mx-1 my-5" color="blue darken-4" label large>
                  <v-icon left color="white" large>mdi-database</v-icon>
                  <span class="tab_body white--text">데이터베이스</span>
                </v-chip>
                <v-chip class="mx-1 my-5" color="blue darken-4" label large>
                  <v-icon left color="white" large
                    >mdi-access-point-network</v-icon
                  >
                  <span class="tab_body white--text">네트워크</span>
                </v-chip>
                <v-chip class="mx-1 my-5" color="blue darken-4" label large>
                  <v-icon left color="white" large>mdi-web</v-icon>
                  <span class="tab_body white--text">웹</span>
                </v-chip>
                <v-chip class="mx-1 my-5" color="blue darken-4" label large>
                  <v-icon left color="white" large>mdi-file-tree</v-icon>
                  <span class="tab_body white--text">자료구조</span>
                </v-chip>
                <v-chip class="mx-1 my-5" color="blue darken-4" label large>
                  <v-icon left color="white" large>mdi-source-branch</v-icon>
                  <span class="tab_body white--text">알고리즘</span>
                </v-chip>
                <v-chip class="mx-1 my-5" color="blue darken-4" label large>
                  <v-icon left color="white" large>mdi-desktop-classic</v-icon>
                  <span class="tab_body white--text">컴퓨터구조</span>
                </v-chip>
                <v-chip class="mx-1 my-5" color="blue darken-4" label large>
                  <v-icon left color="white" large
                    >mdi-dots-horizontal-circle</v-icon
                  >
                  <span class="tab_body white--text">ETC</span>
                </v-chip>
                <v-spacer />
                <v-btn
                  v-if="$store.state.isLogin"
                  color="orange darken-1"
                  class="white--text mt-5 mr-7"
                  large
                  to="/proregi"
                  fab
                >
                  <v-icon large>mdi-plus</v-icon>
                </v-btn>
              </v-row>
              <v-divider />
              <v-col></v-col>
              <span class="mx-5 tab_body2">운영체제</span>
              <v-row align="center">
                <v-col
                  v-for="quiz in categoryQuizList1"
                  :key="quiz.id"
                  cols="12"
                  sm="3"
                >
                  <v-hover>
                    <template v-slot:default="{ hover }">
                      <v-card width="380px">
                        <v-img src="../assets/background.jpg" height="300">
                          <v-row
                            class="ma-0 fill-height"
                            align="center"
                            justify="center"
                          >
                            <p class="quiz mx-5" v-html="quiz.question"></p>
                          </v-row>
                        </v-img>
                        <v-fade-transition>
                          <v-overlay
                            v-if="hover"
                            absolute
                            color="blue darken-4"
                          >
                            <div class="hover_text">
                              <v-icon class="mb-1">mdi-desktop-classic</v-icon>
                              <span class="ml-3">{{ quiz.category }}</span>
                              <br />
                              <br />
                              <v-icon class="mb-1">mdi-sim</v-icon>
                              <span class="ml-3">{{ quiz.company }}</span>
                              <br />
                              <br />
                              <v-icon class="mb-1">mdi-comment-text</v-icon>
                              <span class="ml-3">{{
                                quiz.comments.length
                              }}</span>
                              <br />
                              <br />
                              <v-row align="center" justify="center">
                                <router-link
                                  :to="{
                                    name: 'Solution',
                                    params: { id: quiz.id },
                                  }"
                                >
                                  <v-btn
                                    color="blue darken-4"
                                    @click="storeQuiz(quiz.id)"
                                    >확인</v-btn
                                  >
                                </router-link>
                              </v-row>
                            </div>
                          </v-overlay>
                        </v-fade-transition>
                      </v-card>
                    </template>
                  </v-hover>
                </v-col>
              </v-row>

              <v-divider />
              <v-col></v-col>
              <span class="mx-5 tab_body2">데이터베이스</span>
              <v-row align="center">
                <v-col
                  v-for="quiz in categoryQuizList2"
                  :key="quiz.id"
                  cols="12"
                  sm="3"
                >
                  <v-hover>
                    <template v-slot:default="{ hover }">
                      <v-card width="380px">
                        <v-img src="../assets/background.jpg" height="300">
                          <v-row
                            class="ma-0 fill-height"
                            align="center"
                            justify="center"
                          >
                            <p class="quiz mx-5" v-html="quiz.question"></p>
                          </v-row>
                        </v-img>
                        <v-fade-transition>
                          <v-overlay
                            v-if="hover"
                            absolute
                            color="blue darken-4"
                          >
                            <div class="hover_text">
                              <v-icon class="mb-1">mdi-desktop-classic</v-icon>
                              <span class="ml-3">{{ quiz.category }}</span>
                              <br />
                              <br />
                              <v-icon class="mb-1">mdi-sim</v-icon>
                              <span class="ml-3">{{ quiz.company }}</span>
                              <br />
                              <br />
                              <v-icon class="mb-1">mdi-comment-text</v-icon>
                              <span class="ml-3">{{
                                quiz.comments.length
                              }}</span>
                              <br />
                              <br />
                              <v-row align="center" justify="center">
                                <router-link
                                  :to="{
                                    name: 'Solution',
                                    params: { id: quiz.id },
                                  }"
                                >
                                  <v-btn
                                    color="blue darken-4"
                                    @click="storeQuiz(quiz.id)"
                                    >확인</v-btn
                                  >
                                </router-link>
                              </v-row>
                            </div>
                          </v-overlay>
                        </v-fade-transition>
                      </v-card>
                    </template>
                  </v-hover>
                </v-col>
              </v-row>

              <v-divider />
              <v-col></v-col>
              <span class="mx-5 tab_body2">네트워크</span>
              <v-row align="center">
                <v-col
                  v-for="quiz in categoryQuizList3"
                  :key="quiz.id"
                  cols="12"
                  sm="3"
                >
                  <v-hover>
                    <template v-slot:default="{ hover }">
                      <v-card width="380px">
                        <v-img src="../assets/background.jpg" height="300">
                          <v-row
                            class="ma-0 fill-height"
                            align="center"
                            justify="center"
                          >
                            <p class="quiz mx-5" v-html="quiz.question"></p>
                          </v-row>
                        </v-img>
                        <v-fade-transition>
                          <v-overlay
                            v-if="hover"
                            absolute
                            color="blue darken-4"
                          >
                            <div class="hover_text">
                              <v-icon class="mb-1">mdi-desktop-classic</v-icon>
                              <span class="ml-3">{{ quiz.category }}</span>
                              <br />
                              <br />
                              <v-icon class="mb-1">mdi-sim</v-icon>
                              <span class="ml-3">{{ quiz.company }}</span>
                              <br />
                              <br />
                              <v-icon class="mb-1">mdi-comment-text</v-icon>
                              <span class="ml-3">{{
                                quiz.comments.length
                              }}</span>
                              <br />
                              <br />
                              <v-row align="center" justify="center">
                                <router-link
                                  :to="{
                                    name: 'Solution',
                                    params: { id: quiz.id },
                                  }"
                                >
                                  <v-btn
                                    color="blue darken-4"
                                    @click="storeQuiz(quiz.id)"
                                    >확인</v-btn
                                  >
                                </router-link>
                              </v-row>
                            </div>
                          </v-overlay>
                        </v-fade-transition>
                      </v-card>
                    </template>
                  </v-hover>
                </v-col>
              </v-row>

              <v-divider />
              <v-col></v-col>
              <span class="mx-5 tab_body2">웹</span>
              <v-row align="center">
                <v-col
                  v-for="quiz in categoryQuizList4"
                  :key="quiz.id"
                  cols="12"
                  sm="3"
                >
                  <v-hover>
                    <template v-slot:default="{ hover }">
                      <v-card width="380px">
                        <v-img src="../assets/background.jpg" height="300">
                          <v-row
                            class="ma-0 fill-height"
                            align="center"
                            justify="center"
                          >
                            <p class="quiz mx-5" v-html="quiz.question"></p>
                          </v-row>
                        </v-img>
                        <v-fade-transition>
                          <v-overlay
                            v-if="hover"
                            absolute
                            color="blue darken-4"
                          >
                            <div class="hover_text">
                              <v-icon class="mb-1">mdi-desktop-classic</v-icon>
                              <span class="ml-3">{{ quiz.category }}</span>
                              <br />
                              <br />
                              <v-icon class="mb-1">mdi-sim</v-icon>
                              <span class="ml-3">{{ quiz.company }}</span>
                              <br />
                              <br />
                              <v-icon class="mb-1">mdi-comment-text</v-icon>
                              <span class="ml-3">{{
                                quiz.comments.length
                              }}</span>
                              <br />
                              <br />
                              <v-row align="center" justify="center">
                                <router-link
                                  :to="{
                                    name: 'Solution',
                                    params: { id: quiz.id },
                                  }"
                                >
                                  <v-btn
                                    color="blue darken-4"
                                    @click="storeQuiz(quiz.id)"
                                    >확인</v-btn
                                  >
                                </router-link>
                              </v-row>
                            </div>
                          </v-overlay>
                        </v-fade-transition>
                      </v-card>
                    </template>
                  </v-hover>
                </v-col>
              </v-row>

              <v-divider />
              <v-col></v-col>
              <span class="mx-5 tab_body2">자료구조</span>
              <v-row align="center">
                <v-col
                  v-for="quiz in categoryQuizList5"
                  :key="quiz.id"
                  cols="12"
                  sm="3"
                >
                  <v-hover>
                    <template v-slot:default="{ hover }">
                      <v-card width="380px">
                        <v-img src="../assets/background.jpg" height="300">
                          <v-row
                            class="ma-0 fill-height"
                            align="center"
                            justify="center"
                          >
                            <p class="quiz mx-5" v-html="quiz.question"></p>
                          </v-row>
                        </v-img>
                        <v-fade-transition>
                          <v-overlay
                            v-if="hover"
                            absolute
                            color="blue darken-4"
                          >
                            <div class="hover_text">
                              <v-icon class="mb-1">mdi-desktop-classic</v-icon>
                              <span class="ml-3">{{ quiz.category }}</span>
                              <br />
                              <br />
                              <v-icon class="mb-1">mdi-sim</v-icon>
                              <span class="ml-3">{{ quiz.company }}</span>
                              <br />
                              <br />
                              <v-icon class="mb-1">mdi-comment-text</v-icon>
                              <span class="ml-3">{{
                                quiz.comments.length
                              }}</span>
                              <br />
                              <br />
                              <v-row align="center" justify="center">
                                <router-link
                                  :to="{
                                    name: 'Solution',
                                    params: { id: quiz.id },
                                  }"
                                >
                                  <v-btn
                                    color="blue darken-4"
                                    @click="storeQuiz(quiz.id)"
                                    >확인</v-btn
                                  >
                                </router-link>
                              </v-row>
                            </div>
                          </v-overlay>
                        </v-fade-transition>
                      </v-card>
                    </template>
                  </v-hover>
                </v-col>
              </v-row>

              <v-divider />
              <v-col></v-col>
              <span class="mx-5 tab_body2">알고리즘</span>
              <v-row align="center">
                <v-col
                  v-for="quiz in categoryQuizList6"
                  :key="quiz.id"
                  cols="12"
                  sm="3"
                >
                  <v-hover>
                    <template v-slot:default="{ hover }">
                      <v-card width="380px">
                        <v-img src="../assets/background.jpg" height="300">
                          <v-row
                            class="ma-0 fill-height"
                            align="center"
                            justify="center"
                          >
                            <p class="quiz mx-5" v-html="quiz.question"></p>
                          </v-row>
                        </v-img>
                        <v-fade-transition>
                          <v-overlay
                            v-if="hover"
                            absolute
                            color="blue darken-4"
                          >
                            <div class="hover_text">
                              <v-icon class="mb-1">mdi-desktop-classic</v-icon>
                              <span class="ml-3">{{ quiz.category }}</span>
                              <br />
                              <br />
                              <v-icon class="mb-1">mdi-sim</v-icon>
                              <span class="ml-3">{{ quiz.company }}</span>
                              <br />
                              <br />
                              <v-icon class="mb-1">mdi-comment-text</v-icon>
                              <span class="ml-3">{{
                                quiz.comments.length
                              }}</span>
                              <br />
                              <br />
                              <v-row align="center" justify="center">
                                <router-link
                                  :to="{
                                    name: 'Solution',
                                    params: { id: quiz.id },
                                  }"
                                >
                                  <v-btn
                                    color="blue darken-4"
                                    @click="storeQuiz(quiz.id)"
                                    >확인</v-btn
                                  >
                                </router-link>
                              </v-row>
                            </div>
                          </v-overlay>
                        </v-fade-transition>
                      </v-card>
                    </template>
                  </v-hover>
                </v-col>
              </v-row>

              <v-divider />
              <v-col></v-col>
              <span class="mx-5 tab_body2">컴퓨터구조</span>
              <v-row align="center">
                <v-col
                  v-for="quiz in categoryQuizList7"
                  :key="quiz.id"
                  cols="12"
                  sm="3"
                >
                  <v-hover>
                    <template v-slot:default="{ hover }">
                      <v-card width="380px">
                        <v-img src="../assets/background.jpg" height="300">
                          <v-row
                            class="ma-0 fill-height"
                            align="center"
                            justify="center"
                          >
                            <p class="quiz mx-5" v-html="quiz.question"></p>
                          </v-row>
                        </v-img>
                        <v-fade-transition>
                          <v-overlay
                            v-if="hover"
                            absolute
                            color="blue darken-4"
                          >
                            <div class="hover_text">
                              <v-icon class="mb-1">mdi-desktop-classic</v-icon>
                              <span class="ml-3">{{ quiz.category }}</span>
                              <br />
                              <br />
                              <v-icon class="mb-1">mdi-sim</v-icon>
                              <span class="ml-3">{{ quiz.company }}</span>
                              <br />
                              <br />
                              <v-icon class="mb-1">mdi-comment-text</v-icon>
                              <span class="ml-3">{{
                                quiz.comments.length
                              }}</span>
                              <br />
                              <br />
                              <v-row align="center" justify="center">
                                <router-link
                                  :to="{
                                    name: 'Solution',
                                    params: { id: quiz.id },
                                  }"
                                >
                                  <v-btn
                                    color="blue darken-4"
                                    @click="storeQuiz(quiz.id)"
                                    >확인</v-btn
                                  >
                                </router-link>
                              </v-row>
                            </div>
                          </v-overlay>
                        </v-fade-transition>
                      </v-card>
                    </template>
                  </v-hover>
                </v-col>
              </v-row>

              <v-divider />
              <v-col></v-col>
              <span class="mx-5 tab_body2">ETC</span>
              <v-row align="center">
                <v-col
                  v-for="quiz in categoryQuizList8"
                  :key="quiz.id"
                  cols="12"
                  sm="3"
                >
                  <v-hover>
                    <template v-slot:default="{ hover }">
                      <v-card width="380px">
                        <v-img src="../assets/background.jpg" height="300">
                          <v-row
                            class="ma-0 fill-height"
                            align="center"
                            justify="center"
                          >
                            <p class="quiz mx-5" v-html="quiz.question"></p>
                          </v-row>
                        </v-img>
                        <v-fade-transition>
                          <v-overlay
                            v-if="hover"
                            absolute
                            color="blue darken-4"
                          >
                            <div class="hover_text">
                              <v-icon class="mb-1">mdi-desktop-classic</v-icon>
                              <span class="ml-3">{{ quiz.category }}</span>
                              <br />
                              <br />
                              <v-icon class="mb-1">mdi-sim</v-icon>
                              <span class="ml-3">{{ quiz.company }}</span>
                              <br />
                              <br />
                              <v-icon class="mb-1">mdi-comment-text</v-icon>
                              <span class="ml-3">{{
                                quiz.comments.length
                              }}</span>
                              <br />
                              <br />
                              <v-row align="center" justify="center">
                                <router-link
                                  :to="{
                                    name: 'Solution',
                                    params: { id: quiz.id },
                                  }"
                                >
                                  <v-btn
                                    color="blue darken-4"
                                    @click="storeQuiz(quiz.id)"
                                    >확인</v-btn
                                  >
                                </router-link>
                              </v-row>
                            </div>
                          </v-overlay>
                        </v-fade-transition>
                      </v-card>
                    </template>
                  </v-hover>
                </v-col>
              </v-row>
            </div>
          </v-container>
        </v-tab-item>
      </v-tabs>
    </v-card>
  </v-container>
</template>

<script>
import axios from "axios";

export default {
  data() {
    return {
      select: [],
      items: ["기업형태", "기업이름", "문제분류"],
      overlay: false,
      tab: 0,
      quizList: [],

      companyQuizList1: [],
      companyQuizList2: [],
      companyQuizList3: [],
      companyQuizList4: [],
      companyQuizList5: [],

      categoryQuizList1: [],
      categoryQuizList2: [],
      categoryQuizList3: [],
      categoryQuizList4: [],
      categoryQuizList5: [],
      categoryQuizList6: [],
      categoryQuizList7: [],
      categoryQuizList8: [],
    };
  },
  created() {
    axios
      .get("/interview/list")
      .then((response) => {
        this.quizList = response.data;

        for (var i = 0; i < response.data.length; i++) {
          if (response.data[i].companytype === "대기업") {
            this.companyQuizList1.push(response.data[i]);
          } else if (response.data[i].companytype === "중견기업") {
            this.companyQuizList2.push(response.data[i]);
          } else if (response.data[i].companytype === "중소기업") {
            this.companyQuizList3.push(response.data[i]);
          } else if (response.data[i].companytype === "공기업") {
            this.companyQuizList4.push(response.data[i]);
          } else {
            this.companyQuizList5.push(response.data[i]);
          }
        }

        for (var j = 0; j < response.data.length; j++) {
          if (response.data[j].category === "운영체제") {
            this.categoryQuizList1.push(response.data[j]);
          } else if (response.data[j].category === "데이터베이스") {
            this.categoryQuizList2.push(response.data[j]);
          } else if (response.data[j].category === "네트워크") {
            this.categoryQuizList3.push(response.data[j]);
          } else if (response.data[j].category === "웹") {
            this.categoryQuizList4.push(response.data[j]);
          } else if (response.data[j].category === "자료구조") {
            this.categoryQuizList5.push(response.data[j]);
          } else if (response.data[j].category === "알고리즘") {
            this.categoryQuizList6.push(response.data[j]);
          } else if (response.data[j].category === "컴퓨터구조") {
            this.categoryQuizList7.push(response.data[j]);
          } else {
            this.categoryQuizList8.push(response.data[j]);
          }
        }
      })
      .catch((error) => {
        console.log(error);
      });
  },
  methods: {
    scroll(refName) {
      var element = this.$els[refName];
      var top = element.offsetTop;

      window.scrollTo(0, top);
    },
    storeQuiz(quizId) {
      sessionStorage.setItem("quizId", quizId);
    },
  },
};
</script>

<style scoped>
@import url("https://fonts.googleapis.com/css2?family=Do+Hyeon&display=swap");

.card:hover {
  background-color: rgb(103, 112, 192);
  transform: scale(1.05);
}

.hover_text {
  font-family: "Do Hyeon", sans-serif;
  font-size: 20px;
}

.quiz {
  font-family: "Do Hyeon", sans-serif;
  font-size: 20px;
}

.tab_title {
  font-size: 40px;
  font-family: "Do Hyeon", sans-serif;
}

.tab_body {
  font-family: "Do Hyeon", sans-serif;
  font-size: 20px;
}

.tab_body2 {
  text-shadow: 0 1px 0 #ccc, 0 2px 0 #c9c9c9, 0 3px 0 #bbb, 0 4px 0 #b9b9b9,
    0 5px 0 #aaa, 0 6px 1px rgba(0, 0, 0, 0.1), 0 0 5px rgba(0, 0, 0, 0.1),
    0 1px 3px rgba(0, 0, 0, 0.3), 0 3px 5px rgba(0, 0, 0, 0.2),
    0 5px 10px rgba(0, 0, 0, 0.25), 0 10px 10px rgba(0, 0, 0, 0.2),
    0 20px 20px rgba(0, 0, 0, 0.15);
}
</style>
