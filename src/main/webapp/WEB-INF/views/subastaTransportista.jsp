<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<jsp:include page="layout/cabecera.jsp" />
<title>Subasta Transportista</title>
    <script>
        (function () {
            'use strict';
            window.addEventListener('load', function () {
// Fetch all the forms we want to apply custom Bootstrap validation styles to
                var forms = document.getElementsByClassName('needs-validation');
// Loop over them and prevent submission
                var validation = Array.prototype.filter.call(forms, function (form) {
                    form.addEventListener('submit', function (event) {
                        if (form.checkValidity() === false) {
                            event.preventDefault();
                            event.stopPropagation();
                        } else {
                            $('#modalCargando').modal('show');
                        }
                        form.classList.add('was-validated');
                    }, false);
                });
            }, false);
        })();
    </script>
</head>
<body>
	<div class="page-wrapper chiller-theme toggled">
		<jsp:include page="layout/sidebarTransportista.jsp" />
		<form class="needs-validation" method="post" novalidate action="/subastaTransportista">
			<main class="page-content">
				<div class="container-fluid w-80 mt-2 pl-0 pr-0">
					<div class="row  ml-5 mr-5">
						<div class="col-lg pl-0 pr-0 mr-3 ml-3">
							<div class="container" style="max-width: 45rem;">
								<h3
									class="letras text-center jumbotron-heading font-weight-bold">Subasta
									N° ${venta.idVenta}</h3>

								<table class="table table-sm table-borderless">
									<tbody>
										<tr>
											<th colspan="2" class="lead text-uppercase texto-verde">Datos
												del cliente</th>
										</tr>
										<tr>
											<td class="lead"><strong>Nombre</strong></td>
											<td class="lead">${venta.solicitud.cliente.nombreCli} ${venta.solicitud.cliente.apellidosCli}</td>
											<td class="lead"><strong>Correo</strong></td>
											<td class="lead">${venta.solicitud.cliente.emailCli}</td>
										</tr>
										<tr>
											<th colspan="2" class="lead text-uppercase texto-verde">Datos
												de la venta</th>
										</tr>
										<tr>
											<td class="lead"><strong>Tipo de venta</strong></td>
											<td class="lead"><c:if test="${venta.tipoVenta eq 'E'.charAt(0)}">
												Venta Externa
											</c:if>
												<c:if test="${venta.tipoVenta eq 'I'.charAt(0)}">
													Venta Interna
												</c:if></td>
											<td class="lead"><strong>Fecha limite</strong></td>
											<td class="lead">${venta.solicitud.fechalimiteSol}</td>
										</tr>
										<tr>
											<td class="lead"><strong>Descripción</strong></td>
											<td colspan="3" class="lead">${venta.solicitud.descripcionSol}</td>
										</tr>
										<tr class="table-success">
											<td class="lead"><strong>Dirección</strong></td>
											<td colspan="3" class="lead">${venta.solicitud.direccionDestinoSol},${pais}</td>
										</tr>
									</tbody>
								</table>
							</div>
							<c:set var="kg" value="0"></c:set>
							<c:forEach items="${venta.solicitud.productoSolicitados}" var="p">
								<c:if test="${p.unidadProdS eq 'T'}">
									<c:set var="kg" value="${kg + p.cantidadProdS*1000}"></c:set>
								</c:if>
								<c:if test="${p.unidadProdS eq 'KG'}">
									<c:set var="kg" value="${kg + p.cantidadProdS}"></c:set>
								</c:if>
							</c:forEach>
                            <c:if test="${esAptoParaOfertar}">
							<div class="card mb-3">
								<div class="card-header text-uppercase">
									<table class="table table-sm table-borderless mb-0">
										<tr>
											<td class="text-left font-weight-bold">${venta.solicitud.direccionDestinoSol},${pais}</td>
											<td class="text-right font-weight-bold">${kg} Kg a
												transportar</td>
										</tr>
									</table>
								</div>

								<div class="card-body">
									<form class="form">
										<div class="form-group m-0">
											<label for="validationCustomUsername">Precio a ofrecer</label>
											<div class="input-group">
												<div class="input-group-prepend">
													<span class="input-group-text" id="inputGroupPrepend"><i
														class="fa fa-dollar-sign"></i></span>
												</div>
												<input type="text" class="form-control"
													   onkeypress="return event.charCode >= 48 && event.charCode <= 57"
													id="validationCustomUsername" name="txtPrecio"  placeholder="Precio"
													aria-describedby="inputGroupPrepend" required>
											</div>
										</div>
									</form>
								</div>

                            </div>

                                <input type="submit" value="Agregar oferta a subasta"
                                       id="btnAgregar" class="btn btn-block btn-lg mb-2"
                                       style="background-color: #FF5400; color: white;"/>
                                </c:if>
                                <c:if test="${!esAptoParaOfertar}">
                                <div class="card-body">
                                    <section class="page-section-one" id="portfolio">
                                        <div class="container">
                                            <div class="row" style="margin-right: 0px !important;">
                                                <div class="col-lg-12 text-center">
                                                    <h3 class="section-subheading text-muted mb-0">No cumple con los requisitos de la venta</h3>
                                                </div>
                                            </div>
                                        </div>
                                    </section>
                                </div>

                            </div>

                            <input type="submit" value="Agregar oferta a subasta"
                                   id="btnAgregar" class="btn btn-block btn-lg mb-2"
                                   style="background-color: #FF5400; color: white;" disabled/>
                                <!-- FIN DE DIV NO SE PUEDE -->
                                </c:if>
						</div>
					</div>
			</main>
		</form>
	</div>
	<jsp:include page="layout/modalCargando.jsp"></jsp:include>
</body>
</html>