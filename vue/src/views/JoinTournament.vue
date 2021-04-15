<template>
  <div class="container">
        <div class="content">
            <tournament-details :tournament="tournament" :organizer="organizer"  />
            <br />
            <h2>Your Info:</h2>
            <div>
                <label>First Name:</label>
                <input type="text" placeholder="type first name" v-model="user.firstName" />
            </div>
            <div>
                <label>Last Name:</label>
                <input type="text" placeholder="type last name" v-model="user.lastName" />
            </div>
            <div>
                <label>Nickname:</label>
                <input type="text" placeholder="type nickname" v-model="user.userNickname" />
            </div>
            
            <div class="opt-in">
                <input type="checkbox" @changed="toggleUserNotify" v-model="user.notify" />
                <p>Opt in to email notification when tournament is completed or closed</p>
            </div>

            <div v-if="user.notify">
                <label>Email: </label>
                <input type="email" placeholder="type email address" v-model="user.userEmail" />
            </div>
            <div class="buttons">
                <input type="button" value="Enter" @click="joinTournament" />
                <input type="button" value="Cancel" @click="$router.push('/tournaments')" />
            </div>
            <br />
        </div>
            <img src="@/assets/track-and-field.jpg" alt="People in front of computer screens playing video games">
  </div>
</template>

<script>
import tournamentDetails from '../components/TournamentDetails.vue';
import tournamentService from '../services/TournamentService.js';
import organizerService from '../services/OrganizerService.js';

export default {
    components: {
        tournamentDetails
    },
    data() {
        return {
            tournament: {},
            organizer: {},
            user: {
                userId: this.$store.state.user.id,
                userSeeding: 0,
                firstName: this.$store.state.user.firstName,
                lastName: this.$store.state.user.lastName,
                userEmail: this.$store.state.user.email,
                userNickname: this.$store.state.user.nickname,
                notify: false,
            },
        }
    },
    created() {
        tournamentService.getTournament(this.$route.params.id).then(response => {
            this.tournament = response.data;
            this.user.tournamentId = this.tournament.id
            organizerService.getOrganizerInfo(this.tournament.organizerId).then(response => {
                this.organizer = response.data;
            })
        })
    },
    methods: {
        joinTournament() {
            tournamentService.joinTournament(this.tournament.id, this.user).then(response => {
                if(response.status === 200) {
                    alert("You have been entered into the tournament");
                    this.$router.push(`/tournaments/${this.tournament.id}`)
                }
            })
        },
        toggleUserNotify() {
            this.user.notify = !this.user.notify
        },
    }
}
</script>

<style scoped>
input[type="checkbox"] {
    border-radius: 15px;
    height: 2.2rem;
    width: 20px;
    min-width: 20px;
    padding: 0 20px;
    font-size: 1rem;
    border: 1px solid black;
    color: white;
    -webkit-text-fill-color:none;
    -webkit-box-shadow: none;
}

input[type="button"] {
  height: 60px;
  font-size: 1.3rem;
}

.container {
    height: 125vh;
}

.container img{
    /* overflow-y: hidden; */
    flex-basis: 50vw;
    width: 50vw;
    height: 100%;
}
.opt-in, .buttons {
    display: flex;
    flex-direction: row;
    align-items: center;
}
p {
    line-height: 1.2rem;
    font-size:0.9rem;
    padding-left:15px;
}
</style>