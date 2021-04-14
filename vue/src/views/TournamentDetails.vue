<template>
    <div class="container">
        <div class="content">
            
            <tournament-details v-if="!modifyTournament" :tournament="tournament" :organizer="organizer" />
            <edit-tournament v-if="modifyTournament" :tournament="tournament" :organizer="organizer" />
            
            <div class="buttons">
                <input type="button" value="Modify" v-if="isCurrentUserOrganizer" v-show="!modifyTournament" @click='modifyTournament = true' />
                <input type="button" value="End Tournament" v-if="isCurrentUserOrganizer && !modifyTournament" @click='endTournament' />
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
                // get list of users who opted in to email && send the notification

                // if(joinTournament.user.notify == true){
                //     alert('Email sent');
                // }

                // if person where notify == true, then send email to their email address
                // notify
              
                this.sendEmail();
        },

        sendEmail() {
           
            window.Email && window.Email.send({
                Host: "smtp.gmail.com",
                Username: "brcktproject@gmail.com",
                Password: "thisisapassword1!",
                To: 'brcktproject@gmail.com',
                //To: this.rankings.toString,
                From: "brcktproject@gmail.com",
                Subject: "Your Tournament Has Ended!",
                Body: "A recent tournament you entered has now concluded. Please check the website to see the final bracket! XXX was the winner!"


                // Attachments: [
                // {
                //     name: "File_Name_with_Extension",
                //     path: "Full Path of the file"
                //     // Could we put picture of bracket here????
                // }]


                })
                // .then(message => alert("Mail has been sent successfully")
                // ); 
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