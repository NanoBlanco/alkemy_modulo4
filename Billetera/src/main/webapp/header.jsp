<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
	<!-- Font Awesome -->
    <link
      rel="stylesheet"
      type="text/css"
      href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.1.1/css/all.min.css"
      integrity="sha512-KfkfwYDsLkIlwQp6LFnl8zNdLGxu9YAA1QvwINks4PhcElQSvqcyVLLD9aMhXd13uQjoXtEKNosOWaZqXgel0g=="
      crossorigin="anonymous"
      referrerpolicy="no-referrer"
    />
    <!--  BootStrap  -->
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-sRIl4kxILFvY47J16cr9ZwB07vP4J8+LH7qKQnuqkuIAvNWLzeN8tE5YBujZqJLB" crossorigin="anonymous">
	<link rel="stylesheet" href="assests/estilo.css" />
	<title>e-Wallet</title>
</head>
<body class="bg-light" >

<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
  <div class="container-fluid">
    <a class="navbar-brand" href="#">Billetera Digital</a>
    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarNav">
      <ul class="navbar-nav ms-auto">
        <li class="nav-item">
          <a class="nav-link active" aria-current="page" href="/Billetera/inicio">Home</a>
        </li>
        <c:choose>
        	<c:when test="${empty sessionScope.usuario}">
		        <li class="nav-item">
		          <a class="nav-link" href="/Billetera/login">Login</a>
		        </li>
        	</c:when>
        	<c:otherwise>
	        	<c:if test="${not empty sessionScope.usuario and sessionScope.usuario.rol == 'ADMIN'}">
			        <li class="nav-item">
			          <a class="nav-link" href="/Billetera/usuarios">Usuarios</a>
			        </li>
	        	</c:if>
	        	<c:if test="${not empty sessionScope.usuario and sessionScope.usuario.rol == 'USER'}">
			        <li class="nav-item dropdown">
			          <a class="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false">Transacciones</a>
			          <ul class="dropdown-menu">
	            			<li><a class="dropdown-item" href="/Billetera/deposito">Depositos</a></li>
	            			<li><a class="dropdown-item" href="#">Retiros</a></li>
	            			<li><a class="dropdown-item" href="#">Transferencias</a></li>
			          		<li><hr class="dropdown-divider"></li>
			          		<li><a class="dropdown-item" href="/Billetera/contactos">Contactos</a></li>
	            		</ul>
			        </li>
		        </c:if>
		        <li class="nav-item dropdown">
		        	<a class="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false">
            			<c:out value="${sessionScope.usuario.nombre}" />
          			</a>
          			<ul class="dropdown-menu">
            			<li><a class="dropdown-item" href="/Billetera/editarPerfil">Perfil</a></li>
		          		<li><hr class="dropdown-divider"></li>
		          		<li><a class="dropdown-item" href="/Billetera/logout">Cerrar sesión</a></li>
            		</ul>
		        </li>
        	</c:otherwise>
        </c:choose>
      </ul>
    </div>
  </div>
</nav> 