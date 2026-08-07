package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.CursoDAO;
import dao.CursoDAOImpl;
import dao.ProfesorDAO;
import dao.ProfesorDAOImpl;
import model.Curso;
import model.Profesor;

/**
 * Servlet implementation class CursoServlet
 */
@WebServlet("/curso/*")
public class CursoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private CursoDAO daoCurso;
	private ProfesorDAO daoProfesor;
	
    public CursoServlet() {
        daoCurso = new CursoDAOImpl();
        daoProfesor = new ProfesorDAOImpl();
    }

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//Recopila ruta desde el servlet para adelante, es decir de "/curso/*"
		String metodo = request.getPathInfo();
		
		switch (metodo) {
		case "/listar":
			listar(request,response);
			break;
		case "/nuevo":
			nuevo(request,response);
			break;
		case "/editar":
			editar(request,response);
			break;
		case "/eliminar":
			eliminar(request,response);
			break;
		case "/guardar":
			guardar(request,response);
			break;
		case "/actualizar":
			actualizar(request,response);
			break;
		default:
			System.out.println("Error, ruta no encontrada");
			response.sendError(HttpServletResponse.SC_NOT_FOUND,"RUTA NO ENCONTRADA");
			break;
		}
	}

	private void actualizar(HttpServletRequest request, HttpServletResponse response) throws IOException {
		//Obtenemos el id desde el input hidden del formulario
		String idStr = request.getParameter("id");
		String nombre = request.getParameter("nombre");
		String nivelStr = request.getParameter("nivel");
		Double precio = Double.parseDouble(request.getParameter("precio"));
		Integer idProfesor = Integer.parseInt(request.getParameter("idProfesor"));
		
		//Convertimos el id a Integer
		Integer id = null;
		try {
			id=Integer.parseInt(idStr);
		} catch (NumberFormatException e) {
			System.out.println("ID invalido: "+idStr);
		}
		
		//Armamos el objeto Profesor (solo con el id es suficiente para actualizar)
		Profesor p = new Profesor();
		p.setId(idProfesor);
		
		//Armamos el objeto Curso completo con todos los datos (incluyendo el ID)
		Character nivel = nivelStr.charAt(0);
		Curso curso = new Curso(id, nombre, nivel, precio, true, p);	//ponemos valor fijo a activo
		
		//Llamamos al DAO para actualizar
		Integer resultado = daoCurso.actualizarCursos(curso);
		
		//Realizamos validacion, si se actualiza se redirige al listado
		if (resultado!=null && resultado>0) {
			response.sendRedirect("listar");
		}else {
			System.out.println("Error al actualizar curso");
			response.sendRedirect("editar?id="+id);
		}
		
	}

	private void guardar(HttpServletRequest request, HttpServletResponse response) throws IOException {
		//PARAMETROS DE CURSO
		String nombre = request.getParameter("nombre");
		String nivelStr = request.getParameter("nivel"); //nivel en formato string
		Double precio = Double.parseDouble(request.getParameter("precio"));
		
		Integer idProfesor = Integer.parseInt(request.getParameter("idProfesor")); //id profesor que estrara en objeto
		
		//ARMAMOS OBJETO PROFESOR
		Profesor p = new Profesor();
		p.setId(idProfesor);
		
		//ARMAMOS OBJETO CURSO
		
		//cambios de string a chart - "nivel"
		Character nivel = nivelStr.charAt(0);
		Curso c = new Curso(null, nombre, nivel, precio, true, p);
		
		//Guardamos en la BD
		Integer resultado = daoCurso.insertarCursos(c);

		//Relizamos validacion del resultado de filas afectadas
		if (resultado!=null && resultado>0) {
			response.sendRedirect("listar");
		}else {
			System.out.println("Error al insertar el curso");
			response.sendRedirect("nuevo");
		}
	}

	private void eliminar(HttpServletRequest request, HttpServletResponse response) throws IOException {
		//obtenemos el id desde la url
		String idStr = request.getParameter("id");
		Integer id = null;
		
		try {
			id = Integer.parseInt(idStr);
		} catch (NumberFormatException e) {
			System.out.println("ID Invalido: "+e.getMessage());
		}
		
		//Llamamos al DAO para hacer la "baja logica"
		Integer resultado = daoCurso.desactivarCursos(id);
		
		//Validamos y redirigimos
		if (resultado!=null && resultado>0) {
			System.out.println("Curso desactivado exitosamente, id: "+id);
		}else {
			System.out.println("No se pudo desactivar el curso con id: "+id);
		}
		
		//Se redirige ya sea q pase o no a listado
		//Para que al refrescar la vista, el curso ya no aparesca
		response.sendRedirect("listar");
	}

	private void editar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String idStr = request.getParameter("id");
		
		Integer id = null;
		try {
			id = Integer.parseInt(idStr);
		} catch (NumberFormatException e) {
			System.out.println("ID invalido: "+idStr);
		}
		
		//Luego de la validacion y asegurarnos q el id existe
		//Buscamos el curso en la BD
		Curso curso = daoCurso.buscarPorId(id);
		
		if (curso!=null) {
			request.setAttribute("curso", curso);
		
			//tambien enviamos la lista de profesores para el "select"
			request.setAttribute("listaProfesores", daoProfesor.findAll());
			request.getRequestDispatcher("/WEB-INF/curso/editar.jsp").forward(request, response);
		}else {
			System.out.println("Curso no encontrado con ID: "+id);
			response.sendRedirect("listar");
		}
	}

	private void nuevo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("listadoProfesores", daoProfesor.findAll());
		request.getRequestDispatcher("/WEB-INF/curso/nuevo.jsp").forward(request, response);
	}

	private void listar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("listarCursos", daoCurso.listarCursos());
		request.getRequestDispatcher("/WEB-INF/curso/listar.jsp").forward(request, response);
	}

}
