package conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

// se va a ocupar de realizar conexiones con la BD.
public class Conexion {
	private static final String url = "jdbc:mysql://localhost/crudhilos";
	private static final String usuario = "root";
	private static final String pswd = "AlumnoIFP";
	
	public static  Connection open() throws SQLException {
		Properties props = new Properties();
		
		
		props.setProperty("user", usuario);
		props.setProperty("password", pswd);
		props.setProperty("ssl", "true");
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		return DriverManager.getConnection(url, props);
		

	}

}
