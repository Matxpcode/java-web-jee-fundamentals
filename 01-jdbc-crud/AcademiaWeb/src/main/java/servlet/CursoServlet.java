package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.CursoDAO;
import dao.CursoDAOImpl;

/**
 * Servlet implementation class CursoServlet
 */
@WebServlet("/curso/*")
public class CursoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private CursoDAO dao;
	
    public CursoServlet() {
        dao = new CursoDAOImpl();
    }

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//Recopila ruta desde el servlet para adelante, es decir de "/curso/*"
		String metodo = request.getPathInfo();
		
		switch (metodo) {
		case "/listar":
			listar(request,response);
			break;
		case "/insertar":
			insertar(request,response);
			break;
		case "/actualizar":
			actualizar(request,response);
			break;
		case "/eliminar":
			eliminar(request,response);
			break;
		case "/guardar":
			guardar(request,response);
			break;
		default:
			System.out.println("Error, ruta no encontrada");
			response.sendError(HttpServletResponse.SC_NOT_FOUND,"RUTA NO ENCONTRADA");
			break;
		}
	}

	private void guardar(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		
	}

	private void eliminar(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		
	}

	private void actualizar(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		
	}

	private void insertar(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		
	}

	private void listar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("listarCursos", dao.listarCursos());
		request.getRequestDispatcher("/WEB-INF/curso/listar.jsp").forward(request, response);
	}

}
