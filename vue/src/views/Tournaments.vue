<template>
  <div class="tournaments">
      <display-tournaments :organizer="organizer" :tournaments="tournaments" :userTournaments="userTournaments" />
  </div>
</template>

<script>
import displayTournaments from '../components/DisplayTournaments.vue';
import tournamentService from '../services/TournamentService.js'
import organizerService from "../services/OrganizerService.js";

export default {
    components: { displayTournaments },
    data() {
        return {
            tournaments: [],
            organizer: {},
            userTournaments: []
        }
    },
    created() {
        tournamentService.getAllTournaments().then(response =>{
            if(response.status === 200) {
                this.tournaments = response.data;
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
        
        if(this.$store.state.user.id > 0) {
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
            });
             tournamentService.getUserTournaments().then(response => {
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
        }
        
    },
}
</script>

<style>

</style>