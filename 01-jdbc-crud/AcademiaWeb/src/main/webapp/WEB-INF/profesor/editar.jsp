<%@page import="model.Profesor"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Editar Profesor</title>
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css">
</head>
<body>
	<div class="container mt-5">
		<div class="card">
			<div class="card-body">
				<h1 class="card-title text-center">Editar Profesor</h1>

				<%
                    // Por qué: Recuperamos el objeto Profesor que el Servlet envió.
                    Profesor profesor = (Profesor) request.getAttribute("profesor");
                %>

				<div class="row justify-content-center">
					<div class="col-md-6">
						<!-- Por qué: action="actualizar" envía los datos a /profesor/actualizar -->
						<form action="actualizar" method="post" class="row g-3">

							<!-- Por qué: Input hidden para enviar el ID (no lo vemos pero se envía) -->
							<input type="hidden" name="id" value="<%= profesor.getId() %>">

							<div class="col-md-12">
								<label for="nombres" class="form-label">Nombres</label>
								
								<input type="text" class="form-control" id="nombres"
									name="nombres" value="<%= profesor.getNombres() %>" required>
							</div>

							<div class="col-md-12">
								<label for="apellidos" class="form-label">Apellidos</label> <input
									type="text" class="form-control" id="apellidos"
									name="apellidos" value="<%= profesor.getApellidos() %>"
									required>
							</div>

							<div class="col-md-12">
								<label for="email" class="form-label">Email</label> <input
									type="email" class="form-control" id="email" name="email"
									value="<%= profesor.getEmail() %>" required>
							</div>

							<div class="d-flex justify-content-between">
								<a href="listar" class="btn btn-outline-dark">Regresar</a>
								<button type="submit" class="btn btn-primary">Actualizar</button>
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>