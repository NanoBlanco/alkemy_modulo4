package servicios;

import java.util.List;
import java.util.Scanner;

import excepciones.SaldoInsuficienteException;
import modelos.Moneda;
import modelos.UserDAO;
import modelos.Usuario;

public class UsuarioServicio {
	
	//private List<Usuario> usuarios = new ArrayList<>();
	private UserDAO dao = new UserDAO();
	
	/*
	public UsuarioServicio(Usuario u) {
		if (u!=null) usuarios.add(u);
	}
	*/
	
	public Usuario login(String user, String pass) {
		Usuario u = dao.buscarUsuario(user);
		if(u!=null && u.getClave().equals(pass)) return u;
		return null;
	}
	
	public void valoresUsuario(Scanner sc) {
		System.out.print("Ingresa tu edad: ");
		int edad = sc.nextInt();
		if(esMayor(edad)) {
			System.out.print("Ingrese el Id: ");
			int id = sc.nextInt();
			System.out.print("Ingrese el Nombre: ");
			String nombre = sc.next();
			if(nombre != null && !nombre.isBlank()) {
				Usuario u = new Usuario();
				//Usuario u = new Usuario(id, nombre); -- Por ahora
				// usuarios.add(u);
				System.out.println("Usuario Agregado.");
			}else {
				System.out.println("Debe ingresar un nombre");
			}
		}else {
			System.out.println("Debe ser mayor de edad para abrir la cuenta");
		}
	}
	
	public void agregarUsuario(Usuario u) {
		dao.guardar(u);
		System.out.println("Usuario Agregado.");
	}
	
	public boolean esMayor(int edad) {
		return edad >= 18;
	}
	
	public Usuario getUsuario(int id) {
		/*
		for(Usuario u : usuarios) {
			if(u.getId() == id) {
				return u;
			}
		}
		return null;
		*/
		return dao.buscarPorId(id);
		
	}
	
	public void saldosUsuario(int id) {
		Usuario u = getUsuario(id);
		if (u != null){
			u.getBilletera().mostrarSaldos();
		}else {
			System.out.println("El Id no existe");
		}
	}
	
	public void depositoUsuario(Usuario u, String m, int monto){
		if(monto < 0) return;
		if (u != null){
			u.getBilletera().depositar(Moneda.valueOf(m), monto);
		}else {
			System.out.println("El Usuario no existe");
		}
	}
	
	public void retiroUsuario(int id, String m, int monto) throws SaldoInsuficienteException {
		if(monto < 0) return;
		Usuario u = getUsuario(id);
		if (u != null){
			boolean exito = u.getBilletera().retirar(Moneda.valueOf(m), monto); 
			if (!exito) throw new SaldoInsuficienteException("El monto "+monto+" para la moneda "+Moneda.valueOf(m)+" NO es suficiente.");
		}else {
			System.out.println("El Id no existe");
		}
	}
	
	public List<Usuario> getUsuarios(){
		return dao.listar();
	}

}
