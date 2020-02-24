package practica4;

import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.SQLException;
/**

 * <p>En esta clase se crea la conexión a la base de datos mysql mediante el 
 * driver jdbc y los datos necesarios para la autenticación.<p>

 * @author: Antonio MuRo
 * @version: 20/02/2020/A

 */
public class Conexion {
	/**  */
	private static String url = "jdbc:mysql://localhost:3306/tiendecita?serverTimezone=UTC#&relaxAutoCommit=true";
	private static String usuario = "root";
	private static String password = "Studium2019;";
	
	/**
	 * <p>Metodo para conectar a mysql mediante driver jdbc</p>
	 *
	 * @return devuelve el resultado de la conexión
	*/
	public static Connection conectar() {
		Connection conexion = null;
		/**
		 * <p>Metodo para conectar a mysql mediante driver jdbc
		 * <a href="https://es.wikipedia.org/wiki/Java_Database_Connectivity">Wikipedia</a>
		 * </p>
		 * @param url, indica el driver usado y la dirección del servidor
		 * @param usuario, indica el usuario de mysql
		 * @param password, indica la contraseña de mysql
		 * @return devuelve el resultado de la conexión
		*/
		try {
			conexion = DriverManager.getConnection(url, usuario, password);

		} catch (SQLException e) {
			System.out.println("Ocurrió un error al conectar con la base de datos" + e.getMessage());

		}
		return conexion;
	}
}
