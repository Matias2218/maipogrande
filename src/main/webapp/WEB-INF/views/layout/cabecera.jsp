<%--
  Created by IntelliJ IDEA.
  User: Leonardo
  Date: 01-09-2019
  Time: 13:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="/css/bootstrap.min.css">
    <link rel="stylesheet" href="\css\styles.css">
    <script src='https://kit.fontawesome.com/a076d05399.js'></script>
    <script src="/js/jquery-3.3.1.slim.min.js"></script>
    <link rel="shortcut icon" type="image/png" href="/img/icono-maipo.png"/>
</head>
<body>
    <script src="/js/bootstrap.min.js"></script>
    <script src="/js/popper.min.js"></script>

<nav class="navbar navbar-expand-lg navbar-light bg-lg sticky-top navbar-verde">
    <a class="navbar-brand" href="#">
        <a href="/productor"><img src="/img/logo-maipo.png" height="50" class="d-inline-block align-top" alt=""></a>
    </a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent"
            aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav mr-auto">
        </ul>
         <label class="letras my-2 my-sm-0 mr-3 ml-1">${nombre}</label>
        <button class="btn btnes btn-success my-2 my-sm-0 mr-1 ml-1 letras float-sm-right" onclick="location.href='/logout';" type="button">Cerrar Sesion</button>
    </div>
</nav>
</body>
</html>
