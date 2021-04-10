
<template>
  <div class="upload-photo">
    <h2>Upload a photo</h2>
    
    <div class="uploader">
      <div>
        <input v-if="!imageData" type="button" @click="choosePhoto" value="Choose Photo" />
        <input type="file" ref="input1"
          style="display: none"
          @change="previewImage" accept="image/*" >                
      </div>
 
      <div class="image-preview" v-if="imageData">                     
        <img :src="img1">
      </div>   
      
      <input v-if="imageData" type="button" class="upload" @click="create" value="upload" />
      
    </div>

    
  </div>
</template>


<script>
import firebase from 'firebase';
export default {
  data () {
    return {
      caption : '',
      img1: '',
      imageData: null
    }
  },
  methods: {
    create () {
      this.$store.commit('UPDATE_IMAGE_URL', this.img1);
      const post = {
        photo: this.img1,
        caption: this.caption        
      }
      firebase.database().ref('UserPhotos').push(post)
      .then((response) => {
        console.log(response)
      })
      .catch(err => {
        console.log(err)
      })
    },
    choosePhoto() {
      this.$refs.input1.click()   
    },
    previewImage(event) { 
      this.uploadValue = 0;
      this.img1 = null;
      this.imageData = event.target.files[0];
      this.onUpload()
    },
    onUpload() {
      this.img1 = null;
      const storageRef = firebase.storage().ref(`${this.imageData.name}`).put(this.imageData);
      storageRef.on(`state_changed`, snapshot=> {
        this.uploadValue = (snapshot.bytesTransferred/snapshot.totalBytes)*100;
      }, error=>{console.log(error.message)},
        ()=>{ this.uploadValue=100;
            storageRef.snapshot.ref.getDownloadURL().then((url)=> {
              this.img1 =url;
              console.log(this.img1)
            });
          }      
        );
    },
  }
}
</script>

<style scoped>
.uploader {
  display: flex;
  flex-direction: column;
  justify-content: flex-start;
  align-items: flex-start;
}

 .image-preview {
   height: 15vh;
   min-height: 50px;
   min-width: 50px;
   max-height: 150px;
   max-width: 150px;
 }
 .image-preview img {
   height: 100%;
 }
 input.upload {
   margin: 0;
   border-radius: 0;
   min-width: 50px;
   width:133px;
 }
</style>