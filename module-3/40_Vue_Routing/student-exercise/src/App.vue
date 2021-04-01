<template>
  <div id="app">
    <nav>
      <!--Implement navigation in a `<nav>` element at the top of the `App` component. 
        There must be a link to the `Home` view and a link to the `MyBooks` view.-->
      <router-link to='/'>Home</router-link>
      <router-link to='/myBooks'>My Books</router-link>
    </nav>
    <router-view/> 
  </div>
</template>

<script>

export default {
  name: 'app'
}
</script>

<style>
#app {
  font-family: 'Avenir', Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin: 60px;
}
</style>
