<template>
  <div>
      <p>Pick a date between June 16, 1995 and today
        <input type="date" v-on:change.prevent="getDataForSpecificDate" v-model="date">
      </p>
      <h1>Current Media</h1>
      <h2>{{ nasadata.title}}</h2>
      <p>Date: {{ nasadata.date }}</p>
      <p>{{ nasadata.explanation }}</p>

        
      <div v-if="nasadata.media_type === 'image'">
        <img :src="nasadata.url" :alt="nasadata.title">
      </div>
     <div v-else>
        <p>Link: <a :href="nasadata.url" target="_BLANK">{{ nasadata.media_type}}</a></p>
      </div>

  </div>
</template>

<script>
import nasaApiService from '@/services/NasaApiService'

export default {
    data() {
        return {
            nasadata: {},
            date: ''
        }
    },
    created() {
        this.getDataFromNasa();
    },
    methods: {
        getDataFromNasa() {
            nasaApiService.getImageOfTheDay().then( response => {
                this.nasadata = response.data;
            }).catch (err => console.error(err));
        },
        getDataForSpecificDate() {
            nasaApiService.getImageforDate(this.date).then( response => {
                this.nasadata = response.data;
            }).catch (err => console.error(err));
        }
    }
}
</script>

<style>

</style>