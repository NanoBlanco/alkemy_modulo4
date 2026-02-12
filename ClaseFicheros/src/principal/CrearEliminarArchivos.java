package principal;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.nio.file.StandardOpenOption;

public class CrearEliminarArchivos {

	public static void main(String[] args) {
		try {
			/*
			// Crear un directorio
			Path directorioNuevo = Paths.get("C:", "carpeta_prueba");
			if (!Files.exists(directorioNuevo)) {
				Files.createDirectory(directorioNuevo);
				System.out.println("Directorio creado");
			}
			
			//Crear directorios anidados
			Path directoriosAnidados = Paths.get("C:", "carpeta1","carpeta2","carpeta3");
			Files.createDirectories(directoriosAnidados);
			
			// Crear archivo
			Path archivo = Paths.get("C:", "carpeta_prueba","datos.txt");
			Files.createFile(archivo);
			System.out.println("Archivo creado");
			
			//Eliminar archivo
			Path eliminar = Paths.get("C:","datos","archivo.txt");
			Files.deleteIfExists(eliminar);
			System.out.println("Archivo elimimnado");
			//Copiar archivo
			Path origen = Paths.get("C:", "carpeta_prueba","datos.txt");
			System.out.println("Existe "+Files.exists(origen));
			Path destino = Paths.get("C:","carpeta1","copia_datos.txt");
			Files.copy(origen, destino, StandardCopyOption.REPLACE_EXISTING);
			
			// Mover / Renombrar
			Files.move(destino, origen, StandardCopyOption.ATOMIC_MOVE);
			 */
			//escribirTexto();
			anexarContenido();
		}catch(IOException e) {
			System.out.println("Error: "+e.getMessage());
		}

	}
	
	// Escribir archivo
	static void escribirTexto() throws IOException {
		String contenido = "Este es el contenido del archivo\n"+"Con múltiples líneas\n"+"De texto.";
		
		Path archivo = Paths.get("C:", "carpeta_prueba","datos.txt");
		Files.writeString(archivo, contenido);
	}
	
	//Anexar líneas
	static void anexarContenido() throws IOException{
		String nuevaLinea = "Linea adicional\n";
		Path archivo = Paths.get("C:", "carpeta_prueba","datos.txt");
		Files.writeString(archivo, nuevaLinea, StandardOpenOption.CREATE, StandardOpenOption.APPEND);
	}

}
