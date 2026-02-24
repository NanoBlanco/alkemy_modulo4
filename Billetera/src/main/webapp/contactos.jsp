<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
    <link rel="stylesheet" href="./assest/css/gestion.css" />
    <title>Gestión de Usuarios</title>
  </head>
  <body>
    <div class="container">
      <h2>Panel de Administración de Usuarios</h2>
      <div class="form-section">
        <div class="form-group">
          <label for="nombre">Nombre</label>
          <input
            type="text"
            name="nombre"
            id="nombre"
            placeholder="Ej. Juan Pérez"
          />
        </div>
        <div class="form-group">
          <label for="edad">Edad</label>
          <input type="number" name="edad" id="edad" placeholder="Ej: 25" />
        </div>
        <button type="button" id="btnAgregar">Registrar</button>
      </div>
      <table>
        <thead>
          <tr>
            <th>Nombre</th>
            <th>Edad</th>
            <th>Acciones</th>
          </tr>
        </thead>
        <tbody id="tablaUsuarios"></tbody>
      </table>
    </div>
    <script src="./assest/js/gestion.js"></script>
  </body>
</html>
