package servicios;

import java.util.ArrayList;
import java.util.List;

import modelos.Libro;
import modelos.Prestamo;
import modelos.Usuario;

public class BibliotecaServicio {
	
	private List<Libro> libros = new ArrayList<>();
	private List<Usuario> usuarios = new ArrayList<>();
	private List<Prestamo> prestamos = new ArrayList<>();
	
	public void agregarLibro(Libro libro) {
		libros.add(libro);
	}
	
	public void agregarUsuario(Usuario u) {
		usuarios.add(u);
	}
	
	public void listarLibros() {
		libros.forEach(Libro::mostrarInfo);
	}
	
	public void prestarLibro(int libroId, int userId) {
		Libro l = buscarLibro(libroId);
		Usuario u = buscarUsuario(userId);
		
		if (l.estaPrestado()) 
			throw new IllegalStateException("Libro ya prestado");
		
		l.prestar();
		prestamos.add(new Prestamo(l, u));
	}
	
	public void devolverLibro(int libroId) {
		Libro l = buscarLibro(libroId);
		
		l.devolver();
		prestamos.removeIf(p-> p.getLibro().getId() == libroId);
	}
	
	public Libro buscarLibro(int libroId) {
		return libros.stream()
				.filter(l -> l.getId() == libroId)
				.findFirst()
				.orElseThrow(() -> new IllegalArgumentException("Libro no encontrado"));
				
	}
	
	public Usuario buscarUsuario(int userId) {
		return usuarios.stream()
				.filter(u -> u.getId() == userId)
				.findFirst()
				.orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado"));
	}
}
