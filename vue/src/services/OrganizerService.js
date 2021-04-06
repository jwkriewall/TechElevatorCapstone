import axios from 'axios';

export default{
    newOrganizer(organizers){
        return axios.post('/organizers', organizers);
    },

    getOrganizer(userId){
        return axios.get('/organizers/info/' + userId);
    },

    getOrganizerInfo(organizerId){
        return axios.get('/organizers/' + organizerId);
    }
}