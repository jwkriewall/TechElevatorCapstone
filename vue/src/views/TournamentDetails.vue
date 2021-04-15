<template>
    <div class="container">
        <div class="content">
            <tournament-details v-if="!modifyTournament" :tournament="tournament" :organizer="organizer" />
            <edit-tournament v-if="modifyTournament" :tournament="tournament" :organizer="organizer" />
            
            <div class="buttons">
                <input type="button" value="View Bracket" @click="$router.push(`/tournaments/${tournament.id}/bracket`)" />
                <input type="button" value="Modify" v-if="isCurrentUserOrganizer" v-show="!modifyTournament && !tournament.ended" @click='modifyTournament = true' />
                <input type="button" value="End Tournament" v-if="isCurrentUserOrganizer && !modifyTournament" v-show='!tournament.ended' @click='endTournament' />
            </div>  
        </div>
        <div class="image">
            <div class="tournament-seeding">
                <h2>Current Seeding</h2>
                
                <div class="headers">
                    <h2>Name / Nickname</h2>
                    <h2>Seeding:</h2>
                </div>
                    
                <div class="user-card" v-for="user in rankings" :key="user.id">
                    <div class="user-image"><img :src="[user.userUrl ? user.userUrl : require('@/assets/profile-placeholder.png')]" alt="Picture or Avatar of Participant" /></div>
                    <div class="user-name">{{ user.userNickname ? (user.userNickname != "NICKNAME" ? user.userNickname : user.firstName + ' ' + user.lastName) : user.firstName + ' ' + user.lastName }}</div>
                    <div class="user-seed">{{ user.userSeeding ? user.userSeeding : 'UnRnkd' }}</div>
                </div>
                
                <div class="no-participants" v-if="rankings.length === 0">
                    <p>No participants in current tournament</p>
                </div>
                
            </div>
        </div>
    </div>
</template>

<script>
import organizerService from "../services/OrganizerService.js";
import tournamentService from "../services/TournamentService.js";
import tournamentDetails from "../components/TournamentDetails.vue";
import editTournament from "../components/EditTournament.vue";
// import joinTournament from "./JoinTournament.vue";


let changeEmailToStringArray = function(emailArray) {
        let emailList = '';
        console.log(emailArray);
            emailArray.forEach(email => {    
                if (email != '' && (email == emailArray.slice(-1))){
                    emailList += email;
                }
                else if (email != ''){
                    emailList += email + ', ';
                }   
                else {
                    emailList += ' ';
                }
            });
        return emailList;
    }

// search tournament_user table by tournament id - get list back, 
// go through list of users where true

export default {
    name: 'tournament-detail-page',
    components: { tournamentDetails, editTournament },
    data(){
        return {
            tournament: {},
            organizer: {},
            modifyTournament: false,
            isCurrentUserOrganizer: false,
            rankings: []
           
        }
    },
    mounted() {
        let emailScript = document.createElement('script');
        emailScript.setAttribute('src', 'https://smtpjs.com/v3/smtp.js')
        document.head.appendChild(emailScript);
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
        updateAll(organizer, tournament){
            this.tournament = tournament;
            this.organizer = organizer;
            tournamentService.updateTournament(tournament);
            organizerService.updateOrganizer(organizer);
            this.toggleModifyTournament();
        },

        endTournament() {
            if(confirm("Are you sure you want to end the tournament and send emails to participants?")) 
                this.tournament.ended = true;
                tournamentService.updateTournament(this.tournament);
                this.sendEmail();
        },

        sendEmail() {
            let tournamentName = this.tournament.name;
            //let linkToTournament = this.$route.params.id;
            //let thisRoute = this.$route;
            tournamentService.getUserEmails(this.$route.params.id)
            .then(response => {
                
            window.Email && window.Email.send({
                Host: "smtp.gmail.com",
                Username: "brcktproject@gmail.com",
                Password: "thisisapassword1!",
                To: changeEmailToStringArray(response.data),
                //To: 'brcktproject@gmail.com', 
                // hardcoded value example above. changeEmailToStringArray function is at top of script-->
                From: "brcktproject@gmail.com",
                Subject: tournamentName + " Has Ended",
                Body: "Your tournament has now concluded. Please click the following link to see the final bracket for " + tournamentName + "! " + window.location.href + "/bracket"

                // Attachments: [
                // {
                //     name: "File_Name_with_Extension",
                //     path: "Full Path of the file"
                //     // Could we put picture of bracket here????
                // }]

                })
                
                console.log(response.data)
                // .then(message => alert("Mail has been sent successfully")
                // ); 
                }).catch(error => {
                    console.log(error)
            })
                
            
        },
        toggleModifyTournament() {
            this.modifyTournament = !this.modifyTournament
        }

    }

}
</script>

<style scoped>

.container {
    background: url('../assets/cards.png') fixed center top;
}
.content {
    background: none;
}
.image {
    display: flex;
    flex-direction: column;
    align-items: center;
    justify-content: flex-start;
}
.tournament-seeding {
    color: white;
    width: 80%;
}
.headers {
    display: flex;
    flex-direction: row;
    justify-content:space-between;
}
.headers h2 {
    border: none;
}
.headers h2:last-child {
    width: 19%;
    margin-right: 20px;
    text-align: center;
}
input.update:hover{
    background-color: #d85b4d;
    -webkit-box-shadow: 0 0 0 1000px #d85b4d inset;
}

input[type="button"] {
  height: 60px;
  font-size: 1.1rem;
}
.user-card{
    background: rgba(255,255,255,0.6);
    color: black;
    display: flex;
    flex-direction: row;
    height: 5vh;
    align-items: center;
    border-bottom: 1px solid black;
    padding: 10px 20px 10px 0;
    justify-content: flex-start;
}
.user-image {
    width: 20%;
    margin:0; padding: 0;
    height: 50px;
    overflow-y: hidden;
    text-align: center;
}
.user-image img {
    margin: 5px 0 0 0;
    padding: 0;
}
.user-name {
    width: 60%;
}
.user-seed {
    text-align: center;
    width: 20%;
}
</style>