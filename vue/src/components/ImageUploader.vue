
<template>   
    <div class="uploader">
      <div class="image-preview">                     
        <img :src="[imageData ? img1 : [storedImgUrl ? storedImgUrl : require('@/assets/profile-placeholder.png')] ]">
      </div>   
        <input v-if="!changingPhoto" type="button" class="upload" @click="choosePhoto" value="Change" />
        <input type="file" ref="input1"
          style="display: none"
          @change="previewImage" accept="image/*" >                
      <input v-if="changingPhoto" type="button" class="upload" @click="create" value="upload" />
    </div>
</template>


<script>
import firebase from 'firebase';
export default {
  data () {
    return {
      caption : '',
      img1: '',
      imageData: null,
      imgPlaceholder: '../assets/profile-placeholder.png',
      changingPhoto: false,
      storedImgUrl: this.$store.state.user.userImageUrl,
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
        this.toggleChangingPhoto();
      })
      .catch(err => {
        console.log(err)
      })
    },
    toggleChangingPhoto() {
      this.changingPhoto = !this.changingPhoto;
    },
    choosePhoto() {
      this.toggleChangingPhoto();
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
  margin-right: 20px;
}

 .image-preview {
   height: 15vh;
   min-height: 50px;
   min-width: 50px;
   max-height: 150px;
   max-width: 10px;
   margin: 0;
   padding: 0;
 }
 .image-preview img {
   height: 100%;
   max-width: 133px;
   margin: 0;
 }
 input.upload {
   margin: 0;
   border-radius: 0;
   min-width: 60px;
   width:134px;
   font-size:0.7rem;
   text-transform: uppercase;
   height: 20px;
 }
</style>