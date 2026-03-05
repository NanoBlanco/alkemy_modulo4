
<%@ include file="layout/header.jsp" %>
<h2>Categorías</h2>
<a href="categorias/nuevo" class="btn btn-primary mb-2">Nueva</a>
<table class="table table-bordered">
<tr><th>ID</th><th>Nombre</th><th>Acciones</th></tr>
<c:forEach var="c" items="${categorias}">
<tr>
<td>${c.id}</td>
<td>${c.nombre}</td>
<td>
<a href="categorias/editar/${c.id}" class="btn btn-warning btn-sm">Editar</a>
<a href="categorias/eliminar/${c.id}" class="btn btn-danger btn-sm">Eliminar</a>
</td>
</tr>
</c:forEach>
</table>
<%@ include file="layout/footer.jsp" %>
