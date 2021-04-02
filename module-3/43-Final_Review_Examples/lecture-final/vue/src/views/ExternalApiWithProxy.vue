<template>
  <div id="parks">
      <h1>National Parks</h1>
      <div>
          <label for="state">State Code:</label>
          <input type="text" id="state" v-model="stateCode" />
          <button :disabled="!stateCode" @click.prevent="getParksForState">Get Parks</button>
      </div>
      <div class="park" v-for="park in parks" :key="park.parkCode">
          <h2><a :href="park.url" target="_BLANK">{{ park.fullName }}</a></h2>
          <p>State: {{park.states}}</p>
          <img :src="park.images[0].url" :alt="park.images[0].altText">
          <p>{{ park.description}}</p>
      </div>
  </div>
</template>

<script>
import parkService from '../services/ParkApiProxyService'

export default {
    data() {
        return {
            parks: [],
            stateCode: ''
        }
    },
    methods: {
        getAllParks() {
            parkService.getAllParks().then( response => {
                this.parks = response.data;
            }).catch( err => console.error(err));
        },
        getParksForState() {
            parkService.getParksForState(this.stateCode).then( response => {
                this.parks = response.data;
            }).catch( err => console.error(err));
        }
    },
    created() {
        this.getAllParks();
    }

}
</script>

<style>
div.park img {
    width: 300px;
}
div.park a {
    text-decoration: none;
}
#state {
    width: 25px;
    margin-left: 10px;
}
div#parks button {
    margin-left: 10px;
}
</style>