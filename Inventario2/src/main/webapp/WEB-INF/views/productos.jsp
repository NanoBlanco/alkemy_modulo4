
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ include file="layout/header.jsp" %>
<h2>Productos</h2>
<a href="productos/nuevo" class="btn btn-primary mb-2">Nuevo</a>
<table class="table table-striped">
<tr><th>ID</th><th>Nombre</th><th>Precio</th><th>Acciones</th></tr>
<c:forEach var="p" items="${productos}">
<tr>
<td>${p.id}</td>
<td>${p.nombre}</td>
<td>${p.precio}</td>
<td>
<a href="productos/editar/${p.id}" class="btn btn-warning btn-sm">Editar</a>
<a href="productos/eliminar/${p.id}" class="btn btn-danger btn-sm">Eliminar</a>
</td>
</tr>
</c:forEach>
</table>
<%@ include file="layout/footer.jsp" %>
