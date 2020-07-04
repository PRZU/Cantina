<template>
    <b-navbar type="light" variant="light">
        <b-navbar-nav class="ml-auto mr-auto">
            <b-nav-item to="/about">
                <small>© 2020 Copyright Cantina App</small>
            </b-nav-item>
        </b-navbar-nav>
    </b-navbar>
</template>

<script>
export default {
  name: 'Footer'
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style lang="scss" scoped>

small{
    font-size: 14px;
    padding: 0;
    margin: 0;
}
</style>
