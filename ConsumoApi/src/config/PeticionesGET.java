package config;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class PeticionesGET {

	private static final HttpClient client = HttpClient.newHttpClient();
	private static final String url = "https://jsonplaceholder.typicode.com";
	
	public static void obtenerUsuario(int id) {
		try {
			HttpRequest request = HttpRequest.newBuilder()
				.uri(URI.create(url+"/users/"+id))
				.header("Aceept", "application/json")
				.GET()
				.build();

			HttpResponse<String> response = client.send(request, 
							HttpResponse.BodyHandlers.ofString());
			
			if(response.statusCode() == 200) {
				System.out.println("Usuario encontrado.");
				System.out.println(response.body());				
			}else {
				System.out.println("Error: "+response.statusCode());
			}
		}catch (IOException | InterruptedException e) {
			System.err.println("Error en la petición: ");
		}

	}
	
	public static void buscarPost(int userId) {
		try {
			
			
					
			HttpRequest request = HttpRequest.newBuilder()
				.uri(URI.create(url+"/posts?userId="+userId))
				.GET()
				.build();

			HttpResponse<String> response = client.send(request, 
							HttpResponse.BodyHandlers.ofString());
			
			if(response.statusCode() == 200) {
				System.out.println("Posts del usuario "+userId+": ");
				System.out.println(response.body());				
			}else {
				System.out.println("Error: "+response.statusCode());
			}
		}catch (IOException | InterruptedException e) {
			System.err.println("Error en la petición: ");
		}

	}
	
	public static void main(String[] args) {		
		obtenerUsuario(1);
		buscarPost(1);
	}

}
