<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="es">
<head>
  <meta charset="UTF-8"><meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Dashboard — Biblioteca Digital</title>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/app.css">
</head>
<body>
<%@ include file="_nav.jsp" %>
<main class="main-content">
<%@ include file="_flash.jsp" %>
<div class="page-header">
  <h1>📚 Dashboard</h1>
  <p class="page-subtitle">Panel general de la Biblioteca Digital</p>
</div>
<div class="stats-grid">
  <div class="stat-card stat-blue">
    <div class="stat-icon">📖</div>
    <div class="stat-body"><div class="stat-number">${totalLibros}</div><div class="stat-label">Total Libros</div></div>
  </div>
  <div class="stat-card stat-green">
    <div class="stat-icon">✅</div>
    <div class="stat-body"><div class="stat-number">${librosDisponibles}</div><div class="stat-label">Disponibles</div></div>
  </div>
  <div class="stat-card stat-orange">
    <div class="stat-icon">🔖</div>
    <div class="stat-body"><div class="stat-number">${prestamosActivos}</div><div class="stat-label">Préstamos Activos</div></div>
  </div>
</div>
<div class="section-card">
  <h2 class="section-title">Acciones rápidas</h2>
  <div class="btn-group">
    <a href="${pageContext.request.contextPath}/libros/nuevo" class="btn btn-primary">+ Registrar libro</a>
    <a href="${pageContext.request.contextPath}/prestamos/nuevo" class="btn btn-secondary">+ Nuevo préstamo</a>
    <a href="${pageContext.request.contextPath}/libros" class="btn btn-outline">Ver catálogo</a>
    <a href="${pageContext.request.contextPath}/prestamos/activos" class="btn btn-outline">Ver préstamos</a>
  </div>
</div>
<div class="section-card">
  <div class="section-header-row">
    <h2 class="section-title">Préstamos Activos Recientes</h2>
    <a href="${pageContext.request.contextPath}/prestamos/activos" class="link-ver-todos">Ver todos →</a>
  </div>
  <c:choose>
    <c:when test="${empty ultimosPrestamos}">
      <p class="empty-state">No hay préstamos activos en este momento.</p>
    </c:when>
    <c:otherwise>
      <table class="table">
        <thead><tr><th>Libro</th><th>Usuario</th><th>Devolución Esperada</th><th>Estado</th><th></th></tr></thead>
        <tbody>
        <c:forEach var="p" items="${ultimosPrestamos}">
          <tr>
            <td><strong>${p.libroTitulo}</strong><br><small class="text-muted">${p.libroIsbn}</small></td>
            <td>${p.nombreUsuario}<br><small class="text-muted">${p.emailUsuario}</small></td>
            <td>${p.fechaDevolucionEsperada}</td>
            <td><span class="badge badge-active">Activo</span></td>
            <td>
              <form method="post" action="${pageContext.request.contextPath}/prestamos/devolver"
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
</main>
<%@ include file="_footer.jsp" %>
</body>
</html>
