package config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
	
	private static Connection cnx = null;
	
	private DBConnection() {
    	try {
    		
    		//1. Cargamos el driver
    		Class.forName("com.mysql.cj.jdbc.Driver");
    		
    		//2. Creamos la conexion
    		cnx = DriverManager.getConnection("jdbc:mysql://localhost:3306/e_wallet","root","");
    		
			//3. Verificamos
			if(cnx != null) {
				System.out.println("Conexion establecida");
			}else {
				System.out.println("Fallo la conexion");
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
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
