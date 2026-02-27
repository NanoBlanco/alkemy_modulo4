<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Agenda</title>
<!--  BootStrap  -->
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-sRIl4kxILFvY47J16cr9ZwB07vP4J8+LH7qKQnuqkuIAvNWLzeN8tE5YBujZqJLB" crossorigin="anonymous">
</head>
<body>
<c:set var="modo" value="${modo}" />
<c:set var="c" value="${contacto}" />
    <div class="bg-light d-flex justify-content-center align-items-center"
    style="height: 90vh">
	<div
      class="card p-4"
      style="width: 50rem; box-shadow: 0 10px 25px rgba(0, 0, 0, 0.3)"
    >
      <h4 class="text-center mb-3"><c:choose>
	        <c:when test="${modo == 'editar'}">
	            Editar Contacto
	        </c:when>
	        <c:otherwise>
	            Registrar Contacto
	        </c:otherwise>
    	</c:choose>
      </h4>
      
      <form id="registrarForm" action="${modo == 'editar' ? 'app?action=actualizar' : 'app?action=guardar'}" method="post">
      	  <c:if test="${modo == 'editar'}">
    		<input type="hidden" name="id" value="${c.id}" />
		  </c:if>
	      <div class="col-12 m-1">
		      <label class="form-label" for="nombre">Nombre: </label>
		        <input
		          class="form-control"
		          type="text"
		          name="nombre"
		          placeholder="tu nombre"
		          autofocus
		          value="${c != null ? c.nombre : ''}"
		          autocomplete="off"
		        />
		  </div>

	      <div class="col-12 m-1">
	      	<label class="form-label" for="correo">Correo: </label>
	        <input
	          class="form-control"
	          type="email"
	          name="correo"
	          placeholder="Ej. correo@dominio.cl"
	          value="${c != null ? c.correo : ''}"
	          autocomplete="off"
	        />
	      </div>

	      <div class="col-12 m-1">
	      	<label class="form-label" for="telefono">Telefono: </label>
	        <input
	          class="form-control"
	          type="text"
	          name="telefono"
	          placeholder="Ej X-XXXX XXXX";
	          value="${c != null ? c.telefono : ''}"
	          autocomplete="off"
	        />
	      </div>
	      <button type="submit" class="btn btn-primary btn-block mt-2">
	        <c:choose>
        		<c:when test="${modo == 'editar'}">
            		Actualizar
        		</c:when>
        		<c:otherwise>
            		Grabar
        		</c:otherwise>
    		</c:choose>
	      </button>
      </form>
    </div>
</div>
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/js/bootstrap.bundle.min.js" 
integrity="sha384-FKyoEForCGlyvwx9Hj09JcYn3nv7wiPVlz7YYwJrWVcXK/BmnVDxM+D2scQbITxI" 
crossorigin="anonymous"></script>
</body>
</html>