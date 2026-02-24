package interfaces;

import modelos.Contacto;

public interface IContactoDAO extends GenericDAO<Contacto, Integer> {
	Contacto buscarContacto(String correo);
}
