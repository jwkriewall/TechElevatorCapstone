<template>
  <div id="app">
    <div id="nav">
     <a v-bind:href="{ name: 'home' }"> <img class="BRCKT" src="@/assets/BRCKT-05.png" alt="Bracket Logo" /></a>
      <!-- <router-link v-bind:to="{ name: 'home' }">Home</router-link>  -->
      <router-link v-bind:to="{name: 'create'}">Create</router-link>
      <router-link class="logout" v-bind:to="{ name: 'logout' }" v-if="$store.state.token != ''">Logout</router-link>
      <router-link class="logout" v-bind:to="{ name: 'login' }" v-if="$store.state.token == ''">Login</router-link>
      <router-link class="logout" v-bind:to="{ name: 'register' }">Sign Up</router-link>
      
   
    </div>
    <router-view />
  </div>
</template>

<style scoped>
  #nav{
    font-family: 'Poppins';
    font-weight: 700;
    display: flex;
    justify-content: space-evenly;
    background: #f4f4f4;
    height: 130px;
    align-items: center;
    font-size: 1.5rem;
  }

  #nav .logout{
    font-weight: 300;
    font-size: 1.0rem;
  }

  #nav a {
    color: #2c3e50;
    text-decoration: none;
  }

  #nav a:hover:not(a:first-child), #nav a.router-link  {
    border-bottom: 2px solid #e74c3c;
  }

  .BRCKT:hover{
    border: none;
  }
  .BRCKT {
    width: 300px;
  }
</style>


