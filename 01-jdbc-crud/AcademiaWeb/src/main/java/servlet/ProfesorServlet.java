package servlet;

import java.io.IOException;
import java.net.Authenticator.RequestorType;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ProfesorDAO;
import dao.ProfesorDAOImpl;
import model.Profesor;

/**
 * Servlet implementation class ProfesorServlet
 */
@WebServlet("/profesor/*")
public class ProfesorServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private ProfesorDAO dao;

	public ProfesorServlet() {
		dao = new ProfesorDAOImpl();
	}

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String metodo = request.getPathInfo();

		switch (metodo) {
		case "/listar":
			listar(request, response);
			break;
		case "/nuevo":
			nuevo(request, response);
			break;
		case "/guardar":
			guardar(request, response);
			break;
		case "/editar":
			editar(request,response);
			break;

		case "/actualizar":
			actualizar(request,response);
			break;
			
		case "/eliminar":
			eliminar(request,response);
			break;
			
		default:
			response.sendError(HttpServletResponse.SC_NOT_FOUND, "RUTA NO ENCONTRADA");
			break;
		}
	}

	protected void eliminar(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String idString = request.getParameter("id");
		Integer id = null;
		
		try {
			id = Integer.parseInt(idString);
		} catch (Exception e) {
			System.out.println("ID invalido: "+idString);
		}
		
		Integer resultado = dao.delete(id);
		
		if (resultado !=null && resultado>0) {
			System.out.println("Profesor eliminado exitosamente!");
		}else {
			System.out.println("No se puede eliminar profesor con ID: "+id);
		}
		response.sendRedirect("listar");
	}

	protected void actualizar(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String idString = request.getParameter("id");
		String nombres = request.getParameter("nombres");
		String apellidos = request.getParameter("apellidos");
		String email = request.getParameter("email");
		
		Integer id = null;
		
		try {
			id = Integer.parseInt(idString);
		} catch (NumberFormatException e) {
			System.out.println("ID Invalido: "+idString);
		}
		
		Profesor p = new Profesor(id, nombres, apellidos, email);
		Integer resultado = dao.update(p);
		
		if (resultado!=null && resultado>0) {
			response.sendRedirect("listar");
		}else {
			System.out.println("Error al actualizar el profesor");
			response.sendRedirect("editar?id="+id);
		}
		
	}

	protected void editar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String idString = request.getParameter("id");
		
		Integer id= null;
		
		try {
			id = Integer.parseInt(idString);
		} catch (NumberFormatException e) {
			System.out.println("ID Invalido: "+ idString);
		}
		
		Profesor p = dao.findById(id);
		
		if (p!=null) {
			request.setAttribute("profesor", p);
			request.getRequestDispatcher("/WEB-INF/profesor/editar.jsp").forward(request, response);
		}else {
			System.out.println("Profesor no encontrado con ID: "+id);
			response.sendRedirect("listar");
		}
	}

	protected void listar(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setAttribute("listaProfesores", dao.findAll());

		request.getRequestDispatcher("/WEB-INF/profesor/listar.jsp").forward(request, response);
	}

	protected void nuevo(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("/WEB-INF/profesor/nuevo.jsp").forward(req, resp);
	}

	protected void guardar(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String nombres = req.getParameter("nombres");
		String apellidos = req.getParameter("apellidos");
		String email = req.getParameter("email");
	
		Profesor p = new Profesor(null, nombres, apellidos, email);
	
		Integer resultado = dao.insert(p);
		
		if (resultado!=null && resultado>0) {
			resp.sendRedirect("listar");
		}else {
			System.out.println("Error al insertar profesor");
			resp.sendRedirect("nuevo");
		}
	}
}
