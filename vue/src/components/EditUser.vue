<template>
    <div class="edit-user">
        <div>   
            <label for="firstName">First Name: </label>
            <input class="input" type="text" name="firstName" id="firstName" v-model.trim="user.firstName" />
        </div>
        <div>   
            <label for="lastName">Last Name: </label>
            <input class="input" type="text" name="lastName" id="lastName" v-model.trim="user.lastName" />
        </div>
        <div>   
            <label for="phone">Phone Number: </label>
            <input class="input" type="tel" pattern="[0-9]{3}[0-9]{3}[0-9]{4}" id="phone" v-model.trim="user.phone" />
        </div>
        <div>   
            <label for="email">Email Address: </label>
            <input class="input" type="email" id="email" v-model.trim="user.email" />
        </div>
        <div>
            <label for="username">Username: </label>
            <input class="input" type="username" id="username" v-model.trim="user.username" />
        </div>
        <!-- <div v-if="showField">
            <label for="password">Password: </label>
            <input class="input" :type="[showPassword ? 'text' : 'password']" id="password" v-model.trim="user.password" />
            <font-awesome-icon :icon="icon" @click="toggleShowPass" />
        </div>
        <div v-if="showField">
            <label for="verify-password">Verify Password: </label>
            <input class="input" :type="[showPassword ? 'text' : 'password']" id="verify-password" v-model.trim="user.password" />
            <font-awesome-icon :icon="icon" @click="toggleShowPass" />
        </div> -->
        <div class="buttons">
            <input class="submit" type="button" value="Update" @click="updateUser" />
            <input class="submit" type="button" value="Cancel" @click="$store.commit('TOGGLE_MODIFY_USER')" />
        </div>
        
    </div>
</template>

<script>
import userService from '../services/AuthService.js';

export default {
    name: 'edit-user',
    data() {
        return {
            showPassword: false,
            verifyPassword: '',
            icon: 'eye',
            user: this.$store.state.user,
        }
    },
    methods: {
        toggleShowPass() {
            this.showPassword = !this.showPassword;
            if(this.icon == 'eye') { this.icon = 'eye-slash'; }
            else { this.icon = 'eye'; }
        },
        updateUser() {
            // if(this.user.password === this.user.password) {
            this.user.userImageUrl = this.$store.state.userImageUrl;
                userService.update(this.user).then(response => {
                    if(response.status == 200) {
                        this.$store.commit('TOGGLE_MODIFY_USER');
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
            // } else {
            //     alert("Passwords do not match");
            // }
        }
    }
}
</script>

<style>

/* Component Styling */
.update-user-info {
    display: flex;
    flex-direction: column;
    align-items: flex-start;
}
svg {
    position:relative;
    right: 30px;
}
textarea:focus + svg, input.input:focus + svg {
    filter: invert(100%);
}
.buttons {
    display:flex;
    justify-content: flex-start;
    margin: 30px 0;
}
.buttons input {
    margin: 0 10px;
}

</style>