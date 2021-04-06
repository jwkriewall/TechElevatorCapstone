<template>
  <div id="app">
    <div id="nav">
      <router-link v-bind:to="{ name: 'home' }">Home</router-link>&nbsp;|&nbsp;
      <router-link v-bind:to="{name: 'create'}">Create</router-link>&nbsp;|&nbsp;
      <router-link v-bind:to="{ name: 'logout' }" v-if="$store.state.token != ''">Logout</router-link>
      <router-link v-bind:to="{ name: 'login' }" v-if="$store.state.token == ''">Login</router-link>
      &nbsp;|&nbsp;
      <router-link v-bind:to="{ name: 'register' }">Sign Up</router-link>
   
   
    </div>
    <router-view />
  </div>
</template>

<style>
  #app #nav { 
    border: 1px solid red;
  }
  #nav a {
    font-weight: bold;
    font-size: 1.3rem;
    text-decoration: none;
    color: black;
  }
  #nav a.router-link-active {
    color: blue;
    border: 1px solid blue;
  }
</style>


