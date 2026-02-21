<jsp:include page="header.jsp" />
    <div class="bg-light d-flex justify-content-center align-items-center"
    style="height: 90vh">
	<div
      class="card p-4"
      style="width: 50rem; box-shadow: 0 10px 25px rgba(0, 0, 0, 0.3)"
    >
      <h4 class="text-center mb-3">Registrar Usuario</h4>
      <% if(request.getAttribute("errores") != null) { %>
      	<div class="alert alert-danger">
      		<ul>
      			<c:forEach items="${errores}" var="error">
      				<li>${error}</li>
      			</c:forEach>
      		</ul>
      	</div>
      <% } %>
      <% if(request.getAttribute("exito") == "exito") {%>
      	<div class="alert alert-success">
      		Usuario Registrado!
      	</div>
      <%} %>
      <form id="registrarForm" action="registrar" method="post">
	      <div class="col-12 m-1">
	      <label class="form-label" for="nombre">Nombre: </label>
	        <input
	          class="form-control"
	          type="text"
	          id="nombre"
	          name="nombre"
	          placeholder="tu nombre"
	          autofocus
	          autocomplete="off"
	        />
	      </div>
	      <div class="text-center text-danger m-2" id="errorMsgNom"></div>

	      <div class="col-12 m-1">
	      	<label class="form-label" for="usuario">Usuario: </label>
	        <input
	          class="form-control"
	          type="text"
	          id="usuario"
	          name="user"
	          placeholder="tu usuario"
	          autofocus
	          autocomplete="off"
	        />
	      </div>
	      <div class="text-center text-danger m-2" id="errorMsgUser"></div>
	      <div class="col-12 m-1">
	      	<label class="form-label" for="password">Clave de acceso: </label>
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
	      <button type="submit" class="btn btn-primary btn-block mt-2">
	        Registrar
	      </button>
      </form>
    </div>
</div>
<script type="text/javascript" src="assests/registrar.js"></script>
<jsp:include page="footer.jsp" />