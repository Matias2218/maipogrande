<%--
  Created by IntelliJ IDEA.
  User: Leonardo
  Date: 18-08-2019
  Time: 20:44
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<jsp:include page="layout/cabecera.jsp" />
<title>Productor</title>
</head>
<body>
	<div class="page-wrapper chiller-theme toggled">
		<jsp:include page="layout/sidebar.jsp" />
		<form method="get" action="/productor">
			<main class="page-content">
				<div class="row">
					<!-- PUBLICACIONES -->
					<div class="col-lg">
						<c:choose>
							<c:when test="${ventas.size() < 1}">
                            no hay productos
                        </c:when>
							<c:otherwise>
								<c:forEach items="${ventas}" var="v">
									<div class="card border-secondary mb-3">
										<div class="card-header text-uppercase text-center">
											<h4 class="letras mb-0">Venta N°${v.idVenta}</h4>
										</div>
										<div class="card-body py-3 px-3 text-center">
											<p class="lead mb-0">
											Tipo de venta:
											<c:if test="${v.tipoVenta eq 'E'.charAt(0)}">
                                            Externa
                                        </c:if>
											<c:if test="${v.tipoVenta eq 'I'.charAt(0)}">
                                            Interna
                                        </c:if>
											<br> Fecha de inicio: ${v.solicitud.fechaEmisionSol}<br>
											Fecha de termino: ${v.solicitud.fechalimiteSol}
										</p>
										</div>
										<div class="card-footer py-0 px-0">
											<button type="button"
											onclick="window.location.href='subasta/${v.idVenta}'"
											class="btn btn-secondary btn-block letras rounded-0 ">Ver
											detalle</button>
										</div>
									</div>



								</c:forEach>
							</c:otherwise>
						</c:choose>
						<div class="text-center">
							<nav aria-label="Page navigation example">
								<ul class="pagination justify-content-center">
									<c:if test="${paginaActual != 1}">
										<li class="page-item"><button type="submit" name="pagina"
												value="${paginaActual-1}" class="page-link paginador-item">&laquo;</button></li>
									</c:if>
									<c:forEach begin="${paginador.desde-1}"
										end="${paginador.hasta-1}" var="i">
										<c:choose>
											<c:when test="${paginaActual-1 eq i}">
												<li class="page-item active"><a class="page-link paginador-current"
													href="#">${i+1}<span class="sr-only">(current)</span></a></li>
											</c:when>
											<c:otherwise>
												<li class="page-item"><button type="submit"
														name="pagina" value="${i+1}" class="page-link paginador-item">${i+1}</button></li>
											</c:otherwise>
										</c:choose>
									</c:forEach>
									<c:if test="${paginaActual  < paginador.totalPaginas}">
										<li class="page-item"><button type="submit" name="pagina"
												value="${paginaActual+1}" class="page-link paginador-item">&raquo;</button></li>
									</c:if>
								</ul>
							</nav>
						</div>
					</div>
				</div>
			</main>
		</form>
</body>
</html>
