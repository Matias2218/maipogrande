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
    <jsp:include page="layout/includes.jsp"></jsp:include>
    <script src="/js/utilidades/mantenedorProducto.js"></script>
    <title>Editar Producto</title>
</head>
<body>
<jsp:include page="layout/cabecera.jsp"/>
<div class="page-wrapper chiller-theme toggled">
    <jsp:include page="layout/sidebar.jsp"></jsp:include>
    <main class="page-content">
        <div class="container-fluid">
            <div class="row">
                <div class="col-lg mt-4 mb-4">
                    <h3 class="letras text-center mb-4">Editar producto</h3>
                    <div class="card card-body">
                        <form:form method="POST" class="needs-validation"
                                   novalidate="novalidate" action="/editarProducto"
                                   enctype="multipart/form-data" modelAttribute="producto">

                            <div class="form-row">
                                <div class="form-group col-md-6">
                                    <form:label path="nombreProdu">Nombre</form:label>
                                    <form:input required="true" cssClass="form-control"
                                                id="txtNombre" path="nombreProdu"></form:input>
                                    <div class="invalid-feedback">Nombre obligatorio</div>
                                </div>
                                <div class="form-group col-md-6">
                                    <form:label path="precioProdu">Precio por Kg</form:label>
                                    <form:input path="precioProdu" required="true"
                                                onkeypress="return event.charCode >= 48 && event.charCode <= 57"
                                                cssClass="form-control" id="txtPrecio"></form:input>
                                    <div class="invalid-feedback">Precio obligatorio</div>
                                </div>
                            </div>

                            <div class="form-row">
                                <div class="form-group col-md-6">
                                    <form:label path="stockProdu">Stock</form:label>
                                    <div class="input-group">
                                        <form:input path="stockProdu" required="true"
                                                    onkeypress="return event.charCode >= 48 && event.charCode <= 57"
                                                    cssClass="form-control" id="txtStock"></form:input>
                                        <div class="input-group-prepend">
                                            <form:select path="unidadMasaProdu" required="true" class="form-control">
                                                <form:option value="">Tipo</form:option>
                                                <form:options items="${unidadesDeMasa}"></form:options>
                                            </form:select>
                                        </div>
                                    </div>
                                    <div class="invalid-feedback">Stock obligatorio</div>
                                </div>
                                <div class="form-group col-md-3">
                                    <div class="form-check-label">
                                        <form:label path="calidadProdu">Calidad del producto</form:label>
                                    </div>
                                    <div class="rate pl-0">
                                        <c:forEach begin="1" end="5" step="1" var="i">
                                            <form:radiobutton
                                                    checked="${6-i == producto.calidadProdu ? 'checked' : ''}"
                                                    path="calidadProdu" required="true" value="${6-i}"
                                                    label="${6-i} Estrellas"></form:radiobutton>
                                        </c:forEach>
                                    </div>
                                    <div class="invalid-feedback">Calidad obligatoria</div>
                                </div>
                                <div class="form-group col-md-3">
                                    <form:label path="tipoComercializacionProdu">Tipo Comercialización</form:label>
                                    <br>
                                    <div class="custom-control custom-radio custom-control-inline">
                                        <form:radiobutton path="tipoComercializacionProdu"
                                                          checked="${producto.tipoComercializacionProdu eq 'I'.charAt(0) ? 'checked':''}"
                                                          id="Interno" cssClass="custom-control-input" required="true"
                                                          value="I"></form:radiobutton>
                                        <label class="custom-control-label" for="Interno">Interno</label>
                                    </div>
                                    <div class="custom-control custom-radio custom-control-inline">
                                        <form:radiobutton path="tipoComercializacionProdu"
                                                          checked="${producto.tipoComercializacionProdu eq 'E'.charAt(0) ? 'checked':''}"
                                                          id="Externo" cssClass="custom-control-input" required="true"
                                                          value="E"></form:radiobutton>
                                        <label class="custom-control-label" for="Externo">Externo</label>
                                    </div>
                                </div>
                            </div>

                            <div class="form-group">
                                <label for="fileImagen" style="padding-right: 80%">Subir
                                    Imagen</label>
                                <div class="custom-file">
                                    <input type="file" class="custom-file-input" id="fileImagen"
                                           name="fileImagen" lang="es" accept=".png, .jpg, .jpeg">
                                    <label class="custom-file-label" id="lblFile" for="fileImagen">Seleccionar
                                        Archivo</label>
                                    <div class="invalid-feedback" id="imagenFeedBack">Imagen
                                        obligatoria</div>
                                </div>
                            </div>

                            <div class="form-group text-center">
                                <figure class="figure">
                                    <img id="imagenSalida" class="img-fluid img-thumbnail" style="max-height:300px;"  name="imagenSalida"
                                         src="${imagen}" alt="">
                                    <figcaption class="figure-caption">Vista previa</figcaption>
                                </figure>
                            </div>


                            <div class="form-group"></div>
                            <div class="btn-group btn-block" role="group"
                                 aria-label="Basic example">
                                <button type="submit" class="btn btn-success">Editar</button>
                                <button type="button" class="btn btn-danger"
                                        onclick="location.href='/productos';">Cancelar</button>
                            </div>
                        </form:form>
                    </div>
                </div>
            </div>
        </div>
    </main>
</div>
<jsp:include page="layout/modalCargando.jsp"></jsp:include>
</body>
</html>
