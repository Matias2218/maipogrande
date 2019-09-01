<%--
  Created by IntelliJ IDEA.
  User: Leonardo
  Date: 29-08-2019
  Time: 22:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="css/bootstrap.min.css">
    <script src="js/bootstrap.min.js"></script>
    <link rel="stylesheet" href="\css\styles.css">
    <script src='https://kit.fontawesome.com/a076d05399.js'></script>
    <script src="js/jquery-3.3.1.slim.min.js"></script>
    <title>Añadir Producto</title>
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
</head>
<body>
<nav class="navbar navbar-expand-lg navbar-light bg-lg sticky-top navbar-verde">
    <a class="navbar-brand" href="#">
        <img src="img/logo-maipo.png" height="50" class="d-inline-block align-top" alt="">
    </a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent"
            aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav mr-auto">
        </ul>
        <button class="btn btn-success my-2 my-sm-0 mr-1 ml-1 letras" onclick="location.href='/logout';" type="button">Cerrar Sesion</button>
    </div>
</nav>
<h1>Producto</h1>
<div class="container mt-2">
    <div class="row">
        <div class="col-md-4 offset-md-4">
            <div class="card card-body">
                <form method="POST" class="needs-validation" novalidate action="/productos" enctype="multipart/form-data">
                    <div class="form-group">
                        <label for="txtNombre">Nombre</label>
                        <input name="txtNombre" required class="form-control" id="txtNombre" type="text">
                        <div class="invalid-feedback">Nombre obligatorio</div>
                    </div>
                    <div class="form-group">
                        <label for="txtPrecio">Precio</label>
                        <input name="txtPrecio" required onkeypress="return event.charCode >= 48 && event.charCode <= 57" class="form-control" id="txtPrecio" type="text">
                        <div class="invalid-feedback">Precio obligatorio</div>
                    </div>
                    <div class="form-group">
                        <label for="txtStock">Stock</label>
                        <input name="txtStock" required onkeypress="return event.charCode >= 48 && event.charCode <= 57" class="form-control" id="txtStock" type="text">
                        <div class="invalid-feedback">Stock obligatorio</div>
                    </div>
                    <div class="form-group">
                        <label for="txtCalidad">Calidad</label>
                        <input name="txtCalidad" maxlength="1" max="5" min="1" pattern="[1-5]" required onkeypress="return event.charCode >= 48 && event.charCode <= 57" class="form-control" id="txtCalidad" type="text">
                        <div class="invalid-feedback">Calidad obligatoria</div>
                    </div>
                    <div class="form-group">
                        <label for="fileImagen">Subir Imagen</label>
                        <div class="custom-file">
                            <input type="file" required class="custom-file-input" id="fileImagen" name="fileImagen" lang="es">
                            <label class="custom-file-label" for="fileImagen">Seleccionar Archivo</label>
                            <div class="invalid-feedback">Imagen obligatoria</div>
                        </div>
                    </div>
                    <div class="form-group">
                        <label>Tipo de venta</label><br>
                        <div class="custom-control custom-radio custom-control-inline">
                            <input type="radio" class="custom-control-input" required id="Local" value="I"  name="tipo">
                            <label class="custom-control-label" for="Local">Interno</label>
                        </div>
                        <div class="custom-control custom-radio custom-control-inline">
                            <input type="radio" class="custom-control-input" required id="Externo" value="E"  name="tipo">
                            <label class="custom-control-label" for="Externo">Externo</label>
                        </div>
                    </div>
                    <div class="form-group">
                        <button type="submit" class="btn btn-success">Agregar</button>
                        <a href="/productor" class="btn btn-danger">Cancelar</a>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
</body>
</html>
