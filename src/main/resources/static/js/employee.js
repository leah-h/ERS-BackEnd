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
  userInfoElement.innerHTML = `firstName: ${eFirstName}, lastName: ${eLastName}, email: ${eEmail}`;

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
        formatSubmitted = d.toLocaleDateString();

        let dr = new Date(reimbursement.resolved);
        formatResolved = dr.toLocaleDateString();

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
          <td>${formatResolved}
          </td><td>${reimbursement.resolver}</td>
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

let logoutButton = document.getElementById("logout");
logoutButton.addEventListener("click", logout);
