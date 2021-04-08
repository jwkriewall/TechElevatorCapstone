<template>
    <div class="user-info">
        <h1>Account Information</h1>
        <ul>
            <li>Name: {{ user.firstName}} {{user.lastName}}</li>
            <li>Email: {{ user.email}}</li>
            <li>Phone: {{user.phone}}</li>
            <li>Username: {{user.username}}</li>
        </ul>
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

<style>
ul {
    list-style-position: outside;
    list-style-type: none;
    padding: 0;
}

ul li {
    line-height: 2rem;
    padding:0 10px;
    border-radius:10px;
}

ul li:nth-child(odd) {
    background-color: #f4f4f4;
    color: black;
}
</style>