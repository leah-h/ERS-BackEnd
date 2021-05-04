window.onload = function () {
  renderCurrentUser();
  getAllReimbursements();
};

async function getAllReimbursements() {
  const reimbursements = await fetch("http://localhost:7000/reimbursements", {
    method: "GET",
    credentials: "include",
  }).then((response) => {
    if (response.status === 400) {
      window.location.href = "/";
    }
    return response.json();
  });

  reimbursements.forEach((reimbursement) => {
    let resultsReimbursementsElement = document.querySelector(
      "#results-reimbursement"
    );
    resultsReimbursementsElement.innerHTML +=
      `ReimbursementId: ${reimbursement.id}, amount: $ ${reimbursement.amount},` +
      `description: ${reimbursement.description}, submitted: ${reimbursement.submitted.monthValue}-${reimbursement.submitted.dayOfMonth},-${reimbursement.submitted.year} by author: ${reimbursement.author}, typeId: ${reimbursement.typeId}<br />`;
  });

  return reimbursements;
}

async function renderCurrentUser() {
  const currentUser = await fetch("http://localhost:7000/current_user", {
    method: "GET",
    credentials: "include",
  }).then((response) => {
    if (response.status === 400) {
      window.location.href = "/";
    }
    return response.json();
  });

  let id = currentUser.id;
  let username = currentUser.username;
  let password = currentUser.password;
  let firstName = currentUser.firstName;
  let lastName = currentUser.lastName;
  let email = currentUser.email;

  // save userId, check browser support
  if (typeof Storage !== "undefined") {
    // store
    console.log("i'm here...");
    sessionStorage.setItem("id", id);
  }

  let userInfoElement = document.querySelector("#manager");
  userInfoElement.innerHTML = `firstName: ${firstName}, lastName: ${lastName}, email: ${email}`;

  return currentUser;
}

function logout() {
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

let mLogout = document.getElementById("manager-logout");
mLogout.addEventListener("click", logout);
