<template>
  <div class="content">
    <h1> Bracket</h1>
    <div class="tournament" :style="{ 'grid-template-columns': getColumns}">
      <div class="participant-list">Participants List
          <draggable class="participant-list-individual" :list="rankings" group="tasks">
              <div class="list-group-item" v-for="user in rankings" :key="user.name">
                  <div class="username-participant">
                      <strong> {{user.firstName}} {{user.userNickname}} {{user.lastName}} </strong>
                  </div>
              </div>
        </draggable>
      </div>
      <h2 v-for="headerIndex in roundMatchups.length" :key="headerIndex.id">Round {{headerIndex}}  </h2>
      <div v-for="roundIndex in roundMatchups.length" :key="roundIndex.id" :class="`round round-${roundIndex}`">
        <!-- <div v-for="matchIndex in roundMatchups[roundIndex-1]" :key="matchIndex.id" v-bind:class="`matchup ${getMatchupNumber(matchIndex-1)}`"> -->
          <div class="participant" v-for="(participant, index) in tournamentMatchups[roundIndex-1]" :key="participant.id">
            <div class="seed">{{ participant ? index + 1 : '&nbsp;' }}</div>
            <draggable :list="bracketArray" group="tasks">
                <div class="name">{{ bracketArray[index] ? bracketArray[index].userNickname : [participant ? participant : '&nbsp;'] }}</div>
            </draggable>
          </div>
        <!-- </div> -->
        <div class="champion" v-if="roundIndex === roundMatchups.length">
            <div class="seed">#</div>
            <div class="name"></div>
          </div>
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
      participantsArray: [1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16],
      rankings: [],
      totalParticipants: 14,
      roundMatchups: [0],
      tournamentMatchups: [],
      tournamentSeeeding: [],
      bracketArray: [],
      numberCheck: '',
    }
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
        let roundMatches = this.roundMatchups;
        let total = this.totalParticipants;
        let participants = this.participantsArray;
        let allMatchups = [];

        let variance = total - (roundMatches[0] * 2);

        for(let j = 0; j <= roundMatches.length; j++) {
        
            let roundMatchups = []
            
            for(let i = 0; i < roundMatches[j] * 2; i++) {
                if(j==0) {
                    roundMatchups.push(participants[0 + variance]);
                    participants.splice(0 + variance, 1);
                } 
                else if(j==1) { 
                    let buffer = (roundMatches[j] * 2) - participants.length
                    if(i < buffer) { roundMatchups.push('') }
                    else { 
                        roundMatchups.push(participants[1]);
                        roundMatchups.unshift(participants[0]);
                        break;
                    }
                } else {
                    roundMatchups.push('');
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
    }
  },
  created() {
    // const tournamentID = this.$route.params.id;
    // tournamentService.getTournament(tournamentID).then(response => {
    //     if(response.status === 200) {
    //         this.tournament = response.data;
    //     }
    // })
    // tournamentService.getTournamentRankings(tournamentID).then(response => {
    //     if(response.status === 200) {
    //         alert("Got it, boss!")
    //         this.participantsArray = response.data;
    //         this.totalParticipants = response.data.length;
    //         this.createRoundMatchups();
    //         this.createTournamentArray();
    //         this.createBracketSeeding();
    //     }
    // })

// //added from construction

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
                this.createRoundMatchups();
                this.createTournamentArray();
                this.createBracketSeeding();
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
  align-items: flex-start;
  justify-content: space-around;
  margin: 10px auto;
}

.participant, .champion {
  display: flex;
  flex-direction: row;
  border: 1px solid black;
  line-height: 1.3rem;
  background-color: #f4f4f4;
  color: black;
}
.round > .participant:nth-child(even) {
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