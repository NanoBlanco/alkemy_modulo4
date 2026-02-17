package servicios;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

public class LogsTransacciones {
	
	private final Path archivoLog;
	private final DateTimeFormatter formatter;
	
	public LogsTransacciones(String nombreArchivo) {
		this.archivoLog = Paths.get("C:","datos",nombreArchivo);
		this.formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		inicializarArchivo();
	}
	
	private void inicializarArchivo() {
		try {
			if (!Files.exists(archivoLog)) {
				Files.createFile(archivoLog);
				System.out.println("Archivo de log creado "+archivoLog);
			}
		} catch(IOException e) {
			System.out.println("Error al inicializar log "+e.getMessage());
		}
	}
	
	public void escribirLog(String nivel, String mensaje) {
		try {
			String timestamp = LocalDateTime.now().format(formatter);
			String registro = String.format("[%s] [%s] %s%n", timestamp, nivel, mensaje);
			
			Files.writeString(archivoLog, registro, StandardOpenOption.CREATE, StandardOpenOption.APPEND);
		}catch(IOException e) {
			System.out.println("Error al escribir log "+e.getMessage());
		}
	}
	
	public void mostrarLogs() {
		try {
			System.out.println("Logs");
			System.out.println("-".repeat(20));
			
			List<String> lineas = Files.readAllLines(archivoLog);
			lineas.forEach(System.out::println);
			System.out.println("-".repeat(30));
			System.out.println("Total registros -> "+lineas.size());
		}catch(IOException e) {
			System.out.println("Error al leer log "+e.getMessage());
		}
	}
	
	public void buscarPorNivel(String nivel) {
		try {
			System.out.println("Logs Nivel "+nivel);
			System.out.println("-".repeat(30));
			
			try (var lineas = Files.lines(archivoLog)){
				List<String> resultados = lineas
						.filter(linea->linea.contains(nivel))
						.collect(Collectors.toList());
				if (resultados.isEmpty()) {
					System.out.println("No se encontraron logs de nivel "+nivel);
				}else {
					resultados.forEach(System.out::println);
					System.out.println("Encontrados -> "+resultados.size());
				}
			}
			
		}catch(IOException e) {
			System.out.println("Error al buscar logs "+e.getMessage());
		}
	}

}
