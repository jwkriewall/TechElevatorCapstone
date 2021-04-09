<template>
    <div class="display-tournaments">
        <table>
            <tr>
                <th></th>
                <th>Tournament Name</th>
                <th>Start Date</th>
                <th>End Date</th>
                <th>Max Participants</th>
                <th>Elimination</th>
                <th>Type</th>
                <th v-if="myTournaments">Role</th>
                <th></th>
            </tr>
            <tr>
                <td></td>
                <td><input type="text" placeholder="search" v-model="search.name" /></td>
                <td><input type="date" placeholder="search" v-model="search.startDate" /></td>
                <td><input type="date" placeholder="search" v-model="search.endDate" /></td>
                <td><input type="number" placeholder="search" v-model="search.maxParticipants" /></td>
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
                <td>
                    <select v-if="myTournaments" v-model="search.role">
                        <option selected value="All">All</option>
                        <option value="Participant">Participant</option>
                        <option value="Organizer">Organizer</option>
                    </select>
                </td>
            </tr>
            <tr v-for="tournament in searchTournaments" v-bind:key="tournament.id" :class="[organizer.organizerId == tournament.organizerId && myTournaments ? 'organizer' : '']">
                <td></td>
                <td>{{ tournament.name }}</td>
                <td>{{ tournament.startDate }}</td>
                <td>{{ tournament.endDate }}</td>
                <td>{{ tournament.maxParticipants }}</td>
                <td>{{ tournament.double ? 'Double' : 'Single'}}</td>
                <td>{{ tournament.team ? 'Team' : 'Individual'}}</td>
                <td v-if="myTournaments">{{ tournament.organizerId == organizer.organizerId ? 'Organizer' : 'Participant' }}</td>
                <td><input type="submit" value="Details" @click="goToDetails(tournament.id)" /></td>
                <td v-if="!myTournaments && tournament.organizerId != organizer.organizerId ">
                    <!-- how to add in line above?        && !userTournaments.includes(tournament.id) -->
                    <input type="submit" value="Join" @click="joinTournament(tournament.id)" />
                </td>
            </tr>
        </table>
            


    </div>
  
</template>

<script>
import tournamentService from '../services/TournamentService';


export default {
    name: 'display-tournaments',
    props: ['organizer', 'tournaments', 'userTournaments'],
    data() {
        return {
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
    methods: {
        goToDetails(tournamentId) {
            this.$router.push('/tournaments/' + tournamentId);
        },
        joinTournament(tournamentId) {
            tournamentService.joinTournament(this.$store.state.user, tournamentId)
        }
    },
    computed: {
        myTournaments() {
            return this.$route.name == "my-tournaments"
        },
        searchTournaments() {
            let filteredTournaments = this.tournaments;

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

<style>
.organizer {
    background-color: lightskyblue;
}
</style>