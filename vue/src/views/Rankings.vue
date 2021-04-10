<template>

<div>

    <draggable v-model="rankings" ghost-class="ghost" @end="onEnd">
        <transition-group type="transition" name="flip-list" class="players">
            <div class="sortable" :id="user.id" v-for="user in rankings" :key="user.userId">
                <strong>{{user.firstName}} {{user.userNickname}} {{user.lastName}}</strong>
            </div>
        </transition-group>
    </draggable>

</div>




<!-- <div class="drop-zone" @ondrop="onDrop($event, user)" @dragenter.prevent @dragover.prevent>
  <div>
        <div v-for="user in rankings" :key="user.id" draggable="true" id="target" @ondragstart='startDrag($event, user)'
        @drop='dragFinish($event, user)'>
        {{user.firstName}} {{user.lastName }} 
        </div>
   </div>
</div> -->
</template>

<script>
import organizerService from "../services/OrganizerService.js";
import tournamentService from "../services/TournamentService.js";
import draggable from 'vuedraggable';

export default {
    components: {
        draggable
    },
    name: 'rankings',
 
    data(){
        return {
            tournament: {},
            rankings: [],
            oldIndex: '',
            newIndex: ''
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
      
    },
    methods: {
        onEnd: function(event) {
            console.log(event)
            this.oldIndex = event.oldIndex;
            this.newIndex = event.newIndex;
        }

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