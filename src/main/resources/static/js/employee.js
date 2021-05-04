"use strict";

window.onload = function () {
  renderCurrentUser();
};

setTimeout(function () {
  getReimbursementsByUserId(sessionStorage.getItem("id"));
}, 500);

async function renderCurrentUser() {
  const eCurrentUser = await fetch("http://localhost:7000/current_user", {
    method: "GET",
    credentials: "include",
  }).then((response) => {
    if (response.status === 400) {
      window.location.href = "/";
    }
    return response.json();
  });

  console.log(eCurrentUser);

  let eId = eCurrentUser.id;
  let eUsername = eCurrentUser.username;
  let ePassword = eCurrentUser.password;
  let eFirstName = eCurrentUser.firstName;
  let eLastName = eCurrentUser.lastName;
  let eEmail = eCurrentUser.email;

  // save userId, check browser support
  if (typeof Storage !== "undefined") {
    // store
    console.log("i'm here...");
    sessionStorage.setItem("id", eId);
    sessionStorage.setItem("eUsername", eUsername);
  }

  let userInfoElement = document.querySelector("#user");
  userInfoElement.innerHTML = `userid: ${eId} firstName: ${eFirstName}, lastName: ${eLastName}, email: ${eEmail}`;

  return eCurrentUser;
}

async function getReimbursementsByUserId(id) {
  id = sessionStorage.getItem("id");
  console.log("from session id: " + id + typeof id);
  const resultList = await fetch(`http://localhost:7000/reimbursements/` + id, {
    method: "GET",
    credentials: "include",
  })
    .then((response) => {
      if (response.status === 400) {
        window.location.href = "/";
      }
      return response.json();
    })
    .then((data) => {
      data.map((reimbursement) => {
        // format time
        // let formatSubmitted = reimbursement.submitted.toLocaleDateString();
        let d = new Date(reimbursement.submitted);
        let formatSubmitted = d.toLocaleDateString();

        let dr = new Date(reimbursement.resolved);
        let formatResolved = dr.toLocaleDateString();

        let resultsUserReimbursementsElement = document.querySelector(
          "#user-reimbursements"
        );
        resultsUserReimbursementsElement.innerHTML += `<tr>
          <td>${reimbursement.id}</td>
          <td>${reimbursement.amount}</td>
          <td>${reimbursement.typeId}</td>
          <td>${reimbursement.description}</td>
          <td>${formatSubmitted}</td>
          <td>${reimbursement.status}</td>
          <td>${formatResolved}</td>
          <td>${reimbursement.resolver}</td>
        </tr>`;
      });
    });
}

function logout() {
  console.log("you clicked me: logout button");
  fetch("http://localhost:7000/logout", {
    method: "POST",
    mode: "no-cors",
    credentials: "include",
  }).then((response) => {
    if (response.status === 200) {
      // return response.status;
      window.location.href = "../index.html";
    } else if (response.status === 401) {
      return "Please try again!";
    }
  });
}

let reimForm = document.getElementById("reim-request-form");
let eAmount = document.getElementById("amount");
let eType = document.getElementById("r-type");
let eDescription = document.getElementById("description");
let eSubmitted = document.getElementById("submitted");
let eAuthor = document.getElementById("author");
let eReceipt = document.getElementById("receipt");
let reqSubmitButton = document.getElementById("reim-submit");

function addReimbursementRequest() {
  let data = {
    amount: eAmount.value,
    typeId: eType.value,

    description: eDescription.value,
    submitted: eSubmitted.value,
    author: eAuthor.value,
    receipt: eReceipt.value,
  };

  console.log(data);

  fetch(`http://localhost:7000/reimbursements`, {
    method: "POST",
    mode: "no-cors",
    credentials: "include",
    // headers: {
    //   "Content-Type": "application/json",
    // },
    body: JSON.stringify(data),
  }).then((response) => {
    if (response.status === 200) {
      return response;
    } else if (response.status === 401) {
      console.log("Unable to process request. Please try again");
    }
  });

  // console.log("Reimbursement Request result: " + response);
  // if (response != null) {
  //   reimForm.clear();
  // }
  // return response;
}

let logoutButton = document.getElementById("logout");
logoutButton.addEventListener("click", logout);

reqSubmitButton.addEventListener("click", addReimbursementRequest);
