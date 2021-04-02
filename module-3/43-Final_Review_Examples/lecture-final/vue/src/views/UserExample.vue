<template>
  <div>
      <h2>The User object</h2>
      <h3>Current User: {{ $store.state.user.username }}</h3>
      <p>Once the user is logged in you can access the user object using <code>$store.state.user</code> in the Template or 
      <code>this.$store.state.user</code> in the Script</p>
      <table>
          <thead>
            <tr>
                <th>Key</th>
                <th>Example Value</th>
            </tr>
          </thead>
          <tbody>
            <tr>
                <td>id</td>
                <td>{{ user.id }}</td>
            </tr>
            <tr>
                <td>username</td>
                <td>{{ user.username }}</td>
            </tr>
            <tr>
                <td>authorities</td>
                <td>{{ user.authorities}}</td>
            </tr>
          </tbody>
     </table>
     <div class="admin">
         <p>The user.authorities can be used to conditionally authorize or deauthorize content.  For example, only an admin user can see the following red text:</p>
         <div v-if="isAdmin"> Only an Admin can see this</div>
    </div>
    <div class="token">
        <h2>The Token</h2>
        <p>The JWT is handled for you automatically, but if you would need it, the token can be retrieved from the store using <code>$store.state.token</code></p>
        <p class="token">Current Token: </p> <span class="token">{{ $store.state.token }}</span>
    </div>
  </div>
</template>

<script>
export default {
    computed: {
        user() {
            return this.$store.state.user;
        },
        isAdmin() {
            let hasAdminRole = false;
            this.$store.state.user.authorities.forEach( a => {
                if (a.name == 'ROLE_ADMIN') {
                    hasAdminRole = true;
                }
            });
            return hasAdminRole;
        }
    }
}
</script>

<style>
th {
    text-align: left;
}
td:first-child {
    width: 100px;
}
td:last-child {
    width: 500px;
}

td {
    padding-top: 5px;
    padding-bottom: 5px;
    padding-left: 5px;
}

tbody tr:nth-child(even) {
    background-color: lightblue;
}
tbody tr:nth-child(odd) {
    background-color: lightgray;
}
div.admin div {
    color: red;
    font-size: 1.2rem;
    font-weight: 800;
}
span.token {
    font-size: .8rem;
}
div.token {
    margin-top: 50px;
}
p.token {
    font-size: 1.1rem;
    font-weight: 600;
    margin-bottom: 0;
}
</style>