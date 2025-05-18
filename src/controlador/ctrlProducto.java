/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import conexion.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JComboBox;
import modelo.Producto;

/**
 *
 * @author juand
 */
public class ctrlProducto {
    
    public boolean guardar(Producto objeto) {
    boolean respuesta = false;
    Connection cn = Conexion.getConnection();
    try {
        PreparedStatement consulta = cn.prepareStatement(
            "INSERT INTO productos (nombre, descripcion, precio, stock, categoria, porcentajeitbis) VALUES (?, ?, ?, ?, ?, ?)"
        );
        consulta.setString(1, objeto.getNombre());
        consulta.setString(2, objeto.getDescripcion());
        consulta.setDouble(3, objeto.getPrecio());
        consulta.setInt(4, objeto.getStock());
        consulta.setString(5, objeto.getCategoria());
        consulta.setInt(6, objeto.getPorcentajeitbis());

        if (consulta.executeUpdate() > 0) {
            respuesta = true;
        }

        cn.close();

    } catch (SQLException e) {
        System.out.println("Error al guardar producto: " + e);
    }

    return respuesta;
    }

    public boolean existeProducto(String producto) {
        boolean respuesta = false;
        String sql = "select nombre from productos where nombre = '" + producto + "';";
        Statement st;

        try {
            Connection cn = Conexion.getConnection();
            st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                respuesta = true;
            }

        } catch (SQLException e) {
            System.out.println("Error al consultar producto: " + e);
        }
        return respuesta;
    }
    
    public boolean actualizar(Producto objeto, int id_producto) {
    boolean respuesta = false;
    Connection cn = Conexion.getConnection();
    try {
        PreparedStatement consulta = cn.prepareStatement(
    "UPDATE productos SET nombre = ?, descripcion = ?, precio = ?, stock = ?, categoria = ?, porcentajeitbis = ? WHERE id_producto = ?"
);
consulta.setString(1, objeto.getNombre());
consulta.setString(2, objeto.getDescripcion());
consulta.setDouble(3, objeto.getPrecio());
consulta.setInt(4, objeto.getStock());
consulta.setString(5, objeto.getCategoria());
consulta.setInt(6, objeto.getPorcentajeitbis());
consulta.setInt(7, id_producto);  // ESTE ES CORRECTO AQUÍ

        if (consulta.executeUpdate() > 0) {
            respuesta = true;
        }

        cn.close();
    } catch (SQLException e) {
        System.out.println("Error al actualizar producto: " + e.getMessage());
    }
    return respuesta;
    }
    
    
    /**
     * **************************************************
     * metodo para eliminar un producto
     * **************************************************
     */
    public boolean eliminar(int id_producto) {
        boolean respuesta = false;
    Connection cn = Conexion.getConnection();

    try {
        PreparedStatement consulta = cn.prepareStatement(
            "DELETE FROM productos WHERE id_producto = ?"
        );
        consulta.setInt(1, id_producto);

        int filasAfectadas = consulta.executeUpdate();
        if (filasAfectadas > 0) {
            respuesta = true;
        }

        cn.close();
    } catch (SQLException e) {
        System.out.println("Error al eliminar producto: " + e.getMessage());
    }

    return respuesta;
    }
    
    public boolean actualizarStock(Producto object, int id_producto) {
        boolean respuesta = false;
        Connection cn = Conexion.getConnection();
        try {
            PreparedStatement consulta = cn.prepareStatement("update productos set stock=? where id_producto ='" + id_producto + "'");
            consulta.setInt(1, object.getStock());

            if (consulta.executeUpdate() > 0) {
                respuesta = true;
            }
            cn.close();
        } catch (SQLException e) {
            System.out.println("Error al actualizar stock del producto: " + e);
        }
        return respuesta;
    }
    
    public ArrayList<Producto> obtenerProductosConBajoStock(int limite) {
    ArrayList<Producto> listaBajoStock = new ArrayList<>();
    Connection cn = Conexion.getConnection();
    String sql = "SELECT id_producto, nombre, stock FROM productos WHERE stock < ?";
    
    try {
        PreparedStatement ps = cn.prepareStatement(sql);
        ps.setInt(1, limite);
        ResultSet rs = ps.executeQuery();
        
        while (rs.next()) {
            Producto producto = new Producto();
            producto.setId_producto(rs.getInt("id_producto"));
            producto.setNombre(rs.getString("nombre"));
            producto.setStock(rs.getInt("stock"));
            listaBajoStock.add(producto);
        }
        cn.close();
    } catch (SQLException e) {
        System.out.println("Error al obtener productos con bajo stock: " + e);
    }

    return listaBajoStock;
}

    
    
     }
    
    
