<template>
  <!-- add main class to div#app -->
  <div id="app">
    <product-reviews></product-reviews>

    <!-- Title and Description -->
    <!-- div.well-display -->
      <!-- Average Summary -->
      <!-- Star Summaries -->
    <!-- Add Review -->
    <!-- Review List -->
  </div>
</template>

<script>
import ProductReviews from './components/ProductReviews.vue'

export default {
  name: 'App',
  components: {
    ProductReviews
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
div.main {
    margin: 1rem 0;
}
div.main div.well-display {
    display: flex;
    justify-content: space-around;
}

div.main div.well-display div.well {
    display: inline-block;
    width: 15%;
    border: 1px black solid;
    border-radius: 6px;
    text-align: center;
    margin: 0.25rem;
}

div.main div.well-display div.well span.amount {
    color: darkslategray;
    display: block;
    font-size: 2.5rem;
}

div.main div.well-display div.well span.amount:hover {
    color: blue;
    cursor: pointer;
}
</style>
