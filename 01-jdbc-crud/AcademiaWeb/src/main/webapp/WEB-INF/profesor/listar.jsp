<%@page import="model.Profesor"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Listado de Profesores</title>
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css">

<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/7.3.0/css/all.min.css"
	integrity="sha512-ApSLB1Pd3/bZN8fWB/RG9YhN/7bd9Hkf3AGaE2mPfebjrxagjuBtx2GcgdqIlJkUzwylBo61r9Xa9NmgBI0swA=="
	crossorigin="anonymous" referrerpolicy="no-referrer" />
</head>
<body>
	<%
	List<Profesor> listado = (List<Profesor>) request.getAttribute("listaProfesores");
	%>

	<div class="container">

		<div class="mb-3">
			<a href="nuevo" class="btn btn-success"><i
				class="fa-solid fa-plus"></i>Nuevo</a>
		</div>

		<table class="table">
			<thead>
				<tr>
					<th scope="col">ID</th>
					<th scope="col">Nombres</th>
					<th scope="col">Apellidos</th>
					<th scope="col">Email</th>
					<th scope="col"></th>
				</tr>
			</thead>
			<tbody>
				<%
				if (listado != null) {
					for (Profesor p : listado) {
				%>
				<tr>
					<th scope="row"><%=p.getId()%></th>
					<td><%=p.getNombres()%></td>
					<td><%=p.getApellidos()%></td>
					<td><%=p.getEmail()%></td>
					<td><a href="editar?id=<%=p.getId()%>"
						class="btn btn-sm btn-warning"><i
							class="fa-solid fa-pen-to-square"></i> Editar </a></td>
					<td><a href="eliminar?id=<%=p.getId()%>"
						onclick="return confirm('¿Estas seguro de eliminar este profesor?');"
						class="btn btn-danger">Eliminar</a></td>
				</tr>

				<%
				}
				} else {
				%>
				<div class="alert alert-danger" role="alert">No hay Profesores
					disponibles!</div>
				<%
				}
				%>

			</tbody>
		</table>
	</div>

</body>
</html>