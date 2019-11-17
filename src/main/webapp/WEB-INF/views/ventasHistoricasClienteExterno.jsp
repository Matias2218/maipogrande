<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<jsp:include page="layout/includes.jsp"></jsp:include>
<title>Ventas Historicas</title>
</head>
<body>
	<jsp:include page="layout/cabecera.jsp" />
	<div class="page-wrapper chiller-theme toggled">
		<jsp:include page="layout/sidebarCliente.jsp" />
		<main class="page-content">
			<div class="container-fluid w-80 mt-2 pl-0 pr-0">
				<div class="row  ml-5 mr-5">
					<div class="col-lg pl-0 pr-0 mr-3 ml-3">
						<h3 class="letras text-center mb-4">Ventas Historicas</h3>
					<div class="table-responsive">
						<table class="table table-bordered table-hover mb-0">
							<thead>
								<tr>
									<th scope="col">Fecha</th>
									<th scope="col">Venta</th>
									<th scope="col">Tipo de Venta</th>
									<th scope="col">Resolución</th>
									<th scope="col" class="text-right">Boleta</th>
								</tr>
							</thead>
							<tbody>
								<tr>
									<td>01/01/2019</td>
									<td>Venta N°001</td>
									<td>Venta Externa</td>
									<td>Aceptada</td>
									<td class="text-right"><a class="text-success h5" href="#"><i class="far fa-file-pdf"></i></a></td>
								</tr>
							</tbody>
						</table>
						<p class="text-right text-muted"><i class="far fa-file-pdf"></i>: Visualizar PDF</p>
					</div>
					</div>
				</div>
			</div>
		</main>
	</div>
</body>
</html>