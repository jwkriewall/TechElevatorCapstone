import Vue from 'vue'
import App from './App.vue'
import router from './router/index'
import store from './store/index'
import axios from 'axios'
import { library } from '@fortawesome/fontawesome-svg-core'
import { faEye, faEyeSlash, faStar } from '@fortawesome/free-solid-svg-icons'
//import { faEyeSlash } from '@fortawesome/free-solid-svg-icons'
import { FontAwesomeIcon } from '@fortawesome/vue-fontawesome'
import firebase from 'firebase/app'

library.add(faEye, faEyeSlash, faStar)

Vue.component('font-awesome-icon', FontAwesomeIcon)

Vue.config.productionTip = false

// Your web app's Firebase configuration
var firebaseConfig = {
  apiKey: "AIzaSyDx9RYVjmX-lwzlu9hxqukPyZtN5nwz7Ws",
  authDomain: "brckt-125af.firebaseapp.com",
  projectId: "brckt-125af",
  storageBucket: "brckt-125af.appspot.com",
  messagingSenderId: "1016170367317",
  appId: "1:1016170367317:web:10b1da3306ee92dcd087dc"
};
// Initialize Firebase
firebase.initializeApp(firebaseConfig);

axios.defaults.baseURL = process.env.VUE_APP_REMOTE_API;

new Vue({
  router,
  store,
  render: h => h(App)
}).$mount('#app')
