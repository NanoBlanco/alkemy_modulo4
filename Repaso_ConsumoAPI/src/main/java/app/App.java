package app;

import application.UserService;
import infraestructure.ApiClient;

public class App {

	public static void main(String[] args) throws Exception {
		
		ApiClient client = new ApiClient();
		UserService us = new UserService(client);
		
		us.obtenerUsuarios().forEach(u->System.out.println(u.toString()));
		
	}

}
