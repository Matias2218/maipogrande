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
    <script src="/js/utilidades/mantenedorProducto.js"></script>
    <script src="/js/utilidades/index.js"></script>
    <title>Productos</title>
</head>
<body>
<div class="page-wrapper chiller-theme toggled">
    <jsp:include page="layout/sidebar.jsp"></jsp:include>
    <main class="page-content">
        <div class="container-fluid mt-4">
            <div class="row  ml-4 mr-4">
                <div class="col-lg">
                    <c:if test="${alerta != null}">
                        <div class="toast" id="myToast" data-autohide="true" data-delay="5000"
                             style="position: relative;">
                            <div class="toast-header" style="background-color: orange; color: white;">
                                <h5><strong class="mr-auto">${alerta}</strong></h5>
                                <button type="button" class="ml-2 mb-1 close" data-dismiss="toast" style="width: 50px">
                                    <span aria-hidden="true">&times;</span>
                                </button>
                            </div>
                        </div>
                        <br>
                    </c:if>
                    <h3 class="letras text-center mb-4">Productos</h3>
                    <form action="/productos" method="get">

                        <div class="row justify-content-between">
                            <div class="col-7 form-inline">
                                <input type="text" name="txtBuscar" id="txtBuscar" class="form-control mr-2"
                                       placeholder="Ingrese producto a buscar">
                                <button type="submit" id="btnBuscar" class="btn btn-success ">Buscar</button>
                            </div>
                            <div class="col-5 text-right">
                                <button type="button" class="btn btn-success"
                                        onclick="window.location.href='/añadirProducto'">Agregar Producto
                                </button>
                            </div>
                        </div>
                        <c:choose>
                        <c:when test="${fn:length(productos) < 1 }">
                        <div class="alert alert-warning mt-2" role="alert">
                            No hay productos
                        </div>
                        </c:when>
                        <c:otherwise>
                        <div class="table-responsive mt-2">
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
                                        <td><img style="width: 100px; height: 100px;" src="${imagenes.get(i)}" alt="">
                                        </td>
                                        <td>${productos.get(i).nombreProdu}</td>
                                        <td>${fechas.get(i)}</td>
                                        <td>${productos.get(i).stockProdu}${productos.get(i).unidadMasaProdu}</td>
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
                                        <td>
                                            <button type="button" class="btn btn-link pt-0 pl-1"
                                                    onclick="location.href='/productos/${productos.get(i).idProdu}';">
                                                Editar
                                            </button>
                                        </td>
                                        <td>
                                            <button type="button" name="btnEliminar"
                                                    value="${productos.get(i).idProdu}.${productos.get(i).nombreProdu}"
                                                    class="btn btn-link pt-0 pl-1" data-toggle="modal"
                                                    data-target="#exampleModal">
                                                Eliminar
                                            </button>
                                        </td>
                                    </tr>
                                </c:forEach>
                                </tbody>
                            </table>
                        </div>
                        </c:otherwise>
                        </c:choose>
                        <nav aria-label="Page navigation example">
                            <ul class="pagination">
                                <c:if test="${paginaActual != 1}">
                                    <li class="page-item"><button type="submit" name="pagina" value="${paginaActual-1}" class="page-link">&laquo;</button></li>
                                </c:if>
                                <c:forEach begin="${paginador.desde-1}" end="${paginador.hasta-1}" var="i">
                                    <c:choose>
                                        <c:when test="${paginaActual-1 eq i}">
                                            <li class="page-item active">
                                                <a class="page-link" href="#">${i+1}<span class="sr-only">(current)</span></a>
                                            </li>
                                        </c:when>
                                        <c:otherwise>
                                            <li class="page-item"><button type="submit" name="pagina" value="${i+1}" class="page-link">${i+1}</button></li>
                                        </c:otherwise>
                                    </c:choose>
                                </c:forEach>
                                <c:if test="${paginaActual  < paginador.totalPaginas}">
                                    <li class="page-item"><button type="submit" name="pagina" value="${paginaActual+1}" class="page-link">&raquo;</button></li>
                                </c:if>
                            </ul>
                        </nav>
                </div>
                </form>
                <div class="modal fade" id="exampleModal" tabindex="-1" role="dialog"
                     aria-labelledby="exampleModalLabel"
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
                                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Cancelar
                                    </button>
                                    <button type="submit" id="btnConfirmar" class="btn btn-danger">Eliminar</button>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </main>
</div>
<jsp:include page="layout/modalCargando.jsp"></jsp:include>
</body>
</html>
