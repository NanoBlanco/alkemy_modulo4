package servicios;

import java.util.ArrayList;
import java.util.List;

import modelos.Moneda;
import modelos.Usuario;

public class BilleteraServicio {
	
	private List<Usuario> usuarios = new ArrayList<>();
	
	public void agregarUsuario(Usuario u) {
		usuarios.add(u);
	}
	
	public List<Usuario> getUsuarios(){
		return usuarios;
	}
	
	public void transferir(Usuario origen, Usuario destino, Moneda m, int monto) {
		boolean ok = origen.getBilletera().retirar(m, monto);
		
		if(!ok) {
			System.out.println("Saldo Insuficiente.");
			return;
		}
		
		destino.getBilletera().depositar(m, monto);
		System.out.println("Transferencia realizada.");
	}

}
