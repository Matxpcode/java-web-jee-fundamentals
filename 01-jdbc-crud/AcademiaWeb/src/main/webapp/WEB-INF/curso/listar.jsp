<%@page import="java.util.ArrayList"%>
<%@page import="model.Curso"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Listado Cursos</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-sRIl4kxILFvY47J16cr9ZwB07vP4J8+LH7qKQnuqkuIAvNWLzeN8tE5YBujZqJLB"
	crossorigin="anonymous">
</head>
<body>
	<%
	List<Curso> cursos = (List<Curso>) request.getAttribute("listarCursos");
	%>

	<div class="container" id="tablaCursos">
		<div class="mb-3">
			<a href="nuevo" class="btn btn-success"><i
				class="fa-solid fa-plus"></i>Nuevo</a>
		</div>
		
		<table class="table">
			<thead>
				<tr>
					<th scope="col">ID</th>
					<th scope="col">Nombre</th>
					<th scope="col">Nivel</th>
					<th scope="col">Precio</th>
					<th scope="col">Activo</th>
					<th scope="col">Profesor</th>
					<th scope="col">Acciones</th>
				</tr>
			</thead>
			<tbody>
				<%
					if(cursos!=null&& !cursos.isEmpty()){
						for(Curso c : cursos){
				%>
			
				<tr>
					<td><%=c.getId()%></td>
					<td><%=c.getNombre()%></td>
					<td>
						<%
							//Validacion para los tipos niveles
							String colorCelda = "bg-secondary";
							if("B".equals(c.getNivel())) colorCelda = "bg-info text-dark";
							else if("I".equals(c.getNivel())) colorCelda = "bg-warning text-dark";
							else if("A".equals(c.getNivel())) colorCelda = "bg-danger";						
						%>
						<span class="badge <%=colorCelda%>"><%=c.getNivel() %></span>
					</td>
					<td>S/.<%=String.format("%.2f", c.getPrecio()) %></td>
					<td>			
						<%=c.getActivo()?"Si":"No"%>
					</td>
					<td><%=c.getProfesor().getNombres()+" "+c.getProfesor().getApellidos()%></td>	
					<td>
						<a href="editar?id=<%=c.getId()%>" class="btn btn-warning">Editar</a>
						<a href="eliminar?id=<%=c.getId() %>" class="btn btn-danger"
						onclick="return confirm('¿Estas seguro de desactivar este curso?(accion irreversible)')">Eliminar</a>
					</td>
				</tr>
				
				<%
						}
					}
				%>
				
			</tbody>
		</table>
	</div>

</body>
</html>