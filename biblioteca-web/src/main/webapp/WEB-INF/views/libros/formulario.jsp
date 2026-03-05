<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="es">
<head>
  <meta charset="UTF-8"><meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>${accion == 'editar' ? 'Editar' : 'Nuevo'} Libro — Biblioteca Digital</title>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/app.css">
</head>
<body>
<%@ include file="../_nav.jsp" %>
<main class="main-content">
<%@ include file="../_flash.jsp" %>

<div class="page-header">
  <div>
    <h1>${accion == 'editar' ? '✏️ Editar Libro' : '➕ Nuevo Libro'}</h1>
    <p class="page-subtitle">
      <a href="${pageContext.request.contextPath}/libros">← Volver al catálogo</a>
    </p>
  </div>
</div>

<c:if test="${not empty error}">
  <div class="alert alert-error">❌ ${error}</div>
</c:if>

<div class="section-card form-card">

  <%-- Determinar valores: libro existente (editar) o libroForm (repoblado tras error) --%>
  <c:set var="isbn"       value="${not empty libro ? libro.isbn       : (not empty libroForm ? libroForm.isbn       : '')}"/>
  <c:set var="titulo"     value="${not empty libro ? libro.titulo     : (not empty libroForm ? libroForm.titulo     : '')}"/>
  <c:set var="autor"      value="${not empty libro ? libro.autor      : (not empty libroForm ? libroForm.autor      : '')}"/>
  <c:set var="editorial"  value="${not empty libro ? libro.editorial  : (not empty libroForm ? libroForm.editorial  : '')}"/>
  <c:set var="anio"       value="${not empty libro ? libro.anioPublicacion : (not empty libroForm ? libroForm.anioPublicacion : '2024')}"/>
  <c:set var="ejemplares" value="${not empty libro ? libro.totalEjemplares : (not empty libroForm ? libroForm.totalEjemplares : '1')}"/>
  <c:set var="generoSel"  value="${not empty libro ? libro.genero.name() : (not empty libroForm ? libroForm.genero : '')}"/>

  <form method="post"
        action="${pageContext.request.contextPath}/libros/${accion}"
        class="form-grid">

    <c:if test="${accion == 'editar'}">
      <input type="hidden" name="id" value="${libro.id}">
    </c:if>

    <div class="form-group">
      <label for="isbn">ISBN *</label>
      <input type="text" id="isbn" name="isbn" value="${isbn}"
             class="form-control" placeholder="978-XX-XXX-XXXX-X" required>
    </div>

    <div class="form-group">
      <label for="titulo">Título *</label>
      <input type="text" id="titulo" name="titulo" value="${titulo}"
             class="form-control" placeholder="Título del libro" required>
    </div>

    <div class="form-group">
      <label for="autor">Autor *</label>
      <input type="text" id="autor" name="autor" value="${autor}"
             class="form-control" placeholder="Nombre del autor" required>
    </div>

    <div class="form-group">
      <label for="editorial">Editorial</label>
      <input type="text" id="editorial" name="editorial" value="${editorial}"
             class="form-control" placeholder="Editorial">
    </div>

    <div class="form-group">
      <label for="anioPublicacion">Año de Publicación *</label>
      <input type="number" id="anioPublicacion" name="anioPublicacion" value="${anio}"
             class="form-control" min="1450" max="2024" required>
    </div>

    <div class="form-group">
      <label for="genero">Género *</label>
      <select id="genero" name="genero" class="form-control" required>
        <option value="">-- Seleccionar --</option>
        <c:forEach var="g" items="${generos}">
          <option value="${g.name()}" ${g.name() == generoSel ? 'selected' : ''}>
            ${g.etiqueta}
          </option>
        </c:forEach>
      </select>
    </div>

    <div class="form-group">
      <label for="totalEjemplares">Total de Ejemplares *</label>
      <input type="number" id="totalEjemplares" name="totalEjemplares" value="${ejemplares}"
             class="form-control" min="1" max="100" required>
    </div>

    <div class="form-actions">
      <button type="submit" class="btn btn-primary">
        ${accion == 'editar' ? '💾 Guardar cambios' : '➕ Registrar libro'}
      </button>
      <a href="${pageContext.request.contextPath}/libros" class="btn btn-outline">Cancelar</a>
    </div>

  </form>
</div>
</main>
<%@ include file="../_footer.jsp" %>
</body>
</html>
