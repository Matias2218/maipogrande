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
		$(document).ready(()=> {
			function exit(e)
			{
				const divSinProductos = "<div class='container text-center'> <h5>No posee productos necesarios</h5> </div>";
				$('#' + e.currentTarget.id + '').parent().parent().children('.card-header.text-uppercase').append(divSinProductos);
			}
			function cargarProductos(e,num,response)
			{
				const productos = response.productos;
				$('#' + e.currentTarget.id + '').parent().parent().find('.d-flex.justify-content-center.mt-3').remove();
				if(productos.length === 0)
				{
					exit(e);
				}
				else {
					localStorage.setItem("items".concat(num[1]), JSON.stringify(productos));
					let productosCombo = "";
					productos.forEach((p) => {
						productosCombo += "<option value='" + p.idProdu + "'>" + p.nombreProdu + "</option>";
						return productosCombo;
					});
					const divAgregar = "<div class=\"card-body\">\n" +
							"<div class=\"row\">\n" +
							"<div class=\"col\">\n" +
							"<div class=\"form-group\">\n" +
							"<label>Producto a ofrecer</label> <select\n" +
							"class=\"custom-select browser-default\" id='cmbCancelar" + num[1] + "' name='cmbCancelar[]' required>\n" +
							"<option value=\"\" disabled selected>-Mis productos-</option>\n" +
							productosCombo +
							"</select>\n" +
							"</div>\n" +
							"</div>\n" +
							"<div class=\"col\">\n" +
							"<div class=\"form-group\">\n" +
							"<label>Disponible</label> <input name='txtDisponible' class=\"form-control\" \n" +
							"readonly='true' value=\"\" />\n" +
							"</div>\n" +
							"</div>\n" +
							"</div>\n" +
							"\n" +
							"<div class=\"form-group\">\n" +
							"<label>Precio a ofertar X Kg</label> <input\n" +
							"class=\"form-control\" type='number' required name='precioOfertar[]' />\n" +
							"</div>\n" +
							"</div>\n" +
							"<div class=\"card-footer py-0 px-0\">\n" +
							"<button type=\"button\"\n" +
							"class=\"btn btn-secondary rounded-0 btn-block\" id='btnCancelar" + num[1] + "' name='btnCancelar'>Cancelar</button>\n" +
							"</div>";
					const divPrincipal = $('#' + e.currentTarget.id + '').parent().parent();
					const divOfertar = $('#' + e.currentTarget.id + '').parent();
					divOfertar.remove();
					$(divPrincipal).append(divAgregar);
				}
			}
			$('[name="btnOfertar"]').click((e)=>
			{
				const num = e.currentTarget.id.split(/(\d+)/);
				const divCargando = "<div class=\"d-flex justify-content-center mt-3\">\n" +
						"  <div class=\"spinner-border\" role=\"status\">\n" +
						"    <span class=\"sr-only\">Loading...</span>\n" +
						"  </div>\n" +
						"</div>";
				$('#' + e.currentTarget.id + '').parent().parent().children('.card-header.text-uppercase').append(divCargando);
				$('#' + e.currentTarget.id + '').attr('disabled','disabled');
				$.ajax({
					type : 'POST',
					contentType: 'application/json; charset=utf-8',
					dataType: 'json',
					url: '/productosDisponibles',
					data: JSON.stringify({
						idProductoSolicitado:num[1]
					}),
					success : function (response) {
						cargarProductos(e,num,response);
					},
					error : function (e) {
						console.log(e);
					}
				});
			});
			$('body').on('click', '[name="btnOfertar"]', (e) => {
				const num = e.currentTarget.id.split(/(\d+)/);
				const divCargando = "<div class=\"d-flex justify-content-center mt-3\">\n" +
						"  <div class=\"spinner-border\" role=\"status\">\n" +
						"    <span class=\"sr-only\">Loading...</span>\n" +
						"  </div>\n" +
						"</div>";
				$('#' + e.currentTarget.id + '').parent().parent().children('.card-header.text-uppercase').append(divCargando);
				$('#' + e.currentTarget.id + '').attr('disabled','disabled');
				$.ajax({
					type : 'POST',
					contentType: 'application/json; charset=utf-8',
					dataType: 'json',
					url: '/productosDisponibles',
					data: JSON.stringify({
						idProductoSolicitado:num[1]
					}),
					success : function (response) {
						const productos = response.productos;
						if(productos.length === 0)
						{
							exit();
						}
						else {
					          cargarProductos(e,num,response)
						}
					},
					error : function (e) {
						console.log(e);
					}
				});
			});
			$(document).on('click', '[name="btnCancelar"]', (e) => {
				const num = e.currentTarget.id.split(/(\d+)/);
				const divAgregar = "<div class=\"card-body py-0 px-0\">\n" +
						"<button type=\"button\"  id='btnOfertar"+num[1]+"' name=\"btnOfertar\"  class=\"btn btn-success btn-block rounded-0\">O F E R T A R</button>\n" +
						"</div>\n"
				const divCancelar  = $('#'+e.currentTarget.id+'').parent();
				const divPadre = $('#'+e.currentTarget.id+'').parent().parent();
				const divBody  = $('#'+e.currentTarget.id+'').parent().parent().children('.card-body');
				divCancelar.remove();
				divBody.remove();
				$(divPadre).append(divAgregar);
			});

			$(document).on('change', '[name="cmbCancelar[]"]', (e) => {
				const cmb = e.currentTarget.id;
				const num = e.currentTarget.id.split(/(\d+)/);
				const idProd = $('#'.concat(cmb)).val();
				const productos = JSON.parse(localStorage.getItem('items'.concat(num[1])));
				const txtDisponible = $(e.currentTarget).parent().parent().parent().parent().find('[name="txtDisponible"]');

				console.log(txtDisponible);
				productos.forEach((producto) => {
					if(producto.idProdu == idProd)
					{
						txtDisponible.val(producto.stockProdu+" "+firstLetter(producto.unidadMasaProdu.toLowerCase()));
					}
				});

			});
			function firstLetter(s) {
				return s.replace(/^.{1}/g, s[0].toUpperCase());
			}
			$('#btnAgregar').click((e)=>{
				if(!$('[name="cmbCancelar[]"]').length)
				{
					e.preventDefault();
				}
			});
		});


	</script>
</head>
<body>
	<div class="page-wrapper chiller-theme toggled">
		<jsp:include page="layout/sidebar.jsp" />
		<form method="post" action="/subasta" novalidate class="needs-validation">
		<main class="page-content">
			<div class="container-fluid w-80 mt-2 pl-0 pr-0">
				<div class="row  ml-5 mr-5">
					<div class="col-lg pl-0 pr-0 mr-3 ml-3">
						<div class="container" style="max-width: 45rem;">
							<h3 class="letras text-center jumbotron-heading font-weight-bold">Subasta N°
								${venta.solicitud.idSol}</h3>

							<table class="table table-sm table-borderless">
								<tbody>
								<tr>
									<th colspan="2" class="lead text-uppercase texto-verde">Datos del cliente</th>
								</tr>
								<tr>
									<td class="lead"><strong>Nombre</strong></td>
									<td class="lead">${venta.solicitud.cliente.nombreCli} ${venta.solicitud.cliente.apellidosCli}</td>
									<td class="lead"><strong>Correo</strong></td>
									<td class="lead">${venta.solicitud.cliente.emailCli}</td>
								</tr>
								<tr>
									<th colspan="2" class="lead text-uppercase texto-verde">Datos de la venta</th>
								</tr>
								<tr>
									<td class="lead"><strong>Tipo de venta</strong></td>
									<td class="lead">
										<c:if test="${venta.tipoVenta eq 'E'.charAt(0)}">
											Venta Externa
										</c:if>
										<c:if test="${venta.tipoVenta eq 'I'.charAt(0)}">
											Venta Interna
										</c:if>

									</td>
									<td class="lead"><strong>Fecha limite</strong></td>
									<td class="lead">${venta.solicitud.fechalimiteSol}</td>
								</tr>
								<tr>
									<td class="lead"><strong>Dirección</strong></td>
									<td colspan="3" class="lead">${venta.solicitud.direccionDestinoSol}, ${pais}</td>
								</tr>
								<tr>
									<td class="lead"><strong>Descripción</strong></td>
									<td colspan="3" class="lead">${venta.solicitud.descripcionSol}</td>
								</tr>
								</tbody>
							</table>


						</div>
						<c:forEach items="${venta.solicitud.productoSolicitados}" var="p">
						<div class="card border-success mb-3">
							<div class="card-header text-uppercase">
								<table class="table table-sm table-borderless mb-0">
									<tr>
										<td class="text-left font-weight-bold">${p.nombreProdS}</td>
										<td class="text-right font-weight-bold">${p.cantidadProdS} ${p.unidadProdS}</td>
									</tr>
								</table>
							</div>
							<div class="card-body py-0 px-0">
								<button type="button"  id="btnOfertar${p.idProdS}" name="btnOfertar"  class="btn btn-success btn-block rounded-0">O F E R T A R</button>
							</div>

							<!-- Info a aparecer -->
							<!-- Info a aparecer -->
						</div>

						</c:forEach>

						<input type="submit"  value="Agregar oferta a subasta" id="btnAgregar" class="btn btn-block btn-lg mb-2" style="background-color: #FF5400; color: white;"/>
						<hr class="border border-secondary my-5">
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

							<div class="card-body text-cennter" >
								<table class="table table-sm table-borderless" style="max-width: 550px; margin: auto;">
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
		</form>
	</div>
<jsp:include page="layout/modalCargando.jsp"></jsp:include>
</body>
</html>
