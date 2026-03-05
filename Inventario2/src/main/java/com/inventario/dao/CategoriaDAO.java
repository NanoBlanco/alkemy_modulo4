
package com.inventario.dao;

import com.inventario.model.Categoria;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;
import java.util.*;

@Repository
public class CategoriaDAO {
    private final DataSource ds;
    public CategoriaDAO(DataSource ds) { this.ds = ds; }

    public List<Categoria> listar() throws Exception {
        List<Categoria> l = new ArrayList<>();
        try (Connection c = ds.getConnection();
             PreparedStatement ps = c.prepareStatement("SELECT * FROM categorias");
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Categoria x = new Categoria();
                x.setId(rs.getInt("id"));
                x.setNombre(rs.getString("nombre"));
                x.setDescripcion(rs.getString("descripcion"));
                x.setActivo(rs.getBoolean("activo"));
                l.add(x);
            }
        }
        return l;
    }

    public Categoria obtener(int id) throws Exception {
        try (Connection c = ds.getConnection();
             PreparedStatement ps = c.prepareStatement("SELECT * FROM categorias WHERE id=?")) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Categoria x = new Categoria();
                x.setId(rs.getInt("id"));
                x.setNombre(rs.getString("nombre"));
                x.setDescripcion(rs.getString("descripcion"));
                x.setActivo(rs.getBoolean("activo"));
                return x;
            }
        }
        return null;
    }

    public void guardar(Categoria c) throws Exception {
        try (Connection cn = ds.getConnection();
             PreparedStatement ps = cn.prepareStatement(
                     "INSERT INTO categorias(nombre,descripcion,activo) VALUES(?,?,?)")) {
            ps.setString(1, c.getNombre());
            ps.setString(2, c.getDescripcion());
            ps.setBoolean(3, c.isActivo());
            ps.executeUpdate();
        }
    }

    public void actualizar(Categoria c) throws Exception {
        try (Connection cn = ds.getConnection();
             PreparedStatement ps = cn.prepareStatement(
                     "UPDATE categorias SET nombre=?,descripcion=?,activo=? WHERE id=?")) {
            ps.setString(1, c.getNombre());
            ps.setString(2, c.getDescripcion());
            ps.setBoolean(3, c.isActivo());
            ps.setInt(4, c.getId());
            ps.executeUpdate();
        }
    }

    public void eliminar(int id) throws Exception {
        try (Connection cn = ds.getConnection();
             PreparedStatement ps = cn.prepareStatement(
                     "DELETE FROM categorias WHERE id=?")) {
            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }
}
