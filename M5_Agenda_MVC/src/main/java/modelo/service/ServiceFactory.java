package modelo.service;

public class ServiceFactory {
	
	private static final ContactoService service = new ContactoService();
	
	public static ContactoService getContactoService() {
		return service;
	}

}
