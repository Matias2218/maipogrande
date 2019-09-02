<%--
  Created by IntelliJ IDEA.
  User: Leonardo
  Date: 29-08-2019
  Time: 22:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <jsp:include page="layout/cabecera.jsp" />
    <script>
        $(document).ready(function () {
            $("input[type=file]").change(function () {
                var fieldVal = $(this).val();

                // Change the node's value by removing the fake path (Chrome)
                fieldVal = fieldVal.replace("C:\\fakepath\\", "");

                if (fieldVal != undefined || fieldVal != "") {
                    $(this).next(".custom-file-label").attr('data-content', fieldVal);
                    $(this).next(".custom-file-label").text(fieldVal);
                }
            });
            $('#txtNombre').keypress(function (e) {
                var regex = new RegExp("^[a-zA-ZÁ-ÿ \s]+$");
                var str = String.fromCharCode(!e.charCode ? e.which : e.charCode);
                if (regex.test(str)) {
                    return true;
                }
                else
                {
                    e.preventDefault();
                    return false;
                }
            });
        })
    </script>
    <script>
        (function() {
            'use strict';
            window.addEventListener('load', function() {
// Fetch all the forms we want to apply custom Bootstrap validation styles to
                var forms = document.getElementsByClassName('needs-validation');
// Loop over them and prevent submission
                var validation = Array.prototype.filter.call(forms, function(form) {
                    form.addEventListener('submit', function(event) {
                        if (form.checkValidity() === false) {
                            event.preventDefault();
                            event.stopPropagation();
                        }
                        form.classList.add('was-validated');
                    }, false);
                });
            }, false);
        })();

    </script>
    <title>Editar Producto</title>
</head>
<body>
<div class="container-fluid">
    <div class="row">
        <div class="col-lg mt-4 mb-4">
            <h3 class="letras text-center mb-4">Editar producto</h3>
            <div class="card card-body">
                <form method="POST" class="needs-validation" novalidate action="/editarProducto"
                      enctype="multipart/form-data">
                    <div class="form-group">
                        <label for="txtNombre">Nombre</label>
                        <input name="txtNombre" required class="form-control" id="txtNombre" type="text"
                               value="${producto.nombreProdu}">
                        <div class="invalid-feedback">Nombre obligatorio</div>
                    </div>
                    <div class="form-group">
                        <label for="txtPrecio">Precio</label>
                        <input name="txtPrecio" required
                               onkeypress="return event.charCode >= 48 && event.charCode <= 57"
                               value="${producto.precioProdu}" class="form-control" id="txtPrecio" type="text">
                        <div class="invalid-feedback">Precio obligatorio</div>
                    </div>
                    <div class="form-group">
                        <label for="txtStock">Stock</label>
                        <input name="txtStock" required onkeypress="return event.charCode >= 48 && event.charCode <= 57"
                               value="${producto.stockProdu}" class="form-control" id="txtStock" type="text">
                        <div class="invalid-feedback">Stock obligatorio</div>
                    </div>
                    <div class="form-group">
                        <label for="txtCalidad">Calidad</label>
                        <input name="txtCalidad" maxlength="1" max="5" min="1" value="${producto.calidadProdu}"
                               pattern="[1-5]" required onkeypress="return event.charCode >= 48 && event.charCode <= 57"
                               class="form-control" id="txtCalidad" type="text">
                        <div class="invalid-feedback">Calidad obligatoria</div>
                    </div>
                    <div class="form-group">
                        <label for="fileImagen">Subir Imagen</label>
                        <div class="custom-file">
                            <input type="file" class="custom-file-input" id="fileImagen" name="fileImagen"
                                   lang="es">
                            <label class="custom-file-label" for="fileImagen">Seleccionar Archivo</label>
                            <div class="invalid-feedback">Imagen obligatoria</div>
                        </div>
                    </div>
                    <div class="form-group">
                        <img style="width: 100px; height: 100px;" src="${imagen}" alt="">
                    </div>
                    <c:choose>
                        <c:when test="${producto.tipoComercializacionProdu eq 'I'.charAt(0)}">
                            <div class="form-group">
                                <label>Tipo de venta</label><br>
                                <div class="custom-control custom-radio custom-control-inline">
                                    <input type="radio" class="custom-control-input" required id="Local" checked
                                           value="I" name="tipo">
                                    <label class="custom-control-label" for="Local">Interno</label>
                                </div>
                                <div class="custom-control custom-radio custom-control-inline">
                                    <input type="radio" class="custom-control-input" required id="Externo" value="E"
                                           name="tipo">
                                    <label class="custom-control-label" for="Externo">Externo</label>
                                </div>
                            </div>
                        </c:when>
                        <c:otherwise>
                            <div class="form-group">
                                <label>Tipo de venta</label><br>
                                <div class="custom-control custom-radio custom-control-inline">
                                    <input type="radio" class="custom-control-input" required id="Local" value="I"
                                           name="tipo">
                                    <label class="custom-control-label" for="Local">Interno</label>
                                </div>
                                <div class="custom-control custom-radio custom-control-inline">
                                    <input type="radio" class="custom-control-input" required id="Externo" value="E"
                                           checked name="tipo">
                                    <label class="custom-control-label" for="Externo">Externo</label>
                                </div>
                            </div>
                        </c:otherwise>
                    </c:choose>
                    <div class="btn-group btn-block" role="group" aria-label="Basic example">
                        <button type="submit" class="btn btn-success">Editar</button>
                        <button type="button" class="btn btn-danger" onclick="location.href='/productos';">Cancelar</button>
                    </div>
                </form>
            </div>
        </div>
        <div class="col-sm-12 col-lg-3 pr-0 pl-0 pt-0 text-center menu">
            <div class="w-100 div-menu-sin-hover">
                <h1 class="text-light carrito mt-4"><i class="fas fa-shopping-cart"></i></h1>
                <p class="letras mt-3">3 Ventas en proceso</p>
            </div>
            <a style="text-decoration: none" href="/">
                <div class="w-100 div-menu border-top border-white">
                    <p class="letras mt-4">Mi Perfil</p>
                </div>
            </a>
            <a style="text-decoration: none" href="/productos">
                <div class="w-100 div-menu border-top border-bottom border-white">
                    <p class="letras mt-4">Mis Productos</p>
                </div>
            </a>
            <div class="w-100 div-menu border-top border-bottom border-white">
                <p class="letras mt-4">Mis Ventas</p>
            </div>
            <!-- FOOTER -->
            <footer class="py-2 text-white-50">
                <div class="footer-copyright text-center py-3">© 2019 Copyright:
                    <a href="#" class="text-success"> Quality Solution Team</a>
                </div>
            </footer>
    </div>
</div>
</body>
</html>
