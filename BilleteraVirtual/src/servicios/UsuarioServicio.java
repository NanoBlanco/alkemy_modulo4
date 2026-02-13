package servicios;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import excepciones.SaldoInsuficienteException;
import modelos.Moneda;
import modelos.Usuario;

public class UsuarioServicio {
	
	private List<Usuario> usuarios = new ArrayList<>();
	
	public UsuarioServicio(Usuario u) {
		if (u!=null) usuarios.add(u);
	}
	
	public void agregarUsuario(Scanner sc) {
		System.out.print("Ingresa tu edad: ");
		int edad = sc.nextInt();
		if(esMayor(edad)) {
			System.out.print("Ingrese el Id: ");
			int id = sc.nextInt();
			System.out.print("Ingrese el Nombre: ");
			String nombre = sc.next();
			if(nombre != null && !nombre.isBlank()) {			
				Usuario u = creaUsuario(id, nombre);
				usuarios.add(u);
				System.out.println("Usuario Agregado.");
			}else {
				System.out.println("Debe ingresar un nombre");
			}
		}else {
			System.out.println("Debe ser mayor de edad para abrir la cuenta");
		}
	}
	
	public Usuario creaUsuario(int id, String nombre) {
		Usuario u = new Usuario(id, nombre);
		return u;
	}
	public boolean esMayor(int edad) {
		return edad >= 18;
	}
	
	public Usuario getUsuario(int id) {
		for(Usuario u : usuarios) {
			if(u.getId() == id) {
				return u;
			}
		}
		return null;
	}
	
	public void saldosUsuario(int id) {
		Usuario u = getUsuario(id);
		if (u != null){
			u.getBilletera().mostrarSaldos();
		}else {
			System.out.println("El Id no existe");
		}
	}
	
	public void depositoUsuario(int id, String m, int monto){
		if(monto < 0) return;
		Usuario u = getUsuario(id);
		if (u != null){
			u.getBilletera().depositar(Moneda.valueOf(m), monto);
		}else {
			System.out.println("El Id no existe");
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
		return usuarios;
	}

}
