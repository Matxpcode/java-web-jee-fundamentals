package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import model.Curso;
import model.Profesor;
import util.MySQLConexion;

public class CursoDAOImpl implements CursoDAO{

	@Override
	public List<Curso> listarCursos() {
		List<Curso> lista = new ArrayList<Curso>();
		//recuerda que en curso declaramos "id" y en profesor declaramos un "ALIAS" para evitar confusion de JAVA
		String sql = "select c.id,c.nombre,c.nivel,c.precio,c.activo,p.id as id_profesor,p.nombres,p.apellidos "+
		"from tbl_curso as c "+
		"inner join tbl_profesor as p on c.id_profesor=p.id "+
		"where c.activo=1 "+
		"order by c.id desc";
		
		try(
			Connection cn = MySQLConexion.getConexion();
			PreparedStatement ps = cn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			){
			while (rs.next()) {
				//Declaramos el objeto "curso vacio" para insertar datos recopilados en "rs"
				Curso curso = new Curso();
				
				//Modificamos las propiedades de curso para insertar datos nuevos (nombres iguales a columnas de BD)
				curso.setId(rs.getInt("id"));
				curso.setNombre(rs.getString("nombre"));
				
				//Realizamos 2 pasos para CHAR: "obtenemos" como string y luego "extraemos" la primera cifra
				String nivelStr = rs.getString("nivel");
				//obtengo 1era cifra
				curso.setNivel((nivelStr!=null&&!nivelStr.isEmpty())?nivelStr.charAt(0):null); 
		
				curso.setPrecio(rs.getDouble("precio"));
				curso.setActivo(rs.getBoolean("activo"));
				
				//Declaramos el objeto "profesor vacio" para insertar datos recopilados en "rs"
				Profesor profesor = new Profesor();
				
				//Modificamos las propiedades de profesor para insertar datos nuevos
				profesor.setId(rs.getInt("id_profesor"));
				profesor.setNombres(rs.getString("nombres"));
				profesor.setApellidos(rs.getString("apellidos"));
				
				//añadimos el profesor curso mediante su propiedad SET
				curso.setProfesor(profesor);
				
				//añadimos el curso(con profesor dentro) a la lista
				lista.add(curso);
			}
			
		} catch (Exception e) {
			System.out.println("Error al listar cursos: "+e.getMessage());
		}
		return lista;
	}

	
}
