package infraestructure;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ApiClient {
	
	private HttpClient client = HttpClient.newHttpClient();
	
	public String get(String url) throws IOException, InterruptedException {
		
		HttpRequest request = HttpRequest.newBuilder(URI.create(url))
				.GET()
				.build();
		
		
		HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
		
		if (response.statusCode() != 200) throw new RuntimeException("Error HTTP");
		
		return response.body();
	}
	

}
