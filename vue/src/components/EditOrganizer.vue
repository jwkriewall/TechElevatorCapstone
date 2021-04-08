<template>
    <div class="edit-organizer">
        <div class="update-organizer-info">
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
            <input class="submit" type="submit" value="Update" @click="updateOrganizer" />
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

<style>

/* Input Styling */
input.input {
    border-radius: 15px;
    height: 2.2rem;
    width: 18vw;
    min-width: 280px;
    padding: 0 20px;
    font-size: 1rem;
    background-color: #44617e;
    border: 1px solid white;
    color: white;
    -webkit-text-fill-color: white;
    -webkit-box-shadow: 0 0 0px 1000px #44617e inset;
}
label {
    margin: 10px 10px 10px 0;
    width: 12vw;
    min-width: 125px;
    display: inline-block;
}
textarea:focus, input.input:focus {
  outline: none;
  box-shadow: 0px 0px 0px 2px #e74c3c;
  background-color:white;
  color: #707070;
  -webkit-text-fill-color: #707070;
  -webkit-box-shadow: 0 0 0px 1000px #fff inset;
  -webkit-box-shadow: 0 0 0px 2px #e74c3c;
}
input[type="submit"] {
    background-color: #e74c3c;
    -webkit-box-shadow: 0 0 0px 1000px #e74c3c inset;
    color: white;
    font-size: 1rem;
    height: 2rem;
    font-weight: 900;
    margin-top: 20px;
    border: none;
    border-radius: 15px;
    width: 10vw;
    min-width: 80px;
}
input[type="submit"]:hover {
    background-color:#dc8980;
    -webkit-box-shadow: 0 0 0px 1000px #dc8980 inset;
}
input[type="submit"]:focus {
    outline: none;
    border:1px solid white;
}

/* Component Styling */
.update-organizer-info {
    display: flex;
    flex-direction: column;
    align-items: flex-start;
}

</style>