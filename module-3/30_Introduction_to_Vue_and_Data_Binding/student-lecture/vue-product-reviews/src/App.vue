<template>
  <div id="app">
    <product-reviews></product-reviews>
    <add-email></add-email>
  </div>
</template>

<script>

// add a component by:
// 1. add an import to script ( Javascript)
// 2. Add import name to the components section
// 3. Use the imported component as a tag in the template (HTML)

import ProductReviews from './components/ProductReviews.vue'
import AddEmail from './components/AddEmail.vue'
// .vue is optional

export default {
  name: 'App',
  components: {
    
    ProductReviews,
    AddEmail
  }
}
</script>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}
</style>
