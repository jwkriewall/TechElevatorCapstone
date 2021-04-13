<template>
    <form class="tournamentForm">
        <h1>Edit Tournament</h1>
        <div v-if="!modifyOrganizer" class="tournament-info">
            <div>
                <label for="name">Tournament Name: </label>
                <input type="text" id="name" name="name" v-model="tournament.name" />
            </div>
            <div>
                <label for="maxParticipants">Max. Participants: </label>
                <input type="number" id="maxParticipants" name="maxParticipants" min="2" max="16" step="2" value="8" v-model="tournament.maxParticipants" />
            </div>
            <div>
                <label for="startDate">Start Date: </label>
                <input type="date" id="startDate" name="startDate" :min="$parent.findTodaysDate" v-model="tournament.startDate" />
            </div>
            <div>
                <label for="endDate">End Date: </label>
                <input type="date" id="endDate" name="endDate" v-model="tournament.endDate" />
            </div>
            <div class="toggleSwitch">
                    <p>Individual</p> 
                    <input class="toggle" type="checkbox" id="switch"  v-model="tournament.team" /><label class="toggle" for="switch">Toggle</label>
                    <p>Team</p> 
            </div>  
            <div class="toggleSwitch">
                    <p>Single Elimination</p> 
                    <input class="toggle" type="checkbox" id="flip" v-model="tournament.double" /><label class="toggle" for="flip">Toggle</label>
                    <p>Double Elimination</p> 
            </div>
        </div>
        <div v-if="modifyOrganizer" class="organizer-info">
            <div>
                <label for="organizerFirstName">Organizer First Name: </label>
                <input type="text" id="organizerFirstName" name="organizerFirstName" v-model="organizer.firstName" />
            </div>
            <div>
                <label for="organizerLastName">Organizer Last Name: </label>
                <input type="text" id="organizerLastName" name="organizerLastName" v-model="organizer.firstName" />
            </div>
            <div>
                <label for="organizerName">Organizer Email: </label>
                <input type="text" id="organizerEmail" name="organizerEmail" v-model="organizer.firstName" />
            </div>
        </div>
        <div class="buttons">
            <input v-if="modifyOrganizer" type="button" value="Back" @click="toggleModifyOrganizer" />
            <input v-if="!modifyOrganizer" type="button" value="Next" @click="toggleModifyOrganizer" />
            <input type="button" value="Update" @click="$parent.updateAll(organizer, tournament)" />
            <input type="button" value="Cancel" @click="$parent.toggleModifyTournament" />
        </div>
    </form>
</template>

<script>
export default {
    name: 'edit-tournament',
    props: ['tournament', 'organizer'],
    data() {
        return {
            modifyOrganizer: false,
        }
    },
    methods: {
        toggleModifyOrganizer() {
            this.modifyOrganizer = !this.modifyOrganizer;
        }
    }
}
</script>

<style>

</style>