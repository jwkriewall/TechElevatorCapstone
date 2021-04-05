<template>
   <div class="createTournament">
       <form v-on:submit.prevent="addTournament">
           <div>
                <label for="tName">Tournament Name</label>
                <input type="text" id="tName" name="tName" v-model="newTournament.name" />
           </div>

           <div>
                <label for="maxParticipants">Max. Participants</label>
                <input type="number" id="maxParticipants" name="maxParticipants" v-model="newTournament.maxParticipants" />
           </div>
         
           <div>
                <p>Individual</p> 
                <input type="checkbox" id="switch"  v-model="newTournament.team" /><label class="toggle" for="switch">Toggle</label>
                <p>Team</p> 
           </div>  
           
           <div>
                <p>Single Elimination</p> 
                <input type="checkbox" id="flip" v-model="newTournament.double" /><label class="toggle" for="flip">Toggle</label>
                <p>Double Elimination</p> 
           </div>
           <div class="organizerInfo" v-if="getOrganizer">
               <label for="firstName">First name </label>
               <input type="text" name="firstName" id="firstName" v-model="organizer.firstName" />

               <label for="lastName">Last Name </label>
               <input type="text" name="lastName" id="lastName" v-model="organizer.lastName" />

               <label for="oPhone">Phone Number </label>
               <input type="tel" pattern="[0-9]{3}[0-9]{3}[0-9]{4}"  id="oPhone" v-model="organizer.phone" />

               <label for="oEmail">Email Address </label>
               <input type="email" id="oEmail" v-model="organizer.email" />
           </div>
            <input type="submit" value="Create Tournament" v-bind:disabled="!isFormValid" />
           <!-- <button type="submit" v-bind:disabled="!isFormValid">Create Tournament</button> -->
       </form>

   </div>
</template>

<script>
import organizerService from "../services/OrganizerService.js";
import tournamentService from "../services/TournamentService.js";

export default {
    name: 'create-tournament',
    data(){
        return {
            newTournament: {},
            organizer: {}
        }
    },
    computed:{

        isFormValid(){
            return this.newTournament.name &&
                   this.newTournament.maxParticipants &&
                   this.organizer.firstName &&
                   this.organizer.lastName &&
                   this.organizer.phone &&
                   this.organizer.email;

        }

    },
    methods:{
            getOrganizer() {
            const userId = this.$store.state.user.id;
            organizerService.getOrganizer(userId).then(response => {
                if(response.status === 201){
                    this.$store.commit('SET_ORGANIZER', response.data);
                    this.organizer = response.data;
                    return this.$store.state.organizer;
                } else {
                    return false;
                }
            })
            .catch(error => {
            if (error.response) {
              alert(error.response.statusText);
            } else if (error.request) {
              alert("Server could not be reached.");
            } else {
              alert("Request could not be created.");
            }
            return false;
          });
        },
        addTournament(){
            tournamentService.newTournament(this.newTournament).then(response => {
                if(response.status === 200){
                    const tournamentID = response.data.id; 
                    this.$router.push({name: 'tournament-details', params: {id: tournamentID}});
                    //alert("Tournament Created");
                }
            });
            if(this.organizer != this.$store.state.organizer){
                  organizerService.newOrganizer(this.organizer).then(response => {
                      if(response.status === 200){
                          alert("Organizer Added");
                      }
                  });
            }
          
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