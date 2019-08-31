<%--
  Created by IntelliJ IDEA.
  User: Leonardo
  Date: 29-08-2019
  Time: 0:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fn" uri = "http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>
    <link rel="stylesheet" href="css/bootstrap.min.css">
    <script src="js/bootstrap.min.js"></script>
    <title>Title</title>
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
<h1>Productos</h1>
<table>
    <tr>
        <td>ID</td>
        <td>Imagen</td>
        <td>Nombre</td>
        <td>Fecha de ingreso</td>
        <td>Stock</td>
        <td>Precio</td>
        <td>Editar</td>
        <td>Eliminar</td>
    </tr>
    <c:forEach begin="0" end="${fn:length(productos)-1}" var="i">
        <tr>
            <td>${productos.get(i).idProdu}</td>
            <td><img style="width: 100px; height: 100px;" src="${imagenes.get(i)}" alt=""></td>
            <td>${productos.get(i).nombreProdu}</td>
            <td>${productos.get(i).fechaIngresoProdu}</td>
            <td>${productos.get(i).stockProdu}</td>
            <td>${productos.get(i).precioProdu}</td>
            <td><a href="/productos/${productos.get(i).idProdu}">Editar</a></td>
            <td><a href="">Eliminar</a></td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
