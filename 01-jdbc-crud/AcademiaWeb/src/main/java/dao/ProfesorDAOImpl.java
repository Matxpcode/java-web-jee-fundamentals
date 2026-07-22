package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import model.Profesor;
import util.MySQLConexion;

public class ProfesorDAOImpl implements ProfesorDAO {

	// Metodo Listar Tabla Profesor Completa
	@Override
	public List<Profesor> findAll() {
		List<Profesor> listado = new ArrayList<Profesor>();

		String sql = "select * from tbl_profesor";

		try (Connection cn = MySQLConexion.getConexion();
				PreparedStatement ps = cn.prepareStatement(sql);
				ResultSet rs = ps.executeQuery();) {
			while (rs.next()) {
				Integer idProfesor = rs.getInt("id");
				String nombres = rs.getString("nombres");
				String apellidos = rs.getString("apellidos");
				String email = rs.getString("email");

				Profesor p = new Profesor(idProfesor, nombres, apellidos, email);
				listado.add(p);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return listado;
	}

	@Override
	public Integer insert(Profesor profesor) {
		Integer resultado = null;
		String sql = "insert into tbl_profesor(nombres,apellidos,email) values(?,?,?)";

		try (Connection cn = MySQLConexion.getConexion(); PreparedStatement ps = cn.prepareStatement(sql);) {
			ps.setString(1, profesor.getNombres());
			ps.setString(2, profesor.getApellidos());
			ps.setString(3, profesor.getEmail());

			resultado = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultado;
	}

	@Override
	public Profesor findById(Integer id) {
		Profesor profesor = null;

		String sql = "select * from tbl_profesor where id=?";

		try (Connection cn = MySQLConexion.getConexion();
			PreparedStatement ps = cn.prepareStatement(sql);) {
			// Primero espera los parametros antes de ejecutar
			ps.setInt(1, id);

			try (ResultSet rs = ps.executeQuery();) {
				if (rs.next()) {
					Integer idProfesor = rs.getInt("id");
					String nombres = rs.getString("nombres");
					String apellidos = rs.getString("apellidos");
					String email = rs.getString("email");

					profesor = new Profesor(idProfesor, nombres, apellidos, email);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return profesor;
	}

	@Override
	public Integer update(Profesor profesor) {
		Integer filas = null;
		String sql = "update tbl_profesor set nombres=?,apellidos=?,email=? where id=?";
		
		try(Connection cn = MySQLConexion.getConexion();
			PreparedStatement ps = cn.prepareStatement(sql)) {
			
			ps.setString(1, profesor.getNombres());
			ps.setString(2, profesor.getApellidos());
			ps.setString(3, profesor.getEmail());
			ps.setInt(4, profesor.getId());
			
			filas = ps.executeUpdate();
		} catch (Exception e) {
			System.out.println("No se modifico profesor con ID: "+profesor.getId());
		}
		return filas;
	}

	@Override
	public Integer delete(Integer id) {
		Integer resultado = null;
		String sql = "delete from tbl_profesor where id=?";
		
		try (Connection cn = MySQLConexion.getConexion();
			PreparedStatement ps = cn.prepareStatement(sql)){
			ps.setInt(1, id);
			resultado = ps.executeUpdate();
		} catch (Exception e) {
			System.out.println("No se elimino profesor con ID:"+id);
		}
		return resultado;
	}
}
