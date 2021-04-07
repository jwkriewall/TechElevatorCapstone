<template>
   <div class="createTournament">
       <form v-on:submit.prevent="createTournament" class="tournamentForm">
           <h1>Generate Tournament</h1>
           <div>
                <label for="tName">Tournament Name: </label>
                <input type="text" id="tName" name="tName" v-model="newTournament.name" />
           </div>
           <div>
                <label for="maxParticipants">Max. Participants: </label>
                <input type="number" id="maxParticipants" name="maxParticipants" v-model="newTournament.maxParticipants" />
           </div>
           <div>
               <label for="startDate">Start Date: </label>
               <input type="date" id="startDate" name="startDate" v-model="newTournament.startDate" />
           </div>
           <div>
               <label for="endDate">End Date: </label>
               <input type="date" id="endDate" name="endDate" v-model="newTournament.endDate" />
           </div>
           <div class="toggleSwitch">
                <p>Individual </p> 
                <input type="checkbox" id="switch"  v-model="newTournament.team" /><label class="toggle" for="switch">Toggle</label>
                <p> Team</p> 
           </div>  
           <div class="toggleSwitch">
                <p>Single Elimination </p> 
                <input type="checkbox" id="flip" v-model="newTournament.double" /><label class="toggle" for="flip">Toggle</label>
                <p> Double Elimination</p> 
           </div>
           <div class="organizerInfo" v-if="!isOrganizer">
               <h2>Organizer Information</h2>
               <p>This information will be used for contact purposes only. Your phone number will remain private, but your email address will be seen by users.</p>
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
            <input class="button" type="submit" value="Generate Tournament" v-bind:disabled="!isFormValid" />
       </form>
       <img src="@/assets/soccer.jpg" alt="Players on the soccer field during a match">
   </div>
</template>

<script>
import organizerService from "../services/OrganizerService.js";
import tournamentService from "../services/TournamentService.js";
export default {
    name: 'create-tournament',
    data(){
        return {
            newTournament: {
                team: false,
                double: false
            },
            organizer: {
                userId: this.$store.state.user.id
            },
            isOrganizer: false,
            userId: this.$store.state.user.id
        }
    },
    computed:{
        isFormValid(){ 
            return this.newTournament.name &&
                   this.newTournament.maxParticipants &&
                   this.newTournament.startDate &&
                   this.newTournament.endDate &&
                   this.organizer.firstName &&
                   this.organizer.lastName &&
                   this.organizer.phone &&
                   this.organizer.email;
        }
    },
    created(){
            organizerService.getOrganizer(this.userId).then(response => {
                if(response.status === 200){
                    this.$store.commit('SET_ORGANIZER', response.data);
                    this.organizer = response.data;
                    this.isOrganizer = true;
                }
                if(response.status === 404) {
                    this.isOrganizer = false;
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
          });
        
    },
    methods:{
        createTournament(){
            if(this.organizer != this.$store.state.organizer){
                  organizerService.newOrganizer(this.organizer)
                  .then(response => {
                      if(response.status === 200){
                          alert("Organizer Added");
                          this.organizer = response.data;
                          this.newTournament.organizerId = this.organizer.organizerId;
                          this.addTournament();
                        }
                    })
                    .catch(error => {
                        alert(error);
                    });
            } else { 
                this.newTournament.organizerId = this.organizer.organizerId;
                this.addTournament(); 
                }
        },
        addTournament() {
            tournamentService.newTournament(this.newTournament)
            .then(response => {
                if(response.status === 200){
                const tournamentID = response.data.id; 
                this.$router.push({name: 'tournament-details', params: {id: tournamentID}});
                alert("Tournament Created");
                }
            });

        }
    }
    
}
</script>
<style scoped>
form.tournamentForm h1 {
    border-bottom: 2px solid #e74c3c;
    display: inline-block;
    font-weight:normal;
}
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
.createTournament {
    display: grid;
    grid-template-columns: 1fr 1fr;
    color: #fff;
    font-family: 'Poppins';
    font-size: 1.2rem;
    grid-template-areas: "form img";
}
.tournamentForm{
    background-color: #2c3e50;
    padding: 30px 50px 50px 50px;
    grid-area: form;
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
.tournamentForm div:not(div.organizerInfo) {
    display: flex;
    align-items: center;
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

.toggleSwitch > p:last-child {
    margin-left: 10px;
}

.tournamentForm > div.toggleSwitch:nth-child(6) {
    margin:20px 0 -20px;
}
.tournamentForm input[type="submit"], textarea {
    background-color: #e74c3c;
    -webkit-box-shadow: 0 0 0px 1000px #e74c3c inset;
    color: white;
    font-size: 1.3rem;
    height: 3em;
    font-weight: 900;
    margin-top: 20px;
    border: none;
}
.tournamentForm input[type="submit"]:disabled {
    color: #e4a6a6;
    -webkit-text-fill-color: #e4a6a6;
}
.tournamentForm > div.organizerInfo {
    display: flex;
    flex-direction:column;
    align-items:flex-start;
    margin-bottom:20px;
}
.tournamentForm > div.organizerInfo > div {
    margin-bottom:10px;
}
.tournamentForm > div.organizerInfo h2 {
    text-transform:uppercase;
    font-size:1.5rem;
    margin-bottom: 10px;
}
.tournamentForm > div.organizerInfo p {
    font-size: 0.9rem;
    font-weight:200;
    margin:0 0 20px;
}
img{
    grid-area: img;
    overflow-y: hidden;
    width: 50vw;
}

</style>