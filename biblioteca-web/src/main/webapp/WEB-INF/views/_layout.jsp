<%-- WEB-INF/views/_layout.jsp - Plantilla base (incluida por cada vista) --%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%
    String flashExito = (String) session.getAttribute("flashExito");
    String flashError = (String) session.getAttribute("flashError");
    session.removeAttribute("flashExito");
    session.removeAttribute("flashError");
    request.setAttribute("_flashExito", flashExito);
    request.setAttribute("_flashError", flashError);
    String ctxPath = request.getContextPath();
    String uri     = request.getRequestURI();
%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>${empty pageTitle ? 'Biblioteca Digital' : pageTitle.concat(' — Biblioteca Digital')}</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/app.css">
</head>
<body>

<nav class="navbar">
    <a class="navbar-brand" href="<%= ctxPath %>/dashboard">📚 Biblioteca Digital</a>
    <div class="nav-links">
        <a class="nav-link <%= uri.contains("/dashboard") || uri.endsWith(ctxPath+"/") ? "active" : "" %>"
           href="<%= ctxPath %>/dashboard">Dashboard</a>
        <a class="nav-link <%= uri.contains("/libros") ? "active" : "" %>"
           href="<%= ctxPath %>/libros">Libros</a>
        <a class="nav-link <%= uri.contains("/prestamos") ? "active" : "" %>"
           href="<%= ctxPath %>/prestamos">Préstamos</a>
    </div>
</nav>

<main class="main-content">

    <c:if test="${not empty _flashExito}">
        <div class="alert alert-success">✅ ${_flashExito}</div>
    </c:if>
    <c:if test="${not empty _flashError}">
        <div class="alert alert-error">❌ ${_flashError}</div>
    </c:if>

    <jsp:include page="${viewContent}" />

</main>

<footer class="footer">
    <p>Biblioteca Digital © 2024 — Java 17 + Jakarta Servlets + HikariCP + H2</p>
</footer>

<script src="${pageContext.request.contextPath}/js/app.js"></script>
</body>
</html>
