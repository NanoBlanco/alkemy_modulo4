<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Inicio</title>
</head>
<body>
<h2>Bienvenido ${usuario.nombre}</h2>

<p>Correo: ${usuario.correo}</p>
<p>Rol: ${usuario.rol}</p>
<% java.util.Date d = new java.util.Date(); %>
<p><%= java.text.DateFormat.getDateInstance().format(d) %></p>
</body>
</html>