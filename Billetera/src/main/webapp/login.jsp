<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="bg-light d-flex justify-content-center align-items-center"
    style="height: 90vh">
	<div
      class="card p-4"
      style="width: 350px; box-shadow: 0 10px 25px rgba(0, 0, 0, 0.3)"
    >
      <h4 class="text-center mb-3">Wallet Digital</h4>
      <c:if test="${not empty errores}">
      	<div class="alert alert-danger">
      		<ul>
      			<c:forEach items="${errores}" var="error">
      				<li>${error}</li>
      			</c:forEach>
      		</ul>
      	</div>
      </c:if>
      <c:if test="${error == 'Credenciales Incorrectas'}">
      	<div class="alert alert-danger">
      		Usuario o contraseña incorrectos
      	</div>
      </c:if>
      <form id="loginForm" action="login" method="post">
	      <div class="input-group m-1">
	        <input
	          class="form-control"
	          type="text"
	          id="usuario"
	          name="user"
	          placeholder="tu_usuario"
	          autofocus
	          autocomplete="off"
	        />
	      </div>
	      <div class="text-center text-danger m-2" id="errorMsgUser"></div>
	      <div class="input-group m-1">
	        <input
	          class="form-control"
	          type="password"
	          id="password"
	          name="pass"
	          placeholder="Clave de acceso"
	          autocomplete="off"
	        />
	      </div>
	      <div class="text-center text-danger m-2" id="errorMsgPass"></div>
	      <button type="submit" class="btn btn-primary btn-block">
	        Ingresar
	      </button>
      </form>
    </div>
</div>
