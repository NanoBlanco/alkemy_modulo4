package principal;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class EjemplosPaths {

	public static void main(String[] args) {
		// Crear un Path
		Path ruta1 = Paths.get("Documentos","MyKey.txt");
		Path ruta2 = Paths.get("C:","datos","boletas.csv");
		
		System.out.println("Ruta Completa " + ruta1.toAbsolutePath());
		System.out.println("Nombre Archivo "+ ruta1.getFileName());
		System.out.println("Directorio padre: "+ruta1.getParent());
		
		//Verificar existencia
		boolean existe = Files.exists(ruta1);
		boolean esDirectorio = Files.isDirectory(ruta1);
		boolean esArchivo = Files.isRegularFile(ruta1);
		
		System.out.println("Existe "+existe);
		System.out.println("Es directorio "+esDirectorio);
		System.out.println("Es archivo "+esArchivo);
	}

}
