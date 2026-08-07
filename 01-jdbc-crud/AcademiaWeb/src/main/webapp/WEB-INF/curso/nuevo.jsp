<%@page import="model.Profesor"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Registra Curso</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css"
	rel="stylesheet">
</head>
<body>

	<%
	List<Profesor> listaProfesores = (List<Profesor>) request.getAttribute("listadoProfesores");
	%>


	<div class="container mt-5">
		<div class="card">
			<div class="card-body">
				<h2 class="text-center mb-4">Registrar Nuevo Curso</h2>

				<form action="guardar" method="post" class="row g-3">

					<div class="col-md-12">
						<label for="nombre" class="form-label">Nombre del Curso</label> <input
							type="text" class="form-control" id="nombre" name="nombre"
							required>
					</div>

					<div class="col-md-6">
						<label for="nivel" class="form-label">Nivel</label> 
						<select class="form-select" id="nivel" name="nivel" required>
							<option value="" selected disabled>Seleccione un nivel</option>
							<option value="B">Básico</option>
							<option value="I">Intermedio</option>
							<option value="A">Avanzado</option>
						</select>
					</div>

					<div class="col-md-6">
						<label for="precio" class="form-label">Precio (S/.)</label>
						<input
							type="number" step="0.01" class="form-control" id="precio"
							name="precio" required>
					</div>

					<div class="col-md-12">
						<label for="idProfesor" class="form-label">Profesor Asignado</label> 
						<select class="form-select" id="idProfesor" name="idProfesor" required>
							<option value="" selected disabled>Seleccione un profesor</option>

							<%
							// Por qué: Recorremos la lista de profesores para crear una <option> por cada uno.
							// El 'value' es el ID (lo que se envía al servidor).
							// El texto visible es el nombre completo (lo que ve el usuario).
							if (listaProfesores != null) {
								for (Profesor p : listaProfesores) {
							%>
							<option value="<%=p.getId()%>">
								<%=p.getNombres() + " " + p.getApellidos()%>
							</option>
							<%
							}
							}
							%>
						</select>
					</div>

					<div class="col-12 d-flex justify-content-between mt-4">
						<a href="listar" class="btn btn-secondary">Cancelar</a>
						<button type="submit" class="btn btn-success">Guardar Curso</button>
					</div>
					
				</form>
			</div>
		</div>
	</div>
</body>
</html>

</body>
</html>