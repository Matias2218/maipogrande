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
        <link href="https://use.fontawesome.com/releases/v5.0.6/css/all.css" rel="stylesheet">
        <script src="/js/jquery-3.3.1.min.js"></script>
        <link rel="shortcut icon" type="image/png" href="/img/icono-maipo.png"/>
        <script src="/js/utilidades/sidebar.js"></script>
        <link rel="stylesheet" href="/css/sidebar.css">
        <script src="/js/popper.min.js"></script>
            <script src="/js/bootstrap.min.js"></script>
        </head>
        <body>


        <nav class="navbar navbar-expand-lg navbar-light bg-lg sticky-top navbar-verde">
        <a class="navbar-brand" href="/productor">
        <img src="/img/logo-maipo.png" height="50" class="d-inline-block align-top" alt="">
        </a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent"
        aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav mr-auto">
        </ul>
        <div class="form-inline my-2 my-lg-0 float-right">
        <label class="letras my-2 my-sm-0 mr-3 ml-1">${nombre} ${apellido}</label>
        <button class="btn btn-success my-2 my-sm-0 mr-1 ml-1 letras btnes" type="submit"
        onclick="window.location.href='/logout'">Cerrar Sesion</button>
        </div>
        </div>
        </nav>
        </body>
        </html>
