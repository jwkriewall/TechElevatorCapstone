<template>
  <div class="newBookForm">
    <form v-on:submit.prevent="addNewBook()">
        <div>
            <label class="title-input">Title: </label>
            <input type="text" class="title" v-model="newBook.title" />   
        </div>
        <div>
            <label class="author-input">Author: </label>
            <input type="text" class="author" v-model="newBook.author" />
        </div>
        <div>
            <label class="isbn-input">ISBN: </label>
            <input type="text" class="isbn" v-model="newBook.isbn" />
        </div>
        <input type="submit" value="Save" />
        <input type="button" value="Cancel" @click.prevent="resetForm" />
    </form>
  </div>
</template>

<script>
export default {
    name: 'new-book',
    data() {
        return {
            newBook: {
                title: '',
                author: '',
                read: false,
                isbn: ''
            }
            
        }
    },
    methods: {

        addNewBook() {

            this.$store.commit("ADD_BOOK", this.newBook);
            this.resetForm();
        },
        resetForm() {
            this.newBook = {
                read: false
            };
            //this.showForm = false;
        }
    }
}
</script>

<style>

</style>

