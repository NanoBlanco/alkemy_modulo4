<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ page import="java.util.List" %>
 <%@ page import="modelo.Usuario" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Usuarios</title>
</head>
<body>
<h2>Listado</h2>

<table>
	<tr>
		<th>Id</th>
		<th>Nombre</th>
		<th>Clave</th>
		<th>Correo</th>
	</tr>
	<% List<Usuario> lista = (List<Usuario>) request.getAttribute("usuarios");
		if (lista != null) {
			for(Usuario u: lista) {	
	%>
	<tr>
		<td><%= u.getId_usuario() %></td>
		<td><%= u.getNombre() %></td>
		<td><%= u.getClave() %></td>
		<td><%= u.getCorreo() %></td>
	</tr>
	<% 	}
	} %>
</table>

<a href="index.jsp">Volver</a>

<a href="/usuario" >Modificar</a>
</body>
</html>