package dao;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import interfaces.IContactoDAO;
import modelos.Contacto;

public class ContactoDAO implements IContactoDAO {

	// De esta manera permite concurrencia web, multiples requests, es thread-safe.
	private final List<Contacto> contactos = Collections.synchronizedList(new ArrayList<>());
	
	public ContactoDAO() {}
		
	@Override
	public Contacto buscarPorId(Integer id) {
		return contactos.stream()
				.filter(c -> c.getId() == id)
				.findFirst()
				.orElse(null);
	}

	@Override
	public List<Contacto> listar() {
		return contactos;
	}

	@Override
	public void guardar(Contacto entidad) {
		int id = contactos.stream()
				.mapToInt(Contacto::getId)
				.max().orElse(0)+1;
		
		entidad.setId(id);
		contactos.add(entidad);
	}

	@Override
	public void actualizar(Contacto entidad) {
		Contacto actual = buscarPorId(entidad.getId());
		if(actual != null) {
			actual.setNombre(entidad.getNombre());
			actual.setCorreo(entidad.getCorreo());
		}
	}

	@Override
	public void eliminar(Integer id) {
		int idx = contactos.indexOf(id);
		contactos.remove(idx);
	}

	@Override
	public Contacto buscarContacto(String correo) {
		return contactos.stream()
				.filter(c -> c.getCorreo().equals(correo))
				.findFirst()
				.orElse(null);
	}

}
