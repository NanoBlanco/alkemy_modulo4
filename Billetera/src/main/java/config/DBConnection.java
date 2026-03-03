package config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
	
	private static final String URL =
	        "jdbc:mysql://localhost:3306/e_wallet";
	    private static final String USER = "root";
	    private static final String PASS = "";
	    
	static {
    	try {    		
    		// load driver
    		Class.forName("com.mysql.cj.jdbc.Driver");
    		
		} catch(ClassNotFoundException e) {
			System.out.println(e.getMessage());
		}
        
    }
    
    public static Connection getConnection() throws SQLException {
    	return DriverManager.getConnection(URL,USER,PASS);    }
	
	
}
