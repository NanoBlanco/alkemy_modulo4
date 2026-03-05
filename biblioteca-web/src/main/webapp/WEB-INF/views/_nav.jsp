<%@ page contentType="text/html;charset=UTF-8" %>
<%
    String ctxPath = request.getContextPath();
    String uri     = request.getRequestURI();
%>
<nav class="navbar">
    <a class="navbar-brand" href="<%= ctxPath %>/dashboard">📚 Biblioteca Digital</a>
    <div class="nav-links">
        <a class="nav-link <%= uri.contains("dashboard") ? "active" : "" %>"
           href="<%= ctxPath %>/dashboard">Dashboard</a>
        <a class="nav-link <%= uri.contains("libros") ? "active" : "" %>"
           href="<%= ctxPath %>/libros">Libros</a>
        <a class="nav-link <%= uri.contains("prestamos") ? "active" : "" %>"
           href="<%= ctxPath %>/prestamos">Préstamos</a>
    </div>
</nav>
