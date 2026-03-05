<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="es">
<head>
  <meta charset="UTF-8"><meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Catálogo — Biblioteca Digital</title>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/app.css">
</head>
<body>
<%@ include file="../_nav.jsp" %>
<main class="main-content">
<%@ include file="../_flash.jsp" %>

<div class="page-header">
  <div>
    <h1>📚 Catálogo de Libros</h1>
    <p class="page-subtitle">${libros.size()} libro(s) encontrado(s)</p>
  </div>
  <a href="${pageContext.request.contextPath}/libros/nuevo" class="btn btn-primary">+ Nuevo Libro</a>
</div>

<div class="section-card">
  <form method="get" action="${pageContext.request.contextPath}/libros" class="search-form">
    <input type="text" name="q" value="${q}" placeholder="Buscar por título, autor o ISBN..."
           class="input-search">
    <button type="submit" class="btn btn-primary">🔍 Buscar</button>
    <c:if test="${not empty q}">
      <a href="${pageContext.request.contextPath}/libros" class="btn btn-outline">✕ Limpiar</a>
    </c:if>
  </form>
</div>

<div class="section-card">
  <c:choose>
    <c:when test="${empty libros}">
      <div class="empty-state-box">
        <p class="empty-state">No se encontraron libros.</p>
        <a href="${pageContext.request.contextPath}/libros/nuevo" class="btn btn-primary">Registrar el primero</a>
      </div>
    </c:when>
    <c:otherwise>
      <table class="table">
        <thead>
          <tr>
            <th>ISBN</th><th>Título / Autor</th><th>Género</th>
            <th>Año</th><th>Disponibles</th><th>Estado</th><th>Acciones</th>
          </tr>
        </thead>
        <tbody>
          <c:forEach var="libro" items="${libros}">
          <tr>
            <td><code>${libro.isbn}</code></td>
            <td>
              <strong>${libro.titulo}</strong><br>
              <small class="text-muted">${libro.autor}</small>
            </td>
            <td><span class="badge badge-genero">${libro.genero.etiqueta}</span></td>
            <td>${libro.anioPublicacion}</td>
            <td class="text-center">
              <span class="${libro.disponible() ? 'text-success' : 'text-danger'}">
                ${libro.ejemplaresDisponibles}</span>
              <span class="text-muted">/${libro.totalEjemplares}</span>
            </td>
            <td>
              <c:choose>
                <c:when test="${libro.disponible()}">
                  <span class="badge badge-active">Disponible</span>
                </c:when>
                <c:otherwise>
                  <span class="badge badge-danger">Agotado</span>
                </c:otherwise>
              </c:choose>
            </td>
            <td class="actions-cell">
              <a href="${pageContext.request.contextPath}/libros/detalle?id=${libro.id}"
                 class="btn btn-sm btn-outline">Ver</a>
              <a href="${pageContext.request.contextPath}/libros/editar?id=${libro.id}"
                 class="btn btn-sm btn-secondary">Editar</a>
              <c:if test="${libro.disponible()}">
                <a href="${pageContext.request.contextPath}/prestamos/nuevo?libroId=${libro.id}"
                   class="btn btn-sm btn-primary">Prestar</a>
              </c:if>
              <form method="post" action="${pageContext.request.contextPath}/libros/eliminar"
                    style="display:inline"
                    onsubmit="return confirm('¿Eliminar el libro ${libro.titulo}?')">
                <input type="hidden" name="id" value="${libro.id}">
                <button type="submit" class="btn btn-sm btn-danger">Eliminar</button>
              </form>
            </td>
          </tr>
          </c:forEach>
        </tbody>
      </table>
    </c:otherwise>
  </c:choose>
</div>
</main>
<%@ include file="../_footer.jsp" %>
</body>
</html>
