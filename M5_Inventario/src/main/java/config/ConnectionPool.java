package config;

import java.sql.Connection;
import java.sql.SQLException;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public class ConnectionPool {
	
	private static volatile ConnectionPool instance;
	private final HikariDataSource dataSource;
	
	private ConnectionPool() {
		// Cargar librería
		HikariConfig config = new HikariConfig();
		
		// Configuración de conexión
		config.setJdbcUrl("jdbc:mysql://localhost:3306/inventario2");
		config.setUsername("root");
		config.setPassword("");
		config.setDriverClassName("com.mysql.cj.jdbc.Driver");
		
		// Parámetros del Pool
		config.setMaximumPoolSize(10); // máx conexiones simultáneas
		config.setMinimumIdle(2); // conexiones mínimas en espera
		config.setConnectionTimeout(30000); // espera max para obtener conexión
		config.setIdleTimeout(60000); // tiempo máxmimo inactivo (10 min)
		config.setMaxLifetime(180000); // vida max de conexión (30 min)
		
		// Pool name para diagnostico
		config.setPoolName("InventarioDB");
		
		// Consulta de validación
		config.setConnectionTestQuery("SELECT 1");
		this.dataSource = new HikariDataSource(config);
		System.out.println("[HikariCP] Pool Inicializado");
	}

	public static ConnectionPool getInstance() {
		if (instance == null) {
			synchronized (ConnectionPool.class) {
				if (instance == null) {
					instance = new ConnectionPool();
				}
			}
		}
		return instance;
	}
	
	public Connection getConnection() throws SQLException {
		return dataSource.getConnection();
	}
	
	public void close() {
		if (dataSource != null && !dataSource.isClosed()) {
			dataSource.close();
		}
	}
}
