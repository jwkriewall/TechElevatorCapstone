<template>
  <div class="details">
      <h1>{{tournament.name}} Details</h1>
      <ul>
          <li>Tournament Name: {{tournament.name}}</li>
          <li>Max. Participants: {{tournament.maxParticipants}}</li>
          <li>Start Date: {{tournament.startDate}}</li>
          <li>End Date: {{tournament.endDate}}</li>
          <li v-if="tournament.team" >Team Tournament</li>
          <li v-if="!tournament.team" >Individual Tournament</li>
          <li v-if="tournament.double">Double Elimination</li>
          <li v-if="!tournament.double">Single Elimination</li>
          <li>Organizer Name: {{organizer.firstName}} {{organizer.lastName}}</li>
          <li>Organizer Email: {{organizer.email}}</li>
      </ul>

      <input type="submit" value="Modify" v-if="isCurrentUserOrganizer" v-show="!okModify" @click='okModify = true' />
        <div class="tournament-seeding">
            <h2>Tournament Seeding</h2>
            <table class="rankings" >
            <tr>
                <th>User Name/Nick</th>
                <th>Seeding</th>
            </tr>
            <tr v-for="user in rankings" :key="user.id">
                <td>{{ user.userNickname ? (user.userNickname != "NICKNAME" ? user.userNickname : user.firstName + ' ' + user.lastName) : user.firstName + ' ' + user.lastName }}</td>
                <td>{{ user.userSeeding }}</td>
            </tr>
        </table>
        </div>
      <div v-if="okModify">
          <form v-on:submit.prevent="updateTournament" class="tournamentForm">
              <h2>Update Tournament Information</h2>
           <div>
                <label for="tName">Tournament Name: </label>
                <input type="text" id="tName" name="tName"  v-model="tournament.name" />
           </div>

           <div>
                <label for="maxParticipants">Max. Participants: </label>
                <input type="number" id="maxParticipants" name="maxParticipants" v-model="tournament.maxParticipants" />
           </div>

            <div>
               <label for="startDate">Start Date: </label>
               <input type="date" id="startDate" name="startDate" v-model="tournament.startDate" />
           </div>

           <div>
               <label for="endDate">End Date: </label>
               <input type="date" id="endDate" name="endDate" v-model="tournament.endDate" />
           </div>
         
           <div class="toggleSwitch">
                <p>Individual</p> 
                <input type="checkbox" id="switch"  v-model="tournament.team" /><label class="toggle" for="switch">Toggle</label>
                <p>Team</p> 
           </div>  
           
           <div class="toggleSwitch">
                <p>Single Elimination</p> 
                <input type="checkbox" id="flip" v-model="tournament.double" /><label class="toggle" for="flip">Toggle</label>
                <p>Double Elimination</p> 
           </div>

           <div class="organizerInfo" >
               <h2>Update Organizer Information</h2>
                    <div>
                        <label for="firstName">First name: </label>
                        <input type="text" name="firstName" id="firstName" v-model="organizer.firstName" />
                    </div>
                    
                    <div>
                        <label for="lastName">Last Name: </label>
                        <input type="text" name="lastName" id="lastName" v-model="organizer.lastName" />
                    </div>

                    <div>
                        <label for="oPhone">Phone Number: </label>
                        <input type="tel" pattern="[0-9]{3}[0-9]{3}[0-9]{4}"  id="oPhone" v-model="organizer.phone" />
                    </div>

                    <div>
                        <label for="oEmail">Email Address: </label>
                        <input type="email" id="oEmail" v-model="organizer.email" />
                    </div>
           </div>
            <input class="update" type="submit" value="Update" @click.prevent="updateAll"/>
           <!-- <button type="submit" v-bind:disabled="!isFormValid">Create Tournament</button> -->
       </form>

      </div>
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
            organizer: {},
            okModify: false,
            isCurrentUserOrganizer: false,
            rankings: []
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
        })
    },
    methods: {
        updateAll(){
            tournamentService.updateTournament(this.tournament);
            organizerService.updateOrganizer(this.organizer);
            this.okModify = false;
        }
    }
}
</script>

<style scoped>

input[type=checkbox]{
    height: 0;
    width: 0;
    visibility: hidden;
}
label.toggle {
    cursor: pointer;
    text-indent: -9999px;
    width: 50px;
    height: 20px;
    background: #BADA55;
    display: block;
    border-radius: 100px;
    position: relative;
}
label.toggle:after {
    content: '';
    position: absolute;
    top: 5px;
    left: 5px;
    width: 10px;
    height: 10px;
    background: #fff;
    border-radius: 90px;
    transition: 0.3s;
}
input:checked + label {
    background: #e74c3c;
}
input:checked + label:after {
    left: calc(100% - 5px);
    transform: translateX(-100%);
}
label.toggle:active:after {
    width: 20px;
} 

.details{
    background: url('../assets/cards.png') fixed center top;
    padding: 20px 20px 20px 50px;
    color: white;
    font-family: 'Poppins';

}

h1{
    border-bottom: 2px solid #e74c3c;
    display: inline-block;
}

input:not(div.toggleSwitch > input) {
    border-radius: 15px;
    height: 2.2rem;
    width: 18vw;
    min-width: 280px;
    padding: 0 20px;
    font-size: 1rem;
    background-color: #44617e;
    border: 1px solid white;
    color: white;
    -webkit-text-fill-color: white;
    -webkit-box-shadow: 0 0 0px 1000px #44617e inset;
}
input[type="date"]::-webkit-calendar-picker-indicator {
    filter: invert(100%);
}
input[type="date"]:focus::-webkit-calendar-picker-indicator {
    filter: invert(0%);
}

label:not(.toggle){
    margin: 10px 10px 10px 0;
    width: 15vw;
    min-width: 125px;
}

textarea:focus, input:not(div.toggleSwitch > input):focus
 {
  outline: none;
  box-shadow: 0px 0px 0px 2px #e74c3c;
  background-color:white;
  color: #707070;
  -webkit-text-fill-color: #707070;
  -webkit-box-shadow: 0 0 0px 1000px #fff inset;
}
.tournamentForm input[type="submit"], textarea, .details input[type="submit"]{
    background-color: #e74c3c;
    -webkit-box-shadow: 0 0 0px 1000px #e74c3c inset;
    color: white;
    font-size: 1.3rem;
    height: 2.5em;
    font-weight: 900;
    margin-top: 20px;
    border: none;
    min-width: 150px;
}


.tournamentForm div{
    display: flex;
    align-items: center;

}

.tournamentForm div.organizerInfo{
    display: flex;
    flex-direction: column;
    align-items: flex-start;
}
.toggleSwitch p:last-child{
    margin-left: 10px;
}

ul li{
    padding: 5px 0;
    margin: 0;
    display: block;
}
ul{
    margin: 0;
    padding: 0;
    list-style-position: outside;
    list-style-type: none;
}
.tournamentForm{
    border-top: 2px dashed #e74c3c;
    margin-top: 50px;
    padding-top: 20px;
    margin-left: -50px;
    padding-left: 50px;
}
h2{
    border-bottom: 2px solid #e74c3c;
    display: inline-block;
    margin-bottom: 10px;
}
input.update:hover{
    background-color: #d85b4d;
    -webkit-box-shadow: 0 0 0 1000px #d85b4d inset;
    border: 1px solid red;
}

</style>