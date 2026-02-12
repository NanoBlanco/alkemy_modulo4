package config;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class HttpClientBasico {

	public static void main(String[] args) throws InterruptedException {
		// Crear el cliente HTTP
		HttpClient client = HttpClient.newHttpClient();
		
		// Crear la petición
		HttpRequest request = HttpRequest.newBuilder()
				.uri(URI.create("https://jsonplaceholder.typicode.com/users/1"))
				.GET()
				.build();
		try {
			// Enviar la respuesta
			HttpResponse<String> response = client.send(request, 
							HttpResponse.BodyHandlers.ofString());
			
			System.out.println("Código de estado: "+response.statusCode());
			System.out.println("Cuerpo de respuesta: ");
			System.out.println(response.body());
		}catch (IOException | InterruptedException e) {
			System.err.println("Error en la petición: ");
		}

	}

}
