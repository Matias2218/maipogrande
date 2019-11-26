<%--
  Created by IntelliJ IDEA.
  User: Matias
  Date: 24-11-2019
  Time: 17:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <jsp:include page="layout/includes.jsp"></jsp:include>
    <title>Transportista Contrato</title>
</head>
<body>
<jsp:include page="layout/cabecera.jsp"/>
<div class="page-wrapper chiller-theme toggled">
    <jsp:include page="layout/sidebarTransportista.jsp"></jsp:include>
    <main class="page-content">
        <div class="container">
        <object data="https://apipdf.s3.us-east-2.amazonaws.com/${contrato.pdfContra}" type="application/pdf" width="800px" height="500px">
            <embed src="https://apipdf.s3.us-east-2.amazonaws.com/${contrato.pdfContra}" type="application/pdf">
            <p>Este navegador no soporta vista de pdfs. Porfavor descargar el pdf para verlo: <a href="https://apipdf.s3.us-east-2.amazonaws.com/${contrato.pdfContra}">Download PDF</a>.</p>
            </embed>
        </object>
        </div>
    </main>
</div>
</body>
</html>
