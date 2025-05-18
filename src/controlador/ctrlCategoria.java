/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import conexion.Conexion;
import modelo.Categoria;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;

/**
 *
 * @author juand
 */
public class ctrlCategoria {

    

    public boolean guardar(Categoria objeto) {
    boolean respuesta = false;
    Connection cn = Conexion.getConnection();
    try {
        PreparedStatement consulta = cn.prepareStatement(
            "INSERT INTO categorias (nombre_categoria, descripcion) VALUES (?, ?)"
        );
        consulta.setString(1, objeto.getNombre_categoria());
        consulta.setString(2, objeto.getDescripcion());

        if (consulta.executeUpdate() > 0) {
            respuesta = true;
        }

        cn.close();
    } catch (SQLException e) {
        System.out.println("Error al guardar categoria: " + e);
    }

    return respuesta;
    }

    public boolean existeCategoria(String categoria) {
        boolean respuesta = false;
        String sql = "select descripcion from categorias where descripcion = '" + categoria + "';";
        Statement st;

        try {
            Connection cn = Conexion.getConnection();
            st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                respuesta = true;
            }

        } catch (SQLException e) {
            System.out.println("Error al consultar categoria: " + e);
        }
        return respuesta;
    }

    public boolean actualizar(Categoria objeto, int id_categoria) {
        boolean respuesta = false;
        Connection cn = conexion.Conexion.getConnection();
        try {
            PreparedStatement consulta = cn.prepareStatement(
                    "UPDATE categorias SET nombre_categoria = ?, descripcion = ? WHERE id_categoria = ?"
            );
            consulta.setString(1, objeto.getNombre_categoria());
            consulta.setString(2, objeto.getDescripcion());
            consulta.setInt(3, id_categoria);

            if (consulta.executeUpdate() > 0) {
                respuesta = true;
            }

            cn.close();
        } catch (SQLException e) {
            System.out.println("Error al actualizar categoria: " + e);
        }

        return respuesta;
    }

    public boolean eliminar(int id_categoria, String nombre_categoria) {
        boolean respuesta = false;
    Connection cn = Conexion.getConnection();
    try {
       
        PreparedStatement consulta = cn.prepareStatement(
            "delete from categorias where id_categoria = ? and nombre_categoria = ?");
        consulta.setInt(1, id_categoria); // Establecemos el ID
        consulta.setString(2, nombre_categoria); 

        if (consulta.executeUpdate() > 0) {
            respuesta = true;
        }

        cn.close();
    } catch (SQLException e) {
        System.out.println("Error al eliminar categoria: " + e);
    }

    return respuesta;
    }

}
