package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.cj.MysqlConnection;

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

	@Override
	public Integer insertarCursos(Curso curso) {
		Integer filas = null;
		
		String sql = "insert into tbl_curso(nombre, nivel, precio, id_profesor) values (?,?,?,?)";
		
		try (Connection cn = MySQLConexion.getConexion();
			PreparedStatement ps = cn.prepareStatement(sql);){
			ps.setString(1,curso.getNombre());
			ps.setString(2,String.valueOf(curso.getNivel()));
			ps.setDouble(3, curso.getPrecio());
			ps.setInt(4, curso.getProfesor().getId());
			filas = ps.executeUpdate();
		} catch (SQLException e) {
			System.out.println("Error al insertar curso: "+e.getMessage());
		}catch (Exception e) {
			System.out.println("Error general: "+e.getMessage());
		}
		return filas;
	}

	@Override
	public Curso buscarPorId(Integer id) {
		Curso c = null;
		String sql = "select c.id,c.nombre,c.nivel,c.precio,c.activo,p.id as id_profesor,p.nombres,p.apellidos\r\n"
				+ "from tbl_curso c\r\n"
				+ "join tbl_profesor p \r\n"
				+ "	on c.id_profesor=p.id\r\n"
				+ "where c.id=?";
		
		try (Connection cn = MySQLConexion.getConexion();
			PreparedStatement ps = cn.prepareStatement(sql);){
			ps.setInt(1, id);
			
			try (ResultSet rs = ps.executeQuery()){
				if (rs.next()) {
					c = new Curso();
					
					String nivelStr = rs.getString("nivel");
					Character nivel = (nivelStr!=null&&!nivelStr.isEmpty()?nivelStr.charAt(0):null);
					
					c.setId(rs.getInt("id"));
					c.setNombre(rs.getString("nombre"));
					c.setNivel(nivel);
					c.setPrecio(rs.getDouble("precio"));
					c.setActivo(rs.getBoolean("activo"));
					
					Profesor p = new Profesor();
					p.setId(rs.getInt("id_profesor"));
					p.setNombres(rs.getString("nombres"));
					p.setApellidos(rs.getString("apellidos"));
					
					c.setProfesor(p);
				}
			} catch (SQLException e) {
				System.out.println("Error al buscar curso por id: "+e.getMessage());
			}
			
		} catch (Exception e) {
			System.out.println("Error general: "+e.getMessage());
		}
		return c;
	}

	
	@Override
	public Integer actualizarCursos(Curso curso) {
		Integer resultado = null;
		
		String sql = "UPDATE tbl_curso "
				+ "SET "
				+ "nombre = ?, "
				+ "nivel = ?, "
				+ "precio = ?, "
				+ "id_profesor = ? "
				+ "WHERE id = ?";
		
		try(Connection cn = MySQLConexion.getConexion();
			PreparedStatement ps = cn.prepareStatement(sql);) {
			ps.setString(1, curso.getNombre());
			ps.setString(2, String.valueOf(curso.getNivel()));
			
			ps.setDouble(3, curso.getPrecio());
			ps.setInt(4, curso.getProfesor().getId());	//foreign key
			ps.setInt(5, curso.getId()); 	//ID del curso para el WHERE
			
			resultado = ps.executeUpdate();
					
		} catch (SQLException e) {
			System.out.println("Error al actualizar el curso: "+e.getMessage());
		}
		return resultado;
	}


	@Override
	public Integer desactivarCursos(Integer id) {
		Integer resultado = null;
		
		//Usamos UPDATE en lugar de DELETE, para cambiar el estado a 0
		//Esto es "BAJA LOGICA" para preservar el historial -> desaparece del listado pero existe en la BD -> UPDATE 
		//Su contraparte seria "BAJA FISICA" -> desaparece definitivamente de la BD -> DELETE
		String sql = "UPDATE tbl_curso SET activo=0 WHERE id=?";
		
		try(Connection cn = MySQLConexion.getConexion();
			PreparedStatement ps = cn.prepareStatement(sql);) {
			ps.setInt(1, id);
			resultado = ps.executeUpdate();
			
		} catch (SQLException e) {
			System.out.println("Error al desactivar el curso: "+e.getMessage());
		}
		return resultado;
	}
	
}
