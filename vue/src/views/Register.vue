<template>
  <div id="register" class="text-center">
    <form class="form-register" @submit.prevent="register">
      <h1 class="h3 mb-3 font-weight-normal">Create Account</h1>
      <div class="alert alert-danger" role="alert" v-if="registrationErrors">
        {{ registrationErrorMsg }}
      </div>
      <label for="username" class="sr-only">Username</label>
      <input
        type="text"
        id="username"
        class="form-control"
        placeholder="Username"
        v-model="user.username"
        required
        autofocus
      />
      <label for="password" class="sr-only">Password</label>
      <input
        type="password"
        id="password"
        class="form-control"
        placeholder="Password"
        v-model="user.password"
        required
      />
      <input
        type="password"
        id="confirmPassword"
        class="form-control"
        placeholder="Confirm Password"
        v-model="user.confirmPassword"
        required
      />
      <router-link :to="{ name: 'login' }">Have an account?</router-link>
      <button class="btn btn-lg btn-primary btn-block" type="submit">
        Create Account
      </button>
    </form>
  </div>
</template>

<script>
import authService from '../services/AuthService';

export default {
  name: 'register',
  data() {
    return {
      user: {
        username: '',
        password: '',
        confirmPassword: '',
        role: 'user',
      },
      registrationErrors: false,
      registrationErrorMsg: 'There were problems registering this user.',
    };
  },
  methods: {
    register() {
      if (this.user.password != this.user.confirmPassword) {
        this.registrationErrors = true;
        this.registrationErrorMsg = 'Password & Confirm Password do not match.';
      } else {
        authService
          .register(this.user)
          .then((response) => {
            if (response.status == 201) {
              this.$router.push({
                path: '/login',
                query: { registration: 'success' },
              });
            }
          })
          .catch((error) => {
            const response = error.response;
            this.registrationErrors = true;
            if (response.status === 400) {
              this.registrationErrorMsg = 'Bad Request: Validation Errors';
            }
          });
      }
    },
    clearErrors() {
      this.registrationErrors = false;
      this.registrationErrorMsg = 'There were problems registering this user.';
    },
  },
};
</script>

<style scoped>
div#register {
  font-family: 'Poppins';
  text-align: center;
  background: url('../assets/createAccount.jpg') fixed center top;
  background-size: cover;
  padding-bottom: 40vh;
  color: white;
}

#register h1{ 
  font-weight: 800;
}

form.form-register{
  display: flex;
  flex-direction: column;
  align-items: center;
}

textarea:focus, form.form-register > input:focus {
  outline: none;
  box-shadow: 0px 0px 0px 2px #e74c3c;
  background-color:white;
  color: #707070;
}

form.form-register > input, textarea {
  width: 15vw;
  border-radius: 10px;
  height: 2em;
  margin-bottom: 10px;
  text-align: center;
  font-weight:lighter;
  border: 1px solid white;
  background-color: #44617e;
  color: #f4f4f4;
  -webkit-text-fill-color: #f4f4f4;
}

form.form-register label{
  font-size: 1.5rem;
  padding: 5px 0;
}
form.form-register label:not(label:nth-child(2)) {
  margin-top:40px;
}

input#confirmPassword {
  margin-top: 5px;
}

form.form-register button.btn{
  height: 3em;
  width: 10vw;
  min-width: 50px;
  margin-top: 20px;
  border-radius: 15px;
  background-color: #e74c3c;
  color: white;
  font-size: 1.1rem;
  border: none;
}

#register a{
  color: white;
  margin-top:30px;
}

#register a:hover {
  color: #e74c3c;
  text-decoration: none;
}

</style>
