<template>
  <div class="content">
    <h1> Bracket</h1>
    <div class="tournament" :style="{ 'grid-template-columns': getColumns}">
      <div class="participant-list">Participants List</div>
      <h2 v-for="headerIndex in roundMatchups.length" :key="headerIndex.id">Round {{headerIndex}}  </h2>
      <div v-for="roundIndex in roundMatchups.length" :key="roundIndex.id" :class="`round ${roundIndex}`">
        <div v-for="(matchIndex) in roundMatchups[roundIndex-1]" :key="matchIndex.id" v-bind:class="`matchup ${getMatchupNumber(matchIndex-1)}`">
          <div class="participant" v-for="participantIndex in 2" :key="participantIndex.id">
            <div class="seed">{{matchIndex}}</div>
            <div class="name">Player Name</div>
          </div>
        </div>
        <div class="champion" v-if="roundIndex === roundMatchups.length">
            <div class="seed">#</div>
            <div class="name"></div>
          </div>
      </div>
    </div>






  </div>
</template>

<script>
export default {
  data() {
    return {
      participantsArray: [ 1,2,3,4,5,6,7,8,8,8,11,12,13,14,15,16 ],
      participants: '',
      roundMatchups: [0],
      playerIndex: -1,
      currentRoundMatchups: this.getMatchups,
      bracket16Seeding: [ 1,16,8,9,4,13,5,12,2,15,7,10,3,14,6,11 ],
      bracket14Seeding: [ 8,9,4,13,5,12,7,10,3,14,6,11,1, '', '', '', 2, '', '', '' ],
      bracket12Seeding: [ 8,9,4,5,12,7,10,6,11,1,'',4,'',2,'',3,'' ],
      bracket10Seeding: [ 8,9,7,10,1,'',4,5,2,'',3,6 ],
      bracket8Seeding: [ 1,8,4,5,2,7,3,6 ],
      bracket4Seeding: [ 1,4,2,3 ],
    }
  },
  methods: {
    getMatchupNumber(index) {
      return index + 1;
    },
    getParticipant() {
      this.playerIndex += 1;
      return true;
    },
    // getBracketSeed(round, match, participant) {
    //   if(participantsArray.length === 16) {
    //     if(match === 1) 
    //       if(round === 1)
    //   }
    // }
  },
  created() {
    let total = this.participantsArray.length
    this.participants = total;
    let currentCount = 1;   //2 
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
  computed: {
    getRounds() {
      let count = 0;
      //let participants = this.participantsArray.length;
      let participants = this.participants;
      for(let i = 0; i<participants; i++) {
        count += 1;
        if(participants % 2 === 0) {
          participants = participants / 2
        } else { break; }
        
        if(participants === 0) { break; }
      }
      return count;
    },
    getMatchups() {
      return this.participants / 2;
    },
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
.matchup {
  padding: 20px 0;
}
.matchup, .champion {
  margin-bottom: 20px;
}
.participant, .champion {
  display: flex;
  flex-direction: row;
  border: 1px solid black;
  line-height: 1.3rem;
  background-color: #f4f4f4;
  color: black;
}
.matchup > .participant:nth-child(2) {
  border-top: none;
  margin-top: 2px;
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