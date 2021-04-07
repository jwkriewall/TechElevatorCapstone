<template>
  <div id="login" class="text-center">
    <form class="form-signin" @submit.prevent="login">
      <h1 class="h3 mb-3 font-weight-normal">Please Sign In</h1>
      <div
        class="alert alert-danger"
        role="alert"
        v-if="invalidCredentials"
      >Invalid username and password!</div>
      <div
        class="alert alert-success"
        role="alert"
        v-if="this.$route.query.registration"
      >Thank you for registering, please sign in.</div>
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
      <router-link :to="{ name: 'register' }">Need an account?</router-link>
      <button class="submit" type="submit">Sign in</button>
    </form>
  </div>
</template>

<script>
import authService from "../services/AuthService";

export default {
  name: "login",
  components: {},
  data() {
    return {
      user: {
        username: "",
        password: ""
      },
      invalidCredentials: false
    };
  },
  methods: {
    login() {
      authService
        .login(this.user)
        .then(response => {
          if (response.status == 200) {
            this.$store.commit("SET_AUTH_TOKEN", response.data.token);
            this.$store.commit("SET_USER", response.data.user);
            this.$router.push("/");
          }
        })
        .catch(error => {
          const response = error.response;

          if (response.status === 401) {
            this.invalidCredentials = true;
          }
        });
    }
  }
};
</script>
<style scoped>
  div h1 {
    text-decoration: underline;
    text-decoration-color: #e74c3c;
  }

  div#login {
    font-family: 'Poppins';
    text-align: center;
    background: url('../assets/blue-dice.jpg') fixed center top;
    background-size: cover;
    padding-bottom: 40vh;
    color: white;
  }

  form.form-signin{
    display: flex;
    flex-direction: column;
    align-items: center;
  }

  input#password, input#username {
    width: 15vw;
    height: 2em;
    border-radius: 10px;
    margin-bottom: 10px;
    text-align: center;
    font-weight:lighter;
    border: 1px solid white;
    background-color: #44617e;
    color: #f4f4f4;
    -webkit-text-fill-color: #f4f4f4;
  }

div button.submit {
  height: 2em;
  width: 12vw;
  min-width: 50px;
  margin-top: 40px;
  border-radius: 15px;
  background-color: #e74c3c;
  color: white;
  font-size: 1.1rem;
  border: none;
}


div button.submit:hover {
  background-color: #d85b4d;
}

#login a{
  color: white;
  margin-top:10px;
}
#login a:hover {
  color:#e74c3c;
  text-decoration:none;
}
label {
  text-shadow: #44617e;
  font-size: 1.35rem;
}

label.sr-only {
  font-size: 1.6rem;;
}

textarea:focus, form.form-signin > input:focus {
  outline: none;
  box-shadow: 0px 0px 0px 2px #e74c3c;
  background-color:white;
  color: #707070;
  /*this should change the color of the box on focus?*/
}



</style>
