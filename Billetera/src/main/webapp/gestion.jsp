<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <link rel="stylesheet" href="assests/gestion.css" />
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
    </div>
  </body>
</html>
