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

  console.log(reimbursements);

  reimbursements.forEach((reimRequest) => {
    let resultsReimRequestElement = document.querySelector("reimbursements");
    // format date
    let d = new Date(reimRequest.submitted);
    let formatSubmitted = d.toLocaleDateString();

    let dr = new Date(reimRequest.resolved);
    let formatResolved = dr.toLocaleDateString();

    let resultsReimRequestsElement = document.querySelector(
      "#manager-reimbursements"
    );
    resultsReimRequestsElement.innerHTML += `<tr>
       <td>${reimRequest.id}</td>
       <td>${reimRequest.amount}</td>
       <td>${reimRequest.typeId}</td>
       <td>${reimRequest.description}</td>
       <td>${formatSubmitted}</td>
       <td>${reimRequest.author}</td>
       <td>${reimRequest.statusId}</td>
       <td>${formatResolved}</td>
       <td>${reimRequest.resolver}</td>
     </tr>`;

    return reimbursements;
  });
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
  userInfoElement.innerHTML = `userid: ${id}, firstName: ${firstName}, lastName: ${lastName}, email: ${email}`;

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

async function filterStatusById(id) {
  console.log("id from onchange: " + id);

  const filteredReim = await fetch(
    `http://localhost:7000/reimbursements/filtered/` + id,
    {
      method: "GET",
      credentials: "include",
    }
  ).then((response) => {
    if (response.status === 400) {
      window.location.href = "/";
    }
    return response.json();
  });

  filteredReim.forEach((reimRequest) => {
    let resultsReimRequestElement = document.querySelector("reimbursements");
    // format date
    let d = new Date(reimRequest.submitted);
    let formatSubmitted = d.toLocaleDateString();

    let dr = new Date(reimRequest.resolved);
    let formatResolved = dr.toLocaleDateString();

    let filteredReimRequestsElement = document.querySelector(
      "#manager-reimbursements"
    );
    filteredReimRequestsElement.innerHTML += `<tr>
       <td>${reimRequest.id}</td>
       <td>${reimRequest.amount}</td>
       <td>${reimRequest.typeId}</td>
       <td>${reimRequest.description}</td>
       <td>${formatSubmitted}</td>
       <td>${reimRequest.author}</td>
       <td>${reimRequest.statusId}</td>
       <td>${formatResolved}</td>
       <td>${reimRequest.resolver}</td>
     </tr>`;

    console.log("filtered reim: " + filteredReim);
    return filteredReim;
  });
}

let mLogout = document.getElementById("manager-logout");
mLogout.addEventListener("click", logout);

const selectStatus = document.getElementById("status");
selectStatus.addEventListener("change", (event) => {
  let id = event.target.value;

  if (id == "") {
    document.getElementById("manager-reimbursements").innerHTML = "";
    getAllReimbursements();
  } else {
    document.getElementById("manager-reimbursements").innerHTML = "";
    filterStatusById(id);
  }
});
