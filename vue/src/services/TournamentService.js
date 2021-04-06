import axios from 'axios';

export default{
    newTournament(tournament){
        return axios.post('/tournaments', tournament);
    }
}