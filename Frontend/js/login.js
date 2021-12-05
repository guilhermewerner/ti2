// Valida a senha
let password = document.getElementById("senha-signup"),
    confirm_password = document.getElementById("senha-confirmation-signup");

function validatePassword() {
    if (password.value != confirm_password.value) {
        confirm_password.setCustomValidity("As senhas estão diferentes");
    } else {
        confirm_password.setCustomValidity('');
    }
}

password.onchange = validatePassword;
confirm_password.onkeyup = validatePassword;

// Fazer login na API

document.getElementById("login-form").addEventListener("submit", function (event) {
    event.preventDefault();
    BackendLogin();
});

function BackendLogin() {
    let name = document.getElementById("e-mail-login").value;
    let password = document.getElementById("senha-login").value;

    let body = {
        name,
        password,
    }

    axios.post("https://dogs.tribufu.com/login", body, { headers: { "Content-Type": "application/json" } })
        .then(function (response) {
            console.log(response);
            window.sessionStorage.setItem("session_id", response.data.id);
            close();
        })
        .catch(function (error) {
            console.log(error.statusText);
        });
}

// Animacoes de cliques de login e signup

let loginClick = function () {
    $("#signup-block").fadeTo("fast", 0).css("display", "none");
    $("#login-block").css("display", "flex").fadeTo("fast", 1);
    $("#wrapper-login-signup").css("display", "flex").fadeTo("fast", 1);
}

let signupClick = function () {
    $("#login-block").fadeTo("fast", 0).css("display", "none");
    $("#signup-block").css("display", "flex").fadeTo("fast", 1);
    $("#wrapper-login-signup").css("display", "flex").fadeTo("fast", 1);
}

let close = function () {
    $("#login-block").fadeTo("fast", 0);
    $("#signup-block").fadeTo("fast", 0);
    $("#wrapper-login-signup").fadeTo("fast", 0);
    setTimeout(function () {
        $("#login-block").css("display", "none");
        $("#signup-block").css("display", "none");
        $("#wrapper-login-signup").css("display", "none");
    }, 200);
}

$("#login-nav-button").on("click", loginClick);

$("#no-account").on("click", signupClick);
$("#with-account").on("click", loginClick);

$(".close-login-signup").on("click", close);
$(".login-overlay").on("click", close);
