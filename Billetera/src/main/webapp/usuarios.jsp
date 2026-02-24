<%@ include file="header.jsp" %>
<div class="contenedor mt-5">
	<h2>Panel de Administración de Usuarios</h2>

	<div class="card" style="width: 53rem;">
	  <div class="card-header d-flex justify-content-between">
	  <div>
	    Usuarios
	  </div>
	  <div>
	  	<button class="btn btn-sm btn-primary"><a class="text-white" href="/Billetera/agregar" style="text-decoration: none;">Nuevo Usuario</a></button>
	  </div>
	  </div>
	  <div class="card-body">
		<table class="table">
		  <thead>
		    <tr>
		      <th>#</th>
		      <th>Nombre</th>
		      <th>Correo</th>
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
			      <td>${usuario.rol}</td>
			      <td>
			      	<button type="button" class="btn btn-sm btn-primary"><a class="text-white" href="/Billetera/editarUsuario?id=${usuario.id}" style="text-decoration: none;">Editar</a></button>
			      	<button type="button" class="btn btn-sm btn-warning"><a class="text-blue" href="#" style="text-decoration: none;">Eliminar</a></button>
			      </td>
			    </tr>
		    </c:forEach>
		  </tbody>
		</table>
	  </div>
	</div>
</div>
<%@ include file="footer.jsp" %>