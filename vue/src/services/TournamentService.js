import axios from 'axios';

export default{
    newTournament(tournaments){
        return axios.post('/tournaments', tournaments);
    }
}