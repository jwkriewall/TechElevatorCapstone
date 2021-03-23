<template>
  <div id="addemail">
      <h1>Add Email</h1>
  
  <div>
      <label for="name"> NAME:</label>
      <input type="text" id="email" v-model="email.name"/>
  </div>

</div>

</template>

<script>
export default {
    name: 'add-email',
    data() {
        return {
            email: {
                name: '',
                email: '',
                type: ''
            }
        }
    }
}
</script>

<style>

</style>