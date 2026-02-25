<%@ include file="header.jsp" %>
<c:set var="modo" value="${modo}" />
<c:set var="u" value="${usuarioEditar}" />
<div class="bg-light d-flex justify-content-center align-items-center"
    style="height: 90vh">
	<div
      class="card p-4"
      style="width: 50rem; box-shadow: 0 10px 25px rgba(0, 0, 0, 0.3)"
    >
      <h4 class="text-center mb-3">
      	<c:choose>
	        <c:when test="${modo == 'editar'}">
	            Editar Usuario
	        </c:when>
	        <c:otherwise>
	            Registrar Usuario
	        </c:otherwise>
    	</c:choose>
      </h4>
      <% if(request.getAttribute("errores") != null) { %>
      	<div class="alert alert-danger">
      		<ul>
      			<c:forEach items="${errores}" var="error">
      				<li>${error}</li>
      			</c:forEach>
      		</ul>
      	</div>
      <% } %>
     
      <form id="registrarForm" action="${modo == 'editar' ? 'editarPerfil' : 'registrar'}" method="post">
      	  <c:if test="${modo == 'editar'}">
    		<input type="hidden" name="id" value="${u.id}" />
    		<input type="hidden" name="rol" value ="${u.rol }" />
		  </c:if>
	      <div class="col-12 m-1">
	      <label class="form-label" for="nombre">Nombre: </label>
	        <input
	          class="form-control"
	          type="text"
	          id="nombre"
	          name="nombre"
	          placeholder="tu nombre"
	          autofocus
	          value="${u != null ? u.nombre : ''}"
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
	          value="${u != null ? u.correo : ''}"
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
	          placeholder="${modo == 'editar' ? 'Nueva clave (opcional)' : 'Clave de acceso'}"
	          value="${u != null ? u.clave : ''}"
	          autocomplete="off"
	        />
	      </div>
	      <div class="text-center text-danger m-2" id="errorMsgPass"></div>
	      <button type="submit" class="btn btn-primary btn-block mt-2">
    		<c:choose>
        		<c:when test="${modo == 'editar'}">
            		Actualizar
        		</c:when>
        		<c:otherwise>
            		Registrar
        		</c:otherwise>
    		</c:choose>
		  </button>
      </form>
    </div>
</div>
<script type="text/javascript" src="assests/registrar.js"></script>
<%@ include file="footer.jsp" %>