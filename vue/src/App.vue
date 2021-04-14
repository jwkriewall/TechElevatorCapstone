<template>
  <div id="app">
    
    <div class="main">
      <navbar />
      <router-view />
    </div>
    
  </div>
</template>

<script>
import navbar from '@/components/Navbar.vue'

export default {
  components: { navbar },
}
</script>

<style>
  #app {
    font-family: 'Poppins';
  }

  h1 {
    border-bottom: 2px solid #e74c3c;
    font-weight: 800;
    display: inline-block;
    color: #fff;
    text-align: left;
    margin-top: 0;
  }
  h2 {
    text-transform: uppercase;
    font-size: 1.3rem;
    border-bottom: 2px solid #e74c3c;
  }
  .main {
    display: flex;
    flex-direction: column;
    justify-content: flex-start;
  }
  .container {
    grid-area: content;
    display: flex;
    align-items: flex-start;
    justify-content: flex-start;
    padding: 0;
    height: 80vh;
    min-height: 80vh;
    /* height: 80vh; */
    min-height: 100vh;
  }
  .content, .content-full-width {
    flex-basis: 50vw;
    display: flex;
    flex-direction: column;
    padding: 20px 30px 0;
    height: 100%;
    box-sizing: border-box;
    color: white;
    font-size: 1.2rem;
    background-color: #2c3e50;
  }
  .content-full-width {
    flex-basis: 100vw;
    background: none;
    justify-content: flex-start;
    align-items: center;
    padding: 50px;
  }
  .image {
    overflow-y: hidden;
    flex-basis: 50vw;
    width: 50vw;
    height: 100%;
  }
  .image img {
    height: 100%;
  }

  /* Input Styling */
input {
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
input[type="date"]::-webkit-calendar-picker-indicator {
    filter: invert(100%);
}
input[type="date"]:focus::-webkit-calendar-picker-indicator {
    filter: invert(0%);
}
label {
    margin: 10px 10px 10px 0;
    width: 12vw;
    min-width: 125px;
    display: inline-block;
}
textarea:focus, input:not(input[type="checkbox"]):focus {
  outline: none;
  box-shadow: 0px 0px 0px 2px #e74c3c;
  background-color:white;
  color: #707070;
  -webkit-text-fill-color: #707070;
  -webkit-box-shadow: 0 0 0px 1000px #fff inset;
  -webkit-box-shadow: 0 0 0px 2px #e74c3c;
}

input[type="button"], input[type="submit"], button {
    background-color: #e74c3c;
    -webkit-box-shadow: 0 0 0px 1000px #e74c3c inset;
    color: white;
    font-size: 1.1 rem;
    height: 2rem;
    font-weight: 900;
    margin-top: 20px;
    border: none;
    border-radius: 15px;
    width: 12vw;
    min-width: 80px;
}
input[type="button"]:hover, input[type="submit"]:hover, button:hover {
    background-color:#dc8980;
    -webkit-box-shadow: 0 0 0px 1000px #dc8980 inset;
}
input[type="button"]:focus, input[type="submit"]:focus, button:focus {
    outline: none;
    border:1px solid white;
}
input[type="button"]:disabled, input[type="submit"]:disabled, button:disabled {
    color: #dc8980;
    -webkit-text-fill-color: #dc8980;
}
label.sr-only {
  text-align: center;
  font-size: 1.4rem;
  margin: 20px 0 10px;
}

/* Little Toggle Switches */
label.toggle {
    cursor: pointer;
    text-indent: -9999px;
    width: 50px;
    min-width: 50px;
    height: 20px;
    background: #BADA55;
    display: block;
    border-radius: 100px;
    position: relative;
}
label.toggle:after {
    content: '';
    position: absolute;
    top: 2px;
    left: 5px;
    width: 15px;
    height: 15px;
    background: #fff;
    border-radius: 90px;
    transition: 0.3s;
}
label.toggle:active:after {
    width: 15px;
}
input.toggle[type=checkbox]{
    height: 0;
    width: 0;
    visibility: hidden;
}
input.toggle {
    min-width: 0px;
}
input.toggle:checked + label.toggle {
    background: #e74c3c;
}
input.toggle:checked + label.toggle:after {
    left: calc(100% - 5px);
    transform: translateX(-100%);
}
.toggleSwitch {
    display: flex;
    flex-direction: row;
    justify-content: flex-start;
    align-items: center;
    height: 30px;
    margin: 20px 0 10px 0;
}
</style>
