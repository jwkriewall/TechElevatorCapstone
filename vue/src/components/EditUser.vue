<template>
    <div class="edit-user">
        <h1>Update User Information</h1>
        <div class="update-user-info">
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
            <div>
                <label for="password">Password: </label>
                <input class="input" :type="[showPassword ? 'text' : 'password']" id="password" v-model.trim="user.password" />
                <font-awesome-icon :icon="icon" @click="toggleShowPass" />
            </div>
            <div>
                <label for="verify-password">Verify Password: </label>
                <input class="input" :type="[showPassword ? 'text' : 'password']" id="verify-password" v-model.trim="verifyPassword" />
                <font-awesome-icon :icon="icon" @click="toggleShowPass" />
            </div>
            <input class="submit" type="submit" value="Update" @click.prevent='updateUser' />
        </div>
    </div>
</template>

<script>
import userService from '../services/AuthService.js';

export default {
    name: 'edit-user',
    props: ['user'],
    data() {
        return {
            showPassword: false,
            verifyPassword: '',
            icon: 'eye'
        }
    },
    methods: {
        toggleShowPass() {
            this.showPassword = !this.showPassword;
            if(this.icon == 'eye') { this.icon = 'eye-slash'; }
            else { this.icon = 'eye'; }
        },
        updateUser() {
            userService.update(this.user).then(response => {
                if(response.status == 200) {
                    this.$router.push({name: 'account'});
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

</style>