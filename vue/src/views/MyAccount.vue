<template>
    <div id="my-account">
        <div class="user-info">
            <h1>Account Information</h1>
            <ul>
                <li>Name: {{ user.firstName}} {{user.lastName}}</li>
                <li>Email: {{ user.email}}</li>
                <li>Phone: {{user.phone}}</li>
                <li>Username: {{user.username}}</li>
            </ul>
        
            <input type="submit" value="Modify" @click.prevent="toggleModify" />
            <div v-if="modify" class="edit-info">
                <edit-user v-bind:user="user" />
            </div>
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
    },
    methods: {
        toggleModify() {
            this.modify = !this.modify;
        }
    }
}
</script>

<style>
.user-info {
    width: 50vw;
    background-color: #2C3E50;
    padding: 20px 20px 20px 50px;
    color: white;
}

ul {
    list-style-position: outside;
    list-style-type: none;
    padding: 0;
}

ul li {
    line-height: 2rem;
    padding:0 10px;
    border-radius:10px;
}

ul li:nth-child(odd) {
    background-color: #f4f4f4;
    color: black;
}

</style>