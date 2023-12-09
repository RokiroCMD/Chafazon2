
const art_containter = document.getElementById("articulos-content");
var boton = document.getElementsByName("boton");
var fondos_texto = document.getElementById("fondos-texto");
var fondos = document.getElementById("fondos");
var logout = document.getElementById("logout");
var informacion = document.getElementById("info");
var carrusel = document.getElementById("carouselExampleDark");



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


function actualizarOfertas() {
    while (carrusel.firstElementChild) {
        carrusel.removeChild(carrusel.firstElementChild);
    }

    $.ajax({
        url: 'ControladorArticulos',
        method: 'POST',
        dataType: 'json',
        data: {
            option: "consultar"
        },
        success: function (data) {
            // Manejar la respuesta JSON aquí

            let  car_items = ``;
            let i = 0;
            for (var id in data) {
                if (data.hasOwnProperty(id)) {
                    var item = data[id];
                    if (item.oferta == true) {
                        if (i < 5) {
                            if (i == 0) {
                                car_items += `<div class="carousel-item active" data-bs-interval="10000">
                        <img src="${item.imagen}" class="d-block mx-auto" height="210" alt="...">
                        <div class="carousel-caption d-none d-md-block">
                            <h5 class="text-black-shadow text-white">${item.nombre}</h5>
                            <p class="h4 text-danger text-black-shadow" >$${item.precio}</p>
                        </div>
                    </div>`;
                                
                            } else {
                                car_items += `<div class="carousel-item">
                        <img src="${item.imagen}" class="d-block mx-auto" height="210" alt="...">
                        <div class="carousel-caption d-none d-md-block">
                            <h5 class="text-black-shadow text-white">${item.nombre}</h5>
                            <p class="h4 text-danger text-black-shadow">$${item.precio}</p>
                        </div>
                    </div>`;
                            }
                        }


                        i += 1;
                    }

                }

            }

            let car_indicator_start = `<div class="carousel-indicators">`;

            car_items = `<div class="carousel-inner">`  + car_items + `</div>` ;

            let car_indicators = ` `;

            for (var n = 0; n < i; i++) {
                if (n == 0) {
                    car_indicators += `<button type="button" data-bs-target="#carouselExampleDark" data-bs-slide-to="${n}" class="active" aria-current="true" aria-label="Slide ${n + 1}"></button>`;
                } else {
                    car_indicators += `<button type="button" data-bs-target="#carouselExampleDark" data-bs-slide-to="${n}" aria-label="Slide ${n + 1}"></button>`;
                }
            }

            let car_indicator_end = `</div>`;

            let car_indicator = car_indicator_start + car_indicators + car_indicator_end;

            let car_buttons = `<button class="carousel-control-prev" type="button" data-bs-target="#carouselExampleDark" data-bs-slide="prev">
                    <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                    <span class="visually-hidden">Previous</span>
                </button>
                <button class="carousel-control-next" type="button" data-bs-target="#carouselExampleDark" data-bs-slide="next">
                    <span class="carousel-control-next-icon" aria-hidden="true"></span>
                    <span class="visually-hidden">Next</span>
                </button>`;

            carrusel.innerHTML += car_indicator + car_items  + car_buttons;

        },
        error: function (error) {
            console.error('Error en la solicitud AJAX:', error);
        }
    });



}

function actualizarArticulos() {

    while (art_containter.firstChild) {
        art_containter.removeChild(art_containter.firstChild);
    }

    $.ajax({
        url: 'ControladorArticulos',
        method: 'POST',
        dataType: 'json',
        data: {
            option: "consultar"
        },
        success: function (data) {
            // Manejar la respuesta JSON aquí


            for (var id in data) {
                if (data.hasOwnProperty(id)) {
                    var item = data[id];

                    crearArticuloItem(id, item.imagen, item.nombre, item.precio);


                }
            }

        },
        error: function (error) {
            console.error('Error en la solicitud AJAX:', error);
        }
    });

}

function desplegarInfo() {
    window.location.href = "UsuarioInfo.html";
}

window.onload = function () {

    actualizarOfertas();
    actualizarArticulos();

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


