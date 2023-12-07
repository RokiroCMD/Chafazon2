
var logout = document.getElementById("logout");
var btn_usuarios = document.getElementById("btn-usuarios");
var btn_inventarios = document.getElementById("btn-inventarios");
var btn_articulos = document.getElementById("btn-articulos");
var btn_ventas = document.getElementById("btn-ventas");

var section_usuarios = document.getElementById("section-usuarios");
var section_inventarios = document.getElementById("section-inventarios");
var section_articulos = document.getElementById("section-articulos");
var section_ventas = document.getElementById("section-ventas");

var seccion = -1;

function cerrarSesion() {

    $.ajax({
        accept: "application/x-www-form-urlencoded",
        method: "POST",
        url: "LogOutServlet"
    }).done(function (respuesta) {
        window.location.href = "./index.html";
    });

}

function cambiarUsuarios() {
    if (seccion != 0) {
        section_usuarios.classList.add('content-enabled');
        section_inventarios.classList.remove('content-enabled');
        section_articulos.classList.remove('content-enabled');
        section_ventas.classList.remove('content-enabled');
        seccion = 0;
        section_usuarios.scrollIntoView();
    }

}

function cambiarInventarios() {
    if (seccion != 1) {
        section_inventarios.classList.add('content-enabled');
        section_usuarios.classList.remove('content-enabled');
        section_articulos.classList.remove('content-enabled');
        section_ventas.classList.remove('content-enabled');
        seccion = 1;
        section_inventarios.scrollIntoView();
    }

}

function cambiarArticulos() {
    if (seccion != 2) {
        section_articulos.classList.add('content-enabled');
        section_inventarios.classList.remove('content-enabled');
        section_usuarios.classList.remove('content-enabled');
        section_ventas.classList.remove('content-enabled');
        seccion = 2;
        section_articulos.scrollIntoView();
    }

}

function cambiarVentas() {
    if (seccion != 3) {
        section_ventas.classList.add('content-enabled');
        section_articulos.classList.remove('content-enabled');
        section_inventarios.classList.remove('content-enabled');
        section_usuarios.classList.remove('content-enabled');
        seccion = 3;
        section_ventas.scrollIntoView();
    }

}



window.onload = function () {

    logout.addEventListener("click", cerrarSesion);
    btn_usuarios.addEventListener("click", cambiarUsuarios);
    btn_inventarios.addEventListener("click", cambiarInventarios);
    btn_articulos.addEventListener("click", cambiarArticulos);
    btn_ventas.addEventListener("click", cambiarVentas);
};