import axios from 'axios';

export default {

  getAllParks() {
    return axios.get('/parks')
  },

  getParksForState(state) {
    return axios.get('/parks?stateCode=' + state);
  }

}