<%@ include file="header.jsp" %>
	<div class="container">
    	<div class="card m-5" style="width: 50rem">
        	<div class="card-header"><h3>Depositar dinero</h3></div>
        	<div class="card-body">
		  		<form id="depositForm" action="/Billetera/app?action=depositoNuevo" method="post">
          			<input type="number" id="monto" name = "monto" class="form-control mb-2" placeholder="Monto" />
					<div class="col-12 m-1">
				      	<label class="form-label" for="moneda">Moneda </label>
				      	<select class="form-select" name="moneda">
					      	<option value ="" selected>Seleccione una moneda</option>
					      	<option value="CLP">CLP</option>
					      	<option value="USD">USD</option>
					      	<option value="EUR">EUR</option>
				      	</select>
				      </div>
          			<button type="submit" class="btn btn-success">Depositar</button>
          		</form>
        	</div>
      </div>
    </div>
<%@ include file="footer.jsp" %>
