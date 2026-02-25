package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import config.DBConnection;
import interfaces.IBilletera;
import modelos.Moneda;

public class BilleteraDAO implements IBilletera{

	private final Connection cnx;
	
	public BilleteraDAO() {
		this.cnx = DBConnection.getConnection();
	}
	
	@Override
	public void generarBilletera(int id) {
		try (PreparedStatement ps = cnx.prepareStatement("INSERT INTO billeteras (id_usuario, saldo, moneda) VALUES (?, ?, ?)")) {
			ps.setInt(1, id);
			ps.setInt(2, 0);
			ps.setString(3, "CLP");
			ps.executeUpdate() ;
			ps.setInt(1, id);
			ps.setInt(2, 0);
			ps.setString(3, "USD");
			ps.executeUpdate() ;
			ps.setInt(1, id);
			ps.setInt(2, 0);
			ps.setString(3, "EUR");
			ps.executeUpdate() ;
		}catch(SQLException e) {
			System.out.println("Error al generar la billetera: "+e.getMessage());
		}
		
	}

	@Override
	public void transaccion(int id, String m, int monto, String operacion) {
		if (operacion.equalsIgnoreCase("deposito")) {
			try (PreparedStatement ps = cnx.prepareStatement("INSERT INTO transacciones (id_usuario, tipo_transaccion, moneda, monto) VALUES (?, ?, ?, ?)");
					PreparedStatement ps2 = cnx.prepareStatement("UPDATE billeteras SET saldo = ? WHERE id_usuario = ? AND moneda = ?")) {
				ps.setInt(1, id);
				ps.setString(2, operacion);
				ps.setString(3, m);
				ps.setInt(4, monto);
				ps.executeUpdate();
				// Actualiza Billetera
				ps2.setInt(1, monto);
				ps2.setInt(2, id);
				ps2.setString(3, m);
				ps2.executeUpdate();
			}catch(SQLException e) {
				System.out.println("Error al operar: "+e.getMessage());
			}
		}else {
			
		}
		
	}

	@Override
	public void transferir(int origen_id, int destino_id, Moneda m, int monto) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int obtenerSaldo(int id) {
		// TODO Auto-generated method stub
		return 0;
	}

}
