//Valida a senha
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

//Animacoes de cliques de login e signup
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
