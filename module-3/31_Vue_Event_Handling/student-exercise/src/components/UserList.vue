<template>
  <div class="container">
    <table id="tblUsers">
      <thead>
        <tr>
          <th>&nbsp;</th>
          <th>First Name</th>
          <th>Last Name</th>
          <th>Username</th>
          <th>Email Address</th>
          <th>Status</th>
          <th>Actions</th>
        </tr>
      </thead>
      <tbody>
        <tr>
          <td>
            <input type="checkbox" id="selectAll" />
          </td>
          <td>
            <input type="text" id="firstNameFilter" v-model="filter.firstName" />
          </td>
          <td>
            <input type="text" id="lastNameFilter" v-model="filter.lastName" />
          </td>
          <td>
            <input type="text" id="usernameFilter" v-model="filter.username" />
          </td>
          <td>
            <input type="text" id="emailFilter" v-model="filter.emailAddress" />
          </td>
          <td>
            <select id="statusFilter" v-model="filter.status">
              <option value>Show All</option>
              <option value="Active">Active</option>
              <option value="Disabled">Disabled</option>
            </select>
          </td>
          <td>&nbsp;</td>
        </tr>
        <tr
          v-for="user in filteredList"
          v-bind:key="user.id"
          v-bind:class="{ disabled: user.status === 'Disabled' }"
        >
          <td>
            <input type="checkbox" 
             v-bind:id="user.id" v-bind:value="user.id" 
             v-on:change="checkedAddUser($event)"
             v-bind:checked="selectedUserIDs.includes(user.id)"
             />
          </td>
          <td>{{ user.firstName }}</td>
          <td>{{ user.lastName }}</td>
          <td>{{ user.username }}</td>
          <td>{{ user.emailAddress }}</td>
          <td>{{ user.status }}</td>
          <td>
            <button v-bind:disabled="actionButtonDisabled" v-bind:id="user.id" 
            v-bind:status="user.status" 
          
            v-bind:showStatus="user.showStatus" v-on:click.prevent="flipStatus(user.id)" 
            class="btnEnableDisable">{{ user.status === 'Active' ? 'Disable' : 'Enable' }}</button>
          </td>
        </tr>
      </tbody>
    </table>


<!----- - Add a `selectedUserIDs` property that defaults to an empty array.
- When the `selectedUserIDs` array is empty, the buttons should be disabled.
  - Note: use a computed property named `actionButtonDisabled` for this.
- When the checkbox for a row is checked, add the user's ID to the `selectedUserIDs` array.


 - Bind the checked value to if the user's ID is in the `selectedUserIDs` array.
- Enable Users
  - Sets the status of each selected user to `Active`.
  - Clears all checkboxes when action is completed.
  - Method name: `enableSelectedUsers()`
- Disable Users
  - Sets the status of each selected user to `Disabled`.
  - Clears all checkboxes when action is completed.
  - Method name: `disableSelectedUsers()`
- Delete Users
  - Deletes the user from the `users` array.
  - Clears all checkboxes when action is completed.
  - Method name: `deleteSelectedUsers()`
--->

    <div class="all-actions">
      <button v-bind:disabled="actionButtonDisabled"
      v-on:click.prevent="enableSelectedUsers">Enable Users</button>
      <button v-bind:disabled="actionButtonDisabled"
      v-on:click.prevent="disableSelectedUsers">Disable Users</button>
      <button v-bind:disabled="actionButtonDisabled"
      v-on:click.prevent="deleteSelectedUsers">Delete Users</button>
    </div>



  
    <button v-on:click.prevent="showForm = true">Add New User</button>
    
    <form id="frmAddNewUser" v-show="showForm" v-on:submit.prevent="saveUser()">
      <div class="field">
        <label for="firstName">First Name:</label>
        <input type="text" name="firstName" v-model="newUser.firstName"/>
      </div>
      <div class="field">
        <label for="lastName">Last Name:</label>
        <input type="text" name="lastName" v-model="newUser.lastName"/>
      </div>
      <div class="field">
        <label for="username">Username:</label>
        <input type="text" name="username" v-model="newUser.username"/>
      </div>
      <div class="field">
        <label for="emailAddress">Email Address:</label>
        <input type="text" name="emailAddress" v-model="newUser.emailAddress"/>
      </div>
      <button type="submit" class="btn save">Save User</button>
    </form>
  </div>
</template>

<script>
export default {
  name: "user-list",
  // added code
  
  data() {
    return {
      selectedUserIDs: [],
      enabledButton: false,
      showForm: false,
      enableOrDisabled: "",
      filter: {
        firstName: "",
        lastName: "",
        username: "",
        emailAddress: "",
        status: ""
      },
      newUser: {
        id: null,
        firstName: "",
        lastName: "",
        username: "",
        emailAddress: "",
        status: "Active"
      },
      users: [
        {
          id: 1,
          firstName: "John",
          lastName: "Smith",
          username: "jsmith",
          emailAddress: "jsmith@gmail.com",
          status: "Active"
        },
        {
          id: 2,
          firstName: "Anna",
          lastName: "Bell",
          username: "abell",
          emailAddress: "abell@yahoo.com",
          status: "Active"
        },
        {
          id: 3,
          firstName: "George",
          lastName: "Best",
          username: "gbest",
          emailAddress: "gbest@gmail.com",
          status: "Disabled"
        },
        {
          id: 4,
          firstName: "Ben",
          lastName: "Carter",
          username: "bcarter",
          emailAddress: "bcarter@gmail.com",
          status: "Active"
        },
        {
          id: 5,
          firstName: "Katie",
          lastName: "Jackson",
          username: "kjackson",
          emailAddress: "kjackson@yahoo.com",
          status: "Active"
        },
        {
          id: 6,
          firstName: "Mark",
          lastName: "Smith",
          username: "msmith",
          emailAddress: "msmith@foo.com",
          status: "Disabled"
        }
      ]
    };
  },
  methods: {
    statusDisplay() {
      if (this.user.status === "Disabled"){
        this.user.showStatus === "Enable";
      }
      if (this.user.status === "Active") {
        this.user.showStatus === "Disable";
      }
    },
    enableSelectedUsers() {
      this.selectedUserIDs.forEach(id =>{
        const i = this.users.findIndex(user => user.id == id);
        this.users[i].status = 'Active';
      });
      this.selectedUserIDs = [];
    },
    disableSelectedUsers() {
        this.selectedUserIDs.forEach(id =>{
        const i = this.users.findIndex(user => user.id == id);
        this.users[i].status = 'Disabled';
      });
      this.selectedUserIDs = [];
    },
    deleteSelectedUsers() {
      this.users = this.users.filter(user => {
        return !(this.selectedUserIDs.includes(user.id));
      })
    },
    
    
    saveUser() {
      this.users.push(this.newUser);
      this.resetForm();
      this.showForm == false;
    },
    resetForm() {
      this.newUser = {
        id: null,
        status: "Active"
      };
      this.toggle();
    },

    
    checkedAddUser(event){
      if (event.target.checked){
        this.selectedUserIDs.push(parseInt(event.target.id));
      } else {
        this.selectedUserIDs = 
        this.selectedUserIDs.filter(id =>{
          return id != parseInt(event.target.id);
        })
      }
      
    },
//new code
    flipStatus(id) {
      return this.users.filter(user =>{
        if (user.id == id && user.status === "Disabled") {
          user.status = "Active";
          this.enableOrDisabled = "Disable"
        }
        else if (user.id == id && user.status === "Active"){
          user.status = "Disabled";
          this.enableOrDisabled = "Enable"
        }
      })
  }
},
  computed: {

      actionButtonDisabled() {
      if (this.selectedUserIDs == {}) {
        return true;
      }
      else return false;
      },

   

    filteredList() {
      let filteredUsers = this.users;
      if (this.filter.firstName != "") {
        filteredUsers = filteredUsers.filter((user) =>
          user.firstName
            .toLowerCase()
            .includes(this.filter.firstName.toLowerCase())
        );
      }
      if (this.filter.lastName != "") {
        filteredUsers = filteredUsers.filter((user) =>
          user.lastName
            .toLowerCase()
            .includes(this.filter.lastName.toLowerCase())
        );
      }
      if (this.filter.username != "") {
        filteredUsers = filteredUsers.filter((user) =>
          user.username
            .toLowerCase()
            .includes(this.filter.username.toLowerCase())
        );
      }
      if (this.filter.emailAddress != "") {
        filteredUsers = filteredUsers.filter((user) =>
          user.emailAddress
            .toLowerCase()
            .includes(this.filter.emailAddress.toLowerCase())
        );
      }
      if (this.filter.status != "") {
        filteredUsers = filteredUsers.filter((user) =>
          user.status === this.filter.status
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
  font-family: -apple-system, BlinkMacSystemFont, "Segoe UI", Roboto, Oxygen,
    Ubuntu, Cantarell, "Open Sans", "Helvetica Neue", sans-serif;
  margin-bottom: 20px;
}
th {
  text-transform: uppercase;
}
td {
  padding: 10px;
}
tr.disabled {
  color: red;
}
input,
select {
  font-size: 16px;
}

form {
  margin: 20px;
  width: 350px;
}
.field {
  padding: 10px 0px;
}
label {
  width: 140px;
  display: inline-block;
}
button {
  margin-right: 5px;
}
.all-actions {
  margin-bottom: 40px;
}
.btn.save {
  margin: 20px;
  float: right;
}
</style>
