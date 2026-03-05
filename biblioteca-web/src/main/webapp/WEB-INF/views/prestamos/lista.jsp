<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="es">
<head>
  <meta charset="UTF-8"><meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Préstamos — Biblioteca Digital</title>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/app.css">
</head>
<body>
<%@ include file="../_nav.jsp" %>
<main class="main-content">
<%@ include file="../_flash.jsp" %>

<div class="page-header">
  <div>
    <h1>🔖 ${vista == 'activos' ? 'Préstamos Activos' : 'Historial de Préstamos'}</h1>
    <p class="page-subtitle">${prestamos.size()} préstamo(s)</p>
  </div>
  <div class="btn-group">
    <a href="${pageContext.request.contextPath}/prestamos/activos"
       class="btn ${vista == 'activos' ? 'btn-primary' : 'btn-outline'}">Activos</a>
    <a href="${pageContext.request.contextPath}/prestamos"
       class="btn ${vista != 'activos' ? 'btn-primary' : 'btn-outline'}">Historial</a>
    <a href="${pageContext.request.contextPath}/prestamos/nuevo" class="btn btn-secondary">+ Nuevo Préstamo</a>
  </div>
</div>

<div class="section-card">
  <c:choose>
    <c:when test="${empty prestamos}">
      <p class="empty-state">No hay préstamos para mostrar.</p>
    </c:when>
    <c:otherwise>
      <table class="table">
        <thead>
          <tr>
            <th>#</th><th>Libro</th><th>Usuario</th>
            <th>Prestado</th><th>Devolución Esperada</th>
            <th>Devolución Real</th><th>Estado</th><th>Acciones</th>
          </tr>
        </thead>
        <tbody>
          <c:forEach var="p" items="${prestamos}">
          <tr class="${p.estaVencido() ? 'row-warning' : ''}">
            <td>${p.id}</td>
            <td>
              <strong>${p.libroTitulo}</strong><br>
              <small class="text-muted">${p.libroIsbn}</small>
            </td>
            <td>
              ${p.nombreUsuario}<br>
              <small class="text-muted">${p.emailUsuario}</small>
            </td>
            <td>${p.fechaPrestamo}</td>
            <td>
              <c:choose>
                <c:when test="${p.estaVencido()}">
                  <span class="text-danger"><strong>${p.fechaDevolucionEsperada}</strong> ⚠️</span>
                </c:when>
                <c:otherwise>${p.fechaDevolucionEsperada}</c:otherwise>
              </c:choose>
            </td>
            <td>${not empty p.fechaDevolucionReal ? p.fechaDevolucionReal : '—'}</td>
            <td>
              <c:choose>
                <c:when test="${p.estado.name() == 'ACTIVO' && p.estaVencido()}">
                  <span class="badge badge-danger">Vencido</span>
                </c:when>
                <c:when test="${p.estado.name() == 'ACTIVO'}">
                  <span class="badge badge-active">Activo</span>
                </c:when>
                <c:otherwise>
                  <span class="badge badge-neutral">Devuelto</span>
                </c:otherwise>
              </c:choose>
            </td>
            <td>
              <c:if test="${p.estaActivo()}">
                <form method="post"
                      action="${pageContext.request.contextPath}/prestamos/devolver"
                      onsubmit="return confirm('¿Confirmar devolución del libro ${p.libroTitulo}?')">
                  <input type="hidden" name="id" value="${p.id}">
                  <button type="submit" class="btn btn-sm btn-success">✔ Devolver</button>
                </form>
              </c:if>
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
