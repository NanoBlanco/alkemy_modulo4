package app;

import modelos.Producto;
import modelos.Usuario;
import repositorios.RepositorioEnMemoria;
import servicios.InventarioService;

public class App {

	public static void main(String[] args) {
		
		var repoProductos = new RepositorioEnMemoria<Producto, Integer>();
		var servProductos = new InventarioService<Producto, Integer>(repoProductos);
		
		servProductos.guardar(new Producto(1, "Mouse", 15000));
		servProductos.guardar(new Producto(2,"Teclado", 25000));
		
		servProductos.listar().forEach(Producto::mostrarInfo);
		
		System.out.println("\nUsuarios");
		System.out.println("=".repeat(20));
		var repoUsuarios = new RepositorioEnMemoria<Usuario, Integer>();
		var servUsuarios = new InventarioService<Usuario, Integer>(repoUsuarios);
		
		servUsuarios.guardar(new Usuario(1, "Renny"));
		servUsuarios.guardar(new Usuario(2,"Daniel"));
		
		servUsuarios.listar().forEach(Usuario::mostrarInfo);

	}

}
