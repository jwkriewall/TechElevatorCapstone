<template>
  <div id="addemail">
      <h1>Add Email</h1>
      <div>
          <label for="name">Name:</label>
          <input type="text" id="name" v-model="email.name" />
      </div>
      <div>
          <label for="email">Email:</label>
          <input type="text" id="email" v-model="email.email" />
      </div>
      <div>
          <label for="emailType">Email Type:</label>
          <select id="emailType" v-model="email.type">
              <option value="HOME">Home</option>
              <option value="WORK">Work</option>
              <option value="OTHER">Other</option>
          </select>
      </div>
      <div>
          <h2>You Selected</h2>
          <div>
              Name: {{ email.name }}
          </div>
          <div>
              Email: {{ email.email }}
          </div>
          <div>
              Email Type: {{ email.type }}
          </div>
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