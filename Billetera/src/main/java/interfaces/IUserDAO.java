package interfaces;

import modelos.Usuario;

public interface IUserDAO extends GenericDAO<Usuario, Integer> {
	Usuario buscarUsuario(String correo);
}
