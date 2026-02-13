package config;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import modelos.Usuario;

public class HttpClientBasico {

	public static void main(String[] args) throws InterruptedException {
		// Crear el cliente HTTP
		HttpClient client = HttpClient.newHttpClient();
		
		// Crear la petición
		HttpRequest request = HttpRequest.newBuilder()
				.uri(URI.create("https://jsonplaceholder.typicode.com/users"))
				.GET()
				.build();
		try {
			// Enviar la respuesta
			HttpResponse<String> response = client.send(request, 
							HttpResponse.BodyHandlers.ofString());
			
			List<Usuario> lista = new ArrayList<>();
			
			JSONArray arreglo = new JSONArray(response.body());
			
			for(int i = 0; i < arreglo.length(); i++) {	
				JSONObject obj = arreglo.getJSONObject(i);
				int id = obj.getInt("id");
				String name = obj.getString("name");
				String username = obj.getString("username");
				String email = obj.getString("email");
				String phone = obj.getString("phone");
				
				lista.add(new Usuario(id, name, username, email, phone));
			}
			
			
			System.out.println("Código de estado: "+response.statusCode());
			System.out.println("Cuerpo de respuesta: ");
			lista.forEach(System.out::println);
		}catch (IOException | InterruptedException e) {
			System.err.println("Error en la petición: ");
		}

	}

}
