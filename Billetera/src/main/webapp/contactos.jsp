<%@ include file="header.jsp" %>
    <div class="contenedor">
      <h2>Panel de Administración de Contactos</h2>
      <form action="contacto" method="post">
      <div class="form-section">
        <div class="form-group">
          <label class="etiqueta" for="nombre">Nombre</label>
          <input
          	class="entrada"
            type="text"
            name="nombre"
            id="nombre"
            placeholder="Ej. Juan Perez"
          />
        </div>
        <div class="form-group">
          <label class="etiqueta" for="correo">Correo</label>
          <input class="entrada" type="email" name="correo" id="correo" placeholder="Ej: correo@dominio.cl" />
        </div>
        <button type="submit" class="btn btn-sm btn-primary">Registrar</button>
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
        <tbody id="tablaUsuarios"></tbody>
      </table>
    </div>
<%@ include file="footer.jsp" %>
