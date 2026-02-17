package servicios;

import java.util.List;
import java.util.Optional;

import repositorios.Repository;

public class InventarioService<T, ID> {

	private Repository<T, ID> repo;
	
	public InventarioService(Repository<T, ID> repo) {
		this.repo = repo;
	}
	
	public void guardar(T entidad) {
		repo.save(entidad);
	}
	
	public Optional<T> obtener(ID id){
		return Optional.ofNullable(repo.findById(id).orElse(null));
	}
	
	public List<T> listar(){
		return repo.findAll();
	}
	
	public void eliminar(ID id) {
		repo.eliminar(id);
	}
}

