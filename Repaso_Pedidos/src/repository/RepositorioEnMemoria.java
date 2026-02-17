package repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import interfaces.Identificable;

public class RepositorioEnMemoria<T extends Identificable<ID>, ID> implements Repository<T, ID> {

	private Map<ID, T> almacenaje = new HashMap<>();
	
	@Override
	public void save(T entidad) {
		almacenaje.put(entidad.getId(), entidad);
	}

	@Override
	public Optional<T> findById(ID id) {
		return Optional.ofNullable(almacenaje.get(id));
	}

	@Override
	public List<T> findAll() {
		return new ArrayList<>(almacenaje.values());
	}

	@Override
	public void delete(ID id) {
		almacenaje.remove(id);
	}
	
}
