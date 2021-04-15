<template>
  <div class="content-full-width">
    <h1>{{ tournament.name }} Bracket</h1>
    
    <div class="full-bracket">

      <div class="participant-list">
        <h2>Participant List</h2>
        <draggable v-model="rankings" group="tasks" :disabled="!isCurrentOrganizer">
          <div class="list-items" v-for="user in rankings" :key="user.name">
              <div class="participant">
                  <div class="seed">{{ !user.userSeeding ? "" : user.userSeeding }}</div>
                  <div class="name">{{ user.userNickname ? user.userNickname : user.firstName + ' ' + user.lastName }}</div>
              </div>
          </div>
        </draggable>
      </div>

      <div class="bracket">
        <div v-for="roundIndex in roundMatchups.length" :key="roundIndex.id" :class="`round ${roundIndex}`">
          <h2>Round {{ roundIndex }}</h2>
          <draggable v-model="bracketSeeding[roundIndex-1]" :disabled="!isCurrentOrganizer" easing="cubic-bezier(0.45, 0, 0.55, 1)" group="tasks" class="draggable"  
            @add="checkForSlot"
            @remove="createPlaceholder"
          >
            <div :class="`participant ${index+1}`" @dblclick="advanceToNextRound" v-for="(participant, index) in bracketSeeding[roundIndex-1]" :key="participant.id">
              <div class="seed">{{ tournamentSeeding[roundIndex-1][index] }}</div>
              <div class="name">{{ participant.userNickname ? participant.userNickname : 
                ( (participant.firstName + participant.lastName) ? participant.firstName + ' ' + participant.lastName : "" ) }}</div>
            </div>
          </draggable>
        </div>
      </div>
    </div>

    <div class="buttons">
      <input type="button" value="Back to Details" @click="$router.push(`/tournaments/${tournament.id}`)" />
      <input type="button"  :value="[autoSeeded ? 'Cancel Auto Seed' : 'Auto Seed']" @click="toggleAutoSeed" />
      <input type="button" v-if="isCurrentOrganizer" value="Save Bracket" @click="saveTournament" /> 
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
      tournamentSeeding: [],  //keep for future 
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
    saveTournament() {    // saves current tournament 
      this.tournament.rankArray = Array.from(this.bracketSeeding);
        tournamentService.updateTournament(this.tournament).then(response =>{
          if(response.status===200) {
            alert("Tournament Updated");
          }
        })
    },
    /** This populates the participants list to match the max participants
     * allowed in this tournament. It fills in the difference with "Bye"
     */
    fillRankings(rankArray) {   
      let rankSize = rankArray.length;
      let total = this.maxParticipants;
        if(total % 2 != 0) { total = total + 1 }   // if odd number, increase by one

      let bye = { userNickname: 'Bye' }
        if(rankSize < total) {
              for(let i = rankSize; i < total; i++) {
                rankArray.push(bye);
              }
        }
        this.rankings = rankArray;
    },
    /** Checks to see if there is a slot available to drag and drop a participant into a round
     * if there is no room, the participant is placed back into the list of origination
     */
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
    /** When draggable is removed from bracket list, a temporary placeholder is created to show
     * the empty bracket spot
    */
    createPlaceholder(evt) {
      let position = evt.oldIndex;
      let roundIndex = evt.from.parentElement.classList[1] - 1;
      this.bracketSeeding[roundIndex].splice(position,0,' ');
    },
    /** Double click advances the participant to the proper spot in the next round */
    advanceToNextRound(evt) {
      let currentSlotIndex = 0;   // iterator
      
      let roundIndex = evt.path[3].classList[1] - 1;  // find current round index
      let oldPosition = evt.srcElement.parentElement.classList[1] - 1;   // find the position in the current round
      const winnerObject = this.bracketSeeding[roundIndex][oldPosition]  // grab the object that is double clicked on
      let bracketSize = this.bracketSeeding.length; // what is the number of total rounds in this tournament

      for(let i = 1; i < this.bracketSeeding[roundIndex].length; i++) {   // loop as many times as the number of slots in current round index
            
        if(oldPosition <= i && (roundIndex < (bracketSize-1) ) ) {  // if the old position is less than current iterator && roundIndex < bracketsize index
            
            if(roundIndex === 0 && this.bracketSeeding[roundIndex+1][currentSlotIndex] != ' ') {  // check to see if destination in the corresponding slot in the next round is filled
              currentSlotIndex++                                                                  // if it is, go to the next slot in the same matchup
            } 
              this.bracketSeeding[roundIndex+1].splice(currentSlotIndex,1,winnerObject);          // put the object into the proper slot in the next round
              break;
        }
        currentSlotIndex += 1;               // advance slot iterator to look for next slot
        i++;                         // advance count iterator - every matchup is two participants, so cycle through two iterators each cycle
      }     
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
    autoSeedBrackets() {
        let roundMatches = Array.from(this.roundMatchups);
        let total = this.tournament.maxParticipants;
          if(total % 2 != 0) { total = total +1 }

        let participants = Array.from(this.rankings);
        
        let allMatchups = [];

        let variance = total - (roundMatches[0] * 2);  // determines how many many we skip for round 1 / how many filled spots in round two?
        
        let roundParticipants = roundMatches[1] * 2;  // how many slots in round two?

        let fillSlot = []; // where do those empty spaces go?

        for(let i = 0; i < variance; i++) {
          fillSlot.push(i); 
          fillSlot.push(roundParticipants-(1+i));
          i++;
        }

        let sortedPositions = fillSlot.sort((a, b) => 
            a > b ? 1 : -1
        );

        fillSlot = Array.from(sortedPositions);


        for(let j = 0; j < roundMatches.length; j++) {     // how many round we have - iterate through
          let roundMatchups = [];
            
                    
                      

                     
            
            for(let i = 0; i < roundMatches[j] * 2; i++) {    // multiple by 2 because there are two participants per match


                if(j===0) {  // if round one
                    roundMatchups.push(participants[0 + variance]);
                    participants.splice(0 + variance, 1);
                }

                else if(j===1) { //if round two
                      
                    if(fillSlot[0] != i) {
                      roundMatchups.push(' ');
                    } else {
                      roundMatchups.push(participants[0]);
                      participants.splice(0,1);
                      fillSlot.splice(0,1);
                    }

                } 
                
                else {
                    roundMatchups.push(' ');
                }
                
            }
            // add one slot if last round (for the winner)
            if(j === roundMatches.length -1) { roundMatchups.push(' '); }
            
            allMatchups.push(roundMatchups);
        }

        this.tournamentMatchups = allMatchups;
    },
    // Get the correct seeding positions for the bracket
    createTournamentSeeding() {
        let total = this.tournament.maxParticipants;
        let round1number = this.roundMatchups[0] * 2

      let round1Bracket = []
      let round2Bracket = []

        for(let i = round1number; i > 0; i--) {
            round1Bracket.push(total);
            total = total - 1;
        }
        let currentTotal = total
        for(let i = currentTotal; i > 0; i--) {
          round2Bracket.push(total);
          total = total -1;
        }
        let round1Final = []
        let bracketLength = round1Bracket.length;

        for(let i = 0; i < bracketLength/2; i++) {
          if(i % 2 == 0) {
            round1Final.push(round1Bracket[0]);
            round1Bracket.splice(0,1);
            round1Final.unshift(round1Bracket[0]);
            round1Bracket.splice(0,1);
          } else {
            round1Final.unshift(round1Bracket[0]);
            round1Bracket.splice(0,1);
            round1Final.push(round1Bracket[0]);
            round1Bracket.splice(0,1);
          }
        }

        let round2Prelim = []
        bracketLength = round2Bracket.length;
        for(let i = 0; i < bracketLength/2; i++) {
          if(i % 2 == 0) {
            round2Prelim.push(round2Bracket[0]);
            round2Bracket.splice(0,1);
            round2Prelim.unshift(round2Bracket[0]);
            round2Bracket.splice(0,1);
          } else {
            round2Prelim.unshift(round2Bracket[0]);
            round2Bracket.splice(0,1);
            round2Prelim.push(round2Bracket[0]);
            round2Bracket.splice(0,1);
          }
        }

        let fillSlot = [];
        total = this.tournament.maxParticipants;
        let roundMatches = Array.from(this.roundMatchups);
        let roundParticipants = roundMatches[1] * 2;
        let variance = total - (roundMatches[0] * 2);

        for(let i = 0; i < variance; i++) {
          fillSlot.push(i); 
          fillSlot.push(roundParticipants-(1+i));
          i++;
        }

        let sortedPositions = fillSlot.sort((a, b) => 
            a > b ? 1 : -1
        );

        fillSlot = Array.from(sortedPositions);

        let round2Final = []
        let round2Length = this.roundMatchups[1] * 2
        for(let i = 0; i < round2Length; i++) {
          if(fillSlot[0] == i) {
            round2Final.push(round2Prelim[0])
            round2Prelim.splice(0,1);
            fillSlot.splice(0,1);
          } else {
            round2Final.push('-');
          }
        }

        let seedBracket = [];
        seedBracket[0] = round1Final;
        seedBracket[1] = round2Final;
        console.log(this.roundMatchups.length)
        for(let i=2; i < this.roundMatchups.length; i++) {
          let thisSeedRound = [];
          for(let j = 0; j < this.roundMatchups[i] * 2; j++) {
            thisSeedRound.push('-');
          }
          if(i == this.roundMatchups.length-1) { thisSeedRound.push('-'); }
          seedBracket[i] = thisSeedRound;
        }




        this.tournamentSeeding = seedBracket;
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
                    this.autoSeedBrackets();
                    this.createTournamentSeeding();
                    
                }
              });
            }
        });
    






  },
}
</script>

<style scoped>
.content-full-width {
  background: #2c3e50;
  align-items: flex-start;
}
.full-bracket {
  display: flex;
  flex-direction: row;
}
.bracket {
  display: flex;
  flex-direction: row;
}
.participant-list {
  /* background: rgba(255,255,255,1); */
  display: flex;
  flex-direction: column;
  align-items: center;
  color: black;
  width: 167px;
  margin-right: 50px;
  padding: 0;
}
.participant-list h2 {
  font-weight: bold;
  font-size: 1.1rem;
  background-color: #e74c3c;
  margin: 0 ;
   width: 100%;
   color: white;
}
.round {
  display: flex;
  flex-direction: column;
  align-items: center;
  padding: 0 20px;
}
.draggable {
  height: 100%;
  display: flex;
  flex-direction: column;
  justify-content: space-around;
}
.participant {
  display: flex;
  flex-direction: row;
  border: 1px solid black;
  line-height: 2.3rem;
  background-color: #f4f4f4;
  color: black;
  justify-content: space-around;
}
.participant:nth-child(even) {
  border-top: none;
  margin-bottom: 20px;
}
.seed {
  background-color:#e74c3c;
  font-size: 0.8rem;
  color: black;
  width: 30px;
  text-align:center;
}
.participant-list .seed {
  background-color: hsl(207, 33%, 63%);
}

input[type="button"] {
  height: 50px;
  font-size: 1.0rem;
}

.name {
  font-size: 0.8rem;
  padding-left:5px;
  margin:0;
  width: 8vw;
  min-width: 110px;
}
.participant-list .name {
  width: 132px;
}
h2 {
  border-bottom: none;
  font-size: 1rem;
  text-align: center;
}
</style>