<%--
  Created by IntelliJ IDEA.
  User: Leonardo
  Date: 29-08-2019
  Time: 0:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>
    <jsp:include page="layout/cabecera.jsp"/>
    <script>
        $(document).ready(function () {
            $("#myToast").toast('show');
            $('[name="btnEliminar"]').click(function () {
                var array = $(this).val().split(".");
                var id = array[0];
                var nombre = array[1];
                $('#lblNombre').text(nombre);
                $('#idProdu').val(id);
            });
        });
    </script>
    <title>Productos</title>
</head>
<body>
<c:if test="${alerta != null}">
    <div class="toast" id="myToast" data-autohide="true" data-delay="5000" style="position: relative; float: right">
        <div class="toast-header" style="background-color: orange; color: white;">
            <h5><strong class="mr-auto">${alerta}</strong></h5>
            <button type="button" class="ml-2 mb-1 close" data-dismiss="toast" style="width: 50px">
                <span aria-hidden="true">&times;</span>
            </button>
        </div>
    </div>
</c:if>
<div class="container-fluid">
    <div class="row">
        <div class="col-lg">
        <h1>Agregar Productos</h1>
        <a href="/añadirProducto">Agregar producto</a>
        <table class="table table-hover">
            <thead>
            <tr>
                <th scope="col"></th>
                <th scope="col">Nombre</th>
                <th scope="col">Fecha ingreso</th>
                <th scope="col">Stock</th>
                <th scope="col">Precio</th>
                <th scope="col">Tipo de venta</th>
                <th scope="col">Editar</th>
                <th scope="col">Eliminar</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach begin="0" end="${fn:length(productos)-1}" var="i">
                <tr>
                    <td><img style="width: 100px; height: 100px;" src="${imagenes.get(i)}" alt=""></td>
                    <td>${productos.get(i).nombreProdu}</td>
                    <td>${fechas.get(i)}</td>
                    <td>${productos.get(i).stockProdu}</td>
                    <td>${productos.get(i).precioProdu}</td>
                    <td>
                        <c:choose>
                            <c:when test="${productos.get(i).tipoComercializacionProdu eq 'I'.charAt(0)}">
                                Venta Interna
                            </c:when>
                            <c:otherwise>
                                Venta Externa
                            </c:otherwise>
                        </c:choose>
                    </td>
                    <td><button type="button" class="btn btn-link pt-0 pl-1" onclick="location.href='/productos/${productos.get(i).idProdu}';">Editar</button> </td>
                    <td>
                        <button type="button" name="btnEliminar"
                                value="${productos.get(i).idProdu}.${productos.get(i).nombreProdu}"
                                class="btn btn-link pt-0 pl-1" data-toggle="modal" data-target="#exampleModal">
                            Eliminar
                        </button>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
        </div>
        <div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
             aria-hidden="true">
            <div class="modal-dialog" role="document">
                <form action="/eliminarProducto" method="POST">
                    <input type="hidden" id="idProdu" name="idProdu">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title" id="exampleModalLabel">Eliminar</h5>
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                <span aria-hidden="true">&times;</span>
                            </button>
                        </div>
                        <div class="modal-body">
                            ¿Esta seguro que desea eliminar el producto <label id="lblNombre"></label>?
                        </div>
                        <div class="modal-footer">
                            <h1 id="test"></h1>
                            <button type="button" class="btn btn-secondary" data-dismiss="modal">Cancelar</button>
                            <button type="submit" class="btn btn-danger">Eliminar</button>
                        </div>
                    </div>
                </form>
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
        </div>
    </div>
    <!-- FIN FOOTER -->
</div>
<script src="js/jquery-3.3.1.slim.min.js"></script>
<script src="js/popper.min.js"></script>
<script src="js/bootstrap.min.js"></script>
</body>
</html>
