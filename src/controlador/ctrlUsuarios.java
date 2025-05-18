package controlador;

import conexion.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import modelo.Usuario;

public class ctrlUsuarios {

    public boolean actualizar(Usuario usuario, int idUsuario) {
        boolean respuesta = false;
        Connection cn = Conexion.getConnection(); // Asegúrate de que tu clase de conexión se llama así
        try {
            String sql = "UPDATE usuarios SET nombre = ?, apellido = ?, correo = ?, usuario = ?, tipo_usuario = ? WHERE id_usuario = ?";
            PreparedStatement consulta = cn.prepareStatement(sql);
            consulta.setString(1, usuario.getNombre());
            consulta.setString(2, usuario.getApellido());
            consulta.setString(3, usuario.getCorreo());
            consulta.setString(4, usuario.getUsuario());
            consulta.setString(5, usuario.getTipo_usuario());
            consulta.setInt(6, idUsuario);

            if (consulta.executeUpdate() > 0) {
                respuesta = true;
            }

            cn.close();
        } catch (SQLException e) {
            System.out.println("Error al actualizar usuario: " + e);
        }

        return respuesta;
    }

    /**
     * Método para eliminar un usuario por su ID
     */
    public boolean eliminar(int idUsuario) {
        boolean respuesta = false;
        Connection cn = Conexion.getConnection(); // Asegúrate de tener este método
        try {
            String sql = "DELETE FROM usuarios WHERE id_usuario = ?";
            PreparedStatement consulta = cn.prepareStatement(sql);
            consulta.setInt(1, idUsuario);

            if (consulta.executeUpdate() > 0) {
                respuesta = true;
            }

            cn.close();
        } catch (SQLException e) {
            System.out.println("Error al eliminar usuario: " + e);
        }

        return respuesta;
    }
    
    // En Ctrl_Usuario.java
public boolean tieneVentas(int idUsuario) {
    boolean tiene = false;
    String sql = "SELECT 1 FROM ventas WHERE id_usuario = ?";
    try (Connection cn = Conexion.getConnection();
         PreparedStatement ps = cn.prepareStatement(sql)) {
        ps.setInt(1, idUsuario);
        ResultSet rs = ps.executeQuery();
        tiene = rs.next(); // true si hay alguna venta
    } catch (SQLException e) {
        System.out.println("Error al verificar ventas: " + e);
    }
    return tiene;
}


}
