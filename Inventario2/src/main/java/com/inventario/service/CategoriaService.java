
package com.inventario.service;

import com.inventario.dao.CategoriaDAO;
import com.inventario.model.Categoria;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CategoriaService {
    private final CategoriaDAO dao;
    public CategoriaService(CategoriaDAO dao) { this.dao = dao; }

    public List<Categoria> listar() throws Exception { return dao.listar(); }
    public Categoria obtener(int id) throws Exception { return dao.obtener(id); }
    public void guardar(Categoria c) throws Exception { dao.guardar(c); }
    public void actualizar(Categoria c) throws Exception { dao.actualizar(c); }
    public void eliminar(int id) throws Exception { dao.eliminar(id); }
}
