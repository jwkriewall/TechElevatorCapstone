<template>
  <div class="book-container">
   <book-card v-for="b in $store.state.books" v-bind:key="b.isbn"
   :book="b"/>
   
    
  </div>
</template>

<script>
import BookCard from './BookCard.vue'



export default {
    name: 'reading-list',
    components: {
      BookCard
      
    }
}
</script>

<style>
.book-container {
    display:flex;
    justify-content: space-evenly;
    flex-wrap: wrap;
}
</style>