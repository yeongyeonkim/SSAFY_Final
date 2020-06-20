<template>
    <v-layout justify-center>
        <v-flex sm12 md7 my-3>
            <v-card elevation="0">
                <v-layout justify-center>
                    <v-flex pa-3>
                        <v-row justify="center">
                            <v-col>
                                <v-card elevation="2" height="150px">
                                     
                                    <v-card-title class="justify-center"> {{"~~~~문제 내용~~~"}} </v-card-title>
                                    
                                </v-card>
                            </v-col>
                        </v-row>
                        <v-row>
                            <v-col>
                                <v-card elevation="2" height="250px">
                                    <v-layout justify-center my-5 px-4>
                                        <v-flex xs8 md3>
                                            <v-row justify="center">
                                                <v-col>
                                                    <v-card-text style="font-size: 27px">{{time}}</v-card-text>
                                                </v-col>
                                            </v-row>
                                        </v-flex>
                                    </v-layout>
                                    <v-divider></v-divider>
                                    <v-row justify="center">
                                        <v-col>
                                            <v-layout justify-center my-5>
                                                <v-btn color="error" dark large v-on:click="start()">
                                                    <v-icon>mdi-play</v-icon>
                                                </v-btn>
                                                <v-btn color="error" dark large v-on:click="stop()">
                                                    <v-icon>mdi-stop</v-icon>
                                                </v-btn>
                                                <v-btn color="error" dark large v-on:click="reset()">
                                                    <v-icon>mdi-backup-restore</v-icon>
                                                </v-btn>
                                            </v-layout>
                                        </v-col>
                                    </v-row>
                                    <v-row>
                                        <v-col>
                                            <audio-recorder
                                                upload-url="some url"
                                                :attempts="3"
                                                :time="2"
                                                :before-recording="callback"
                                                :after-recording="callback"
                                                :before-upload="callback"
                                                :successful-upload="callback"
                                                :failed-upload="callback"/>
                                        </v-col>
                                    </v-row>
                                </v-card>
                            </v-col>
                        </v-row>
                        <v-row>
                            <v-col>
                                <v-layout justify-end>
                                    <v-card elevation="0">
                                        <v-btn
                                        color="rgb(48,140,233)"
                                        class="ma-2 white--text"
                                        >
                                        건너뛰기
                                        </v-btn>
                                    </v-card>
                            </v-layout>
                            </v-col>
                        </v-row>
                    </v-flex>
                </v-layout>
            </v-card>
        </v-flex>
    </v-layout>
</template>

<script>
export default {
    data () {
      return {
        title: '',
        rules: [v => v.length <= 100 || 'Max 100 characters'],
      }
    },
    methods:{
        callback (msg) {
            console.debug('Event: ', msg)
        }
    }
}
</script>
