<template>
  <form v-on:submit.prevent>
    <div class="field">
      <label for="title">Title</label>
      <input type="text" name="title" v-model="message.title" />
    </div>
    <div class="field">
      <label for="messageText">Message</label>
      <input type="text" name="messageText" v-model="message.messageText" />
    </div>
    <div class="actions">
      <button type="submit" v-on:click="saveMessage(message)">Save Message</button>
    </div>
  </form>
</template>

<script>
import messageService from "../services/MessageService";

export default {
  name: "create-message",
  props: ["topicId"],
  data() {
    return {
      message: {
        id: Math.floor(Math.random() * (1000 - 100) + 100),
        topicId: this.topicId,
        title: "",
        messageText: ""
      }
    };
  },
  methods: {
    saveMessage(message) {
      messageService.addMessage(this.message)
      .then(response => {
        if (response.status === 201) {
          this.$router.push(`/${message.topicId}`);
          
        }
      })
      .catch(error => {
          this.handleErrorResponse("Working on it!");
        
      });
    }
  }
};
</script>

<style>
</style>

<!--Open `/src/components/CreateMessage.vue`. You'll see that the 
`saveMessage()` method is empty. 
You'll need to call the method you created in `MessageService`. 

When the promise is returned, check the status code 
to make sure the new topic was created (201), 
and then use the router to forward the user to `/${message.topicId}`. -->
