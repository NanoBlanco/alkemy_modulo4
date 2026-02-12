package config;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import org.json.JSONObject;

public class ParseoJSON {

	private static final HttpClient client = HttpClient.newHttpClient();
	private static final String url = "https://jsonplaceholder.typicode.com";
	
	public static void parsearUsuario(int id) {
		try {
			HttpRequest request = HttpRequest.newBuilder()
				.uri(URI.create(url+"/users/"+id))
				.header("Accept", "application/json")
				.GET()
				.build();

			HttpResponse<String> response = client.send(request, 
							HttpResponse.BodyHandlers.ofString());
			
			if(response.statusCode() == 200) {
				System.out.println("Usuario encontrado.");
				JSONObject usuario = new JSONObject(response.body());
				int userId = usuario.getInt("id");
				String nombre = usuario.getString("name");
				String correo = usuario.getString("email");
				
				// Objetos anidados
				JSONObject direccion = usuario.getJSONObject("address");
				
				String calle = direccion.getString("street");
				String ciudad = direccion.getString("city");
				
				System.out.println("userId : "+userId);
				System.out.println("Nombre: "+nombre);
				System.out.println("Correo: "+correo);
				System.out.println("Dirección: "+calle+", "+ciudad);
			}else {
				System.out.println("Error: "+response.statusCode());
			}
		}catch (IOException | InterruptedException e) {
			System.err.println("Error en la petición: ");
		}

	}

	public static void crearJSON(int userId) {
		JSONObject post = new JSONObject();
		post.put("title", "Otro titulo");
		post.put("body", "Otro Contenido para el cuerpo");
		post.put("userId", userId);
		
		System.out.println("JSON creado:");
		System.out.println(post.toString(2));
	}
	
	public static void main(String[] args) {
		parsearUsuario(1);
		System.out.println();
		crearJSON(2);
	}

}
