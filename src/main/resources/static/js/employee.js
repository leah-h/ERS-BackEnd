
let document = "../employee.html";
let logoutButton = document.getElementById("logout");

async function logout() {

    const result = await fetch("http://localhost:7000/logout", {
        method: "POST",
        mode: "no-cors",
        credentials: "include",
        // headers: {
        //     "Content-Type": "application/json",
        // }
        // body: JSON.stringify(currentlyLoggedInUser),
    }).then(response => {
        if (response.status === 200){
            return response.status;
        } else if (response.status === 401) {
            return "Please try again!";
        }
    })

    if(result){
        window.location.href = "../index.html";
    }
}

logoutButton.addEventListener('click', logout);