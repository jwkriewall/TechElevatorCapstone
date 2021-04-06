import axios from 'axios';

export default{
    newOrganizer(organizers){
        return axios.post('/organizers', organizers);
    },

    getOrganizer(user){
        return axios.get('/organizers', user);
    },

    getOrganizerInfo(organizerId){
        return axios.get('/organizers/' + organizerId);
    }
}