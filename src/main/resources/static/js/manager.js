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

function approveReimbursementRequest() {
  let reimId = document.getElementById("aId");
  let aStatus = document.getElementById("aStatus");
  let aResolved = document.getElementById("aResolved");
  let aResolver = document.getElementById("aResolver");

  let data = {
    id: reimId.value,
    statusId: aStatus.value,
    resolved: aResolved.value,
    resolver: aResolver.value,
  };

  console.log("data: " + data);
  console.log("data.id: " + data.id);

  fetch(`http://localhost:7000/reimbursements/` + data.id, {
    method: "PUT",
    credentials: "include",
    body: JSON.stringify(data),
  }).then((response) => {
    if (response.status === 200) {
      return response;
    } else if (response.status === 401) {
      console.log("Unable to process request. Please try again");
    }
  });

  //clears table
  document.getElementById("manager-reimbursements").innerHTML = "";
  // re-renders
  getAllReimbursements();
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

let approvalButton = document.getElementById("approval-button");
approvalButton.addEventListener("click", approveReimbursementRequest);
