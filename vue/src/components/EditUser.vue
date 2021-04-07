<template>
    <div class="edit-user">
        <h1>Update User Information</h1>
        <div class="user-info">
            <div>   
                <label for="firstName">First name: </label>
                <input type="text" name="firstName" id="firstName" v-model.trim="user.firstName" />
            </div>
            <div>   
                <label for="lastName">Last Name: </label>
                <input type="text" name="lastName" id="lastName" v-model.trim="user.lastName" />
            </div>
            <div>   
                <label for="phone">Phone Number: </label>
                <input type="tel" pattern="[0-9]{3}[0-9]{3}[0-9]{4}" id="phone" v-model.trim="user.phone" />
            </div>
            <div>   
                <label for="email">Email Address: </label>
                <input type="email" id="email" v-model.trim="user.email" />
            </div>
            <div>
                <label for="username">Username: </label>
                <input type="username" id="username" v-model.trim="user.username" />
            </div>
            <div>
                <label for="password">Password: </label>
                <input :type="[showPassword ? 'text' : 'password']" id="password" v-model.trim="user.password" />
                <label for="showPassword">Show Password?</label><input type="checkbox" id="showPassword" @change="toggleShowPass" />
                <!-- ternary operation syntax -->
            </div>
            <input type="submit" value="Update" @click.prevent='updateUser' />
        </div>
    </div>
</template>

<script>
export default {
    name: 'edit-user',
    props: ['user'],
    data() {
        return {
            showPassword: false
        }
    },
    methods: {
        toggleShowPass() {
            this.showPassword = !this.showPassword
        }
    }
}
</script>

<style>

</style>