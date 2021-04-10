<template>

<div>
    <!-- <h1>Participants</h1>
    <draggable v-model="rankings" ghost-class="ghost" @end="onEnd">
        <transition-group type="transition" name="flip-list" class="players">
            <div class="sortable" :id="user.id" v-for="user in rankings" :key="user.userId">
                <strong>{{user.firstName}} {{user.userNickname}} {{user.lastName}}</strong>
            </div>
        </transition-group>
    </draggable> -->

     <div class="container mt-5">
            <div class="row">
            </div>
            <div class="row mt-3">
                <div class="col-md-3">
                    <div class="p-2 alert alert-secondary">
                        <h1>Participants</h1>
                        <draggable class="list-group" :list="rankings" group="tasks">
                            <div class="list-group-item" v-for="user in rankings" :key="user.name">
                                <div class="username">
                                    {{user.firstName}} {{user.userNickname}} {{user.lastName}}
                                </div>
                            </div>
                        </draggable>
                    </div>
                </div>

                <div class="col-md-3">
                    <div class="p-2 alert alert-primary">
                        <h3>Matchup 1</h3>
                        <draggable class="list-group" :list="firstMatchup" group="tasks">
                            <div class="list-group-item" v-for="user in firstMatchup" :key="user.name">
                                <div class="username">
                                {{user.firstName}} {{user.userNickname}} {{user.lastName}}
                                </div>
                            </div>
                        </draggable>
                    </div>
                </div>

                <div class="col-md-3" v-show="tournament.maxParticipants > 2">
                    <div class="p-2 alert alert-warning">
                        <h3>Matchup 2</h3>
                        <draggable class="list-group" :list="secondMatchup" group="tasks">
                            <div class="list-group-item" v-for="user in secondMatchup" :key="user.name">
                                <div class="username">
                                {{user.firstName}} {{user.userNickname}} {{user.lastName}}
                                </div>
                            </div>
                        </draggable>
                    </div>
                </div>

                <div class="col-md-3" v-show="tournament.maxParticipants > 4">
                    <div class="p-2 alert alert-warning">
                        <h3>Matchup 3</h3>
                        <draggable class="list-group" :list="thirdMatchup" group="tasks">
                            <div class="list-group-item" v-for="user in thirdMatchup" :key="user.name">
                                <div class="username">
                                {{user.firstName}} {{user.userNickname}} {{user.lastName}}
                                </div>
                            </div>
                        </draggable>
                    </div>
                </div>

                <div class="col-md-3" v-show="tournament.maxParticipants > 6">
                    <div class="p-2 alert alert-warning">
                        <h3>Matchup 4</h3>
                        <draggable class="list-group" :list="fourthMatchup" group="tasks">
                            <div class="list-group-item" v-for="user in fourthMatchup" :key="user.name">
                                <div class="username">
                                {{user.firstName}} {{user.userNickname}} {{user.lastName}}
                                </div>
                            </div>
                        </draggable>
                    </div>
                </div>

                <div class="col-md-3" v-show="tournament.maxParticipants > 8">
                    <div class="p-2 alert alert-warning">
                        <h3>Matchup 5</h3>
                        <draggable class="list-group" :list="fifthMatchup" group="tasks">
                            <div class="list-group-item" v-for="user in fifthMatchup" :key="user.name">
                                <div class="username">
                                {{user.firstName}} {{user.userNickname}} {{user.lastName}}
                                </div>
                            </div>
                        </draggable>
                    </div>
                </div>

                <div class="col-md-3" v-show="tournament.maxParticipants > 10">
                    <div class="p-2 alert alert-warning">
                        <h3>Matchup 6</h3>
                        <draggable class="list-group" :list="sixthMatchup" group="tasks">
                            <div class="list-group-item" v-for="user in sixthMatchup" :key="user.name">
                                <div class="username">
                                {{user.firstName}} {{user.userNickname}} {{user.lastName}}
                                </div>
                            </div>
                        </draggable>
                    </div>
                </div>

                <div class="col-md-3" v-show="tournament.maxParticipants > 12">
                    <div class="p-2 alert alert-warning">
                        <h3>Matchup 7</h3>
                        <draggable class="list-group" :list="seventhMatchup" group="tasks">
                            <div class="list-group-item" v-for="user in seventhMatchup" :key="user.name">
                                <div class="username">
                                {{user.firstName}} {{user.userNickname}} {{user.lastName}}
                                </div>
                            </div>
                        </draggable>
                    </div>
                </div>

                <div class="col-md-3" v-show="tournament.maxParticipants > 14">
                    <div class="p-2 alert alert-warning">
                        <h3>Matchup 8</h3>
                        <draggable class="list-group" :list="eightMatchup" group="tasks">
                            <div class="list-group-item" v-for="user in eighthMatchup" :key="user.name">
                                <div class="username">
                                {{user.firstName}} {{user.userNickname}} {{user.lastName}}
                                </div>
                            </div>
                        </draggable>
                    </div>
                </div>



            </div>
    </div>

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
            newIndex: '',
            firstMatchup:[],
            secondMatchup: [],
            thirdMatchup: [],
            fourthMatchup: []
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
        },
        add() {
            if(this.rankings) {
                this.rankings.push({name: this.rankings})
                this.rankings = "";
            }
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

.list-group-item {
    padding: 20px 0;
    margin: 30px;
    /* border: 3px solid #44617e; */
    width: 20vw;
    background-color: rgb(246, 244, 244);
    color: #44617e;
    border-radius: 15px;
    box-shadow: 5px 5px 15px rgb(239, 239, 239), 
            -5px -5px 30px #d9d9da;
}

.username {
    padding-left: 20px;
}

strong {
    padding-left: 20px;
}

h1 {
    color: #44617e;
    padding-left: 40px;
    padding-top: 40px;
    margin-left: 0px;
}

.sortable-drag {
    opacity: .2;
}

.flip-list-move {
    transition: transform 0.5s;
}

.ghost {
    box-shadow: 10px 10px 5px -1px rgba(0,0,0,0, 0.14);
}

h3 {
    padding-left: 20px;
}

</style>