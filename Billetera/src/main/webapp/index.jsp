<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-sRIl4kxILFvY47J16cr9ZwB07vP4J8+LH7qKQnuqkuIAvNWLzeN8tE5YBujZqJLB" crossorigin="anonymous">
<title>e-Wallet</title>
</head>
<body>

<nav class="navbar navbar-expand-lg bg-body-tertiary">
  <div class="container-fluid">
    <a class="navbar-brand" href="#">Billetera Digital</a>
    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarNav">
      <ul class="navbar-nav ms-auto">
        <li class="nav-item">
          <a class="nav-link active" aria-current="page" href="index.jsp?mostrar=inicio">Home</a>
        </li>
        
		<% if(session.getAttribute("usuario") == null)  {%>
	        <li class="nav-item">
	          <a class="nav-link" href="index.jsp?mostrar=login">Login</a>
	        </li>
        <%} else { %>
	        <li class="nav-item">
	          <a class="nav-link" href="/Billetera/usuarios">Usuarios</a>
	        </li>
	        <li class="nav-item">
	          <a class="nav-link" href="#">Transacciones</a>
	        </li>
	        <li class="nav-item">
	          <a class="nav-link" href="index.jsp?mostrar=login">Logout</a>
	        </li>
        <%} %>
      </ul>
    </div>
  </div>
</nav>


<%
String mostrar = request.getParameter("mostrar");
if("login".equals(mostrar)) {
%>
<jsp:include page="login.jsp" />
<% } else if("inicio".equals(mostrar)) { %>
<div class="container d-flex justify-content-center mt-5">
	<img class="img-thumbnail" src="assests/digital-wallet.jpg" alt="Imagen de billetera digital" style="widht: 30rem; height: 30rem;"/>
</div>
<% } else if("users".equals(mostrar)) { %>
<jsp:include page="usuarios.jsp" />
<% } %>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/js/bootstrap.bundle.min.js" integrity="sha384-FKyoEForCGlyvwx9Hj09JcYn3nv7wiPVlz7YYwJrWVcXK/BmnVDxM+D2scQbITxI" crossorigin="anonymous"></script>
</body>
</html>