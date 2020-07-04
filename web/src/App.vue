<template>
  <MainPage id="app"/>
</template>

<script>
import MainPage from './components/MainPage.vue'

export default {
  name: 'App',
  components: {
    MainPage
  }
}
</script>

<style lang="scss">
$text-color: #2c3e50;
$bg-color: #F0F8FF;

body, html{
  width: 100%;
  height: 100%;
  background-color: $bg-color;
}

#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: $text-color;
  margin: 0;
  padding: 0;
}
</style>
