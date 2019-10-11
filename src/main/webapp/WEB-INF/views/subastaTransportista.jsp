<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<jsp:include page="layout/cabecera.jsp" />
<title>Subasta Transportista</title>
</head>
<body>
	<div class="page-wrapper chiller-theme toggled">
		<jsp:include page="layout/sidebar.jsp" />
		<form class="needs-validation">
			<main class="page-content">
				<div class="container-fluid w-80 mt-2 pl-0 pr-0">
					<div class="row  ml-5 mr-5">
						<div class="col-lg pl-0 pr-0 mr-3 ml-3">
							<div class="container" style="max-width: 45rem;">
								<h3 class="letras text-center jumbotron-heading font-weight-bold">Subasta
									N° 001</h3>
									
								<table class="table table-sm table-borderless">
								<tbody>
								<tr>
									<th colspan="2" class="lead text-uppercase texto-verde">Datos del cliente</th>
								</tr>
								<tr>
									<td class="lead"><strong>Nombre</strong></td>
									<td class="lead">Nombre y Apellido Cliente</td>
									<td class="lead"><strong>Correo</strong></td>
									<td class="lead">correodelcliente@gmail.com</td>
								</tr>
								<tr>
									<th colspan="2" class="lead text-uppercase texto-verde">Datos de la venta</th>
								</tr>
								<tr>
									<td class="lead"><strong>Tipo de venta</strong></td>
									<td class="lead">00/00/0000</td>
									<td class="lead"><strong>Fecha limite</strong></td>
									<td class="lead">Fecha limite</td>
								</tr>
								<tr>
									<td class="lead"><strong>Descripción</strong></td>
									<td colspan="3" class="lead">Descripcion de la solicitud</td>
								</tr>
								<tr class="table-success">
									<td class="lead"><strong>Dirección</strong></td>
									<td colspan="3" class="lead">direccion de la venta, pais</td>
								</tr>
								</tbody>
							</table>
							
							
							</div>
						</div>
					</div>
				</div>
			</main>
		</form>
	</div>
</body>
</html>