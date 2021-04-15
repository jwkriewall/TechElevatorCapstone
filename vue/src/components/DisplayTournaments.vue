<template>
    <div class="content-full-width">
        <form name="tournament-filter">
            <table>
            <tr>
                <th>Tournament Name</th>
                <th>Start Date</th>
                <th>End Date</th>
                <th>Max <br />Participants</th>
                <th>Elimination</th>
                <th>Type</th>
                <th v-if="myTournaments">Role</th>
            </tr>
            <tr>
                <td><input type="text" placeholder="search" v-model="search.name" /></td>
                <td><input type="date" placeholder="search" v-model="search.startDate" /></td>
                <td><input type="date" placeholder="search" v-model="search.endDate" /></td>
                <td><input type="number" placeholder="search" min="0" v-model="search.maxParticipants" /></td>
                <td>
                    <select v-model="search.isDouble">
                        <option selected value="All">All</option>
                        <option value="false">Single</option>
                        <option value="true">Double</option>
                    </select>
                </td>
                <td>
                    <select v-model="search.isTeam">
                        <option selected value="All">All</option>
                        <option value="false">Individual</option>
                        <option value="true">Team</option>
                    </select>
                </td>
                <td v-if="!myTournaments"><input type="reset" @click="resetFormFilter" value="Clear Filter" class="clear-filter" /></td>
                <td v-if="myTournaments">
                    <select v-model="search.role">
                        <option selected value="All">All</option>
                        <option value="Participant">Participant</option>
                        <option value="Organizer">Organizer</option>
                    </select>
                </td>
            </tr>
            <tr v-for="tournament in searchTournaments" v-bind:key="tournament.id" 
            :class="[tournament.endDate < findTodaysDate ? 'endedTournament' : ''] + ' ' +  
            [organizer.organizerId == tournament.organizerId && myTournaments ? 'organizer' : '']"
            >
                <td>{{ tournament.name }}</td>
                <td>{{ tournament.startDate }}</td>
                <td>{{ tournament.endDate }}</td>
                <td>{{ tournament.maxParticipants }}</td>
                <td>{{ tournament.double ? 'Double' : 'Single'}}</td>
                <td>{{ tournament.team ? 'Team' : 'Individual'}}</td>
                <td v-if="myTournaments">{{ tournament.organizerId == organizer.organizerId ? 'Organizer' : 'Participant' }}</td>
                <td class="buttonCell">
                    
                    <input type="button" value="Join" 
                    v-if="!myTournaments && 
                    !$store.state.userTournaments.includes(tournament.id) && 
                    tournament.endDate > findTodaysDate"
                    @click="$router.push(`/tournaments/${tournament.id}/join`)" />
                    <input type="button" value="Details" @click="goToDetails(tournament.id)" />
                </td>
            </tr>
        </table>
        </form>
            


    </div>
  
</template>

<script>
import tournamentService from '../services/TournamentService';
// import moment from 'moment'

export default {
    name: 'display-tournaments',
    // components: { moment },
    props: ['organizer', 'tournaments', 'userTournaments'],
    data() {
        return {
            currentEnteredTournaments: [],
            search: {
                name: '',
                startDate: '',
                endDate: '',
                maxParticipants: '',
                isTeam: "All",
                isDouble: "All",
                role: "All"
            },
        }
    },
    created() {
        this.currentEnteredTournaments = this.$store.state.userTournaments;
    },
    
    methods: {
        goToDetails(tournamentId) {
            this.$router.push('/tournaments/' + tournamentId);
        },
        resetFormFilter() {
            this.search.name = '';
            this.search.startDate = '';
            this.search.endDate = '';
            this.search.maxParticipants = '';
            this.search.isTeam = "All";
            this.search.isDouble = "All";
            this.search.role = "All";
        },
        getUserTournaments() {
            tournamentService.getUserTournaments().then(response => {
            if(response.status === 200) {
                this.$store.commit('UPDATE_USER_TOURNAMENTS', response.data);
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
    },
    computed: {
        myTournaments() {
            return this.$route.name == "my-tournaments"
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
        },
        searchTournaments() {
            let sortedTournaments = this.tournaments;
            
            sortedTournaments = sortedTournaments.sort((a, b) => 
                   a.startDate > b.startDate ? -1 : 1
                );

            let filteredTournaments = sortedTournaments;
        
            if(this.search.name != "") {
                filteredTournaments = filteredTournaments.filter( (tournament) => 
                    tournament.name.toLowerCase().includes(this.search.name.toLowerCase())
                );
            }
            if(this.search.maxParticipants != '') {
                filteredTournaments = filteredTournaments.filter((tournament) => 
                    tournament.maxParticipants <= this.search.maxParticipants
                );
            }
            if(this.search.startDate != '') {
                filteredTournaments = filteredTournaments.filter(tournament => 
                    tournament.startDate >= this.search.startDate
                );
            }
            if(this.search.endDate != '') {
                filteredTournaments = filteredTournaments.filter(tournament => 
                    tournament.endDate.valueOf() <= this.search.endDate.valueOf()
                );
            }
            if(this.search.isDouble != 'All' ) {
                let searchDouble = this.search.isDouble == 'true'
                filteredTournaments = filteredTournaments.filter(tournament => 
                    tournament.double == searchDouble
                );
            }
            if(this.search.isTeam != 'All' ) {
                let searchTeam = this.search.isTeam == 'true'
                filteredTournaments = filteredTournaments.filter(tournament => 
                    tournament.team == searchTeam
                );
            }
            if(this.myTournaments && this.userTournaments != '' && this.search.role == 'All') {
                filteredTournaments = filteredTournaments.filter( tournament =>
                    tournament.organizerId == this.organizer.organizerId || this.userTournaments.includes(tournament.id)
                );
            } else if(this.myTournaments && this.search.role == 'Organizer' ) {
                filteredTournaments = filteredTournaments.filter( tournament =>
                    tournament.organizerId == this.organizer.organizerId
                );
            } else if(this.myTournaments && this.userTournament != '' && this.search.role == 'Participant') {
                filteredTournaments = filteredTournaments.filter( tournament =>
                    tournament.organizerId != this.organizer.organizerId && this.userTournaments.includes(tournament.id)
                );
            } else if(this.myTournaments) {
                filteredTournaments = filteredTournaments.filter( tournament =>
                    tournament.organizerId == this.organizer.organizerId
                );
            }

            return filteredTournaments;             
            
        }
    }
}
</script>

<style scoped>
.content-full-width {
    min-height: 80vh;
}
table {
    margin: 0 auto;
    text-align: center;
    border-spacing: 0rem;
    width: 95vw;
    color: white;
    font-size: 1.1rem;
}
td {
    min-height:30px;
}
th{
    color: white;
}
tr:nth-child(odd) td {
    background-color: #f4f4f4;
    color: #707070;
}
tr:first-child {
    background: none;
}
tr.endedTournament td {
    border-bottom: 1px solid white;
    background-color: #707070;
    color: white;
}
tr.endedTournament:last-child td {
    border: none;
}
tr td.buttonCell {
    display: flex;
    flex-direction: row;
    align-items: center;
    justify-content: flex-end;
}
input:not(input[type="button"]) {
    border-radius: 0;
    background-color: #fff;
    -webkit-box-shadow: 0 0 0px 1000px #fff inset;
    -webkit-text-fill-color: black;
    color: black;
    border: 1px solid black;
    width: 120px;
    min-width: 120px;
    height: 30px;
}
input[type="date"]::-webkit-calendar-picker-indicator {
    filter: none;
}
input[type="button"] { 
    padding:0;
    border-radius: 6px;
    width: 50px;
    height: 20px;
    margin: 0;
}
input[type="button"] {
    margin: 2px 10px 3px 20px;
}
input[type="submit"].clear-filter {
    margin: 0;
}
select {
    height: 33px;
    margin:0 0 2px 0;
    border: 1px solid black;
    width: 100%;
}
</style>