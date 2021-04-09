<template>
    <div class="my-tournaments">
        <h1>My Tournaments</h1>
        <display-tournaments :tournaments="allTournaments" :organizer="organizer" :userTournaments="userTournaments" />
    </div>
</template>

<script>
import DisplayTournaments from '../components/DisplayTournaments.vue';
import tournamentService from '../services/TournamentService.js';
import organizerService from '../services/OrganizerService.js';

export default {
    name: 'my-tournaments',
    data() {
        return {
            allTournaments: [],
            organizer: {},
            userTournaments: [],
            currentUser: this.$store.state.user
        }
    },
    components: { DisplayTournaments },
    created() {
        
        tournamentService.getAllTournaments().then(response =>{
            if(response.status === 200) {
                this.allTournaments = response.data;
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
        })
       
        organizerService.getOrganizer(this.$store.state.user.id).then(response => {
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
        })
        
        tournamentService.getUserTournaments(this.currentUser).then(response => {
            if(response.status === 200) {
                this.userTournaments = response.data;
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
    },
}
</script>

<style scoped>
/* h1{
    border-bottom: 2px solid #e74c3c;
    text-align: center;
} */

</style>