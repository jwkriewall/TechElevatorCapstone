<template>
  <div id="nav">
     <a class="logo" href="/"> <img class="BRCKT" src="@/assets/BRCKT-05.png" alt="Bracket Logo" /></a>
      <div class="applinks">
        <router-link v-bind:to="{name: 'create'}">Create</router-link>
        <router-link v-bind:to="{name: 'search-tournaments'}">Search/Join</router-link>
        <router-link class="placeholder" v-bind:to="{name: 'create'}"></router-link>
        <router-link class="placeholder" v-bind:to="{name: 'create'}"></router-link>
      </div>
      <div class="userlinks">
        <router-link class="logout" v-bind:to="{ name: 'my-tournaments' }" v-if="$store.state.token != ''">My Tournaments</router-link>
        <router-link class="logout" v-bind:to="{ name: 'account' }" v-if="$store.state.token != ''">My Account</router-link>
        <router-link class="logout" v-bind:to="{ name: 'logout' }" v-if="$store.state.token != ''">Logout</router-link>
        <router-link class="logout" v-bind:to="{ name: 'login' }" v-if="$store.state.token == '' ">Login</router-link>
        <router-link class="logout" v-bind:to="{ name: 'register' }" v-if="$store.state.token == ''">Sign Up</router-link>
        <router-link class="logout" v-bind:to="{ name: 'rankings' }" v-if="$store.state.token == ''">Rankings</router-link>
      </div>
    </div>
</template>

<script>
export default {
    name: 'navigation-bar',
}
</script>

<style>
/* NAVIGATION BAR */
  #nav {
    font-family: 'Poppins';
    font-weight: 700;
    display: flex;
    justify-content: flex-start;
    background: #f4f4f4;
    height: 130px;
    align-items: center;
    font-size: 1.5rem;
    grid-area: 'header';
  }
  #nav .applinks {
    display:flex;
    justify-content: space-around;
    align-items: center;
    flex-grow:4;
  }
  #nav .applinks a:not(a:first-child) {
    margin-left: 30px;
  }
  #nav .userlinks {
    display: flex;
    justify-content: space-evenly;
    flex-grow: 2;
  }
  #nav .userlinks a {
    min-width:70px;
    text-align:center;
  }
  
  #nav .logout{
    font-weight: 300;
    font-size: 1.0rem;
  }
  #nav a {
    color: #2c3e50;
    text-decoration: none;
    margin-top:2px;
    padding-bottom:2px;
  }
  
  #nav a:hover:not(a.logo), a.router-link-exact-active  {
    padding-bottom:0px;
    border-bottom: 2px solid #e74c3c;
  }
  .BRCKT:hover{
    border: none;
  }
  .BRCKT {
    width: 300px;
    margin-right:50px;
    flex-basis: 350px;
    flex-grow: 1;
  }
</style>