<%--
  Created by IntelliJ IDEA.
  User: Leonardo
  Date: 18-08-2019
  Time: 20:44
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <jsp:include page="layout/cabecera.jsp"/>
    <title>Productor</title>
</head>
<body>
<div class="page-wrapper chiller-theme toggled">
    <jsp:include page="layout/sidebar.jsp"/>
    <form method="get" action="/productor">
        <div class="page-content">
            <div class="row">
                <!-- PUBLICACIONES -->
                <div class="col-lg">
                    <c:choose>
                        <c:when test="${ventas.size() < 1}">
                            no hay productos
                        </c:when>
                        <c:otherwise>
                            <c:forEach items="${ventas}" var="v">
                                <div class="pt-2 mb-4 border rounded text-center">
                                    <h4 class="letras">${v.solicitud.descripcionSol}</h4>
                                    <p class="lead">Tipo de venta:
                                        <c:if test="${v.tipoVenta eq 'E'.charAt(0)}">
                                            Externa
                                        </c:if>
                                        <c:if test="${v.tipoVenta eq 'I'.charAt(0)}">
                                            Interna
                                        </c:if>
                                        <br>
                                        Fecha de inicio: ${v.solicitud.fechaEmisionSol}<br>
                                        Fecha de termino: ${v.solicitud.fechalimiteSol}</p>
                                    <button type="button" onclick="window.location.href='subasta/${v.idVenta}'" class="btn btn-secondary btn-block letras">Ver detalle</button>
                                </div>
                            </c:forEach>
                        </c:otherwise>
                    </c:choose>
                    <div class="text-center">
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
                </div>
            </div>
</div>
    </form>
</body>
</html>
