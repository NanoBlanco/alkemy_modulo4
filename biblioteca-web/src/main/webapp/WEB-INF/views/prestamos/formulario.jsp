<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="es">
<head>
  <meta charset="UTF-8"><meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Nuevo Préstamo — Biblioteca Digital</title>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/app.css">
</head>
<body>
<%@ include file="../_nav.jsp" %>
<main class="main-content">
<%@ include file="../_flash.jsp" %>

<div class="page-header">
  <div>
    <h1>🔖 Nuevo Préstamo</h1>
    <p class="page-subtitle"><a href="${pageContext.request.contextPath}/prestamos/activos">← Volver a préstamos</a></p>
  </div>
</div>

<c:if test="${not empty error}">
  <div class="alert alert-error">❌ ${error}</div>
</c:if>

<div class="section-card form-card">

  <%-- Si hay libro preseleccionado, mostrar su info --%>
  <c:if test="${not empty libro}">
    <div class="info-box">
      <h3>📖 Libro seleccionado</h3>
      <p><strong>${libro.titulo}</strong> — ${libro.autor}</p>
      <p>ISBN: <code>${libro.isbn}</code> &nbsp;|&nbsp;
         Disponibles: <strong class="text-success">${libro.ejemplaresDisponibles}</strong>
         de ${libro.totalEjemplares}</p>
    </div>
  </c:if>

  <form method="post" action="${pageContext.request.contextPath}/prestamos/nuevo" class="form-grid">

    <%-- Libro: selector o hidden si ya está seleccionado --%>
    <c:choose>
      <c:when test="${not empty libroId}">
        <input type="hidden" name="libroId" value="${libroId}">
      </c:when>
      <c:otherwise>
        <div class="form-group form-full">
          <label for="libroId">Seleccionar Libro *</label>
          <select id="libroId" name="libroId" class="form-control" required>
            <option value="">-- Seleccionar libro disponible --</option>
            <c:forEach var="l" items="${libros}">
              <option value="${l.id}">
                ${l.titulo} — ${l.autor} (${l.ejemplaresDisponibles} disponibles)
              </option>
            </c:forEach>
          </select>
        </div>
      </c:otherwise>
    </c:choose>

    <div class="form-group">
      <label for="nombreUsuario">Nombre del Usuario *</label>
      <input type="text" id="nombreUsuario" name="nombreUsuario"
             class="form-control" placeholder="Nombre completo" required>
    </div>

    <div class="form-group">
      <label for="emailUsuario">Email del Usuario *</label>
      <input type="email" id="emailUsuario" name="emailUsuario"
             class="form-control" placeholder="correo@ejemplo.com" required>
    </div>

    <div class="info-box form-full">
      <p>ℹ️ El plazo de devolución es de <strong>15 días</strong> a partir de hoy.</p>
    </div>

    <div class="form-actions">
      <button type="submit" class="btn btn-primary">🔖 Registrar Préstamo</button>
      <a href="${pageContext.request.contextPath}/libros" class="btn btn-outline">Cancelar</a>
    </div>

  </form>
</div>
</main>
<%@ include file="../_footer.jsp" %>
</body>
</html>
