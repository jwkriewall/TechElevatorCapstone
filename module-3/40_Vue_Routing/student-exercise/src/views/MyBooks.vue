<template>
  <div>
      <reading-list/>
      
<!--Add a link to the bottom of the `MyBooks` 
view that links to the `NewBook` view.-->
      <router-link to='addBook'>Add Book</router-link>
    
  </div>
</template>



<script>
import ReadingList from '@/components/ReadingList.vue'


export default {
    components: {
        ReadingList,
        
    }
}
</script>

<style>

</style>