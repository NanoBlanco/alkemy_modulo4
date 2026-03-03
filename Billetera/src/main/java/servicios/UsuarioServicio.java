package servicios;

import java.util.ArrayList;
import java.util.List;

import dao.UserDAO;
import excepciones.SaldoInsuficienteException;
import modelos.Moneda;
import modelos.Usuario;

public class UsuarioServicio {
	
	private final UserDAO dao;
		
	public UsuarioServicio(UserDAO dao) {this.dao = dao;}
	
	public Usuario login(String user, String pass) {
		Usuario u = dao.buscarUsuario(user);
		if(u !=null && u.getClave().equals(pass)) {
			return u;
		}
		return null;
	}
	
	public void agregarUsuario(Usuario u) { dao.guardar(u); }
	
	public Usuario getUsuario(int id) { return dao.buscarPorId(id); }
	
	public List<Usuario> getUsuarios(){ return dao.listar(); }
	
	public void actualizar(Usuario u) { dao.actualizar(u); }
	
	public void eliminar(int id) { dao.eliminar(id); }
	
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
	
	public List<String> validarCredenciales(String user, String pass) {
		
		List<String> errores = new ArrayList<>();
		
		if(user.length() < 5 || user.length() > 20) {
			errores.add("El usuario debe tener entre 5 y 20 caracteres");
		}
		
		if(!user.matches("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$")) {
			errores.add("El usuario solo puede contener letras, numeros y guión bajo");
		}
		
		if(pass.length() < 5) {
			errores.add("La contraseña debe tener al menos 4 caracteres");
		}
		
		if(!pass.matches(".*[A-Z].*") || !pass.matches(".*[0-9].*")) {
			errores.add("La contraseña debe contener al menos una mayúscula y un número.");
		}
		return errores;
	}
	
	
	public List<String> validarCredenciales(String nombre, String user, String pass) {
		
		List<String> errores = new ArrayList<>();
		
		if(nombre.length() < 5 || nombre.length() > 50) {
			errores.add("El nombre debe tener entre 5 y 50 caracteres");
		}
		
		if(!nombre.matches("^[A-Za-zÁÉÍÓÚáéíóúÑñ]+(?: [A-Za-zÁÉÍÓÚáéíóúÑñ]+){0,9}$")) {
			errores.add("El nombre solo puede contener letras");
		}
		
		if(user.length() < 5 || user.length() > 20) {
			errores.add("El usuario debe tener entre 5 y 20 caracteres");
		}
		
		if(!user.matches("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$")) {
			errores.add("El usuario solo puede contener letras, numeros y guión bajo");
		}
		
		if(pass.length() < 5) {
			errores.add("La contraseña debe tener al menos 4 caracteres");
		}
		
		if(!pass.matches(".*[A-Z].*") || !pass.matches(".*[0-9].*")) {
			errores.add("La contraseña debe contener al menos una mayúscula y un número.");
		}
		return errores;
	}

}
