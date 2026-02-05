package principal;

import java.util.HashMap;
import java.util.Scanner;

public class EjercicioHashMap {

	static HashMap<String, Producto> inventario = new HashMap<>();
	static Scanner sc = new Scanner(System.in);	
	
	public static void main(String[] args) {

		int opcion;
		registrarProductos();
		do {
			mostrarMenu();
			opcion = leerEntero("Seleccione una opción: ");
			
			switch(opcion) {
				case 1 -> agregarProducto();
				case 2 -> buscarProducto();
				case 3 -> actualizarPrecio();
				case 4 -> actualizarStock();
				case 5 -> eliminarProducto();
				case 6 -> listarProductos();
				case 7 -> valorTotalInv();
				case 0 -> System.out.println("Saliendo...");
				default -> System.out.println("Ingrese una opción válida.");
			}
			
		}while(opcion != 0);
		
		
	}

	static void registrarProductos() {
		inventario.put("P001", new Producto("P001","Laptop Dell", 1099.90, 10));
		inventario.put("P002", new Producto("P002","Mouse Gamer", 25.50, 50));
		inventario.put("P003", new Producto("P003","Teclado Mecánico", 75.90, 30));
		inventario.put("P004", new Producto("P004","Monitor 25 pulg", 199.90, 15));
		inventario.put("P005", new Producto("P005","Laptop Lenovo", 1299.90, 10));
	}
	
	static void mostrarMenu() {
		System.out.println("\nControl de Asistencias");
		System.out.println("-".repeat(30));
		System.out.println("1. Agregar Producto");
		System.out.println("2. Buscar producto");
		System.out.println("3. Actualizar precio");
		System.out.println("4. Actualizar stock");
		System.out.println("5. Eliminar producto");
		System.out.println("6. Listar productos");
		System.out.println("7. Valor total del inventario");
		System.out.println("0. Salir");
	}
	
	static int leerEntero(String mensaje) {
		while(true) {
			try {				
				System.out.println(mensaje);
				return Integer.parseInt(sc.nextLine());
			}catch (NumberFormatException e) {
				System.out.println("Ingrese un número válido");
			}
		}
	}
	
	static void agregarProducto() {
		System.out.print("Codigo: ");
		String codigo = sc.nextLine();
		
		if (inventario.containsKey(codigo)) {
			System.out.println("El código ya existe");
		}else {
			System.out.println("Nombre");
			String nombre = sc.nextLine();
			System.out.print("Precio: ");
			double precio = sc.nextDouble();
			System.out.print("Stock: ");
			int stock = sc.nextInt();
			
			inventario.put(codigo, new Producto(codigo, nombre, precio, stock));
			System.out.println("Producto agregado");
		}
	}
	
	static void buscarProducto() {
		System.out.print("Codigo: ");
		String codigo = sc.nextLine();
		
		Producto buscado = inventario.get(codigo);
		
		if(buscado!=null) {
			System.out.println(buscado.mostrarDetalle());
		}else {
			System.out.println("Codigo NO encontrado.");
		}
	}

	static void actualizarPrecio() {
		System.out.print("Codigo: ");
		String codigo = sc.nextLine();
		
		if (inventario.containsKey(codigo)) {
			System.out.print("Precio nuevo: ");
			double nuevo = sc.nextDouble();
			inventario.get(codigo).setPrecio(nuevo);
			System.out.println("Precio actualizado");
		}else {
			System.out.println("Codigo NO encontrado.");
		}
	}
	
	static void actualizarStock() {
		System.out.print("Codigo: ");
		String codigo = sc.nextLine();
		
		if (inventario.containsKey(codigo)) {
			System.out.print("Stock actual: "+inventario.get(codigo).getStock());
			System.out.print("\nIngresa nuevo stock");
			int nuevo = sc.nextInt();
			inventario.get(codigo).setStock(nuevo);
			System.out.println("Stock actualizado");
		}else {
			System.out.println("Codigo NO encontrado.");
		}
	}
	
	static void eliminarProducto() {
		System.out.print("Codigo: ");
		String codigo = sc.nextLine();
		
		Producto buscado = inventario.get(codigo);
		
		if(buscado!=null) {
			inventario.remove(codigo);
			System.out.println("Producto Eliminado");
		}else {
			System.out.println("Codigo NO encontrado.");
		}
	}
	
	static void listarProductos() {
		if (inventario.isEmpty()) {
			System.out.println("No hay productos que mostrar");
		}else {
			System.out.println("Inventario Completo");
			System.out.printf("%-10s %-25s %10s %10s %12s\n","Código","Nombre","Precio","Stock", "Valor");
			System.out.println("=".repeat(70));
			for(Producto p: inventario.values()) {
				System.out.printf("%-10s %-25s $%9.2f %10d %11.2f\n", p.getCodigo(), p.getNombre(), p.getPrecio(), p.getStock(), p.getValorTotal());
			}
		}
	}
	
	static void valorTotalInv() {
		
	}
}

class Producto {
	
	private String codigo;
	private String nombre;
	private double precio;
	private int stock;
	
	public Producto(String codigo, String nombre, double precio, int stock) {
		this.codigo = codigo;
		this.nombre = nombre;
		this.precio = precio;
		this.stock = stock;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}
	
	public double getValorTotal() {
		return precio * stock;
	}
	
	public String mostrarDetalle() {
		return String.format("Codigo: %s\nNombre: %s\nPrecio: %.2f\nStock: %d\nValor Total: %.2f", codigo, nombre, precio, stock, getValorTotal());
	}
	
}
