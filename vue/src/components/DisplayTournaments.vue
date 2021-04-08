<template>
    <div class="display-tournaments">
        <h1>All Tournaments</h1>
        <div class="tournament-search">
            <div>
                
            </div>
        </div>
        <table>
            <tr>
                <th></th>
                <th>Tournament Name</th>
                <th>Start Date</th>
                <th>End Date</th>
                <th>Max Participants</th>
                <th>Elimination</th>
                <th>Type</th>
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
                        <option value=""></option>
                        <option value="false">Single</option>
                        <option value="true">Double</option>
                    </select>
                </td>
                <td>
                    <select v-model="search.isTeam">
                        <option value=""></option>
                        <option value="false">Individual</option>
                        <option value="true">Team</option>
                    </select>
                </td>
            </tr>
            <tr v-for="tournament in searchTournaments" v-bind:key="tournament.id" >
                <td></td>
                <td>{{ tournament.name }}</td>
                <td>{{ tournament.startDate }}</td>
                <td>{{ tournament.endDate }}</td>
                <td>{{ tournament.maxParticipants }}</td>
                <td>{{ tournament.isDouble ? 'Double' : 'Single'}}</td>
                <td>{{ tournament.isTeam ? 'Team' : 'Individual'}}</td>
                <td><input type="submit" value="Join" /></td>
            </tr>
        </table>
            


    </div>
  
</template>

<script>
import tournamentService from '../services/TournamentService.js'

export default {
    name: 'display-tournaments',
    data() {
        return {
            tournaments: [],
            search: {
                name: '',
                startDate: '',
                endDate: '',
                maxParticipants: '',
                isDouble: '',
                isTeam: ''
            }
        }
    },
    created() {
        tournamentService.getAllTournaments().then(response =>{
            if(response.status === 200) {
                this.tournaments = response.data;
            }
        })
    },
    computed: {
        searchTournaments() {
            let filteredTournaments = this.tournaments;

            if(this.search.name != "") {
                filteredTournaments = filteredTournaments.filter( (tournament) => 
                    tournament.name.toLowerCase().includes(this.search.name.toLowerCase())
                );
            }
            if(this.search.maxParticipants != '') {
                filteredTournaments = filteredTournaments.filter(tournament => {
                    tournament.maxParticipants <= this.search.maxParticipants;
                });
            }
            if(this.search.startDate != '') {
                filteredTournaments = filteredTournaments.filter(tournament => {
                    tournament.startDate >= this.search.startDate;
                });
            }
            if(this.search.endDate != '') {
                filteredTournaments = filteredTournaments.filter(tournament => {
                    tournament.endDate <= this.search.endDate;
                });
            }
            if(this.search.isTeam != '') {
                filteredTournaments = filteredTournaments.filter(tournament => {
                    tournament.isTeam === this.search.isTeam;
                });
            }
            if(this.search.isDouble != '') {
                filteredTournaments = filteredTournaments.filter(tournament => {
                    tournament.isDouble === this.search.isDouble;
                });
            }
            return filteredTournaments;
        }
    }
}
</script>

<style>

</style>