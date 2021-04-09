<template>
<div class="drop-zone" @ondrop="onDrop($event, user)" @dragenter.prevent @dragover.prevent>
  <div>
      <div v-for="user in rankings" :key="user.id" class="drag-el" 
      draggable="true" @ondragstart='startDrag($event, user)'>
     {{user.firstName}} {{user.lastName }}
    </div>
   </div>
</div>
</template>

<script>
import organizerService from "../services/OrganizerService.js";
import tournamentService from "../services/TournamentService.js";

export default {
    name: 'rankings',
    setup() {
        const startDrag = (event, user) => {
            console.log(user)
            event.dataTransfer.dropEffect = 'move'
            event.dataTransfer.effectAllowed = 'move'
            event.dataTransfer.setData('userID', user.userId)
        }

        const onDrop = (event, user) => {
            console.log(user)
            const userID = event.dataTransfer.getData('userID')
            const theUser = user.value.find((user) => user.id == userID)
            user = theUser;

        }

        return {
            rankings: [],
            startDrag,
            onDrop
        }
    },
    data(){
        return {
            tournament: {},
            rankings: [],
    }
    },
    created() {
        const tournamentID = this.$route.params.id;
        tournamentService.getTournament(tournamentID).then(response => {
            if(response.status === 200){
                this.tournament = response.data;
                organizerService.getOrganizerInfo(response.data.organizerId).then(response => {
                    if(response.status === 200){
                        this.organizer = response.data;
                        if(this.$store.state.user.id == this.organizer.userId) {
                            this.isCurrentUserOrganizer = true;
                        }
                    }
                });
            }
        });
        tournamentService.getTournamentRankings(tournamentID).then(response => {
            if(response.status === 200) {
                this.rankings = response.data;
            }
        });
    }
}
</script>

<style>

.drop-zone {
    width: 50%;
    margin: 50px auto;
    background-color: #ecf0f1;
    padding: 10px;
    min-height: 10px;
}

.drag-el {
    background-color: #3498db;
    color: white;
    padding: 5px;
    margin-bottom: 10px;
}


</style>