window.onload = function() {
    renderCurrentUser();
}

function renderCurrentUser() {
    fetch("http://localhost:7000/current_user", {
        method: 'GET',
        credentials: 'include'
    }).then((response) => {
        if(response.status === 400) {
            window.location.href = '/';
        }
        return response.json();
    }).then((data) => {
        let id = data.id;
        let username = data.username;
        let password = data.password;
        let firstName = data.firstName;
        let lastName = data.lastName;
        let email = data.email;

        let userInfoElement = document.querySelector('#manager');
        userInfoElement.innerHTML = `User id: ${id}, username: ${username}, firstName: ${firstName}`
    })
}

async function logout() {

    const result = await fetch("http://localhost:7000/logout", {
        method: "POST",
        mode: "no-cors",
        credentials: "include",
    }).then(response => {
        if (response.status === 200){
            // return response.status;
            window.location.href = "../index.html";
        } else if (response.status === 401) {
            return "Please try again!";
        }
    })
}

let logoutButton = document.getElementById("manager-logout");
logoutButton.addEventListener('click', logout);

