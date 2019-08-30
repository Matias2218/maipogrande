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
    <title>Añadir Producto</title>
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
<h1>Producto</h1>
    <form method="POST">
        <table>
            <tr>
                <td>Nombre</td>
                <td><input name="txtNombre" type="text"></td>
            </tr>
            <tr>
                <td>Precio</td>
                <td><input name="txtPrecio" type="number"></td>
            </tr>
            <tr>
                <td>Stock</td>
                <td><input name="txtStock" type="number"></td>
            </tr>
            <tr>
                <td>Calidad</td>
                <td><input name="txtCalidad" type="text"></td>
            </tr>
            <tr>
                <td>Imagen</td>
                <td><input name="fileImagen" type="file"></td>
            </tr>
            <tr>
                <td>
                    <input type="radio" name="tipo" value="I">Local
                    <input type="radio" name="tipo" value="E">Externa
                </td>
            </tr>
            <tr>
                <td>
                    <button type="submit">Agregar</button>
                </td>
                <td>
                    <a href="#">Cancelar</a>
                </td>
            </tr>
        </table>
    </form>

</body>
</html>
