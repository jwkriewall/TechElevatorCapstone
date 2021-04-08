<template>
    <div id="my-account">
        <div class="content">
            <user-info v-show="!modify" />
            <input type="submit" value="Modify" @click.prevent="toggleModify" />
            <div v-show="modify" class="edit-info">
                <h1>Update User Information</h1>
                <edit-user />
            </div>
        </div>
        <div class="image">
        </div>
    </div>
</template>

<script>
import EditUser from '../components/EditUser.vue';
import UserInfo from '../components/UserInfo.vue';

export default {
  components: { EditUser, UserInfo },
    data() {
        return {
            user: this.$store.state.user,
            modify: this.$store.state.modifyUserInfo
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
.content {
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