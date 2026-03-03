<%@ include file="header.jsp" %>
<c:set var="modo" value="${modo}" />
<c:set var="c" value="${contactoEditar}" />
    <div class="contenedor mt-5">
      <h2>Panel de Administración de Contactos</h2>
      <form action="/Billetera/app?action=${modo == 'editar' ? 'actualizarContacto' : 'guardarContacto'}" method="post">
      	<c:if test="${modo == 'editar'}">
    		<input type="hidden" name="id" value="${c.id}" />
		</c:if>
      <div class="sectionContacto">
      <% if(request.getAttribute("errores") != null) { %>
      	<div class="alert alert-danger">
      		<ul>
      			<c:forEach items="${errores}" var="error">
      				<li>${error}</li>
      			</c:forEach>
      		</ul>
      	</div>
      <% } %>
        <div class="form-group-contacto">
          <label class="etiqueta" for="nombre">Nombre</label>
          <input
          	class="entrada"
            type="text"
            name="nombre"
            id="nombre"
            value="${c != null ? c.nombre : ''}"
            placeholder="Ej. Juan Perez"
          />
        </div>
        <div class="form-group-contacto">
          <label class="etiqueta" for="correo">Correo</label>
          <input class="entrada" type="email" name="correo" id="correo" value="${c != null ? c.correo : ''}" placeholder="Ej: correo@dominio.cl" />
        </div>
        <button type="submit" id="btnAgregar"><c:choose>
        		<c:when test="${modo == 'editar'}">
            		Actualizar
        		</c:when>
        		<c:otherwise>
            		Grabar
        		</c:otherwise>
    		</c:choose>
    	</button>
      </div>
      </form>
      <table>
        <thead>
          <tr>
            <th>Nombre</th>
            <th>Correo</th>
            <th>Acciones</th>
          </tr>
        </thead>
        <tbody id="tablaUsuarios">
        	<c:forEach var="contacto" items="${contactos}">
			    <tr>
			      <td>${contacto.nombre}</td>
			      <td>${contacto.correo}</td>
			      <td>
			      	<a class="btn btn-sm btn-primary" href="/Billetera/app?action=editarContacto&id=${contacto.id}" style="text-decoration: none;">Editar</a>
			      	<a class="btn btn-sm btn-warning" href="/Billetera/app?action=eliminarContacto&id=${contacto.id}" style="text-decoration: none;"
			      	onclick="return confirm('¿Eliminar ${contacto.nombre}?')">Eliminar</a>
			      </td>
			    </tr>
		    </c:forEach>
        </tbody>
      </table>
    </div>
<%@ include file="footer.jsp" %>
