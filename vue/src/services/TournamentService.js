import axios from 'axios';

export default{
    newTournament(tournament){
        return axios.post('/tournaments', tournament);
    },
    getTournament(tournamentId){
        return axios.get('/tournaments/' + tournamentId);
    },
    updateTournament(tournament){
        return axios.put('/tournaments/' + tournament.id, tournament);
    },
    getAllTournaments(){
        return axios.get('/tournaments');
    },
    getUserTournaments() {
        return axios.get('/users/tournaments/');
    },
    joinTournament(tournamentId, user) {
        return axios.post('/tournaments/' + tournamentId + '/registration/', user);
    },
    getTournamentRankings(tournamentId) {
        return axios.get('/tournaments/' + tournamentId + '/rankings/');
    },
    getUserEmails(tournamentId){
        return axios.get('/tournaments/' + tournamentId + '/emails/');
    }
}