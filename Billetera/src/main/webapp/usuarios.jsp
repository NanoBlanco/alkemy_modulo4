<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-sRIl4kxILFvY47J16cr9ZwB07vP4J8+LH7qKQnuqkuIAvNWLzeN8tE5YBujZqJLB" crossorigin="anonymous">
<title>e-Wallet</title>
</head>
<body>
<div class="container d-flex justify-content-center align-items-center mt-5">
	<div class="card">
	  <div class="card-header">
	    Usuarios
	  </div>
	  <div class="card-body">
		<table class="table">
		  <thead>
		    <tr>
		      <th scope="col">#</th>
		      <th scope="col">Nombre</th>
		      <th scope="col">Correo</th>
		      <th scope="col">Username</th>
		      <th scope="col">Rol</th>
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