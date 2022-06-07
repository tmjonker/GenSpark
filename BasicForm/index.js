let firstName;
let middleInitial;
let lastName;
let address1;
let address2;
let city;
let state;
let zipCode;

function onSubmitClick(event) {

  event.preventDefault();

  firstName = document.getElementById("firstNameInput").value;
  middleInitial = document.getElementById("middleInitialInput").value;
  lastName = document.getElementById("lastNameInput").value;
  address1 = document.getElementById("streetAddressInput").value;
  address2 = document.getElementById("streetAddress2Input").value;
  city = document.getElementById("cityInput").value;
  state = document.getElementById("stateSelector").value;
  zipCode = document.getElementById("zipCodeInput").value;

  localStorage.setItem("firstName", firstName);
  localStorage.setItem("middleInitial", middleInitial);
  localStorage.setItem("lastName", lastName);
  localStorage.setItem("address1", address1);
  localStorage.setItem("address2", address2);
  localStorage.setItem("city", city);
  localStorage.setItem("state", state);
  localStorage.setItem("zipCode", zipCode);

  location.href = "results.html";
}

function displayInfo() {

    firstName = localStorage.getItem("firstName");
    middleInitial = localStorage.getItem("middleInitial");
    lastName = localStorage.getItem("lastName");
    address1 = localStorage.getItem("address1");
    address2 = localStorage.getItem("address2");
    city = localStorage.getItem("city");
    state = localStorage.getItem("state");
    zipCode = localStorage.getItem("zipCode");

    document.getElementById("name").innerHTML = "<b>Full Name:</b> " + firstName + " " + middleInitial + " " + lastName;
    document.getElementById("streetAddress").innerHTML = "<b>Address Line 1:</b> " + address1;
    document.getElementById("streetAddress2").innerHTML = "<b>Address Line 2:</b> " + address2;
    document.getElementById("city").innerHTML = "<b>City:</b> " + city;
    document.getElementById("state").innerHTML = "<b>State:</b> " + state;
    document.getElementById("zipCode").innerHTML = "<b>Zip Code:</b> " + zipCode;
}
