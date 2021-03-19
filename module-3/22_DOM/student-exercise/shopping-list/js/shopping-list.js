

let pageTitle = "My Shopping List";
let groceries = ['milk', 'eggs', 'bread', 'cheese', 'water', 'soda', 'chicken', 'beef', 'chips', 'salad']

/**
 * This function will get a reference to the title and set its text to the value
 * of the pageTitle variable that was set above.
 */
const title = document.getElementsByClassName('shopping-list');

function setPageTitle() {
  const header = document.createElement('h1');
  header.innerText = pageTitle;
  title.appendChild(header);
}

/**
 * This function will loop over the array of groceries that was set above and add them to the DOM.
 */
function displayGroceries() {
  const ul = document.createElement('ul');
  groceries.forEach(grocery => {
    const li = document.createElement('li');
    li.innerText = grocery;
    ul.appendChild(li);
  })

}

/**
 * This function will be called when the button is clicked. You will need to get a reference
 * to every list item and add the class completed to each one
 */

setPageTitle();
displayGroceries();

 // this is confusing
// function markCompleted() {
//   const li = document.getElementById('li');
//     li.map((li) => {
//      ('completed');
//   });
// }


// Don't worry too much about what is going on here, we will cover this when we discuss events.
document.addEventListener('DOMContentLoaded', () => {
  // When the DOM Content has loaded attach a click listener to the button
  const button = document.querySelector('.btn');
  button.addEventListener('click', markCompleted);
});
