
package controlador;

import conexion.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import modelo.Cupon;



public class ctrlCupon {
   public static Cupon buscarCupon(String codigo) {
        Cupon cupon = null;
        String sql = "SELECT * FROM cupones WHERE codigo = ?";

        try (Connection conn = Conexion.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, codigo);

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                cupon = new Cupon(
                    rs.getString("codigo"),
                    rs.getString("descripcion"),
                    rs.getString("tipo_descuento"),
                    rs.getDouble("valor_descuento"),
                    rs.getBoolean("usado")
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return cupon;
    }

    public static void marcarComoUsado(String codigo) {
        String sql = "UPDATE cupones SET usado = true WHERE codigo = ?";
        try (Connection conn = Conexion.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, codigo);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    
}
