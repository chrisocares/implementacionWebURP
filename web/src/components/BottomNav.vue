<template>

  <v-bottom-nav app fixed :value="true">

    <v-btn flat color="primary" :to="{ path: '/'}">
      <span>Me gustan</span>
      <v-icon>thumb_up</v-icon>
    </v-btn>

    <v-btn flat color="primary" :to="{ name: 'code-examples'}">
      <span>Mis publicaciones</span>
      <v-icon>code</v-icon>
    </v-btn>

    <v-btn flat color="primary" :to="{ name: 'my-favorites'}">
      <span>Mis favoritos</span>
      <v-icon>favorite</v-icon>
    </v-btn>
  </v-bottom-nav>

</template>

<script lang="ts">
  import { Component, Vue } from 'vue-property-decorator';
  import axios from "axios";

  @Component
  export default class BottomNav extends Vue {

  }
</script>
