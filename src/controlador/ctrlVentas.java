package controlador;

import conexion.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.table.DefaultTableModel;
import modelo.CabeceraVenta;
import modelo.DetalleVenta;

public class ctrlVentas {

    //ultimo id de la cabecera
    public static int idCabeceraRegistrada;
    java.math.BigDecimal iDColVar;

    /**
     * **************************************************
     * metodo para guardar la cabecera de venta
     * **************************************************
     */
    public boolean guardar(CabeceraVenta objeto) {
        boolean respuesta = false;
        Connection cn = Conexion.getConnection();
        try {
            PreparedStatement consulta = cn.prepareStatement(
                    "INSERT INTO ventas (id_usuario, fecha_hora, total) VALUES (?, ?, ?)",
                    Statement.RETURN_GENERATED_KEYS
            );
            consulta.setInt(1, objeto.getId_usuario());
            consulta.setString(2, objeto.getFecha_hora());
            consulta.setDouble(3, objeto.getTotal());

            if (consulta.executeUpdate() > 0) {
                respuesta = true;
            }

            ResultSet rs = consulta.getGeneratedKeys();
            while (rs.next()) {
                iDColVar = rs.getBigDecimal(1);
                idCabeceraRegistrada = iDColVar.intValue();
            }

            cn.close();
        } catch (SQLException e) {
            System.out.println("Error al guardar cabecera de venta: " + e);
        }
        return respuesta;
    }

    /**
     * **************************************************
     * metodo para guardar el detalle de venta
     * **************************************************
     */
    public boolean guardarDetalle(DetalleVenta objeto) {
        boolean respuesta = false;
        Connection cn = Conexion.getConnection();
        try {
            PreparedStatement consulta = cn.prepareStatement(
                    "INSERT INTO detalleventa (id_detalle, id_venta, id_producto, nombre, cantidad, precio_unitario, itbis, total) VALUES (?,?,?,?,?,?,?,?)"
            );

            consulta.setInt(1, 0);
            consulta.setInt(2, idCabeceraRegistrada);
            consulta.setInt(3, objeto.getId_producto());
            consulta.setString(4, objeto.getNombre());
            consulta.setInt(5, objeto.getCantidad());
            consulta.setDouble(6, objeto.getPrecio_unitario());
            consulta.setDouble(7, objeto.getItbis());
            consulta.setDouble(8, objeto.getTotal());

            if (consulta.executeUpdate() > 0) {
                respuesta = true;
            }
            cn.close();
        } catch (SQLException e) {
            System.out.println("Error al guardar detalle de venta: " + e);
        }
        return respuesta;
    }
    
    public DefaultTableModel listarVentas() {
        DefaultTableModel modelo = new DefaultTableModel();
        String[] columnas = {"ID Venta", "Producto", "Cantidad", "Precio Unitario", "Subtotal", "ITBIS", "Total", "Fecha"};
        modelo.setColumnIdentifiers(columnas);

        String sql = "SELECT v.id_venta, d.nombre, d.cantidad, d.precio_unitario, " +
                     "(d.cantidad * d.precio_unitario) AS subtotal, d.itbis, d.total, v.fecha_hora " +
                     "FROM detalleventa d INNER JOIN ventas v ON d.id_venta = v.id_venta";

        Connection cn = Conexion.getConnection();
        try {
            PreparedStatement ps = cn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Object[] fila = new Object[8];
                fila[0] = rs.getInt("id_venta");
                fila[1] = rs.getString("nombre");
                fila[2] = rs.getInt("cantidad");
                fila[3] = rs.getDouble("precio_unitario");
                fila[4] = rs.getDouble("subtotal");
                fila[5] = rs.getDouble("itbis");
                fila[6] = rs.getDouble("total");
                fila[7] = rs.getString("fecha_hora");
                modelo.addRow(fila);
            }

            cn.close();
        } catch (SQLException e) {
            System.out.println("Error al listar ventas: " + e);
        }

        return modelo;
    }

}
