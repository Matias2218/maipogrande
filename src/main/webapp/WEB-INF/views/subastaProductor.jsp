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
<title>Subasta</title>
</head>
<body>
	<div class="page-wrapper chiller-theme toggled">
		<jsp:include page="layout/sidebar.jsp" />
		<main class="page-content">
			<div class="container-fluid w-80 mt-2 pl-0 pr-0">
				<div class="row  ml-5 mr-5">
					<div class="col-lg pl-0 pr-0 mr-3 ml-3">
						<div class="container" style="max-width: 40rem;">
							<h3 class="letras text-center jumbotron-heading">Subasta N°
								0001</h3>
						</div>
						<div class="card border-success">
							<div class="card-header text-uppercase">
								<table class="table table-sm table-borderless mb-0">
									<tr>
										<td class="text-left font-weight-bold">Manzanas</td>
										<td class="text-right font-weight-bold">500 Kg</td>
									</tr>
								</table>

							</div>
							<!--
							<div class="card-body py-0 px-0">
								<button type="button" class="btn btn-success btn-block rounded-0">O F E R T A R</button>
							</div>
							
							-->
							<!-- Info a aparecer -->
							<div class="card-body">
								<div class="row">
									<div class="col">
										<div class="form-group">
											<label>Producto a ofrecer</label> <select
												class="form-control">
												<option value="" disabled selected>-Mis productos-</option>
												<option value="">Manzanas</option>
											</select>
										</div>
									</div>
									<div class="col">
										<div class="form-group">
											<label>Disponible</label> <input class="form-control"
												required name="" disabled value="600 Kg" />
										</div>
									</div>
								</div>

								<div class="form-group">
									<label>Precio a ofertar X Kg</label> <input
										class="form-control" required name="" />
								</div>
							</div>
							<div class="card-footer py-0 px-0">
								<button type="button"
									class="btn btn-secondary rounded-0 btn-block">Cancelar</button>
							</div>
							<!-- Info a aparecer -->
						</div>

						<hr style="background-color: grey;" class="mt-5 mb-5">

						<h5 class="letras jumbotron-heading">Mejores subastas</h5>
						<div class="card border-success">
							<div class="card-header text-uppercase">
								<table class="table table-sm table-borderless mb-0">
									<tr>
										<td class="text-left font-weight-bold">Manzanas</td>
										<td class="text-right text-warning py-0"><h3 class="mb-0">
												<i class="fas fa-trophy"></i>
											</h3></td>
									</tr>
								</table>
							</div>

							<div class="card-body text-cennter">
								<table class="table table-sm table-borderless" style="max-width: 30rem;">
									<thead>
										<tr>
											<th scope="col"></th>
											<th scope="col">Proveedor</th>
											<th scope="col" class="text-right">Precio X Kg</th>
										</tr>
									</thead>
									<tbody>
										<tr>
											<th scope="row" class="text-center">1</th>
											<td>Juanito Perez</td>
											<td class="text-right">$ 400</td>
										</tr>
										<tr>
											<th scope="row" class="text-center">2</th>
											<td>Juanito Perez</td>
											<td class="text-right">$ 420</td>
										</tr>
										<tr>
											<th scope="row" class="text-center">3</th>
											<td>Matias maldonado</td>
											<td class="text-right">$ 500</td>
										</tr>
									</tbody>
								</table>
							</div>
						</div>
					</div>
				</div>
			</div>
		</main>
	</div>
</body>
</html>
