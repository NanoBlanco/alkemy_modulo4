<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<!--  BootStrap  -->
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-sRIl4kxILFvY47J16cr9ZwB07vP4J8+LH7qKQnuqkuIAvNWLzeN8tE5YBujZqJLB" crossorigin="anonymous">
<title>Agenda</title>
</head>
<body>
	<div class="container mt-5">
	<div class="card mt-5" style="width: 53rem;">
	  <div class="card-header d-flex justify-content-between">
		  <div>
		    <h3>Lista de Contactos</h3>
		  </div>
		  <div>
		  	<button class="btn btn-sm btn-primary"><a class="text-white" href="contacto?accion=nuevo" style="text-decoration: none;">Nuevo Contacto</a></button>
		  </div>
	  </div>
	  <div class="card-body">
		<table class="table table-striped">
	  		<thead class="table-dark">
	    		<tr>
	      			<th scope="col">#</th>
	      			<th scope="col">Nombre</th>
	      			<th scope="col">Correo</th>
	      			<th scope="col">Telefono</th>
	    		</tr>
	  		</thead>
	  		<tbody>
	  			<c:forEach var="c" items="${contactos}">
		    		<tr>
		      			<th scope="row">${c.id}</th>
		      			<td>${c.nombre}</td>
		      			<td>${c.correo}</td>
		      			<td>${c.telefono}</td>
		    		</tr>
	  			</c:forEach>
	    	</tbody>
		</table>
		</div>
	</div>
	</div>
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/js/bootstrap.bundle.min.js" 
integrity="sha384-FKyoEForCGlyvwx9Hj09JcYn3nv7wiPVlz7YYwJrWVcXK/BmnVDxM+D2scQbITxI" 
crossorigin="anonymous"></script>
</body>
</html>