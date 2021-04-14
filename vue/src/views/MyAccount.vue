<template>
    <div class="container">
        <div class="content">
            <div v-show="!modifyUser && !modifyOrganizer" class="user-information">
                <user-info />
                <input  type="button" value="Modify" @click.prevent="toggleModifyUser" />
            </div>
            <div v-show="!modifyOrganizer && organizer.organizerId && !modifyUser" class="organizer-information">
                <organizer-info  :organizer="organizer" />
                <input type="button" value="Modify" @click.prevent="toggleModifyOrganizer" />
            </div>
            <div v-show="modifyUser" class="edit-user-info">
                <h1>Update User Information</h1>
                <edit-user />
            </div>

            <div v-if="organizer.organizerId && modifyOrganizer" class="edit-organizer-info">
                <edit-organizer :organizer="organizer" />
            </div>
        </div>
        <div class="image">
            <img src="@/assets/ps4.jpg" alt="Gaming Consoles on table" />
        </div>
    </div>
</template>

<script>
import EditUser from '../components/EditUser.vue';
import UserInfo from '../components/UserInfo.vue';
import EditOrganizer from '../components/EditOrganizer.vue'
import organizerService from '../services/OrganizerService.js'
import OrganizerInfo from '../components/OrganizerInfo.vue'

export default {
  components: { EditUser, UserInfo, EditOrganizer, OrganizerInfo },
    data() {
        return {
            user: this.$store.state.user,
            organizer: {}
        }
    },
    methods: {
        toggleModifyUser() {
            this.$store.commit('TOGGLE_MODIFY_USER');
        },
        toggleModifyOrganizer() {
            this.$store.commit('TOGGLE_MODIFY_ORGANIZER');
        }
    },
    computed: {
        modifyUser() {
            return this.$store.state.modifyUserInfo
        },
        modifyOrganizer() {
            return this.$store.state.modifyOrganizer
        }
    },
    created() {
        organizerService.getOrganizer(this.user.id).then(response => {
            if(response.status === 200) {
                this.organizer = response.data;
            }
        })
        .catch(error => {
        if (error.response) {
            //alert(error.response.statusText);
        } else if (error.request) {
            alert("Server could not be reached.");
        } else {
            alert("Request could not be created.");
        }
        });
    }
}
</script>

<style scoped>


td.label {
    font-size: 1.1rem;
    font-weight: bold;
    min-width: 150px;
}
table {
    border-collapse: collapse;
}
tr {
    line-height: 2rem;
}
tr:nth-child(odd) {
    background-color: #f3f3f3;
    color: black;
}
input[type="button"]:nth-child(2) {
    margin-bottom:30px;
}
</style>