<template>
  <div class="home">
    <h1>Home</h1>
    <p>You must be authenticated to see this</p>
    <h2>Examples</h2>
    <ul>
      <router-link :to="{ name: 'userexample' }" tag="li">The User Object and Token</router-link>
      <router-link :to="{ name: 'apiwithjs' }" tag="li">External from API with JavaScript</router-link>
      <router-link :to="{ name: 'apiwithproxy' }" tag="li">External from API with Java API Proxy</router-link>

  
    </ul>
  </div>
</template>

<script>
export default {
  name: "home"
};
</script>

<style>
li {
  color: blue;
  font-size: 1.2rem;
}
li:hover {
  font-weight: 700;
  cursor: pointer;
}
</style>
