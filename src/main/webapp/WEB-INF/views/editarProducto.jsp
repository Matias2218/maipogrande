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
    <link rel="stylesheet" href="/css/bootstrap.min.css">
    <script src="/js/bootstrap.min.js"></script>
    <title>Editar Productos</title>
</head>
<body>
    <nav class="navbar navbar-expand-lg navbar-light bg-light">
        <a class="navbar-brand" href="#">Navbar</a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>

        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav mr-auto">
                <li class="nav-item">
                    <a class="nav-link" href="/añadirProducto">Añadir Producto</a>
                </li>
                <a href="/logout">Salir</a>
            </ul>
        </div>
    </nav>
<form method="POST" action="/editarProducto" enctype="multipart/form-data">
    <table>
        <tr>
            <td>Nombre</td>
            <td><input name="txtNombre" type="text" value="${producto.nombreProdu}"></td>
        </tr>
        <tr>
            <td>Precio</td>
            <td><input name="txtPrecio" type="number" value="${producto.precioProdu}"></td>
        </tr>
        <tr>
            <td>Stock</td>
            <td><input name="txtStock" type="number" value="${producto.stockProdu}"></td>
        </tr>
        <tr>
            <td>Calidad</td>
            <td><input name="txtCalidad" type="text" value="${producto.calidadProdu}"></td>
        </tr>
        <tr>
            <td>Imagen</td>
            <td><input name="fileImagen" type="file"> <img style="width: 100px; height: 100px;" src="${imagen}" alt="">
            </td>
        </tr>
        <tr>
            <td>
                <c:choose>
                    <c:when test="${producto.tipoComercializacionProdu eq 'I'.charAt(0)}">
                        <input type="radio" name="tipo" checked value="I" >Interna
                        <input type="radio" name="tipo" value="E">Externa
                    </c:when>
                    <c:otherwise>
                        <input type="radio" name="tipo" value="I">Interna
                        <input type="radio" name="tipo" value="E" checked>Externa
                    </c:otherwise>
                </c:choose>
            </td>
        </tr>
        <tr>
            <td>
                <button type="submit">Actualizar</button>
            </td>
            <td>
                <a href="#">Cancelar</a>
            </td>
        </tr>
    </table>
</form>
</body>
</html>
