<template>
    <div class="container">
        <div class="content">
            <tournament-details :tournament="tournament" />
            <div class="buttons">
                <input type="button" value="Modify" v-if="isCurrentUserOrganizer" v-show="!okModify" @click='okModify = true' />
                <input type="button" value="End Tournament" v-if="isCurrentUserOrganizer" @click='endTournament' />
            </div>   
            <div v-if="okModify">
                <!-- INSERT EDIT TOURNAMENT COMPONENT -->

            </div>
        </div>
        <div class="image">
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
        </div>
    </div>
</template>

<script>
import organizerService from "../services/OrganizerService.js";
import tournamentService from "../services/TournamentService.js";
import tournamentDetails from "../components/TournamentDetails.vue";

export default {
    name: 'tournament-details',
    components: [ tournamentDetails ],
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
        },
        endTournament() {
            if(confirm("Are you sure you want to end the tourament?")) 
                // mark tournament object as ended && update database
                this.tournament.ended = true;
                tournamentService.updateTournament(this.tournament);
                // get list of users who opted in to email && send the notification
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
    align-items: flex-end;
    justify-content: center;
}
.tournament-seeding {
    margin-right: 90px;
}
h1 {
    font-size: 2rem;
}
ul{
    margin: 0;
    padding: 0;
    list-style-position: outside;
    list-style-type: none;
}
ul li {
    padding: 3px 0;
    margin: 0;
    display: block;
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