<template>
    <div id="my-account">
        <h1>Account Information</h1>
        
        <div class="user-info">
            <ul>
                <li>Name: {{ user.firstName}} {{user.lastName}}</li>
                <li>Email: {{ user.email}}</li>
                <li>Phone: {{user.phone}}</li>
                <li>Username: {{user.username}}</li>
            </ul>
        </div>
        <input type="submit" value="Modify" @click.prevent="modify == true" />
        <div class="edit-info">
        </div>

    </div>
</template>

<script>
import editUser from '@components'
export default {
    data() {
        return {
            user: {}
        }
    }
}
</script>

<style>

</style>