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
import tournamentService from '../services/TournamentService.vue';

export default {
    components: {
        tournamentDetails
    },
    data() {
        return {
            tournament: {},
        }
    },
    created() {
        tournamentService.getTournament(this.$route.params).then(response => {
            this.tournament = response.data;
        })
    }
}
</script>

<style>

</style>