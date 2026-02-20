<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-sRIl4kxILFvY47J16cr9ZwB07vP4J8+LH7qKQnuqkuIAvNWLzeN8tE5YBujZqJLB" crossorigin="anonymous">
<title>e-Wallet</title>
</head>
<body>
<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
  <div class="container-fluid">
    <a class="navbar-brand" href="#">Billetera Digital</a>
    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarNav">
      <ul class="navbar-nav ms-auto">
        <li class="nav-item">
          <a class="nav-link active" aria-current="page" href="index.jsp?mostrar=inicio">Home</a>
        </li>
        
		<% if(session.getAttribute("usuario") == null)  {%>
	        <li class="nav-item">
	          <a class="nav-link" href="index.jsp?mostrar=login">Login</a>
	        </li>
        <%} else { %>
	        <li class="nav-item">
	          <a class="nav-link" href="/Billetera/usuarios">Usuarios</a>
	        </li>
	        <li class="nav-item">
	          <a class="nav-link" href="#">Transacciones</a>
	        </li>
	        <li class="nav-item">
	          <a class="nav-link" href="/Billetera/logout">Logout</a>
	        </li>
        <%} %>
      </ul>
    </div>
  </div>
</nav>
<div class="container mt-5">
	<div class="card" style="width: 60rem;">
	  <div class="card-header d-flex justify-content-between">
	  <div>
	    Usuarios
	  </div>
	  <div>
	  	<button class="btn btn-sm btn-primary"><a class="text-white" href="/Billetera/agregarUsuario" style="text-decoration: none;">Nuevo Usuario</a></button>
	  </div>
	  </div>
	  <div class="card-body">
		<table class="table">
		  <thead>
		    <tr>
		      <th>#</th>
		      <th>Nombre</th>
		      <th>Correo</th>
		      <th>Username</th>
		      <th>Rol</th>
		      <th>Acciones</th>
		    </tr>
		  </thead>
		  <tbody>
		  	<c:forEach var="usuario" items="${usuarios}">
			    <tr>
			      <th scope="row">${usuario.id}</th>
			      <td>${usuario.nombre}</td>
			      <td>${usuario.correo}</td>
			      <td>${usuario.username}</td>
			      <td>${usuario.rol}</td>
			      <td>
			      	<button type="button" class="btn btn-sm btn-primary">Editar</button>
			      	<button type="button" class="btn btn-sm btn-warning">Eliminar</button>
			      </td>
			    </tr>
		    </c:forEach>
		  </tbody>
		</table>
	  </div>
	</div>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/js/bootstrap.bundle.min.js" integrity="sha384-FKyoEForCGlyvwx9Hj09JcYn3nv7wiPVlz7YYwJrWVcXK/BmnVDxM+D2scQbITxI" crossorigin="anonymous"></script>

</body>
</html>