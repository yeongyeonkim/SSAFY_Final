<template>
  <v-app>
    <toolbar/>   
    <route-view :key="$route.fullPath"/>
  </v-app>
</template>

<script>
import Toolbar from "@/components/Toolbar";
import RouteView from "@/components/RouteView";

export default {
  name: "App",
  components: {
    Toolbar,
    RouteView,
  },
  data() {
    return {};
  },
};
</script>
