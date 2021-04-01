<template>
  <div>
      <router-view v-bind:to="{path: '/book/:id', params: {isbn: book.isbn}}" >Hi</router-view>
      {{ this.$store.book.title }}
  </div>
</template>

<!-- Create a new component called `BookDetails` that shows all of 
a book's details on the screen. A basic version would be something like this:

![Book Detail Page](./etc/book-detail-page.png)

Create a view and a dynamic route with the path `/book/` 
plus the `isbn` number on the end—for example, `/book/9781400079278`. 
Use that number to look up the book for the `BookDetails` component.

Finally, link the `BookCard` components in the `ReadingList` component 
to this new route, making sure to have the book's `isbn` as part of the 
link when the `BookCard` is clicked. -->

<script>
export default {
    computed: {
           individualBook() {
            return this.$store.state.products.find( book => {
                return book.isbn == this.$store.state.activeProduct;
            })
        }
    }
}
</script>

<style>

</style>