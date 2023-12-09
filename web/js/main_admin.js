
var logout = document.getElementById("logout");
var btn_usuarios = document.getElementById("btn-usuarios");
var btn_inventarios = document.getElementById("btn-inventarios");
var btn_articulos = document.getElementById("btn-articulos");
var btn_ventas = document.getElementById("btn-ventas");

var table_inventarios = document.getElementById("tbl-inventarios");
var table_usuarios = document.getElementById("tbl-usuarios");
var table_articulos = document.getElementById("tbl-articulos");
var table_ventas = document.getElementById("tbl-ventas");

var section_usuarios = document.getElementById("section-usuarios");
var section_inventarios = document.getElementById("section-inventarios");
var section_articulos = document.getElementById("section-articulos");
var section_ventas = document.getElementById("section-ventas");

var name_inv = document.getElementById("txt-name-inv");
var id_inv = document.getElementById("txt-id-inv");
var category_inv = document.getElementById("txt-category-inv");
var existance_inv = document.getElementById("txt-existance-inv");
var chooser_inv = document.getElementById("chooser-img-inv");
var img_add_inv = document.getElementById("img-add-inv");
var img_inv = null;
var btn_submit_inv = document.getElementById("btn-sbumit-add-inv");
var btn_remove_inv = document.getElementById("btn-sbumit-remove-inv");

var txt_id_art = document.getElementById("txt-id-art");
var txt_precio_art = document.getElementById("txt-precio-art");
var check_oferta_art = document.getElementById("check-oferta-art");
var img_add_art = document.getElementById("img-add-art");

var txt_id_remove_art = document.getElementById("txt-id-remove-art"); 

var btn_submit_add_art = document.getElementById("btn-sbumit-add-art");
var btn_submit_remove_art = document.getElementById("btn-sbumit-remove-art");

var seccion = -1;

function cerrarSesion() {

    $.ajax({
        accept: "application/x-www-form-urlencoded",
        method: "POST",
        url: "ControladorUsuarios",
        data: {
            option: "cerrarSesionUsuario"
        }
    }).done(function (respuesta) {
        window.location.href = "./index.html";
    });

}

function cargarTablaUsuarios() {
    $.ajax({
        url: 'ControladorUsuarios',
        method: 'POST',
        dataType: 'json',
        data: {
            option: "consultarJSON"
        },
        success: function (data) {
            // Manejar la respuesta JSON aquí

            while (table_usuarios.firstChild) {
                table_usuarios.removeChild(table_usuarios.firstChild);
            }

            table_usuarios.innerHTML += `<thead>
                            <tr>
                                <th scope="col">Id</th>
                                <th scope="col">Nombre</th>
                                <th scope="col">Correo</th>
                                <th scope="col">Contraseña</th>
                                <th scope="col">Saldo</th>
                            </tr>
                        </thead>`;

            let content = ``;

            for (var id in data) {
                if (data.hasOwnProperty(id)) {
                    var item = data[id];
                    content += `<tr>
                                <td>${id}</td>
                                <td>${item.nombre}</td>
                                <td>${item.correo}</td>
                                <td>${item.contraseña}</td>
                                <td>${item.saldo}</td>
                            </tr>`

                    // Aquí puedes hacer algo más con los datos, como agregarlos a la página HTML
                }
            }

            table_usuarios.innerHTML += `<tbody>` + content + `</tbody>`;


        },
        error: function (error) {
        }
    });
}

function cargarTablaArticulos() {
    
    while (table_articulos.firstChild) {
        table_articulos.removeChild(table_articulos.firstChild);
    }

    table_articulos.innerHTML +=
            `<thead>
                            <tr>
                               <th scope="col">Id</th>
                                <th scope="col">Nombre</th>
                                <th scope="col">Categoria</th>
                                <th scope="col">Existencias</th>
                                <th scope="col">Imagen</th>
                                <th scope="col">Precio</th>
                                <th scope="col">En Oferta</th>
                            </tr>
                        </thead>`;
    
    $.ajax({
        url: 'ControladorArticulos',
        method: 'POST',
        dataType: 'json',
        data: {
            option: "consultar"
        },
        success: function (data) {
            // Manejar la respuesta JSON aquí

            let content = ``;

            for (var id in data) {
                if (data.hasOwnProperty(id)) {
                    var item = data[id];
                    content += `<tr>
                                <td>${id}</td>
                                <td>${item.nombre}</td>
                                <td>${item.categoria}</td>
                                <td>${item.existencias}</td>
                                <td><img src="${item.imagen}" width="128px" height="128px;"/></td>
                                <td>$${item.precio}</td>
                                <td>${item.oferta}</td>
                            </tr>`;

                    // Aquí puedes hacer algo más con los datos, como agregarlos a la página HTML
                }
            }

            table_articulos.innerHTML += `<tbody>` + content + `</tbody>`;


        },
        error: function (error) {
            console.error('Error en la solicitud AJAX:', error);
        }
    });

}

function cargarTablaVentas(){
    while (table_ventas.firstChild) {
        table_ventas.removeChild(table_ventas.firstChild);
    }

    table_ventas.innerHTML +=
            `<thead>
                            <tr>
                               <th scope="col">Id</th>
                                <th scope="col">Comprador</th>
                                <th scope="col">Articulo</th>
                                <th scope="col">Monto</th>
                                <th scope="col">Imagen</th>
                                <th scope="col">Fecha</th>
                            </tr>
                        </thead>`;
    
    $.ajax({
        url: 'ControladorVentas',
        method: 'POST',
        dataType: 'json',
        data: {
            option: "consultar"
        },
        success: function (data) {
           
            let content = ``;

            for (var id in data) {
                if (data.hasOwnProperty(id)) {
                    var item = data[id];
                    content += `<tr>
                                <td>${id}</td>
                                <td>${item.nombreComprador}</td>
                                <td>${item.nombreProducto}</td>
                                <td>$${item.monto}</td>
                                <td><img src="${item.imagen}" width="128px" height="128px;"/></td>
                                <td>${item.fecha}</td>
                            </tr>`;

                    // Aquí puedes hacer algo más con los datos, como agregarlos a la página HTML
                }
            }

            table_ventas.innerHTML += `<tbody>` + content + `</tbody>`;


        },
        error: function (error) {
            console.error('Error en la solicitud AJAX:', error);
        }
    });
}

function cargarTablaInventarios() {
    $.ajax({
        url: 'ControladorInventario',
        method: 'POST',
        dataType: 'json',
        data: {
            option: "consultar"
        },
        success: function (data) {
            // Manejar la respuesta JSON aquí

            while (table_inventarios.firstChild) {
                table_inventarios.removeChild(table_inventarios.firstChild);
            }

            table_inventarios.innerHTML += `<thead>
                            <tr>
                                <th scope="col">Id</th>
                                <th scope="col">Nombre</th>
                                <th scope="col">Categoria</th>
                                <th scope="col">Existencias</th>
                                <th scope="col">Imagen</th>
                            </tr>
                        </thead>`;

            let content = ``;

            for (var id in data) {
                if (data.hasOwnProperty(id)) {
                    var item = data[id];
                    content += `<tr>
                                <td>${id}</td>
                                <td>${item.nombre}</td>
                                <td>${item.categoria}</td>
                                <td>${item.existencias}</td>
                    
                                <td><img src="${item.imagen}" width="128px" height="128px;"/></td>
                            </tr>`;

                    // Aquí puedes hacer algo más con los datos, como agregarlos a la página HTML
                }
            }

            table_inventarios.innerHTML += `<tbody>` + content + `</tbody>`;


        },
        error: function (error) {
            console.error('Error en la solicitud AJAX:', error);
        }
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
    cargarTablaUsuarios();

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
    cargarTablaInventarios();


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
    cargarTablaArticulos();

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
    cargarTablaVentas();

}


function mostrarImagen(event) {
    let imagenSource = event.target.result;
    img_inv = imagenSource;
    img_add_inv.src = imagenSource;
}

function procesarArchivo(event) {
    let imagen = event.target.files[0];
    let lector = new FileReader();
    lector.addEventListener("load", mostrarImagen, false);
    lector.readAsDataURL(imagen);
}

function guardarInventario(event) {
    let name = name_inv.value;
    let category = category_inv.value;
    let exist = parseInt(existance_inv.value);

    $.ajax({
        accept: "application/x-www-form-urlencoded",
        method: "POST",
        url: "ControladorInventario",
        data: {
            option: "agregar",
            nombre: name,
            categoria: category,
            existencias: exist,
            imagen: img_inv
        }
    }).done(function (respuesta) {
        cargarTablaInventarios();
        name_inv.value = "";
        category_inv.value = "";
        existance_inv.value = "";
        img_add_inv.src = "";
        chooser_inv.value = null;
        section_inventarios.scrollIntoView();
    });

}

function removerInventario() {

    let id = parseInt(id_inv.value);

    $.ajax({
        accept: "application/x-www-form-urlencoded",
        method: "POST",
        url: "ControladorInventario",
        data: {
            option: "eliminar",
            idEliminar: id
        }
    }).done(function (respuesta) {
        cargarTablaInventarios();
        id_inv.value = "";
        section_inventarios.scrollIntoView();
    });
}

function cargarPreviewArticulo() {

    let id = parseInt(txt_id_art.value);

    $.ajax({
        accept: "application/x-www-form-urlencoded",
        method: "POST",
        url: "ControladorInventario",
        data: {
            option: "consultarImagen",
            id: id
        }
    }).done(function (respuesta) {
        img_add_art.src = respuesta.toString();
    });

}

function guardarArticulo() {
    let id = txt_id_art.value;
    let precio = txt_precio_art.value;
    let oferta = check_oferta_art.checked;
    $.ajax({
        accept: "json",
        method: "POST",
        url: "ControladorInventario",
        data: {
            option: "consultarInfo",
            id: id
        }
    }).done(function (data) {
        let nombre = data.nombre;
        let categoria = data.categoria;
        let existencias = data.existencias;
        let imagen = data.imagen;

        $.ajax({
            accept: "json",
            method: "POST",
            url: "ControladorArticulos",
            data: {
                option: "agregar",
                nombre: nombre,
                categoria: categoria,
                existencias: existencias,
                imagen: imagen,
                precio: precio,
                enOferta: oferta
            }
        }).done(function (respuesta) {
            
                txt_id_art.value = "";
                txt_precio_art.value = "";
                img_add_art.src = "";
                check_oferta_art.checked = false;
                cargarTablaArticulos();
            
        });


    });

}

function removerArticulo(){
    let id = txt_id_remove_art.value;
    $.ajax({
        accept: "application/x-www-form-urlencoded",
        method: "POST",
        url: "ControladorArticulos",
        data: {
            option: "eliminar",
            idEliminar: id
        }
    }).done(function (respuesta) {
        txt_id_remove_art.value = "";
        cargarTablaArticulos();
        section_articulos.scrollIntoView();
    });
}


window.onload = function () {

    logout.addEventListener("click", cerrarSesion);
    btn_usuarios.addEventListener("click", cambiarUsuarios);
    btn_inventarios.addEventListener("click", cambiarInventarios);
    btn_articulos.addEventListener("click", cambiarArticulos);
    btn_ventas.addEventListener("click", cambiarVentas);

    chooser_inv.addEventListener("change", procesarArchivo, false);
    btn_submit_inv.addEventListener("click", guardarInventario);
    btn_remove_inv.addEventListener("click", removerInventario);

    txt_id_art.addEventListener("blur", cargarPreviewArticulo);

    btn_submit_add_art.addEventListener("click", guardarArticulo);
    btn_submit_remove_art.addEventListener("click", removerArticulo);
    
    
};