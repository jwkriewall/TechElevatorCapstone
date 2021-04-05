import axios from 'axios';

export default{
    newOrganizer(organizers){
        return axios.post('/organizers', organizers);
    },

    getOrganizer(userID){
        return axios.get('/organizers', userID);
    },

    getOrganizerInfo(organizerId){
        return axios.get('/organizers/:id', organizerId);
    }
}