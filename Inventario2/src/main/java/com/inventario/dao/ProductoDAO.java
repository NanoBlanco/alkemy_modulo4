
package com.inventario.dao;

import com.inventario.model.Producto;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;
import java.util.*;

@Repository
public class ProductoDAO {
    private final DataSource ds;
    public ProductoDAO(DataSource ds) { this.ds = ds; }

    public List<Producto> listar() throws Exception {
        List<Producto> l = new ArrayList<>();
        try (Connection c = ds.getConnection();
             PreparedStatement ps = c.prepareStatement("SELECT * FROM productos");
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Producto p = new Producto();
                p.setId(rs.getInt("id"));
                p.setNombre(rs.getString("nombre"));
                p.setDescripcion(rs.getString("descripcion"));
                p.setPrecio(rs.getInt("precio"));
                p.setStock(rs.getInt("stock"));
                p.setStockMin(rs.getInt("stock_min"));
                p.setIdCategoria(rs.getInt("id_categoria"));
                p.setActivo(rs.getBoolean("activo"));
                l.add(p);
            }
        }
        return l;
    }

    public Producto obtener(int id) throws Exception {
        try (Connection c = ds.getConnection();
             PreparedStatement ps = c.prepareStatement("SELECT * FROM productos WHERE id=?")) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Producto p = new Producto();
                p.setId(rs.getInt("id"));
                p.setNombre(rs.getString("nombre"));
                p.setDescripcion(rs.getString("descripcion"));
                p.setPrecio(rs.getInt("precio"));
                p.setStock(rs.getInt("stock"));
                p.setStockMin(rs.getInt("stock_min"));
                p.setIdCategoria(rs.getInt("id_categoria"));
                p.setActivo(rs.getBoolean("activo"));
                return p;
            }
        }
        return null;
    }

    public void guardar(Producto p) throws Exception {
        try (Connection cn = ds.getConnection();
             PreparedStatement ps = cn.prepareStatement(
                     "INSERT INTO productos(nombre,descripcion,precio,stock,stock_min,id_categoria,activo,fecha_alta) VALUES(?,?,?,?,?,?,?,NOW())")) {
            ps.setString(1, p.getNombre());
            ps.setString(2, p.getDescripcion());
            ps.setInt(3, p.getPrecio());
            ps.setInt(4, p.getStock());
            ps.setInt(5, p.getStockMin());
            ps.setInt(6, p.getIdCategoria());
            ps.setBoolean(7, p.isActivo());
            ps.executeUpdate();
        }
    }

    public void actualizar(Producto p) throws Exception {
        try (Connection cn = ds.getConnection();
             PreparedStatement ps = cn.prepareStatement(
                     "UPDATE productos SET nombre=?,descripcion=?,precio=?,stock=?,stock_min=?,id_categoria=?,activo=? WHERE id=?")) {
            ps.setString(1, p.getNombre());
            ps.setString(2, p.getDescripcion());
            ps.setInt(3, p.getPrecio());
            ps.setInt(4, p.getStock());
            ps.setInt(5, p.getStockMin());
            ps.setInt(6, p.getIdCategoria());
            ps.setBoolean(7, p.isActivo());
            ps.setInt(8, p.getId());
            ps.executeUpdate();
        }
    }

    public void eliminar(int id) throws Exception {
        try (Connection cn = ds.getConnection();
             PreparedStatement ps = cn.prepareStatement(
                     "DELETE FROM productos WHERE id=?")) {
            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }
}
