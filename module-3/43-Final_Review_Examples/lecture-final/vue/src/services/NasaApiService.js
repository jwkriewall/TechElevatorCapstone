import axios from 'axios';

const url = "https://api.nasa.gov/planetary/apod?api_key=NsA9i4uYaMnpztCayCuajajhDAOe5whPQmVkT6yp";

/*
Since the default version of Axios is configured to send the TE Bearer Token it cannot be used to call
a 3rd party external API.  To call one use the axios.create() method to create a new instance and use
the new instance to call the external api
*/
const newAxiosInstance = axios.create();

export default {

    getImageOfTheDay() {
        return newAxiosInstance.get(url);
    },
    getImageforDate(date) {
        return newAxiosInstance.get(url + '&date=' + date);
    }
}