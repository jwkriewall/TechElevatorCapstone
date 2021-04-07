<template>
  <div id="home">
    <div class="info">
      <h1>Get Started</h1>
      <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. 
        Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. 
        Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. 
        Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum
      </p>
      <div class="home-buttons">
        <input type="button" value="Create" />
        <input type="button" value="Login" />
      </div>
    </div>
    <div class="placeholder">Image placeholder</div>
  </div>
</template>

<script>
export default {
  name: "home"
};
</script>

<style scoped>
#home {
  font-family: 'Poppins';
  display:grid;
  grid-template-columns: 1fr 1fr;
}
#home > div {
  display:flex;
  flex-direction:column;
  align-items: center;
  justify-content: center;
}
#home div.info h1 {
  border-bottom: 2px solid #e74c3c;
}
#home div.info {
  align-items: flex-start;
  justify-content: flex-start;
  padding: 20px 30px 30px;
  background-color: #707070;
  color:white;
}
#home div.home-buttons {
  flex-direction: row;
}
input[type=button] {
  height: 2em;
  width: 12vw;
  min-width: 50px;
  border-radius: 15px;
  background-color: #e74c3c;
  color: white;
  font-size: 1.1rem;
  border: none;
  margin: 30px;
} 
input[type=button]:hover {
  background-color: #d85b4d;
  cursor:grab;
  
}
</style>
