<jsp:include page="header.jsp" />
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
<jsp:include page="footer.jsp" />