<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-sRIl4kxILFvY47J16cr9ZwB07vP4J8+LH7qKQnuqkuIAvNWLzeN8tE5YBujZqJLB" crossorigin="anonymous">
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
	          <a class="nav-link" href="/Billetera/logout">Logout</a>
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
<% } else { %>
<div class="container d-flex justify-content-center mt-5" style="height: 80vh">
	<img class="img-thumbnail" src="assests/digital-wallet.jpg" alt="Imagen de billetera digital" style="width: 30rem; height: 30rem;"/>
</div>
<% } %>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/js/bootstrap.bundle.min.js" integrity="sha384-FKyoEForCGlyvwx9Hj09JcYn3nv7wiPVlz7YYwJrWVcXK/BmnVDxM+D2scQbITxI" crossorigin="anonymous"></script>
<script>

	const formulario = document.querySelector("#loginForm");
	
	formulario.addEventListener("submit", function(event) {
		
		let isValid = true;
		
		const user = document.querySelector("#usuario");
		const pass = document.querySelector("#password");
		
		// Validar usuario
		const userRegex = /^[a-zA-Z0-9_]{5,20}$/;
		
		if(!userRegex.test(user.value)){
			user.classList.add('is-invalid');
			errorMsgUser.style.display = "block";
			errorMsgUser.innerText = "Debe ingresar un usuario válido";
			isValid = false;
		}else{
			user.classList.remove('is-invalid');
		}
		
		// Validar clave
		const passRegex = /^(?=.*[A-Z])(?=.*\d).{5,}$/;
		if(!passRegex.test(pass.value)) {
			pass.classList.add('is-invalid');
			errorMsgPass.style.display = "block";
			errorMsgPass.innerText = "Debe ingresar una clave válida";
	
			isValid = false;
		}else{
			pass.classList.remove('is-invalid');
		}
		
		if (!isValid) {
			event.preventDefault();
			event.stopPropagation();
		}
	});
	
	const user = document.querySelector("#usuario");
	const pass = document.querySelector("#password");
	
	user.addEventListener("input", function () {
		errorMsgUser.style.display = "none";
		user.classList.remove('is-invalid');
	});

	pass.addEventListener("input", function () {
		errorMsgPass.style.display = "none";
		pass.classList.remove('is-invalid');
	});
</script>
</body>
</html>