<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-sRIl4kxILFvY47J16cr9ZwB07vP4J8+LH7qKQnuqkuIAvNWLzeN8tE5YBujZqJLB"
	crossorigin="anonymous">
</head>

<body>

	<h1 class="text-center m-5">Nuevo Profesor</h1>
	<div class="d-flex align-items-center justify-content-center vh-80">
		<form action="guardar" method="post" class="bg-dark bg-gradient text-white p-5 border rounded fw-semibold">
			<div class="mb-3">
				<label for="nombres" class="form-label">Nombres</label>
				 <input name="nombres" class="form-control" id="nombres" >
			</div>
				<div class="mb-3">
				<label for="apellidos" class="form-label">Apellidos</label>
				 <input name="apellidos" class="form-control"
					id="apellidos" >
			</div>
				<div class="mb-3">
				<label for="email" class="form-label">Email</label>
				 <input name="email" class="form-control"
					id="email" >
			</div>
			
			<div class="mb-3">
				<a href="listar" class="btn btn-success">Regresar</a>			
				<button type="submit" class="btn btn-primary">
				<i class="fa-solid fa-floppy-disk"></i>Guardar</button>
			</div>
		
		</form>
	</div>






	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-FKyoEForCGlyvwx9Hj09JcYn3nv7wiPVlz7YYwJrWVcXK/BmnVDxM+D2scQbITxI"
		crossorigin="anonymous"></script>
</body>
</html>