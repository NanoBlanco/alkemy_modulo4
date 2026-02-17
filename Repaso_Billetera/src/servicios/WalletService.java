package servicios;

import java.util.ArrayList;
import java.util.List;

import modelos.Moneda;
import modelos.Usuario;

public class WalletService {
	
	private List<Usuario> usuarios = new ArrayList<>();
	
	public void agregarUsuario(Usuario u) {
		usuarios.add(u);
	}
	
	public Usuario buscarUsuario(int id) {
		return usuarios.stream()
				.filter(u -> u.getId() == id)
				.findFirst()
				.orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado"));
	}

	public void transferir(int origen, int destino, Moneda m, double monto) {
		Usuario uOrigen = buscarUsuario(origen);
		Usuario uDestino = buscarUsuario(destino);
		
		uOrigen.getCuenta().retirar(m, monto);
		uDestino.getCuenta().depositar(m, monto);
	}
}
