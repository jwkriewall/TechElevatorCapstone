<template>
   <div class="container">
        <div class="content">
            <form v-if="!editTournamentOrganizer" class="tournamentForm">
                <h1>Generate Tournament</h1>
                <div>
                    <label for="name">Tournament Name: </label>
                    <input type="text" id="name" name="name" v-model="newTournament.name" />
                </div>
                <div>
                    <label for="maxParticipants">Max. Participants: </label>
                    <input type="number" id="maxParticipants" name="maxParticipants" min="2" max="16" step="2" value="8" v-model="newTournament.maxParticipants" />
                </div>
                <div>
                    <label for="startDate">Start Date: </label>
                    <input type="date" id="startDate" name="startDate" :min="findTodaysDate" v-model="newTournament.startDate" />
                </div>
                <div>
                    <label for="endDate">End Date: </label>
                    <input type="date" id="endDate" name="endDate" v-model="newTournament.endDate" />
                </div>
                <div class="toggleSwitch">
                        <p>Individual</p> 
                        <input class="toggle" type="checkbox" id="switch"  v-model="newTournament.team" /><label class="toggle" for="switch">Toggle</label>
                        <p>Team</p> 
                </div>  
                <div class="toggleSwitch">
                        <p>Single Elimination</p> 
                        <input class="toggle" type="checkbox" id="flip" v-model="newTournament.double" /><label class="toggle" for="flip">Toggle</label>
                        <p>Double Elimination</p> 
                </div>
            </form>
            
            <edit-organizer v-if="editTournamentOrganizer" :organizer="$store.state.organizer" />
            
                <input type="button" v-if="!editTournamentOrganizer" value="Next" @click="toggleEditOrganizer" />
                <input type="button" v-if="editTournamentOrganizer" value="Back" @click="toggleEditOrganizer" />
                <input type="button" v-if="editTournamentOrganizer" value="Generate" v-bind:disabled="!isFormValid" @click="createTournament" />
                <input type="button" v-if="editTournamentOrganizer" value="Generate Tournament" @click="createTournament" v-bind:disabled="!isFormValid" />
        </div>
        
        <div class="image">
            <img src="@/assets/darts.jpg" alt="Players on the soccer field during a match">
        </div>
   </div>
</template>

<script>
import organizerService from "../services/OrganizerService.js";
import tournamentService from "../services/TournamentService.js";
import EditOrganizer from "../components/EditOrganizer.vue";

export default {
    name: 'create-tournament',
    components: {
        EditOrganizer
    },
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
            userId: this.$store.state.user.id,
            editTournamentOrganizer: false,
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
        },
        findTodaysDate(){
            let today = new Date();
            let dd = today.getDate();
            let mm = today.getMonth()+1;
            let yyyy = today.getFullYear();
            if ( dd<10 ) {
                dd = '0' + dd
            }
            if ( mm<10 ) {
                mm = '0' + mm
            }
            return yyyy + '-' + mm + '-' + dd;
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
            //alert(error.response.statusText);
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
                        if(response.status === 404){
                            // Do Nothing
                        }
                    })
                    .catch(error => {
                    if (error.response) {
                        //alert(error.response.statusText);
                    } else if (error.request) {
                        alert("Server could not be reached.");
                    } else {
                        alert("Request could not be created.");
                    }
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

        },
        toggleEditOrganizer() {
            this.editTournamentOrganizer = !this.editTournamentOrganizer;
        }
    }
    
}
</script>

<style scoped>
.tournamentForm div:not(div.organizerInfo) {
    display: flex;
    align-items: center;
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
img {
    width: 100%;
} 

.container{
    height: 100vh;
}

input[type="button"]{
  height: 45px;
  font-size: 1.3rem;
}


</style>