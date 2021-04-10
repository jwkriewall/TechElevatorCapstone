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

     <div class="some-page-wrapper">
            <div class="row">
            </div>
            <div class="row">
                <div class="column">
                    <div class="p-2 alert alert-secondary">
                        <h1>{{tournament.name}}</h1>
                        <h3>Participants</h3>
                        <draggable class="list-group bracket-column" :list="rankings" group="tasks">
                            <div class="list-group-item" v-for="user in rankings" :key="user.name">
                                <div class="username">
                                    {{user.firstName}} {{user.userNickname}} {{user.lastName}}
                                </div>
                            </div>
                        </draggable>
                    </div>
                </div>

                <div class="column">
                    <div class="p-2 alert alert-primary">
                        <h3>Matchup 1</h3>
                        <draggable class="list-group bracket-column" :list="firstMatchup" v-if="firstMatchup.length <= 2" group="tasks">
                            <div class="list-group-item" v-for="user in firstMatchup" :key="user.name">
                                <div class="username">
                                {{user.firstName}} {{user.userNickname}} {{user.lastName}}
                                </div>
                            </div>
                        </draggable>
                        <draggable class="list-group bracket-column" :list="firstMatchup" v-else v-on:click="event.prevent" group="tasks">
                        </draggable>
                    </div>
                </div>

                <div class="column" v-show="tournament.maxParticipants > 2" id="column2">
                    <div class="p-2 alert alert-warning">
                        <h3>Matchup 2</h3>
                        <draggable class="list-group bracket-column" :list="secondMatchup" v-if="secondMatchup.length <= 2" group="tasks">
                            <div class="list-group-item" v-for="user in secondMatchup" :key="user.name">
                                <div class="username">
                                {{user.firstName}} {{user.userNickname}} {{user.lastName}}
                                </div>
                            </div>
                        </draggable>
                        <draggable class="list-group bracket-column" :list="secondMatchup" v-else v-on:click="event.prevent" group="tasks">
                        </draggable>
                    </div>
                </div>

                <div class="column" v-show="tournament.maxParticipants > 4 && tournament.maxParticipants != 6" id="column2">
                    <div class="p-2 alert alert-warning">
                        <h3>Matchup 3</h3>
                        <draggable class="list-group bracket-column" :list="thirdMatchup" v-if="thirdMatchup.length <= 2" group="tasks">
                            <div class="list-group-item" v-for="user in thirdMatchup" :key="user.name">
                                <div class="username">
                                {{user.firstName}} {{user.userNickname}} {{user.lastName}}
                                </div>
                            </div>
                        </draggable>
                         <draggable class="list-group bracket-column" :list="thirdMatchup" v-else v-on:click="event.prevent" group="tasks">
                        </draggable>
                    </div>
                </div>

                <div class="column" v-show="tournament.maxParticipants > 6" id="column2">
                    <div class="p-2 alert alert-warning">
                        <h3>Matchup 4</h3>
                        <draggable class="list-group bracket-column" :list="fourthMatchup" v-if="fourthMatchup.length <= 2" group="tasks">
                            <div class="list-group-item" v-for="user in fourthMatchup" :key="user.name">
                                <div class="username">
                                {{user.firstName}} {{user.userNickname}} {{user.lastName}}
                                </div>
                            </div>
                        </draggable>
                        <draggable class="list-group bracket-column" :list="fourthMatchup" v-else v-on:click="event.prevent" group="tasks">
                        </draggable>
                    </div>
                </div>

                <div class="column" v-show="tournament.maxParticipants > 8">
                    <div class="p-2 alert alert-warning">
                        <h3>Matchup 5</h3>
                        <draggable class="list-group bracket-column" :list="fifthMatchup" v-if="fifthMatchup.length <= 2" group="tasks">
                            <div class="list-group-item" v-for="user in fifthMatchup" :key="user.name">
                                <div class="username">
                                {{user.firstName}} {{user.userNickname}} {{user.lastName}}
                                </div>
                            </div>
                        </draggable>
                        <draggable class="list-group bracket-column" :list="fifthMatchup" v-else v-on:click="event.prevent" group="tasks">
                        </draggable>
                    </div>
                </div>

                <div class="column" v-show="tournament.maxParticipants > 10">
                    <div class="p-2 alert alert-warning">
                        <h3>Matchup 6</h3>
                        <draggable class="list-group bracket-column" :list="sixthMatchup" v-if="sixthMatchup.length <= 2" group="tasks">
                            <div class="list-group-item" v-for="user in sixthMatchup" :key="user.name">
                                <div class="username">
                                {{user.firstName}} {{user.userNickname}} {{user.lastName}}
                                </div>
                            </div>
                        </draggable>
                        <draggable class="list-group bracket-column" :list="sixthMatchup" v-else v-on:click="event.prevent" group="tasks">
                        </draggable>
                    </div>
                </div>

                <div class="column" v-show="tournament.maxParticipants > 12">
                    <div class="p-2 alert alert-warning">
                        <h3>Matchup 7</h3>
                        <draggable class="list-group bracket-column" :list="seventhMatchup" v-if="seventhMatchup.length <= 2" group="tasks">
                            <div class="list-group-item" v-for="user in seventhMatchup" :key="user.name">
                                <div class="username">
                                {{user.firstName}} {{user.userNickname}} {{user.lastName}}
                                </div>
                            </div>
                        </draggable>
                        <draggable class="list-group bracket-column" :list="seventhMatchup" v-else v-on:click="event.prevent" group="tasks">
                        </draggable>
                    </div>
                </div>

                <div class="column" v-show="tournament.maxParticipants > 14">
                    <div class="p-2 alert alert-warning">
                        <h3>Matchup 8</h3>
                        <draggable class="list-group bracket-column" :list="eighthMatchup" v-if="eighthMatchup.length <= 2" group="tasks">
                            <div class="list-group-item" v-for="user in eighthMatchup" :key="user.name">
                                <div class="username">
                                {{user.firstName}} {{user.userNickname}} {{user.lastName}}
                                </div>
                            </div>
                        </draggable>
                        <draggable class="list-group bracket-column" :list="eighthMatchup" v-else v-on:click="event.prevent" group="tasks">
                        </draggable>
                    </div>
                </div>

                <div class="column" v-show="tournament.maxParticipants == 6">
                    <div class="p-2 alert alert-warning">
                        <h3>Second Round: 1 Bye</h3>
                        <draggable class="list-group bracket-column" :list="secondRoundByeMatchup" v-if="secondRoundByeMatchup.length <= 2" group="tasks">
                            <div class="list-group-item" v-for="user in secondRoundByeMatchup" :key="user.name">
                                <div class="username">
                                {{user.firstName}} {{user.userNickname}} {{user.lastName}}
                                </div>
                            </div>
                        </draggable>
                        <draggable class="list-group bracket-column" :list="secondRoundByeMatchup" v-else v-on:click="event.prevent" group="tasks">
                        </draggable>
                    </div>
                </div>

                <div class="column" v-show="tournament.maxParticipants == 6">
                    <div class="p-2 alert alert-warning">
                        <h3>Second Round: 2 Bye</h3>
                        <draggable class="list-group bracket-column" :list="secondRoundByeMatchupTwo" v-if="secondRoundByeMatchupTwo.length <= 2" group="tasks">
                            <div class="list-group-item" v-for="user in secondRoundByeMatchupTwo" :key="user.name">
                                <div class="username">
                                {{user.firstName}} {{user.userNickname}} {{user.lastName}}
                                </div>
                            </div>
                        </draggable>
                        <draggable class="list-group bracket-column" :list="secondRoundByeMatchupTwo" v-else v-on:click="event.prevent" group="tasks">
                        </draggable>
                    </div>
                </div>



            </div>
    </div>

</div>


<!-- Drag and drop attempt below -->

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
            fourthMatchup: [],
            fifthMatchup: [],
            sixthMatchup: [],
            seventhMatchup: [],
            eighthMatchup: [],
            secondRoundByeMatchup: [],
            secondRoundByeMatchupTwo: []
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
    padding: 10px 0;
    margin: 10px;
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
    display: flex;
    width: 100vw;
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

h2 {
    font-family: 'Poppins';
}

h3 {
    padding-left: 20px;
}


.bracket-column {
    min-height: 100px;
}


.row {
  display: flex;
  flex-direction: row;
  flex-wrap: wrap;
  width: 100%;
}

.column {
  display: flex;
  flex-direction: column;
  flex-basis: 100%;
  flex: 1;
}



</style>