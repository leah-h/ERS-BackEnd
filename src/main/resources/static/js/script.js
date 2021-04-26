"use strict";

const errorElement = document.getElementById("error");
let submitButton = document.getElementById("login");
let username = document.getElementById("username");
let password = document.getElementById("password");
let form = document.querySelector("form");

function login() {
    let data = {
        username: username.value,
        password: password.value,
    };

    console.log(data);

    fetch("http://localhost:7001/login", {
        method: "POST",
        mode: "no-cors",
        credentials: "include",
        headers: {
            "Content-Type": "application/json",
        },
        body: JSON.stringify(data),
    }).then((response) => {
        if (response.status === 200) {
            window.location.href = "/employee.html";
        }
    });
}

function reset() {
    username.innerText = "";
    password.innerText = "";
}

submitButton.addEventListener("click", login);