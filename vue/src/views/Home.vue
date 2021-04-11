<template>
  <div class="container">
    <div class="content">
      <h1>Get Started</h1>
      <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. 
        Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. 
        Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. 
        Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum
      </p>
      <div class="home-buttons">
        <input type="button" value="Create" @click="$router.push('/create')" />
        <input type="button" value="Login" @click="$router.push('/login')" v-if="$store.state.token == ''" />
      </div>
    </div>
    <div class="image">
      <img src="@/assets/intro-tourney.jpg" alt="People in front of computer screens playing video games">
    </div>
  </div>
</template>

<script>
export default {
  name: "home"
};
</script>

<style scoped>
.content {
  background-color: #2c3e50;
  color:white;
}
.home-buttons {
  flex-direction: row;
}
.home-buttons input[type="button"]:first-child {
  margin-right: 80px;
}
</style>
