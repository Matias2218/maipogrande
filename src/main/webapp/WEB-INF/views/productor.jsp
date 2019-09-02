<%--
  Created by IntelliJ IDEA.
  User: Leonardo
  Date: 18-08-2019
  Time: 20:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <jsp:include page="layout/cabecera.jsp" />
    <title>Productor</title>
</head>
<body>
<div class="container-fluid">
    <div class="row">
        <!-- PUBLICACIONES -->
        <div class="col-lg">
            <div class="mt-4 mr-4 ml-4 pt-2 border rounded text-center">
                <h4 class="letras">Venta Nº10</h4>
                <p class="lead">Tipo de venta: Extranjera<br>Fecha de inicio: 00/00/0000</p>
                <button type="button" class="btn btn-secondary btn-block letras">Ver detalle</button>
            </div>

            <div class="mt-4 mr-4 ml-4 pt-2 border rounded text-center">
                <h4 class="letras">Venta Nº21</h4>
                <p class="lead">Tipo de venta: Local<br>Fecha de inicio: 00/00/0000</p>
                <button type="button" class="btn btn-secondary btn-block letras">Ver detalle</button>
            </div>

            <div class="mt-4 mr-4 ml-4 pt-2 mb-4 border rounded text-center">
                <h4 class="letras">Venta Nº19</h4>
                <p class="lead">Tipo de venta: Extranjera<br>Fecha de inicio: 00/00/0000</p>
                <button type="button" class="btn btn-secondary btn-block letras">Ver detalle</button>
            </div>

        </div>

        <!-- MENU -->
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
            <!-- FIN FOOTER -->
        </div>
    </div>
</div>
</body>
</html>
