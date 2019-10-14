<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<!DOCTYPE html>
<html>
<head>
<title>Detalle venta</title>
<jsp:include page="layout/cabecera.jsp" />
</head>
<body>
	<div class="page-wrapper chiller-theme toggled">
		<jsp:include page="layout/sidebar.jsp" />
		<main class="page-content">
			<div class="container-fluid w-80 mt-2 pl-0 pr-0">
				<div class="row  ml-5 mr-5">
					<div class="col-lg pl-0 pr-0 mr-3 ml-3">
						<section class="page-section-one pb-3" >
							<div class="container">
								<div class="row" style="margin-right: 0px !important;">
									<div class="col-lg-12 text-center">
										<h3
											class="letras text-center jumbotron-heading font-weight-bold">Venta
											N°1</h3>
										<h3 class="section-subheading text-muted">Estado:
											Aprovado</h3>
									</div>
								</div>
							</div>
						</section>

						<div class="list-group list-group-horizontal" id="list-tab"
							role="tablist">
							<a class="list-group-item list-group-item-action active"
								id="list-home-list" data-toggle="list" href="#list-home"
								role="tab" aria-controls="home">
								<div class="media">
									<div class="media-body">
										<strong class="titulo-media mb-0">SUBASTA I</strong>
										<p class="text-muted mb-0">Elección de productos</p>
									</div>
									<i class="fa fa-chevron-right align-self-center"></i>
								</div>
							</a> <a class="list-group-item list-group-item-action"
								id="list-profile-list" data-toggle="list" href="#list-profile"
								role="tab" aria-controls="profile">
								<div class="media">
									<div class="media-body">
										<strong class="titulo-media mb-0">SUBASTA II</strong>
										<p class="text-muted mb-0">Elección de transporte</p>
									</div>
									<i class="fa fa-chevron-right align-self-center"></i>
								</div>
							</a> <a class="list-group-item list-group-item-action"
								id="list-messages-list" data-toggle="list" href="#list-messages"
								role="tab" aria-controls="messages">
								<div class="media">
									<div class="media-body">
										<strong class="titulo-media mb-0">ENVÍO</strong>
										<p class="text-muted mb-0">Proceso de envio</p>
									</div>
									<i class="fa fa-chevron-right align-self-center"></i>
								</div>
							</a> <a class="list-group-item list-group-item-action"
								id="list-settings-list" data-toggle="list" href="#list-settings"
								role="tab" aria-controls="settings">
								<div class="media">
									<div class="media-body">
										<strong class="titulo-media mb-0">ORDEN FINALIZADA</strong>
										<p class="text-muted mb-0">Fin de la venta</p>
									</div>
									<i class="fa fa-chevron-right align-self-center"></i>
								</div>
							</a>
						</div>

						<div class="tab-content mt-3" id="nav-tabContent">
							<div class="tab-pane fade show active card" id="list-home"
								role="tabpanel" aria-labelledby="list-home-list">
								
								<div class="alert alert-warning mb-0" role="alert">
									<div class="container">
										Sus productos se encuentran <strong>en espera</strong> de ser confirmados.
									</div>
								</div>
								<div class="alert alert-success mb-0" role="alert">
									<div class="container">
										Sus productos han sido seleccionados, y pasaran al proceso de <strong>envio</strong>.
									</div>
								</div>
								
								
								<section class="page-section-one" id="portfolio">
									<div class="container">
										<div class="row">
											<div class="col-lg-12 py-3">
												<h5 class="letras text-left jumbotron-heading mb-0">ELECCION
													DE PRODUCTOS</h5>
												<h3 class="section-subheading text-muted mb-0">Sus productos ofertados todavia no han sido aceptados</h3>
											</div>
										</div>

										<!-- PRODUCTOS -->
										<div class="card mb-3">
											<div class="card-header text-uppercase">
												<table class="table table-sm table-borderless mb-0">
													<tr>
														<td class="text-left font-weight-bold">MANZANAS</td>
														<td class="text-right font-weight-bold">300 KG</td>
													</tr>
												</table>
											</div>
											<div class="card-body">
												<div class="row">
													<input type="hidden" name="idProds[]" value="22">
													<div class="col">
														<div class="form-group mb-0">
															<label>Producto ofrecido</label> 
															<input class="form-control" disabled value="Manzanas" style="background-color:white;">
														</div>
													</div>
													<div class="col">
														<div class="form-group mb-0">
															<label>Precio</label>
															<div class="input-group">
																<div class="input-group-prepend">
																	<span class="input-group-text" id="inputGroupPrepend"><i
																		class="fa fa-dollar-sign"></i></span>
																</div>
																<input type="text" class="form-control"
																	id="validationCustomUsername" placeholder="Precio"
																	aria-describedby="inputGroupPrepend" disabled style="background-color:white;" value="900 X KG">
															</div>
														</div>
													</div>
												</div>
											</div>
										</div>
										<!-- PRODUCTOS -->
										
									</div>
								</section>
							</div>

							<div class="tab-pane fade card" id="list-profile" role="tabpanel"
								aria-labelledby="list-profile-list">
								
								<div class="alert alert-warning mb-0" role="alert">
									<div class="container">
										El transporte se encuentra <strong>en espera</strong> de ser confirmado.
									</div>
								</div>
								
								<section class="page-section-one">
									<div class="container">
										<div class="row">
											<div class="col-lg-12 py-3">
												<h5 class="letras text-left jumbotron-heading mb-0">ELECCION
													DE TRANSPORTISTA</h5>
												<h3 class="section-subheading text-muted">Los productos</h3>
											</div>
											
											
										</div>
									</div>
								</section>
							</div>

							<div class="tab-pane fade card" id="list-messages"
								role="tabpanel" aria-labelledby="list-messages-list">
								<section class="page-section-one" id="portfolio">
									<div class="container">
										<div class="row">
											<div class="col-lg-12 py-3">
												<h5 class="letras text-left jumbotron-heading mb-0">ELECCION
													DE PRODUCTOS</h5>
												<h3 class="section-subheading text-muted">Los productos</h3>
											</div>
										</div>
									</div>
								</section>
							</div>

							<div class="tab-pane fade card" id="list-settings"
								role="tabpanel" aria-labelledby="list-settings-list">
								<section class="page-section-one" id="portfolio">
									<div class="container">
										<div class="row">
											<div class="col-lg-12 py-3">
												<h5 class="letras text-left jumbotron-heading mb-0">ELECCION
													DE PRODUCTOS</h5>
												<h3 class="section-subheading text-muted">Los productos</h3>
											</div>
										</div>
									</div>
								</section>
							</div>
						</div>
					</div>


				</div>
			</div>
		</main>
	</div>
</body>
</html>