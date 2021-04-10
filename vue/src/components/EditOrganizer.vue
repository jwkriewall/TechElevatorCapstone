<template>
    <div class="edit-organizer">
        <h1>Organizer Info</h1>
        <div>   
            <label for="firstName">First Name: </label>
            <input class="input" type="text" name="firstName" id="organizer-firstName" v-model.trim="organizer.firstName" />
        </div>
        <div>   
            <label for="lastName">Last Name: </label>
            <input class="input" type="text" name="lastName" id="organizer-lastName" v-model.trim="organizer.lastName" />
        </div>
        <div>   
            <label for="phone">Phone Number: </label>
            <input class="input" type="tel" pattern="[0-9]{3}[0-9]{3}[0-9]{4}" id="organizer-phone" v-model.trim="organizer.phone" />
        </div>
        <div>   
            <label for="email">Email Address: </label>
            <input class="input" type="email" id="organizer-email" v-model.trim="organizer.email" />
        </div>
        <div class="buttons">
            <input class="submit" type="button" value="Update" @click="updateOrganizer" />
            <input type="button" value="Cancel" @click="$store.commit('TOGGLE_MODIFY_ORGANIZER')" />
        </div>
    </div>
</template>

<script>
import organizerService from '../services/OrganizerService.js';

export default {
    name: 'edit-organizer',
    props: ['organizer'],
    methods: {
        updateOrganizer() {
            organizerService.updateOrganizer(this.organizer).then(response => {
                if(response.status == 200) {
                    this.$store.commit('TOGGLE_MODIFY_ORGANIZER');
                }
            })
            .catch(error => {
            if (error.response) {
            alert(error.response.statusText);
            } else if (error.request) {
            alert("Server could not be reached.");
            } else {
            alert("Request could not be created.");
            }
            });
        }
    }
}
</script>

<style scoped>
</style>