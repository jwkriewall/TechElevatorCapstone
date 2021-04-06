import axios from 'axios';

export default{
    newTournament(tournament){
        return axios.post('/tournaments', tournament);
    },
    getTournament(tournamentId){
        return axios.get('/tournaments/' + tournamentId);
    },
    updateTournament(tournament){
        return axios.put('/tournaments', tournament);
    }
}