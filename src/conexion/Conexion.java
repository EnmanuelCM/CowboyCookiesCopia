
package conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Conexion {


    private final String base = "cowboycookiesrd"; // nombre de tu base de datos
    private final String user = "root"; // tu usuario de base de datos
    private final String password = ""; // tu contraseña
    private final String url = "jdbc:mysql://localhost:3306/" + base; // la URL de conexión
    private Connection con = null;
    
    public Connection getConexion() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(this.url, this.user, this.password);
        } catch (SQLException e) {
            System.err.println("Error de conexión SQL: " + e.getMessage());
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return con;
    }   
    
    // Arreglamos este método para que funcione correctamente
    public static Connection getConnection() {
        Conexion conexion = new Conexion();
        return conexion.getConexion();
    }   
    
}
