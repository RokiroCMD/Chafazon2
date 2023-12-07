
var fondos_texto = document.getElementById("fondos-texto");
var fondos = document.getElementById("fondos");
var logout = document.getElementById("logout");
var informacion = document.getElementById("info");
var cerrar = document.getElementById("logout");


function actualizarDinero() {

    $.ajax({
        accept: "application/x-www-form-urlencoded",
        method: "POST",
        url: "getDinero"
    }).done(function (respuesta) {
        fondos_texto.innerHTML = "$" + respuesta;
    });

}



function agregarDinero() {

    $.ajax({
        accept: "application/x-www-form-urlencoded",
        method: "POST",
        url: "agregarDinero"
    }).done(function (respuesta) {
        fondos_texto.innerHTML = "$" + respuesta;
    });

}

function cerrarSesion() {

    $.ajax({
        accept: "application/x-www-form-urlencoded",
        method: "POST",
        url: "LogOutServlet"
    }).done(function (respuesta) {
        window.location.href = "index.html";
    });

}

function cerrarSesion() {
    $.ajax({
        accept: "application/x-www-form-urlencoded",
        method: "POST",
        url: "LogOutServlet"
    }).done(function (respuesta) {
        window.location.href = "index.html";
    });
}

function cargarValores() {
    $(document).ready(function () {
        $.get("ObtenerInfoUsuarioServlet", function (userInfo) {
            console.log("Información del usuario:", userInfo);

            $("#name").text(userInfo.Nombre);
            $("#email").text(userInfo.Correo);

            if (userInfo.Saldo !== undefined && userInfo.Saldo !== null) {
                $("#balance").text("$" + userInfo.Saldo.toFixed(2));
            } else {

                $("#balance").text("Saldo no disponible");
            }
        });
    });
}

window.onload = function () {


    actualizarDinero();
    cargarValores();

    logout.addEventListener("click", cerrarSesion);
    fondos.addEventListener("click", agregarDinero);
    cerrar.addEventListener("click", cerrarSesion);
}


