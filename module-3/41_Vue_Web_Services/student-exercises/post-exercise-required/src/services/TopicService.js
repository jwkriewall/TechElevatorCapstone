import axios from 'axios';

const http = axios.create({
  baseURL: "http://localhost:3000"
});

//Add a new method to the service object that accepts a topic as an argument, 
//performs a `POST` request to the URL `/topics`, and returns a Promise. Use Postman to perform a 
//`POST` request to `/topics` and make sure the service endpoint works before moving on. -->

export default {

  list() {
    return http.get('/topics');
  },

  get(id) {
    return http.get(`/topics/${id}`);
  },

  addTopic(topic) {
    return http.post(`/topics`, topic);
  },

  updateTopic(topic) {
    return http.put(`/topics/${topic.id}`, topic);
  },

  deleteTopic(topic) {
    return http.delete(`/topics/${topic}`);
  }


  

  
  




}


