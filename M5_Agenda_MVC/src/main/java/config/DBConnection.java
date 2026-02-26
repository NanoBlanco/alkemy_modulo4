package config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

	private static DBConnection instance;
	private Connection cnx;
	
	private static final String URL = "jdbc:mysql://localhost:3306/mvc_prueba";
	private static final String USER = "root";
	private static final String PASS = "";
	
	private DBConnection() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			cnx = DriverManager.getConnection(URL,USER,PASS);
		} catch(SQLException e) {
			e.printStackTrace();
		} catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public static DBConnection getInstance(){
		if (instance == null) {
			instance = new DBConnection();
		}
		return instance;
	}
	
	public Connection getConnection() {
		return cnx;
	}
}
