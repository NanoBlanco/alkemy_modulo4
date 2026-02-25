package servicios;

import java.util.List;
import java.util.Scanner;

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
			System.out.println("Usuario Encontrado");
			return u;
		}else {
			System.out.println("Usuario no encontrado");
		}
		return null;
	}
	
	public void agregarUsuario(Usuario u) {
		dao.guardar(u);
		System.out.println("Usuario Agregado.");
	}
	
	public Usuario getUsuario(int id) {
		return dao.buscarPorId(id);
	}
	
	public List<Usuario> getUsuarios(){
		return dao.listar();
	}
	
	public void actualizar(Usuario u) {
		dao.actualizar(u);
	}
	
	public void eliminar(int id) {
		dao.eliminar(id);
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

}
