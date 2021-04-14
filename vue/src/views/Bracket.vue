<template>
  <div class="content">
    <h1> Bracket</h1>
    <div class="tournament" :style="{ 'grid-template-columns': getColumns}">
      <div class="participant-list">Participants List
        
        <draggable :list="rankings" group="tasks" @end="onEnd">
          <div class="list-group-item" v-for="user in rankings" :key="user.name">
              <div class="username-participant">
                  <strong> {{user.firstName}} {{user.userNickname}} {{user.lastName}} </strong>
              </div>
          </div>
        </draggable>
      </div>

      <h2 v-for="headerIndex in roundMatchups.length" :key="headerIndex.id">Round {{headerIndex}}  </h2>
      <div v-for="roundIndex in roundMatchups.length" :key="roundIndex.id" :class="`round ${roundIndex}`">
        <!-- <div v-for="matchIndex in roundMatchups[roundIndex-1]" :key="matchIndex.id" v-bind:class="`matchup ${getMatchupNumber(matchIndex-1)}`"> -->
          <draggable :list="bracketArray[roundIndex-1]" group="tasks" class="draggable" @end="onEnd" >
            <!-- <div class="participant" v-for="(participant, index) in tournamentMatchups[roundIndex-1]" :key="participant.id"> -->
            <div :class="`participant participant-${index+1}`" @click="event=$event.data" v-for="(participant, index) in bracketArray[roundIndex-1]" :key="participant.id">
              <div class="seed">{{ participant ? index + 1 : '&nbsp;' }}</div>
              <div class="name">{{ participant.userNickname ? participant.userNickname : 
                  ( (participant.firstName + participant.lastName) ? participant.firstName + ' ' + participant.lastName : "" ) }}</div>
            </div>
            <div class="champion" v-if="roundIndex === roundMatchups.length">
              <div class="seed">#</div>
              <div class="name"></div>
            </div>
          </draggable>
          <!-- <draggable :list="bracketArray[roundIndex-1]" group="tasks" class="draggable" v-else v-on:click.prevent=""></draggable> -->
        <!-- </div> -->
        
      </div>
    </div>
  </div>
</template>

<script>
import organizerService from "../services/OrganizerService.js";
import tournamentService from "../services/TournamentService.js";
import draggable from 'vuedraggable';

export default {
    components: {
        draggable,
    },
  data() {
    return {
      tournament: {},
      participantsArray: [1,2,3,4,5,6,7,8,9,10],
      rankings: [],
      maxParticipants: '',
      totalParticipants: 10,
      roundMatchups: [0],
      tournamentMatchups: [],
      tournamentSeeding: [],
      bracketArray1: [],
      bracketArray2: [],
      bracketArray: [],
      numberCheck: '',
      bracketCheck: [],
      event: '',
      newIndex: '',
      oldIndex: ''
    }
  },
  methods: {
    onEnd: function(evt) {
        console.log(evt)
        this.oldIndex = evt.oldIndex;
        this.newIndex = evt.newIndex;
        // this.updatePosition(evt.newIndex);
        this.numberCheck = evt.to.parentElement.classList[1] - 1;
        },
    add() {
        if(this.rankings) {
          this.rankings.push({name: this.rankings})
          this.rankings = "";
            }
        },
    updatePosition(evt) {
     let newIndex = evt.newIndex;
     let roundIndex = evt.to.parentElement.classList[1] - 1;

     let oldValue = (this.bracketArray[roundIndex])[newIndex];
     let blankFound = false;

     for(let i = 0; i < this.bracketArray[roundIndex].length; i++) {
       if((this.bracketArray[roundIndex])[i] === ' ') {
         oldValue = (this.bracketArray[roundIndex])[i];
         (this.bracketArray[roundIndex]).splice(i,1);
         blankFound = true;
         break;
       }
     }
     if(!blankFound) {
        this.bracketArray[roundIndex].splice(newIndex,1);
        }
     this.rankings.push(oldValue);
    },
    getMatchupNumber(index) {
      return index + 1;
    },
    createRoundMatchups() {
        let total = this.totalParticipants
        
        //check if odd, if yes, increase by 1
        if(total % 2 != 0) { total += 1; }

        let currentCount = 1;  

        for(let i = 0; i <= total; i++) {
            if((currentCount * 2) < total) {
                currentCount = currentCount * 2;
                this.roundMatchups.unshift(currentCount/2);
            } else {
                if(total === 6) {
                    this.roundMatchups.unshift(1);
                    break;
                } else {
                    this.roundMatchups.unshift(total-currentCount);
                    break;
                }
            }
        }
    },
    createTournamentArray() {
        let roundMatches = Array.from(this.roundMatchups);
        let total = this.maxParticipants;
        let participants = Array.from(this.rankings);
        let allMatchups = [];

        let variance = total - (roundMatches[0] * 2);

        for(let j = 0; j <= roundMatches.length; j++) {
        
            let roundMatchups = [];
            
            for(let i = 0; i < roundMatches[j] * 2; i++) {

                if(j==0 && participants[0 + variance] && !(i%2)) {
                    roundMatchups.push(participants[0 + variance]);
                    participants.splice(0 + variance, 1);
                } 
                else if(j==1 && participants.length) { 
                    let buffer = total - roundMatches[0];  // how many are we adding to this round?
                    let emptySpaces = roundMatches[0];    // how many empty spaces do we need
                    let emptyPosition = []; // where do those empty spaces go?
                    if(emptySpaces === 2) { 
                      emptyPosition.push(0 + 1); 
                      emptyPosition.push(buffer-2) 
                    }  
                    if(emptyPosition[0] === i) {
                      roundMatchups.push(' ');
                      emptyPosition.splice(0,1);

                    } else {
                      roundMatchups.push(participants[0]);
                      participants.splice(0,1);
                    }
                    
                } else {
                    roundMatchups.push(' ');
                }
            }
            
            allMatchups.push(roundMatchups);
            if(j==0) { variance = 0; }
        }

        this.tournamentMatchups = allMatchups;
    },
    createBracketSeeding() {
        let total = this.totalParticipants;
        let halvedTotal = total / 2;
        
        let topBracket = [1,2,3];
        let count = total;

        for(let i = 0; i < halvedTotal; i++) {
            count++;
            topBracket.pop(count);
        }

        let bottomBracket = [];

        for(let i = 0; i < halvedTotal; i++) {
            count++;
            bottomBracket.unshift(count);
        }


        let seedBracket = [];
        for(let i = 0; i < halvedTotal; i++) {
            seedBracket.push(topBracket[i]);
            seedBracket.push(bottomBracket[i]);
        }

        this.tournamentSeeding = seedBracket;
        count = 0;
    },
    fillDraggableArrays() {
      for(let j = 0; j < this.roundMatchups.length; j++) {
        let iterator = this.roundMatchups[j]*2;
        let thisBracket = [];

        for(let i = 0; i < iterator; i++) {
          thisBracket.push(' ');
        }

       

        if(j === 0) { this.bracketArray1 = thisBracket; }
        if(j === 1) { this.bracketArray2 = thisBracket; }
        this.bracketArray[j] = Array.from(thisBracket);
      } 
    }
  },
  created() {
    

    const tournamentID = this.$route.params.id;
        tournamentService.getTournament(tournamentID).then(response => {
            if(response.status === 200){
                this.tournament = response.data;
                this.maxParticipants = this.tournament.maxParticipants;
                organizerService.getOrganizerInfo(response.data.organizerId).then(response => {
                    if(response.status === 200){
                        this.organizer = response.data;
                        if(this.$store.state.user.id == this.organizer.userId) {
                            this.isCurrentUserOrganizer = true;
                        }
                    }
                });
              tournamentService.getTournamentRankings(tournamentID).then(response => {
                if(response.status === 200) {
                    this.rankings = response.data;
                    this.createRoundMatchups();
                    this.createTournamentArray();
                    this.createBracketSeeding();
                    this.fillDraggableArrays();
                }
              });
            }
        });
    






  },
  computed: {
    getColumns() {
      let total = this.roundMatchups.length;
      let columnStyle = '1fr ';
      for(let i = 0; i < total; i++) {
        columnStyle += '1fr'
        if(i<total-1) { columnStyle += ' ' }
      }
      return columnStyle;
    },
  },
  
}
</script>

<style scoped>
.tournament {
  display: grid;
  flex-direction: row;
}
.participant-list {
  background: white;
  grid-row-start: 1;
  grid-row-end: span 2;
  display: flex;
  flex-direction: column;
  align-items: center;
  color: black;
}
.round {
  display: flex;
  flex-direction: column;
  align-items: center;
}
.draggable {
  height: 100%;
  display: flex;
  flex-direction: column;
  justify-content: space-around;
}

.participant, .champion {
  display: flex;
  flex-direction: row;
  border: 1px solid black;
  line-height: 1.3rem;
  background-color: #f4f4f4;
  color: black;
  justify-content: space-around;
}
.participant:nth-child(even) {
  border-top: none;
  margin-bottom: 20px;
}

.seed {
  background-color:lightblue;
  font-size: 0.8rem;
  color: black;
  width: 20px;
  text-align:center;
}
.name {
  font-size: 0.8rem;
  padding-left:5px;
  margin:0;
  min-width: 110px;
}
h2 {
  border-bottom: none;
  font-size: 1rem;
  text-align: center;
}
</style>