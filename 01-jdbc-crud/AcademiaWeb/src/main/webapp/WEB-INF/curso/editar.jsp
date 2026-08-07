<%@page import="model.Profesor"%>
<%@page import="model.Curso"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Editar Curso</title>
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css">
</head>
<body>
	<div class="container mt-5">
		<div class="card">
			<div class="card-body">
				<h2 class="text-center mb-4">Editar Curso</h2>

				<%
				// Por qué: Recuperamos el objeto Curso que el Servlet envió
				Curso curso = (Curso) request.getAttribute("curso");
				List<Profesor> listaProfesores = (List<Profesor>) request.getAttribute("listaProfesores");
				%>

				<form action="actualizar" method="post" class="row g-3">

					<!-- Por qué: Input hidden para enviar el ID del curso -->
					<input type="hidden" name="id" value="<%=curso.getId()%>">

					<div class="col-md-12">
						<label for="nombre" class="form-label">Nombre del Curso</label> <input
							type="text" class="form-control" id="nombre" name="nombre"
							value="<%=curso.getNombre()%>" required>
					</div>

					<div class="col-md-6">
						<label for="nivel" class="form-label">Nivel</label> <select
							class="form-select" id="nivel" name="nivel" required>
							<option value="" disabled>Seleccione un nivel</option>
							<!-- Por qué: Comparamos el Character del curso con cada opción para marcarla como selected -->
							<option value="B"
								<%=curso.getNivel() == 'B' ? "selected" : ""%>>Básico</option>
							<option value="I"
								<%=curso.getNivel() == 'I' ? "selected" : ""%>>Intermedio</option>
							<option value="A"
								<%=curso.getNivel() == 'A' ? "selected" : ""%>>Avanzado</option>
						</select>
					</div>

					<div class="col-md-6">
						<label for="precio" class="form-label">Precio (S/.)</label> <input
							type="number" step="0.01" class="form-control" id="precio"
							name="precio" value="<%=curso.getPrecio()%>" required>
					</div>

					<div class="col-md-12">
						<label for="idProfesor" class="form-label">Profesor
							Asignado</label> <select class="form-select" id="idProfesor"
							name="idProfesor" required>
							<option value="" disabled>Seleccione un profesor</option>

							<%
							// Por qué: Recorremos la lista de profesores
							if (listaProfesores != null) {
								for (Profesor p : listaProfesores) {
									// Por qué: Comparamos el ID del profesor actual con el ID del profesor del curso
									// Si coinciden, marcamos esa opción como "selected"
									String selected = (p.getId().equals(curso.getProfesor().getId())) ? "selected" : "";
							%>
							<option value="<%=p.getId()%>" <%=selected%>>
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
						<button type="submit" class="btn btn-primary">Actualizar
							Curso</button>
					</div>
				</form>
			</div>
		</div>
	</div>
</body>
</html>