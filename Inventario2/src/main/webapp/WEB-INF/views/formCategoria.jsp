
<%@ include file="layout/header.jsp" %>
<h2>Categoría</h2>
<form action="guardar" method="post">
<input type="hidden" name="id" value="${categoria.id}"/>
<input name="nombre" value="${categoria.nombre}" class="form-control mb-2" placeholder="Nombre"/>
<textarea name="descripcion" class="form-control mb-2">${categoria.descripcion}</textarea>
<label><input type="checkbox" name="activo" ${categoria.activo?'checked':''}/> Activo</label><br>
<button class="btn btn-success mt-2">Guardar</button>
</form>
<%@ include file="layout/footer.jsp" %>
