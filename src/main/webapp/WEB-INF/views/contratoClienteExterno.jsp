<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<title>Contrato</title>
<jsp:include page="layout/includes.jsp"></jsp:include>
</head>
<body>
	<jsp:include page="layout/cabecera.jsp" />
	<div class="page-wrapper chiller-theme toggled">
		<jsp:include page="layout/sidebarCliente.jsp" />
		<main class="page-content">
			<div class="container-fluid w-80 mt-2 pl-0 pr-0">
				<div class="row  ml-5 mr-5">
					<div class="col-lg pl-0 pr-0 mr-3 ml-3">
						<h3 class="letras text-center mb-4">Estado de Contrato</h3>

						<div class="alert alert-danger" role="alert">¡Su contrato esta cerca de la fecha de <strong>expiración</strong>!</div>

						<!-- 4:3 aspect ratio -->
						<div class="embed-responsive embed-responsive-4by3">
							<iframe class="embed-responsive-item"
								src="http://mozilla.github.io/pdf.js/web/viewer.html"></iframe>
						</div>

					</div>
				</div>
			</div>
		</main>
	</div>
</body>
</html>