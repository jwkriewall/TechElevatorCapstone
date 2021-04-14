<template>
  <div class="content">
    <h1>{{ tournament.name }} Bracket</h1>
    <div class="tournament" :style="{ 'grid-template-columns': getColumns}">
      <div class="participant-list">Participants List
        
        <draggable v-model="rankings" group="tasks" :disabled="!isCurrentOrganizer">
          <div class="list-group-item" v-for="user in rankings" :key="user.name">
              <div class="username-participant">
                  <strong> {{ user.userNickname ? user.userNickname : user.firstName + ' ' + user.lastName }} </strong>
              </div>
          </div>
        </draggable>
      </div>

      <h2 v-for="headerIndex in roundMatchups.length" :key="headerIndex.id">Round {{headerIndex}}  </h2>
      <div v-for="roundIndex in roundMatchups.length" :key="roundIndex.id" :class="`round ${roundIndex}`">
        <draggable v-model="bracketSeeding[roundIndex-1]" :disabled="!isCurrentOrganizer" group="tasks" class="draggable"  @add="checkForSlot" @remove="createPlaceholder">
          <div :class="`participant ${index+1}`" @dblclick="advanceToNextRound" v-for="(participant, index) in bracketSeeding[roundIndex-1]" :key="participant.id">
            <div class="seed">{{ participant ? index + 1 : '&nbsp;' }}</div>
            <div class="name">{{ participant.userNickname ? participant.userNickname : 
                ( (participant.firstName + participant.lastName) ? participant.firstName + ' ' + participant.lastName : "" ) }}</div>
          </div>
        </draggable>
        
      </div>
      <div class="buttons">
        <input type="button" value="Back to Details" @click="$router.push(`/tournaments/${tournament.id}`)" />
        <input type="button" v-if="isCurrentOrganizer" :value="[autoSeeded ? 'Cancel Auto Seed' : 'Auto Seed']" @click="toggleAutoSeed" />
        <input type="button" v-if="isCurrentOrganizer" value="Save Bracket" @click="saveTournament" /> 
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
      // participantsArray: [1,2,3,4,5,6,7,8,9,10],
      rankings: [],
      // maxParticipants: '',
      // totalParticipants: 10,
      roundMatchups: [0],
      tournamentMatchups: [],
      //tournamentSeeding: [],  //keep for future 
      bracketSeeding: [],
      bracketArray: [],
      // numberCheck: '',
      // bracketCheck: [],
      // updated: true,
      organizer: {},
      isCurrentOrganizer: false,
      autoSeeded: false,
    }
  },
  methods: {
    toggleAutoSeed() {
        if(!this.autoSeeded) {
            this.bracketSeeding = this.tournamentMatchups;
        } else {
          this.bracketSeeding = this.bracketArray;
        }
        this.$forceUpdate();
        this.autoSeeded = !this.autoSeeded
    },
    saveTournament() {
      this.tournament.rankArray = Array.from(this.bracketSeeding);
        tournamentService.updateTournament(this.tournament).then(response =>{
          if(response.status===200) {
            alert("Tournament Updated");
          }
        })
    },
    fillRankings(rankArray) {
      let rankSize = rankArray.length;
      let bye = { userNickname: 'Bye' }
        if(rankSize < this.maxParticipants) {
              for(let i = rankSize; i < this.maxParticipants; i++) {
                rankArray.push(bye);
              }
        }
        this.rankings = rankArray;
    },
    checkForSlot(evt) {
     let newIndex = evt.newIndex;
     let roundIndex = evt.to.parentElement.classList[1] - 1;

     let oldValue = (this.bracketSeeding[roundIndex])[newIndex];
     let blankFound = false;

     for(let i = 0; i < this.bracketSeeding[roundIndex].length; i++) {
       if((this.bracketSeeding[roundIndex])[i] === ' ') {
         
         this.bracketSeeding[roundIndex].splice(i,1);
         blankFound = true;
         break;
       }
     }
     if(!blankFound) {
        this.bracketSeeding[roundIndex].splice(newIndex,1);
        this.rankings.push(oldValue);
        }
    },
    createPlaceholder(evt) {
      let position = evt.oldIndex;
      let roundIndex = evt.from.parentElement.classList[1] - 1;
      this.bracketSeeding[roundIndex].splice(position,0,' ');
      console.log(evt.oldDraggableIndex);
    },
    advanceToNextRound(evt) {
      let newIndex = 0;
      
      let roundIndex = evt.path[3].classList[1] - 1;
      let oldPosition = evt.srcElement.parentElement.classList[1] - 1;
      const winnerObject = this.bracketSeeding[roundIndex][oldPosition]
      let bracketSize = this.bracketSeeding.length;

      for(let i = 1; i < this.bracketSeeding[roundIndex].length; i++) {
            
        if(oldPosition <= i && (roundIndex < (bracketSize-1) ) ) { 
            if(roundIndex === 0 && this.bracketSeeding[roundIndex+1][newIndex] != ' ') {
              newIndex++
            } 
              this.bracketSeeding[roundIndex+1].splice(newIndex,1,winnerObject);
              break;
        }
        newIndex += 1;
        i++;
      }
      this.$forceUpdate();

    },
    getMatchupNumber(index) {
      return index + 1;
    },
    createRoundMatchups() {
        let total = this.tournament.maxParticipants;
        
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
        let total = this.tournament.maxParticipants;
        let participants = Array.from(this.rankings);
        
        let allMatchups = [];

        let variance = total - (roundMatches[0] * 2);  // determines how many many we skip for round 1

        for(let j = 0; j < roundMatches.length; j++) {     // how many round we have - iterate through
        
            let roundMatchups = [];
            
            for(let i = 0; i < roundMatches[j] * 2; i++) {    // multiple by 2 because there are two participants per match

                if(j==0) {  // if round one
                    roundMatchups.push(participants[0 + variance]);
                    participants.splice(0 + variance, 1);
                } 
                else if(j==1 && participants.length) { //if round two
                    let buffer = total - roundMatches[0];  // how many are we adding to this round?
                    let emptySpaces = roundMatches[0];    // how many empty spaces do we need
                    let emptyPosition = []; // where do those empty spaces go?

                    console.log(emptyPosition);
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
            if(j === roundMatches.length -1) { roundMatchups.push(' '); }
            
            allMatchups.push(roundMatchups);
            if(j==0) { variance = 0; }
        }

        this.tournamentMatchups = allMatchups;
    },
    //not working at the moment
    createTournamentSeeding() {
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

        if(j == this.roundMatchups.length -1) {
          thisBracket.push(' ')
        }

        this.bracketArray[j] = Array.from(thisBracket);
      } 
      this.bracketSeeding = Array.from(this.bracketArray);
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
                            this.isCurrentOrganizer = true;
                        }
                    }
                });
              tournamentService.getTournamentRankings(tournamentID).then(response => {
                if(response.status === 200) {
                    this.fillRankings(response.data);
                    this.createRoundMatchups();
                    this.fillDraggableArrays();
                    this.createTournamentArray();
                    this.createTournamentSeeding();
                    
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
  width: 15vw;
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