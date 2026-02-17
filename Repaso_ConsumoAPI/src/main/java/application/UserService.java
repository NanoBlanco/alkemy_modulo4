package application;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import infraestructure.ApiClient;
import infraestructure.UserDTO;

public class UserService {
	
	private ApiClient client;
	
	public UserService(ApiClient client) {
		this.client = client;
	}

	public List<UserDTO> obtenerUsuarios() throws Exception {
		
		String json = client.get("http://jsonplaceholder.typicode.com/users"); 
		
		JSONArray array = new JSONArray(json);
		
		List<UserDTO> lista = new ArrayList<>();
		
		for(int i = 0; i < array.length(); i++) {
			JSONObject obj = array.getJSONObject(i);
			
			lista.add(new UserDTO(
					obj.getInt("id"),
					obj.getString("name"),
					obj.getString("email")
					));
		}
		
		return lista;
	}
}
