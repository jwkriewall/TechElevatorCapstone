<template>
    <div class="user-info">
        <h1>Account Information</h1>
        <div class="account-info">
            <img v-if="$store.state.user.userImageUrl" :src="$store.state.user.userImageUrl" alt="User Photo" />
            <table>
                <tr>
                    <td class="label">Name: </td><td>{{ user.firstName}} {{user.lastName}}</td>
                </tr>
                <tr>
                    <td class="label">Email: </td><td>{{ user.email}}</td>
                </tr>
                <tr>
                    <td class="label">Phone: </td><td>{{user.phone}}</td>
                </tr>
                <tr>
                    <td class="label">Username: </td><td>{{user.username}}</td>
                </tr>
            </table>
            
        </div>
    </div>
</template>

<script>
export default {
    name: 'user-info',
    data() {
        return {
            user: this.$store.state.user
        }
    }
}
</script>

<style scoped>
img {
    max-width:150px;
    margin-right: 20px;
}
.account-info {
    display: flex;
    align-items: flex-start;
    margin: 0;
    padding: 0;
}
</style>