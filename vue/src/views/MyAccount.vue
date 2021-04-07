<template>
    <div id="my-account">
        <h1>Account Information</h1>
        
        <div class="user-info">
            <ul>
                <li>Name: {{ user.firstName}} {{user.lastName}}</li>
                <li>Email: {{ user.email}}</li>
                <li>Phone: {{user.phone}}</li>
                <li>Username: {{user.username}}</li>
            </ul>
        </div>
        <input type="submit" value="Modify" @click.prevent="modify=true" />
        <div v-if="modify" class="edit-info">
            <edit-user v-bind:user="user" />
        </div>

    </div>
</template>

<script>
import EditUser from '../components/EditUser.vue';

export default {
  components: { EditUser },
    data() {
        return {
            user: this.$store.state.user,
            modify: false
        }
    }
}
</script>

<style>

</style>