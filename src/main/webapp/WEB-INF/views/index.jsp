<%--
  Created by IntelliJ IDEA.
  User: Leonardo
  Date: 15-08-2019
  Time: 17:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="ie=edge">
<title>Maipo Grande</title>
<link rel="stylesheet" href="\css\bootstrap.min.css">
<link rel="stylesheet" href="\css\styles.css">
<script src='https://kit.fontawesome.com/a076d05399.js'></script>
<script src="js/jquery-3.3.1.slim.min.js"></script>
<link rel="shortcut icon" type="image/png" href="img\icono-maipo.png" />
<script src="js/utilidades/index.js">
	
</script>
</head>
<body>
	<!-- HEADER -->
	<header class="py-0 bg-dark text-white-50">
		<div class="text-center py-1">
			<a href="#" class="text-light mr-4"><i
				class='fab fa-facebook-square'></i></a> <a href="#"
				class="text-light mr-4"><i class='fab fa-twitter-square'></i></a> <a
				href="#" class="text-light"><i class='fab fa-instagram'></i></a>
		</div>
	</header>
<script type="text/javascript">
	$(document).ready(function() {
		$("#alert").fadeOut(5000);
		});
	</script>
	<c:if test="${logout != null}">
		<div class="alert alert-warning alert-dismissible fade show text-right mb-0 alerta" id="alert" role="alert" data-autohide="true" data-delay="5000">
			<strong>${logout}</strong>
			<button type="button" class="close" data-dismiss="alert"
				aria-label="Close">
				<span aria-hidden="true">&times;</span>
			</button>
		</div>
	</c:if>

	<c:if test="${error != null}">
		<div class="toast" id="myToast" data-autohide="true" data-delay="5000"
			style="position: relative; float: right">
			<div class="toast-header"
				style="background-color: red; color: white;">
				<h5>
					<strong class="mr-auto">${error}</strong>
				</h5>
				<button type="button" class="ml-2 mb-1 close" data-dismiss="toast"
					style="width: 50px">
					<span aria-hidden="true">&times;</span>
				</button>
			</div>
		</div>
	</c:if>


	<!-- NAVBAR -->
	<nav
		class="navbar navbar-expand-lg navbar-light bg-lg sticky-top navbar-verde">
		<a class="navbar-brand" href="#"> <img src="img/logo-maipo.png"
			height="50" class="d-inline-block align-top" alt="">
		</a>
		<button class="navbar-toggler" type="button" data-toggle="collapse"
			data-target="#navbarSupportedContent"
			aria-controls="navbarSupportedContent" aria-expanded="false"
			aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>

		<div class="collapse navbar-collapse" id="navbarSupportedContent">
			<ul class="navbar-nav mr-auto">
			</ul>
			<form class="form-inline my-2 my-lg-0" action="/" method="post">
				<input class="form-control my-2 my-sm-0 mr-1 ml-1" type="text"
					placeholder="Usuario" name="username"> <input
					class="form-control my-2 my-sm-0 mr-1 ml-1" type="password"
					placeholder="Contraseña" name="password">
				<button class="btn btnes btn-success my-2 my-sm-0 mr-1 ml-1 letras"
					type="submit">Ingresar</button>
			</form>
		</div>
	</nav>
	<header class="sc-main">
		<!-- TEXTO O IMAGENES -->
		<div class="sc-header-content"></div>
	</header>
	<!-- FIN HEADER -->




	<!-- LOGO/PRESENTACION -->
	<section class="bg-light page-section pb-3" id="portfolio">
		<div class="">
			<div class="container">
				<div class="row">
					<div class="col-lg-12 text-center">
						<img class="d-block mx-auto mt-5 mb-3" src="img/logo-maipo-bg.png"
							alt="" height="110">
						<h3 class="section-subheading text-muted mb-5">¡Maipo Grande
							es la feria virtual mas grande del pais! En nuestra página se
							encuentran registradas las mayores empresas dedicadas a la
							producción de frutas de la zona metropolitana.</h3>
					</div>
				</div>
			</div>
			<div class="row">
				<div class="col-md-4 col-sm-6 portfolio-item">
					<a class="portfolio-link" data-toggle="modal"
						href="#portfolioModal1">
						<div class="portfolio-hover">
							<div class="portfolio-hover-content">
								<i class="fas fa-shopping-cart fa-3x"></i>
							</div>
						</div> <img class="img-fluid rounded" src="img/venta-1.jpg" alt="">
					</a>
					<div class="portfolio-caption">
						<h4>Venta de fruta</h4>
						<p class="text-muted">escribir</p>
					</div>
				</div>
				<div class="col-md-4 col-sm-6 portfolio-item">
					<a class="portfolio-link" data-toggle="modal"
						href="#portfolioModal2">
						<div class="portfolio-hover">
							<div class="portfolio-hover-content">
								<i class="fas fa-user fa-3x"></i>
							</div>
						</div> <img class="img-fluid rounded" src="img/venta-2.jpg" alt="">
					</a>
					<div class="portfolio-caption">
						<h4>Cuentas de usuario</h4>
						<p class="text-muted">escribir</p>
					</div>
				</div>
				<div class="col-md-4 col-sm-6 portfolio-item">
					<a class="portfolio-link" data-toggle="modal"
						href="#portfolioModal3">
						<div class="portfolio-hover">
							<div class="portfolio-hover-content">
								<i class="fas fa-lock fa-3x"></i>
							</div>
						</div> <img class="img-fluid rounded" src="img/venta-3.jpg" alt="">
					</a>
					<div class="portfolio-caption">
						<h4>Seguridad garantizada</h4>
						<p class="text-muted">escribir</p>
					</div>
				</div>
			</div>
		</div>
	</section>

	<!-- FIN LOGO/PRESENTACION -->

	<!-- FOOTER -->
	<footer class="py-2 bg-dark text-white-50">
		<div class="footer-copyright text-center py-3">
			© 2019 Copyright: <a href="#" class="text-success"> Quality
				Solution Team</a>
		</div>

	</footer>
	<!-- FIN FOOTER -->


	<script src="js/popper.min.js"></script>
	<script src="js/bootstrap.min.js"></script>
</body>
</html>
