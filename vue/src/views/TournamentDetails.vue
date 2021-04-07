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

      <button v-if="isCurrentUserOrganizer" v-show="!okModify" @click='okModify = true' >Modify/Update</button>

      <div v-if="okModify">
          <form v-on:submit.prevent="updateTournament" class="tournamentForm">
           <div>
                <label for="tName">Tournament Name</label>
                <input type="text" id="tName" name="tName"  v-model="tournament.name" />
           </div>

           <div>
                <label for="maxParticipants">Max. Participants</label>
                <input type="number" id="maxParticipants" name="maxParticipants" v-model="tournament.maxParticipants" />
           </div>

            <div>
               <label for="startDate">Start Date</label>
               <input type="date" id="startDate" name="startDate" v-model="tournament.startDate" />
           </div>

           <div>
               <label for="endDate">Start Date</label>
               <input type="date" id="endDate" name="endDate" v-model="tournament.endDate" />
           </div>
         
           <div>
                <p>Individual</p> 
                <input type="checkbox" id="switch"  v-model="tournament.team" /><label class="toggle" for="switch">Toggle</label>
                <p>Team</p> 
           </div>  
           
           <div>
                <p>Single Elimination</p> 
                <input type="checkbox" id="flip" v-model="tournament.double" /><label class="toggle" for="flip">Toggle</label>
                <p>Double Elimination</p> 
           </div>
           <div class="organizerInfo" >
               <label for="firstName">First name </label>
               <input type="text" name="firstName" id="firstName" v-model="organizer.firstName" />

               <label for="lastName">Last Name </label>
               <input type="text" name="lastName" id="lastName" v-model="organizer.lastName" />

               <label for="oPhone">Phone Number </label>
               <input type="tel" pattern="[0-9]{3}[0-9]{3}[0-9]{4}"  id="oPhone" v-model="organizer.phone" />

               <label for="oEmail">Email Address </label>
               <input type="email" id="oEmail" v-model="organizer.email" />
           </div>
            <input type="submit" value="Update"/>
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
            isCurrentUserOrganizer: false
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
    },
    methods: {
        updateTournament(){
            tournamentService.updateTournament(this.tournament).then(response => {
                if(response.status === 200){
                    alert('Tournament Updated');
                    this.okModify = false;
                }
            })
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
	height: 30px;
	background: grey;
	display: block;
	border-radius: 100px;
	position: relative;
}
label.toggle:after {
	content: '';
	position: absolute;
	top: 5px;
	left: 5px;
	width: 20px;
	height: 20px;
	background: #fff;
	border-radius: 90px;
	transition: 0.3s;
}
input:checked + label {
	background: #BADA55;
}
input:checked + label:after {
	left: calc(100% - 5px);
	transform: translateX(-100%);
}
label.toggle:active:after {
	width: 20px;
} 

</style>