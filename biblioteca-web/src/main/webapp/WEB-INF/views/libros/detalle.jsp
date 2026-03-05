<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="es">
<head>
  <meta charset="UTF-8"><meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>${libro.titulo} — Biblioteca Digital</title>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/app.css">
</head>
<body>
<%@ include file="../_nav.jsp" %>
<main class="main-content">
<%@ include file="../_flash.jsp" %>

<div class="page-header">
  <div>
    <h1>📖 ${libro.titulo}</h1>
    <p class="page-subtitle"><a href="${pageContext.request.contextPath}/libros">← Volver al catálogo</a></p>
  </div>
  <div class="btn-group">
    <a href="${pageContext.request.contextPath}/libros/editar?id=${libro.id}" class="btn btn-secondary">✏️ Editar</a>
    <c:if test="${libro.disponible()}">
      <a href="${pageContext.request.contextPath}/prestamos/nuevo?libroId=${libro.id}"
         class="btn btn-primary">🔖 Prestar</a>
    </c:if>
  </div>
</div>

<div class="detail-grid">

  <div class="section-card detail-info">
    <h2 class="section-title">Información del Libro</h2>
    <dl class="detail-list">
      <dt>ISBN</dt>          <dd><code>${libro.isbn}</code></dd>
      <dt>Título</dt>        <dd>${libro.titulo}</dd>
      <dt>Autor</dt>         <dd>${libro.autor}</dd>
      <dt>Editorial</dt>     <dd>${not empty libro.editorial ? libro.editorial : '—'}</dd>
      <dt>Año</dt>           <dd>${libro.anioPublicacion}</dd>
      <dt>Género</dt>        <dd><span class="badge badge-genero">${libro.genero.etiqueta}</span></dd>
      <dt>Total Ejemplares</dt>
      <dd>${libro.totalEjemplares}</dd>
      <dt>Disponibles</dt>
      <dd>
        <strong class="${libro.disponible() ? 'text-success' : 'text-danger'}">
          ${libro.ejemplaresDisponibles}
        </strong>
      </dd>
      <dt>Estado</dt>
      <dd>
        <c:choose>
          <c:when test="${libro.disponible()}">
            <span class="badge badge-active">Disponible</span>
          </c:when>
          <c:otherwise>
            <span class="badge badge-danger">Sin ejemplares disponibles</span>
          </c:otherwise>
        </c:choose>
      </dd>
    </dl>
  </div>

  <div class="section-card">
    <h2 class="section-title">Préstamos activos de este libro</h2>
    <c:choose>
      <c:when test="${empty prestamosActivos}">
        <p class="empty-state">Ningún ejemplar prestado actualmente.</p>
      </c:when>
      <c:otherwise>
        <table class="table">
          <thead>
            <tr><th>Usuario</th><th>Email</th><th>Devolución esperada</th><th></th></tr>
          </thead>
          <tbody>
            <c:forEach var="p" items="${prestamosActivos}">
            <tr>
              <td>${p.nombreUsuario}</td>
              <td>${p.emailUsuario}</td>
              <td>${p.fechaDevolucionEsperada}</td>
              <td>
                <form method="post"
                      action="${pageContext.request.contextPath}/prestamos/devolver"
                      onsubmit="return confirm('¿Confirmar devolución?')">
                  <input type="hidden" name="id" value="${p.id}">
                  <button type="submit" class="btn btn-sm btn-success">Devolver</button>
                </form>
              </td>
            </tr>
            </c:forEach>
          </tbody>
        </table>
      </c:otherwise>
    </c:choose>
  </div>

</div>
</main>
<%@ include file="../_footer.jsp" %>
</body>
</html>
