package config;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class PeticionesPOST {

	private static final HttpClient client = HttpClient.newHttpClient();
	private static final String url = "https://jsonplaceholder.typicode.com";
	
	public static void crearPost() {
		try {
			//Datos a enviar
			String jsonBody = """
				{
					"title": "Mi nuevo post",
					"body": "Este es el contenido de mi post",
					"userId": 1
				}""";
			
			HttpRequest request = HttpRequest.newBuilder()
				.uri(URI.create(url+"/posts"))
				.header("Content-Type", "application/json")
				.POST(HttpRequest.BodyPublishers.ofString(jsonBody))
				.build();

			HttpResponse<String> response = client.send(request, 
							HttpResponse.BodyHandlers.ofString());
			
			System.out.println("Codigo: "+response.statusCode());
			System.out.println("Respuesta: ");
			System.out.println(response.body());				
		}catch (IOException | InterruptedException e) {
			System.err.println("Error en la petición: ");
		}

	}
	
	public static void main(String[] args) {
		crearPost();
	}

}
