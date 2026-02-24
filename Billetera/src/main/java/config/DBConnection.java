package config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
	
	private static Connection cnx = null;
	
	private DBConnection() {
    	try {
    		
    		// Cargamos el driver
    		Class.forName("com.mysql.jdbc.Driver");
    		
    		//Creamos la conexion
    		cnx = DriverManager.getConnection("jdbc:mysql://localhost:3306/e-wallet","root","");
    		
			//Verificamos
			if(cnx != null) {
				System.out.println("Conexion establecida");
			}else {
				System.out.println("Fallo la conexion");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch(ClassNotFoundException e) {
			System.out.println(e.getMessage());
		}
        
    }
    
    public static Connection getConnection() {
    	if(cnx == null) {
    		new DBConnection();
    	}
    	return cnx;
    }
	
	
}
