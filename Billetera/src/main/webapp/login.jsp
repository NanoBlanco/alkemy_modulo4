<%@ include file="header.jsp" %>
<div class="bg-light d-flex justify-content-center align-items-center"
    style="height: 90vh">
	<div
      class="card p-4"
      style="width: 350px; box-shadow: 0 10px 25px rgba(0, 0, 0, 0.3)"
    >
      <h4 class="text-center mb-3">Wallet Digital</h4>
      <% if (request.getAttribute("errores") != null) {%>
      	<div class="alert alert-danger">
      		<ul>
      			<c:forEach items="${errores}" var="err">
      				<li>${err}</li>
      			</c:forEach>
      		</ul>
      	</div>
      <% } %>
      <% if (request.getAttribute("error") == "credenciales") {%>
        <div class="alert alert-danger">
      		Usuario o contraseña incorrectos
      	</div>
      <% } %>
      <form id="loginForm" action="/Billetera/app?action=validarLogin" method="post">
	      <div class="input-group m-1">
	        <input
	          class="form-control"
	          type="email"
	          id="usuario"
	          name="user"
	          placeholder="correo@dominio"
	          autofocus
	          autocomplete="off"
	        />
	      </div>
	      <div class="text-center text-danger m-2" id="errorMsgUser"></div>
	      <div class="input-group m-1">
	        <input
	          class="form-control"
	          type="password"
	          id="password"
	          name="pass"
	          placeholder="Clave de acceso"
	          autocomplete="off"
	        />
	      </div>
	      <div class="text-center text-danger m-2" id="errorMsgPass"></div>
	      <div class="d-flex justify-content-between">
	      <div>
		      <button type="submit" class="btn btn-primary btn-block">
		        Ingresar
		      </button>
	      </div>
	      <div>
	      	<a href="/Billetera/app?action=nuevoRegistro" style="text-decoration: none">Registrarse</a>
	      </div>
	      </div>
      </form>
    </div>
</div>
<script type="text/javascript" src="assests/login.js"></script>
<%@ include file="footer.jsp" %>
