<%@ page contentType="text/html;charset=UTF-8" isErrorPage="true" %>
<!DOCTYPE html><html lang="es"><head><meta charset="UTF-8">
<title>Error — Biblioteca Digital</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/app.css">
</head><body>
<%@ include file="../_nav.jsp" %>
<main class="main-content">
<div class="section-card" style="text-align:center;padding:4rem 2rem">
  <h1 style="font-size:4rem;color:#fdecea">500</h1>
  <h2 style="color:#1a3a5c;margin:1rem 0">Error interno del servidor</h2>
  <p class="text-muted">${pageContext.exception != null ? pageContext.exception.message : 'Ocurrió un error inesperado.'}</p>
  <a href="${pageContext.request.contextPath}/dashboard" class="btn btn-primary" style="margin-top:1.5rem">Ir al Dashboard</a>
</div>
</main>
</body></html>
