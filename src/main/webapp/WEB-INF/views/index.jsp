<%--
  Created by IntelliJ IDEA.
  User: Leonardo
  Date: 15-08-2019
  Time: 17:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Maipo Grande</title>
    <link rel="stylesheet" href="\css\bootstrap.min.css">
    <link rel="stylesheet" href="\css\styles.css">
    <script src='https://kit.fontawesome.com/a076d05399.js'></script>
    <link rel="shortcut icon" type="image/png" href="img\icono-maipo.png"/>
</head>
<body>
<!-- HEADER -->
<header class="py-0 bg-dark text-white-50">
    <div class="text-center py-1">
        <a href="#" class="text-light mr-4"><i class='fab fa-facebook-square'></i></a>
        <a href="#" class="text-light mr-4"><i class='fab fa-twitter-square'></i></a>
        <a href="#" class="text-light"><i class='fab fa-instagram'></i></a>
    </div>
</header>

<!-- NAVBAR -->
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
        <form class="form-inline my-2 my-lg-0" action="/" method="post">
            <input class="form-control my-2 my-sm-0 mr-1 ml-1" type="text" placeholder="Usuario" name="username">
            <input class="form-control my-2 my-sm-0 mr-1 ml-1" type="password" placeholder="Contraseña" name="password">
            <button class="btn btn-success my-2 my-sm-0 mr-1 ml-1 letras" type="submit">Ingresar</button>
        </form>
    </div>
</nav>

<header class="sc-main">
    <!-- TEXTO O IMAGENES -->
    <div class="sc-header-content">
    </div>
</header>
<!-- FIN HEADER -->

<!-- LOGO/PRESENTACION -->
<div class="container">
    <div class="py-5 text-center">
        <img class="d-block mx-auto mb-4" src="img/logo-maipo-bg.png" alt="" height="90">
        <p class="lead">¡Maipo Grande es la feria virtual mas grande del pais! En nuestra página se encuentran
            registradas las mayores empresas dedicadas a la producción de frutas de la zona metropolitana.</p>
    </div>
    <div class="card-deck">
        <div class="card text-center">
            <img src="img/venta-1.jpg" class="card-img-top" alt="...">
            <div class="card-body">
                <h5 class="card-title letras">La mejor fruta</h5>
                <p class="card-text text-secondary">Nos encargamos de vender a nuestros clientes la fruta de mejor
                    calidad, por lo que contamos con un algoritmo que selecciona el producto de mejor calidad y lo
                    ofrece al comprador.</p>
            </div>
        </div>
        <div class="card text-center">
            <img src="img/venta-2.jpg" class="card-img-top" alt="...">
            <div class="card-body">
                <h5 class="card-title letras">Venta al extranjero</h5>
                <p class="card-text text-secondary">Ofrecemos al mercado extranjero los productos con el mejor precio
                    del mercado, encargandonos que estos productos sean de calidad</p>
            </div>
        </div>
        <div class="card text-center">
            <img src="img/venta-3.jpg" class="card-img-top" alt="...">
            <div class="card-body">
                <h5 class="card-title letras">Venta local</h5>
                <p class="card-text text-secondary">Tambien ofrecemos productos de manera local, manteniendo la misma
                    calidad de la fruta vendida al extranjero, asegurandonos que nuestros proveedores satisfaga al
                    cliente.</p>
            </div>
        </div>
    </div>
</div>
<!-- FIN LOGO/PRESENTACION -->

<div style="height: 500px;"></div>

<!-- FOOTER -->
<footer class="py-2 bg-dark text-white-50">
    <div class="footer-copyright text-center py-3">© 2019 Copyright:
        <a href="#" class="text-success"> Quality Solution Team</a>
    </div>

</footer>
<!-- FIN FOOTER -->


<script src="js/jquery-3.3.1.slim.min.js"></script>
<script src="js/popper.min.js"></script>
<script src="js/bootstrap.min.js"></script>
</body>
</html>
