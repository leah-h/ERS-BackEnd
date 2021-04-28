import {renderCurrentUser, logout} from "./employee";

window.onload = function() {
    renderCurrentUser();
}

let logoutButton = document.getElementById("logout");
logoutButton.addEventListener('click', logout);

