window.onload = function () {
  renderCurrentUser();
  getReimbursementsByUserId();
};

function renderCurrentUser() {
  const currentUser = fetch("http://localhost:7000/current_user", {
    method: "GET",
    credentials: "include",
  }).then((response) => {
    if (response.status === 400) {
      window.location.href = "/";
    }
    return response.json();
  });

  console.log(currentUser);

  let id = currentUser.id;
  let username = currentUser.username;
  let password = currentUser.password;
  let firstName = currentUser.firstName;
  let lastName = currentUser.lastName;
  let email = currentUser.email;

  let userInfoElement = document.querySelector("#user");
  userInfoElement.innerHTML = `User id: ${id}, username: ${username}, firstName: ${firstName}`;

  return currentUser;
}

function getReimbursementsByUserId(userId) {
  let currentUser = renderCurrentUser();
  console.log(currentUser);

  let id = currentUser.id;

  const resultList = fetch("http://localhost:7000/reimbursements/" + id, {
    method: "GET",
    credentials: "include",
  }).then((response) => {
    if (response.status === 400) {
      window.location.href = "/";
    }
    return response.json();
  });

  resultList.forEach((reimbursement) => {
    let resultsUserReimbursementsElement = document.querySelector(
      "#results-user-reimbursementst"
    );
    resultsUserReimbursementsElement.innerHTML +=
      `ReimbursementId: ${reimbursement.id}, amount: $ ${reimbursement.amount},` +
      `description: ${reimbursement.description}, submitted: ${reimbursement.submitted.monthValue}-${reimbursement.submitted.dayOfMonth},-${reimbursement.submitted.year}
       by author: ${reimbursement.author}, typeId: ${reimbursement.typeId}<br />`;
  });
}

function logout() {
  const result = fetch("http://localhost:7000/logout", {
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
