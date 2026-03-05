<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ include file="layout/header.jsp" %>
<h2>Producto</h2>
<form action="guardar" method="post">
<input type="hidden" name="id" value="${producto.id}"/>
<input name="nombre" value="${producto.nombre}" class="form-control mb-2" placeholder="Nombre"/>
<textarea name="descripcion" class="form-control mb-2">${producto.descripcion}</textarea>
<input name="precio" value="${producto.precio}" class="form-control mb-2" placeholder="Precio"/>
<input name="stock" value="${producto.stock}" class="form-control mb-2" placeholder="Stock"/>
<input name="stockMin" value="${producto.stockMin}" class="form-control mb-2" placeholder="Stock Min"/>
<select name="idCategoria" class="form-control mb-2">
<c:forEach var="c" items="${categorias}">
<option value="${c.id}" ${c.id==producto.idCategoria?'selected':''}>${c.nombre}</option>
</c:forEach>
</select>
<label><input type="checkbox" name="activo" ${producto.activo?'checked':''}/> Activo</label><br>
<button class="btn btn-success mt-2">Guardar</button>
</form>
<%@ include file="layout/footer.jsp" %>
