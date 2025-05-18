package modelo;

public class UsuarioActual {

    private static int idUsuario;
    private static String nombreUsuario;
    private static String tipo_usuario;

    public static String getTipo_usuario() {
        return tipo_usuario;
    }

    public static void setTipo_usuario(String tipo_usuario) {
        UsuarioActual.tipo_usuario = tipo_usuario;
    }

    public static void setIdUsuario(int id) {
        idUsuario = id;
    }

    public static void setNombreUsuario(String nombre) {
        nombreUsuario = nombre;
    }

    public static int getIdUsuario() {
        return idUsuario;
    }

    public static String getNombreUsuario() {
        return nombreUsuario;
    }

}
