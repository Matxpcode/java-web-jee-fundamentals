package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQLConexion {
	public static Connection getConexion() {
		Connection cn = null;
		
		try {
			//Declaramos el driver
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			//Atributos de conexion
			String url = "jdbc:mysql://localhost:3306/db_academia?useSSL=false&serverTimeZone=UTC";
			String user = "root";
			String password = "mysql";
			
			//Declaramos los "atributos de conexion" al driver
			cn = DriverManager.getConnection(url,user,password);
		} catch (ClassNotFoundException e) {
			//Error del driver
			System.out.println("Error del manejo de driver: "+e.getMessage());
		} catch (SQLException e) {
			//Error de conexion a la bd
			System.out.println("Error de conexion a la bd: "+e.getMessage());
		}catch (Exception e) {
			System.out.println("Error general: "+e.getMessage());
		}
		return cn;
	}
}
