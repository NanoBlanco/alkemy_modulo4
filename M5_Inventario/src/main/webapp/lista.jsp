<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib prefix="c" uri="jakarta.tags.core" %>
 <%@ taglib prefix="fmt" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Inventario</title>
</head>
<body>
	<a href="productos?accion=nuevo" class="btn btn-primary">+ Nuevo Producto</a>
	
	<c:if test="${not empty SessionScope.mensaje}">
		<div class="alerta exito">${sessionScope.mensaje}</div>
		<c:remove var="mensaje" scope="session" />
	</c:if>
	
	<c:if test="${not empty productoBajoStock}">
		<div class="alerta" style="background:#fff3cd;">${productoBajoStock.size()} producto(s) con bajo stock</div>
	</c:if>
	<table>
		<thead>
			<tr>#</tr>
			<tr>Nombre</tr>
			<tr>Categoria</tr>
			<tr>Precio</tr>
			<tr>Stock</tr>
			<tr>Acciones</tr>
		</thead>
		<tbody>
			<c:choose>
				<c:when test="${empty productos}">
					<tr>
						<td colspan="6" style="text-align: center;">No hay productos registrados</td>
					</tr>
				</c:when>
				<c:otherwise>
					<c:forEach var="p" items="${productos}" varStatus="s">
						<tr class="${p.stockBajo ? 'stock-bajo' : '' }">
							<td>${s.count}</td>
							<td>
								${p.nombre}
								<c:if test="${p.stockBajo}">
									<span styel="color:red;">&#9888 Stock Bajo</span>
								</c:if>
							</td>
							<td>${p.nombreCategoria}</td>
							<td>
								<fmt:formatNumber value="${p.precio}" type="currency" currencySymbol="$" maxFractionDigit="0" />
							</td>
							<td>${p.stock} / ${p.stockMin}</td>
							<td></td>
						</tr>
					</c:forEach>
				</c:otherwise>
			</c:choose>
		</tbody>
	</table>
</body>
</html>