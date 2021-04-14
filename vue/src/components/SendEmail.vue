<template>
  <div></div>
</template>

<script>
    export default {
    
    
        mounted() {
            let emailScript = document.createElement('script');
            emailScript.setAttribute('src', 'https://smtpjs.com/v3/smtp.js')
            document.head.appendChild(emailScript);
        },
            sendEmail() {
                // this.$loadScript("https://smtpjs.com/v3/smtp.js")
                
                // .then(() =>
                
                window.Email && window.Email.send({
                    Host: "smtp.gmail.com",
                    Username: "brcktproject@gmail.com",
                    Password: "thisisapassword1!",
                    To: 'brcktproject@gmail.com',
                    From: "brcktproject@gmail.com",
                    Subject: "Sending Email using javascript",
                    Body: "Well that was easy!!"


                    // Attachments: [
                    // {
                    //     name: "File_Name_with_Extension",
                    //     path: "Full Path of the file"
                    //     // Could we put picture of bracket here????
                    // }]


                    })
                    // .then(message => alert("Mail has been sent successfully")
                    // ); 
                )}
    }
    
</script>

<style>

</style>