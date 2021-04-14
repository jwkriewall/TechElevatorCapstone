<template>
  <div class="container">
    <div class="content">
      <h1>Get Started</h1>
      <p>Whether for a national video game championship or your local softball league, 
        BRCKT.com provides you with the ultimate website for customizable tournament displays.
        What are you waiting for? Start your tournament today!
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
.container {
  height: 80vh;
  min-height: 80vh;
}
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
input[type="button"] {
  height: 60px;
  font-size: 1.3rem;
}
input[type="button"]:nth-child(2) {
  background-color: #f4f4f4;
  -webkit-box-shadow: 0 0 0px 1000px #f4f4f4 inset;
  color:#e74c3c;
  -webkit-text-fill-color: #e74c3c;
}
input[type="button"]:nth-child(2):hover {
  background-color: #cecece;
  -webkit-box-shadow: 0 0 0px 1000px #cecece inset;
  color:#e74c3c;
  -webkit-text-fill-color: #e74c3c;
}

</style>
