
package com.inventario.service;

import com.inventario.dao.ProductoDAO;
import com.inventario.model.Producto;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ProductoService {
    private final ProductoDAO dao;
    public ProductoService(ProductoDAO dao) { this.dao = dao; }

    public List<Producto> listar() throws Exception { return dao.listar(); }
    public Producto obtener(int id) throws Exception { return dao.obtener(id); }
    public void guardar(Producto p) throws Exception { dao.guardar(p); }
    public void actualizar(Producto p) throws Exception { dao.actualizar(p); }
    public void eliminar(int id) throws Exception { dao.eliminar(id); }
}
