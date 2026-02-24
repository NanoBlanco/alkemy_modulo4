package config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public final class DBConnection {
	
	private static final String URL = "jdbc:mysql://localhost:3306/e_wallet?useSSL=false";
	private static final String USER = "root";
	private static final String PASS = "";
	private static volatile DBConnection instance;
	private Connection cnx;
	
	// Constructor privado: nadie puede instanciar desde afuera
	private DBConnection() throws SQLException {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			cnx = DriverManager.getConnection(URL, USER, PASS);
			System.out.println("Conexion establecida.");
		}catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	// Punto de acceso global (Thread-safe)
	public static DBConnection getInstance() throws SQLException {
		if (instance == null) {
			
			synchronized (DBConnection.class) {
				if (instance == null) {					
					instance = new DBConnection();
				}
			}
		}
		return instance;
	}
	
	// Retorna la conexion; la restablece si fue cerrada
	public Connection getConnection() throws SQLException {
		if (cnx == null || cnx.isClosed()) {
			cnx = DriverManager.getConnection(URL, USER, PASS);
			System.out.println("Conexión Restablecida.");
		}
		return cnx;
	}
	
	// Cerrar conexión (llamar al terminar la aplicación)
	public void closeCnx() {
		try {
			if(cnx != null && !cnx.isClosed()) {
				cnx.close();
				System.out.println("Conexión cerrada");
			}
		} catch(SQLException e) {
			System.out.println("Error al cerrar: "+e.getMessage());
			
		}
	}
	
}
