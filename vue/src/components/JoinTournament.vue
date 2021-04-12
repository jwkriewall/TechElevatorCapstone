<template>
  <div class="container">
        <div class="content">
            <tournament-details :tournament="tournament" />
            <!-- display tournament details
            input user NICKNAME
            opt in to email checkbox
            add join bbutton -->


        </div>
  </div>
</template>

<script>
import tournamentDetails from '/TournamentDetails.vue';

export default {
    props: ['tournament'],
    components: {
        tournamentDetails
    }
}
</script>

<style>

</style>