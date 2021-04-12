<template>
  <div class="container">
      <div class="content">
          <h1>{{tournament.name}} Details</h1>
            <ul>
                <li>Tournament Name: {{tournament.name}}</li>
                <li>Max. Participants: {{tournament.maxParticipants}}</li>
                <li>Start Date: {{tournament.startDate}}</li>
                <li>End Date: {{tournament.endDate}}</li>
                <li v-if="tournament.team" >Type: Team Tournament</li>
                <li v-if="!tournament.team" >Type: Individual Tournament</li>
                <li v-if="tournament.double">Style: Double Elimination</li>
                <li v-if="!tournament.double">Style: Single Elimination</li>
                <li>Organizer Name: {{organizer.firstName}} {{organizer.lastName}}</li>
                <li>Organizer Email: {{organizer.email}}</li>
            </ul>
      </div>
  </div>
</template>

<script>
export default {
    name: 'tournament-details',
    props: ['tournament'],

}
</script>

<style>

</style>