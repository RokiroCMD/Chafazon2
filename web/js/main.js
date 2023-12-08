
const art_containter = document.getElementById("articulos-content");
var boton = document.getElementsByName("boton");
var fondos_texto = document.getElementById("fondos-texto");
var fondos = document.getElementById("fondos");
var logout = document.getElementById("logout");
var informacion = document.getElementById("info");



function actualizarDinero() {

    $.ajax({
        accept: "application/x-www-form-urlencoded",
        method: "POST",
        url: "ControladorUsuarios",
        data: {
            option: "consultarDinero"
        }
    }).done(function (respuesta) {
        fondos_texto.innerHTML = "$" + respuesta;
    });

}



function agregarDinero() {

    $.ajax({
        accept: "application/x-www-form-urlencoded",
        method: "POST",
        url: "ControladorUsuarios",
        data: {
            option: "agregarDinero"            
        }
    }).done(function (respuesta) {
        fondos_texto.innerHTML = "$" + respuesta;
    });

}

function cerrarSesion() {

    $.ajax({
        accept: "application/x-www-form-urlencoded",
        method: "POST",
        url: "ControladorUsuarios",
        data: {
            option: "cerrarSesionUsuario" 
        }
    }).done(function (respuesta) {
        window.location.href = "index.html";
    });

}

function crearArticuloItem(id, imageUrl, nombre, precio) {

    const art_item = `<div class="card articulos-item shadow" style="width: 15rem;">
    <img src="${imageUrl}" class="card-img-top articulos-item-image" alt="...">
    <div class="card-body bg-light ">
        <h5 class="card-title">${nombre}</h5>
        <p class="card-text articulo-item-price">$${precio}</p>
        <a id="${id}" name="boton" href="#" class="btn btn-danger">Comprar</a>
    </div>
    </div>`;

    art_containter.innerHTML += art_item;


}

function desplegarInfo() {
    window.location.href = "UsuarioInfo.html";
}

window.onload = function () {



    crearArticuloItem("PCG1", "https://m.media-amazon.com/images/I/71ECtFcFxlL.jpg", "PC gamer", 15000);
    crearArticuloItem("XBOXSS", "https://i5.walmartimages.com.mx/mg/gm/3pp/asr/183ccffc-0251-490f-95af-6170e030b22d.abe11715af810ddbbd4303c22a7b8a59.jpeg?odnHeight=2000&odnWidth=2000&odnBg=ffffff", "Xbox Series S", 7000);
    crearArticuloItem("PS5FAT", "https://m.media-amazon.com/images/I/61gimpyy0UL.jpg", "Playstation 5", 9800);
    crearArticuloItem("NSOLED", "https://m.media-amazon.com/images/I/71Q54HnKxwS._AC_UF1000,1000_QL80_.jpg", "Nintendo switch OLED", 5999);

    actualizarDinero();

    logout.addEventListener("click", cerrarSesion);
    fondos.addEventListener("click", agregarDinero);
    informacion.addEventListener("click", desplegarInfo);

    for (var i = 0; i < boton.length; i++)
    {
        boton[i].addEventListener("click", boton_click);
    }
    function boton_click(e)
    {
        alert(e.srcElement.id);
    }




}


