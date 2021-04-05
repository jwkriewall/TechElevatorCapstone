<template>
   <div class="createTournament">
       <form >
           <div>
                <label for="tName">Tournament Name</label>
                <input type="text" id="tName" name="tName" />
           </div>

           <div>
                <label for="maxParticipants">Max. Participants</label>
                <input type="number" id="maxParticipants" name="maxParticipants">
           </div>
         
           <div>
                <p>Individual</p> 
                <input type="checkbox" id="switch" /><label class="toggle" for="switch">Toggle</label>
                <p>Team</p> 
           </div>  
           
           <div>
                <p>Single Elimination</p> 
                <input type="checkbox" id="flip" /><label class="toggle" for="flip">Toggle</label>
                <p>Double Elimination</p> 
           </div>
           <div class="organizerInfo" v-if="this.$store.state.organizer == '' "
                >
               <label for="firstName">First name </label>
               <input type="text" name="firstName" id="firstName">

               <label for="lastName">Last Name </label>
               <input type="text" name="lastName" id="lastName">

               <label for="oPhone">Phone Number </label>
               <input type="tel" pattern="[0-9]{3}[0-9]{3}[0-9]{4}"  id="oPhone"/>

               <label for="oEmail">Email Address </label>
               <input type="email" id="oEmail">
           </div>
       </form>

   </div>
</template>

<script>
import organizerService from "../services/OrganizerService.js";
import tournamentService from "../services/TournamentService.js";

export default {
    computed:{
        getOrganizer(){
            organizerService.getOrganizer(this.user.id).then(Response => {
                if(Response.status === 201){
                    this.$store.commit('SET_ORGANIZER', Response.data)
                }
            });
        }
    },
    name: 'create-tournament'
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