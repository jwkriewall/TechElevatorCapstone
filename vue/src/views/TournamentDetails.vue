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
                <table class="rankings" >
                    <tr>
                        <th>User Name/Nick</th>
                        <th>Seeding</th>
                    </tr>
                    <tr v-for="user in rankings" :key="user.id">
                        <td>{{ user.userNickname ? (user.userNickname != "NICKNAME" ? user.userNickname : user.firstName + ' ' + user.lastName) : user.firstName + ' ' + user.lastName }}</td>
                        <td>{{ user.userSeeding }}</td>
                    </tr>
                     <tr class="no-participants" v-if="rankings.length === 0">
                        <td>No participants in current tournament</td>
                        <td></td>
                    </tr>
                </table>
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
    margin: 50px 90px 0 0;
    color: white;
}

h2{
    border-bottom: 2px solid #e74c3c;
    display: inline-block;
    margin-bottom: 10px;
}
input.update:hover{
    background-color: #d85b4d;
    -webkit-box-shadow: 0 0 0 1000px #d85b4d inset;
}
th {
    text-align: left;
    text-decoration: underline;
}
tr.no-participants td {
    color: #e74c3c;
    text-transform: uppercase;
    padding-top: 20px;
}
tr td:nth-child(2) {
    text-align: center;
}

</style>