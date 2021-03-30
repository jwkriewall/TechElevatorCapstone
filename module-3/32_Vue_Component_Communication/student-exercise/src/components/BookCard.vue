<template>
  <div class="card">
    <h2 v-bind:class="book.title"> {{book.title}} </h2>
    <img v-if="book.isbn" v-bind:src="'http://covers.openlibrary.org/b/isbn/' + book.isbn + '-M.jpg'" />
    <h3 v-bind:class="book.author"> {{book.author}} </h3>
    <button v-on:click="iReadThisBook(book)" >{{ book.read == true ? 'Read' : 'To Read' }}</button>
  </div>

  
</template>


<script>

export default {
    name: 'book-card',
    props: ['book'],
    methods: {
        iReadThisBook(book){
            this.$store.commit('READ_BOOK', book)
        }
    }

 
}
</script>

<style>
.card {
    border: 2px solid black;
    border-radius: 10px;
    width: 250px;
    height: 550px;
    margin: 20px;
}

.card.read {
    background-color: lightgray;
}

.card .book-title {
    font-size: 1.5rem;
}

.card .book-author {
    font-size: 1rem;
}
</style>