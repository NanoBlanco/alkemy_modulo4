<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <!-- Bootstrap 4 CSS -->
    <link
      rel="stylesheet"
      href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css"
    />

    <!-- jQuery -->
    <script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
    <title>Document</title>
  </head>
  <body>
    <div class="container">
      <div class="card m-5">
        <div class="card-header">Información</div>
        <div class="card-body">
          <h3>Transacciones recientes</h3>

          <input
            type="text"
            id="filtro"
            class="form-control mb-2"
            placeholder="Filtrar"
          />

          <ul id="lista" class="list-group"></ul>
        </div>
      </div>
    </div>
    <!-- Popper + Bootstrap JS -->
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
    <script src="./assest/js/wallet.js"></script>
  </body>
</html>
