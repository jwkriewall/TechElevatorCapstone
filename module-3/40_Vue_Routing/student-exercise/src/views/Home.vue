<template>
    <div>
        <best-seller-list/>
        <new-releases-list/>
       
        

        <!--<router-link v-bind:to="{ name: 'best-seller-list'}"> </router-link> -->
    </div>
</template>




<script>
import BestSellerList from '../../../instructor/src/components/BestSellerList.vue'
import NewReleasesList from '../../../instructor/src/components/NewReleasesList.vue'

export default {
  components: { 
        BestSellerList,
        NewReleasesList
    }

}
</script>

<style>

</style>