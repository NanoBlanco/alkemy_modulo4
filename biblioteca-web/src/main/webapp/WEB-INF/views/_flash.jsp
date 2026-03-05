<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%
    String flashExito = (String) session.getAttribute("flashExito");
    String flashError = (String) session.getAttribute("flashError");
    session.removeAttribute("flashExito");
    session.removeAttribute("flashError");
    if (flashExito != null) request.setAttribute("_flashExito", flashExito);
    if (flashError != null) request.setAttribute("_flashError", flashError);
%>
<c:if test="${not empty _flashExito}">
    <div class="alert alert-success">✅ ${_flashExito}</div>
</c:if>
<c:if test="${not empty _flashError}">
    <div class="alert alert-error">❌ ${_flashError}</div>
</c:if>
