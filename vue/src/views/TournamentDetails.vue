<template>
  <div class="details">
      <h1>{{tournament.name}} Details</h1>
      <ul>
          <li>Tournament Name: {{tournament.name}}</li>
          <li>Max. Participants: {{tournament.maxParticipants}}</li>
          <li v-if="tournament.team" >Team Tournament</li>
          <li v-if="!tournament.team" >Individual Tournament</li>
          <li>Organizer Name: {{organizer.firstName}} {{organizer.lastName}}</li>
          <li>Organizer Email: {{organizer.email}}</li>
      </ul>
  </div>
</template>

<script>
import organizerService from "../services/OrganizerService.js";
import tournamentService from "../services/TournamentService.js";

export default {
    name: 'tournament-details',
    data(){
        return {
            tournament: {},
            organizer: {}
        }
    },
    created(){
        const tournamentID = this.$route.params.id;
        tournamentService.getTournament(tournamentID).then(response => {
            if(response.status === 200){
                this.tournament = response.data;
                organizerService.getOrganizerInfo(response.data.organizerId).then(response => {
                    if(response.status === 200){
                        this.organizer = response.data;
                    }
                });
            }
        });
    }
}
</script>

<style>

</style>