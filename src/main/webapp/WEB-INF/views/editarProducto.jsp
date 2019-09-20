<%--
  Created by IntelliJ IDEA.
  User: Leonardo
  Date: 29-08-2019
  Time: 22:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <jsp:include page="layout/cabecera.jsp" />
    <script src="/js/utilidades/mantenedorProducto.js"></script>
    <title>Editar Producto</title>
</head>
<body>
<div class="container-fluid">
    <div class="row">
        <div class="col-lg mt-4 mb-4">
            <h3 class="letras text-center mb-4">Editar producto</h3>
            <div class="card card-body">
                <form:form method="POST" class="needs-validation" novalidate="novalidate" action="/editarProducto"
                      enctype="multipart/form-data" modelAttribute="producto">
                    <div class="form-group">
                        <form:label path="nombreProdu">Nombre</form:label>
                        <form:input required="true" cssClass="form-control" id="txtNombre"
                                    path="nombreProdu"></form:input>
                        <div class="invalid-feedback">Nombre obligatorio</div>
                    </div>
                    <div class="form-group">
                        <form:label path="precioProdu">Precio</form:label>
                        <form:input path="precioProdu" required="true"
                                    onkeypress="return event.charCode >= 48 && event.charCode <= 57"
                                    cssClass="form-control" id="txtPrecio"></form:input>
                        <div class="invalid-feedback">Precio obligatorio</div>
                    </div>
                    <div class="form-group">
                        <form:label path="stockProdu">Stock</form:label>
                        <div class="input-group">
                            <form:input path="stockProdu" required="true"
                                        onkeypress="return event.charCode >= 48 && event.charCode <= 57"
                                        cssClass="form-control" id="txtStock"></form:input>
                            <div class="input-group-prepend">
                                <span class="input-group-text">Kg</span>
                            </div>
                        </div>
                        <div class="invalid-feedback">Stock obligatorio</div>
                    </div>
                    <div class="form-group">
                        <div class="form-check-label">
                            <form:label path="calidadProdu">Calidad del producto</form:label>
                        </div>
                        <div class="rate pl-0">
                            <c:forEach begin="1" end="5" step="1"  var="i">
                                <form:radiobutton checked="${6-i == producto.calidadProdu ? 'checked' : ''}" path="calidadProdu" required="true" value="${6-i}"
                                                  label="${6-i} Estrellas"></form:radiobutton>
                            </c:forEach>
                        </div>
                        <div class="invalid-feedback">Calidad obligatoria</div>
                    </div>
                    <br>
                    <div class="form-group">
                        <label for="fileImagen" style="padding-right: 80%">Subir Imagen</label>
                        <div class="custom-file">
                            <input type="file" class="custom-file-input" id="fileImagen" name="fileImagen"
                                   lang="es" accept=".png, .jpg, .jpeg">
                            <label class="custom-file-label"  id="lblFile" for="fileImagen">Seleccionar Archivo</label>
                            <div class="invalid-feedback" id="imagenFeedBack">Imagen obligatoria</div>
                        </div>
                    </div>
                    <div class="form-group">
                        <img id="imagenSalida" name="imagenSalida" class="img-thumbnail h-25 w-25" src="${imagen}" alt="">
                    </div>
                            <div class="form-group">
                                <form:label path="tipoComercializacionProdu">Tipo Comercialización</form:label>
                                <br>
                                <div class="custom-control custom-radio custom-control-inline">
                                    <form:radiobutton path="tipoComercializacionProdu" checked="${producto.tipoComercializacionProdu eq 'I'.charAt(0) ? 'checked':''}" id="Interno"
                                                      cssClass="custom-control-input" required="true"
                                                      value="I"></form:radiobutton>
                                    <label class="custom-control-label" for="Interno">Interno</label>
                                </div>
                                <div class="custom-control custom-radio custom-control-inline">
                                    <form:radiobutton path="tipoComercializacionProdu" checked="${producto.tipoComercializacionProdu eq 'E'.charAt(0) ? 'checked':''}"
                                                      id="Externo"
                                                      cssClass="custom-control-input" required="true"
                                                      value="E"></form:radiobutton>
                                    <label class="custom-control-label" for="Externo">Externo</label>
                                </div>
                            </div>
                    <div class="btn-group btn-block" role="group" aria-label="Basic example">
                        <button type="submit" class="btn btn-success">Editar</button>
                        <button type="button" class="btn btn-danger" onclick="location.href='/productos';">Cancelar</button>
                    </div>
                </form:form>
            </div>
        </div>
        <div class="col-sm-12 col-lg-3 pr-0 pl-0 pt-0 text-center menu">
            <div class="w-100 div-menu-sin-hover">
                <h1 class="text-light carrito mt-4"><i class="fas fa-shopping-cart"></i></h1>
                <p class="letras mt-3">3 Ventas en proceso</p>
            </div>
            <a style="text-decoration: none" href="/">
                <div class="w-100 div-menu border-top border-white">
                    <p class="letras mt-4">Mi Perfil</p>
                </div>
            </a>
            <a style="text-decoration: none" href="/productos">
                <div class="w-100 div-menu border-top border-bottom border-white">
                    <p class="letras mt-4">Mis Productos</p>
                </div>
            </a>
            <div class="w-100 div-menu border-top border-bottom border-white">
                <p class="letras mt-4">Mis Ventas</p>
            </div>
            <!-- FOOTER -->
            <footer class="py-2 text-white-50">
                <div class="footer-copyright text-center py-3">© 2019 Copyright:
                    <a href="#" class="text-success"> Quality Solution Team</a>
                </div>
            </footer>
    </div>
</div>
</body>
</html>
