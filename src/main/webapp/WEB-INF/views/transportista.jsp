<%--
  Created by IntelliJ IDEA.
  User: Leonardo
  Date: 18-08-2019
  Time: 20:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <jsp:include page="layout/cabecera.jsp" />
    <title>Transportista</title>
</head>
<body>
<div class="page-wrapper chiller-theme toggled">
    <jsp:include page="layout/sidebarTransportista.jsp" />
    <main class="page-content">
        <div class="container" style="max-width: 45rem;">
            <h2 class="letras text-center jumbotron-heading font-weight-bold">Transportista</h2>
			<button type="button" onclick="window.location.href='subastaTransportista'"
                        class="btn btn-secondary btn-block letras">Subasta de transporte N°11</button>
        </div>

    </main>
</div>
</body>
</html>
