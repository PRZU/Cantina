<template>
  <b-container fluid>
    <b-navbar type="dark" variant="dark">
      <b-navbar-brand href="#">Cantina App</b-navbar-brand>
      <b-navbar-nav>
        <b-nav-item href="#">Home</b-nav-item>
      </b-navbar-nav>
      <b-navbar-brand href="#/info">
        <b-icon icon="info-circle"></b-icon>
      </b-navbar-brand>
    </b-navbar>

  </b-container>
</template>

<script>
export default {
  name: 'MainPage',
  props: {
    msg: String
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style lang="scss" scoped>

</style>
