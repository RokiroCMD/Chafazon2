<%-- 
    Document   : main_admin
    Created on : 6 dic. 2023, 13:11:01
    Author     : alexc
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Administracion Chafazon</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
        <link rel="stylesheet" href="./css/main_admin.css"/>
    </head>
    <body>

        <nav class="navbar fixed-top bg-primary shadow">
            <div class="container-fluid">

                <a class="navbar-brand" href="">
                    <img src="images/ChafazonLargo32.png" alt="Bootstrap" width="128" height="32">
                </a>

                <div class="d-flex">
                    <a id="logout" class="p-1" href="#cerrar">
                        <svg xmlns="http://www.w3.org/2000/svg" width="32" height="32" fill="white" class="bi bi-box-arrow-left" viewBox="0 0 16 16">
                        <path fill-rule="evenodd" d="M6 12.5a.5.5 0 0 0 .5.5h8a.5.5 0 0 0 .5-.5v-9a.5.5 0 0 0-.5-.5h-8a.5.5 0 0 0-.5.5v2a.5.5 0 0 1-1 0v-2A1.5 1.5 0 0 1 6.5 2h8A1.5 1.5 0 0 1 16 3.5v9a1.5 1.5 0 0 1-1.5 1.5h-8A1.5 1.5 0 0 1 5 12.5v-2a.5.5 0 0 1 1 0z"/>
                        <path fill-rule="evenodd" d="M.146 8.354a.5.5 0 0 1 0-.708l3-3a.5.5 0 1 1 .708.708L1.707 7.5H10.5a.5.5 0 0 1 0 1H1.707l2.147 2.146a.5.5 0 0 1-.708.708l-3-3z"/>
                        </svg>
                    </a>


                </div>

            </div>


        </nav>

        <div class="wrapper">
            <div class="container">

                <div class="container-item" id="btn-usuarios">
                    <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-people-fill" viewBox="0 0 16 16">
                    <path d="M7 14s-1 0-1-1 1-4 5-4 5 3 5 4-1 1-1 1zm4-6a3 3 0 1 0 0-6 3 3 0 0 0 0 6m-5.784 6A2.238 2.238 0 0 1 5 13c0-1.355.68-2.75 1.936-3.72A6.325 6.325 0 0 0 5 9c-4 0-5 3-5 4s1 1 1 1zM4.5 8a2.5 2.5 0 1 0 0-5 2.5 2.5 0 0 0 0 5"/>
                    </svg>
                    <p>Usuarios</p>
                </div>
                <div class="container-item" id="btn-inventarios">
                    <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-box-seam-fill" viewBox="0 0 16 16">
                    <path fill-rule="evenodd" d="M15.528 2.973a.75.75 0 0 1 .472.696v8.662a.75.75 0 0 1-.472.696l-7.25 2.9a.75.75 0 0 1-.557 0l-7.25-2.9A.75.75 0 0 1 0 12.331V3.669a.75.75 0 0 1 .471-.696L7.443.184l.01-.003.268-.108a.75.75 0 0 1 .558 0l.269.108.01.003 6.97 2.789ZM10.404 2 4.25 4.461 1.846 3.5 1 3.839v.4l6.5 2.6v7.922l.5.2.5-.2V6.84l6.5-2.6v-.4l-.846-.339L8 5.961 5.596 5l6.154-2.461z"/>
                    </svg>
                    <p>Inventarios</p>
                </div>
                <div class="container-item" id="btn-articulos">
                    <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-bag-fill" viewBox="0 0 16 16">
                    <path d="M8 1a2.5 2.5 0 0 1 2.5 2.5V4h-5v-.5A2.5 2.5 0 0 1 8 1m3.5 3v-.5a3.5 3.5 0 1 0-7 0V4H1v10a2 2 0 0 0 2 2h10a2 2 0 0 0 2-2V4z"/>
                    </svg>
                    <p>Articulos</p>
                </div>
                <div class="container-item"  id="btn-ventas">
                    <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-cart-check-fill" viewBox="0 0 16 16">
                    <path d="M.5 1a.5.5 0 0 0 0 1h1.11l.401 1.607 1.498 7.985A.5.5 0 0 0 4 12h1a2 2 0 1 0 0 4 2 2 0 0 0 0-4h7a2 2 0 1 0 0 4 2 2 0 0 0 0-4h1a.5.5 0 0 0 .491-.408l1.5-8A.5.5 0 0 0 14.5 3H2.89l-.405-1.621A.5.5 0 0 0 2 1zM6 14a1 1 0 1 1-2 0 1 1 0 0 1 2 0m7 0a1 1 0 1 1-2 0 1 1 0 0 1 2 0m-1.646-7.646-3 3a.5.5 0 0 1-.708 0l-1.5-1.5a.5.5 0 1 1 .708-.708L8 8.293l2.646-2.647a.5.5 0 0 1 .708.708z"/>
                    </svg>
                    <p>Ventas</p>
                </div>

            </div>

            <div id="section-usuarios" class="content">
                <h2>CONTROL DE USUARIOS</h2>

                <div class="content-custom">
                    <table class="table-custom" id="tbl-usuarios">

                    </table>
                </div>

                <div id="form-add-user" class="forms-custom">

                    <div class="form-wrapper">
                        <h3>Agregar usuario nuevo</h3>
                        <div class="form-control form-item">
                            <label>Nombre </label>
                            <input type="text" id="txt-name-user">
                        </div>
                        <div class="form-control form-item">
                            <label>Correo </label>
                            <input type="text" id="txt-correo-user">
                        </div>
                        <div class="form-control form-item">
                            <label>Contraseña </label>
                            <input type="text" id="txt-password-user">
                        </div>
                        <div class="form-item">
                            <label class="checkbox-text">Es admininstrador </label>
                            <input type="checkbox" title="Es admininstrador " id="check-admin-user">
                        </div>
                        <button class="form-submit" id="btn-sbumit-add-user">AGREGAR</button>
                    </div>


                </div>

                <div id="form-remove-user" class="forms-custom">
                    <div class="form-wrapper">
                        <h3>Eliminar usuario</h3>
                        <div class="form-control form-item">
                            <label>ID </label>
                            <input type="number" id="txt-id-user">
                        </div>
                        <button class="form-submit" id="btn-sbumit-remove-user">ELIMINAR</button>
                    </div>
                </div>


            </div>


            <div id="section-inventarios" class="content">
                <h2>CONTROL DE INVENTARIO</h2>


                <div class="content-custom">
                    <table class="table-custom" id="tbl-inventarios">

                    </table>
                </div>

                <div id="form-add-inv" class="forms-custom">

                    <div class="form-wrapper">
                        <h3>Agregar producto a inventario</h3>
                        <div class="form-control form-item">
                            <label>Nombre </label>
                            <input type="text" id="txt-name-inv">
                        </div>
                        <div class="form-control form-item">
                            <label>Categoria </label>
                            <input type="text" id="txt-category-inv">
                        </div>
                        <div class="form-control form-item">
                            <label>Existencias </label>
                            <input type="number" id="txt-existance-inv">
                        </div>
                        <div class="form-item">
                            <label>Imagen </label>
                            <input type="file" id="chooser-img-inv">
                        </div>
                        <button class="form-submit" id="btn-sbumit-add-inv">AGREGAR</button>
                    </div>

                    <img src=""  alt="" id="img-add-inv"/>

                </div>

                <div id="form-remove-inv" class="forms-custom">
                    <div class="form-wrapper">
                        <h3>Eliminar producto del inventario</h3>
                        <div class="form-control form-item">
                            <label>ID </label>
                            <input type="number" id="txt-id-inv">
                        </div>
                        <button class="form-submit" id="btn-sbumit-remove-inv">ELIMINAR</button>
                    </div>
                </div>

            </div>

            <div id="section-articulos" class="content">
                <h2>Control de articulos</h2>

                <div class="content-custom">
                    <table class="table-custom" id="tbl-articulos">
                        <thead>
                            <tr>
                                <th scope="col">Id</th>
                                <th scope="col">Nombre</th>
                                <th scope="col">Precio</th>
                                <th scope="col">Imagen</th>
                                <th scope="col">Existencia</th>
                                <th scope="col">enOferta</th>
                            </tr>
                        </thead>
                    </table>
                </div>
                
                <div id="form-add-art" class="forms-custom">

                    <div class="form-wrapper">
                        <h3>Agregar articulo a la tienda</h3>
                        <div class="form-control form-item">
                            <label>ID </label>
                            <input type="number" id="txt-id-art">
                        </div>
                        <div class="form-control form-item">
                            <label>Precio </label>
                            <input type="number" id="txt-precio-art">
                        </div>
                        <div class="form-item">
                            <label class="checkbox-text">En oferta </label>
                            <input type="checkbox" id="check-oferta-art">
                        </div>
                        <button class="form-submit" id="btn-sbumit-add-art">AGREGAR</button>
                    </div>

                    <img src=""  alt="" id="img-add-art"/>

                </div>
                
                <div id="form-remove-art" class="forms-custom">
                    <div class="form-wrapper">
                        <h3>Eliminar articulo de la tienda</h3>
                        <div class="form-control form-item">
                            <label>ID </label>
                            <input type="number" id="txt-id-remove-art">
                        </div>
                        <button class="form-submit" id="btn-sbumit-remove-art">ELIMINAR</button>
                    </div>
                </div>

            </div>

            <div id="section-ventas" class="content">
                <h2>Ventas totales</h2>

                <div class="content-custom">
                    <table class="table-custom" id="tbl-ventas">
                        <thead>
                            <tr>
                                <th scope="col">Id</th>
                                <th scope="col">Comprador</th>
                                <th scope="col">Producto</th>
                                <th scope="col">Monto</th>
                                <th scope="col">Imagen</th>
                                <th scope="col">Fecha</th>
                            </tr>
                        </thead>
                    </table>
                </div>

            </div>


        </div>





        <script src="./js/main_admin.js"></script>

        <script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL" crossorigin="anonymous"></script>

    </body>
</html>
