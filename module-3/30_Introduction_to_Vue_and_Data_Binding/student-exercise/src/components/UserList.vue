<template>
  <table id="tblUsers">
    <thead>
    <tr>
        
          <th>First Name</th>
          <th>Last Name</th>
          <th>Username</th> 
          <th>Email Address</th>
          <th>Status</th> 
       
    </tr>
    </thead>
    <tbody>
      <tr>
        <td><input type="text" id="firstNameFilter" v-model="filter.firstName"/></td>
        <td><input type="text" id="lastNameFilter" v-model="filter.lastName"/></td>
        <td><input type="text" id="usernameFilter" v-model="filter.username"/></td>
        <td><input type="text" id="emailFilter" v-model="filter.emailAddress"/></td>
        <td>
          <select id="statusFilter" v-model="filter.status">
            <option value="">Show All</option>
            <option value="Active">Active</option>
            <option value="Disabled">Disabled</option>
          </select>
        </td>
      </tr>
      <!-- user listing goes here -->
      <tr class="users" v-for="user in users" v-bind:key="user.id">
          <td>{{user.firstName}}</td>
          <td>{{user.lastName}}</td>
          <td>{{user.username}}</td> 
          <td>{{user.emailAddress}}</td>
          <td>{{user.status}}</td> 
        </tr>
    </tbody>
  </table>
</template>

<script>
export default {
  name: 'user-list',
  data() {
    return {
      users: [
        { firstName: 'John', lastName: 'Smith', username: 'jsmith', emailAddress: 'jsmith@gmail.com', status: 'Active' },
        { firstName: 'Anna', lastName: 'Bell', username: 'abell', emailAddress: 'abell@yahoo.com', status: 'Active' },
        { firstName: 'George', lastName: 'Best', username: 'gbest', emailAddress: 'gbest@gmail.com', status: 'Disabled' },
        { firstName: 'Ben', lastName: 'Carter', username: 'bcarter', emailAddress: 'bcarter@gmail.com', status: 'Active' },
        { firstName: 'Katie', lastName: 'Jackson', username: 'kjackson', emailAddress: 'kjackson@yahoo.com', status: 'Active' },
        { firstName: 'Mark', lastName: 'Smith', username: 'msmith', emailAddress: 'msmith@foo.com', status: 'Disabled' }
      ],

      filter: [
        {
          //these need to be connected
          firstName : '', 
          lastName : '', 
          username : '', 
          emailAddress : '', 
          status : ''
        }
        
      ]
    }
  },

  // throwing my hands up on this one!!
  computed: {
    filteredList() {
      const filterFirstName = this.filter.firstName;
      const filterLast = this.filter.lastName;
      const filterEmail = this.filter.emailAddress;
      const filterUsername = this.filter.username;
      const filterStatus = this.filter.status;
      let filteredUsers = this.users;
      
      if (filterFirstName != "") {
        filteredUsers = filteredUsers.filter((user) => 
          user.firstName.toLowerCase().includes(filterFirstName.toLowerCase())
          );
      }
      if (filterLast != "") {
        filteredUsers = filteredUsers.filter((user) => 
          user.lastName.toLowerCase().includes(filterLast.toLowerCase())
        );
      }
      if (filterEmail != "") {
        filteredUsers = filteredUsers.filter((user) => 
          user.emailAddress.toLowerCase().includes(filterEmail.toLowerCase())
        );
      }
      if (filterUsername != "") {
        filteredUsers = filteredUsers.filter((user) => 
          user.username.toLowerCase().includes(filterUsername.toLowerCase())
        );
      }
      if (filterStatus != "") {
        filteredUsers = filteredUsers.filter((user) => 
          user.status.includes(filterStatus)
        );
      }
      return filteredUsers;
    }  
  }
};

</script>

<style scoped>
table {
  margin-top: 20px;
  font-family:-apple-system, BlinkMacSystemFont, 'Segoe UI', Roboto, Oxygen, Ubuntu, Cantarell, 'Open Sans', 'Helvetica Neue', sans-serif
}
th {
  text-transform: uppercase
}
td {
  padding: 10px;
}
tr.disabled {
  color: red;
}
input, select {
  font-size: 16px;
}
</style>
