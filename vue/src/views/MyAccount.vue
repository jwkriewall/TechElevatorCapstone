<template>
    <div id="my-account">
        <div class="content">
            <div v-show="!modifyUser && !modifyOrganizer" class="user-information">
                <user-info />
                <input  type="submit" value="Modify" @click.prevent="toggleModifyUser" />
            </div>
            <div v-show="!modifyOrganizer && organizer.organizerId && !modifyUser" class="organizer-information">
                <organizer-info  :organizer="organizer" />
                <input type="submit" value="Modify" @click.prevent="toggleModifyOrganizer" />
            </div>
            <div v-show="modifyUser" class="edit-user-info">
                <h1>Update User Information</h1>
                <edit-user />
            </div>

            <div v-if="organizer.organizerId && modifyOrganizer" class="edit-organizer-info">
                <h1>Update Organizer Information</h1>
                <edit-organizer :organizer="organizer" />
            </div>
        </div>
        <div class="image">
            <img src="@/assets/ps4.jpg" alt="">
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

.image{
    width: 45vw;
    overflow: hidden;

}

.image img{
    width: 70vw;
    margin: 0;
    padding: 0;
    grid-area: right;
}



div#my-account {
    display: flex;
    /* display: grid;
    grid-template: 'left right'; */
   
}

div{
    /* grid-area: left; */
    display: grid;
}
</style>