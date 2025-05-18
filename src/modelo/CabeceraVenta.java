package modelo;

public class CabeceraVenta {
    
    //Atributos
    private int id_venta;
    private int id_usuario;
    private String fecha_hora;
    private double total;
    
    public CabeceraVenta(){
        this.id_venta = 0;
        this.id_usuario = 0;
        this.fecha_hora = "";
        this.total = 0.0;
    }
    
    //Constructor

    public CabeceraVenta(int id_venta, int id_usuario, String fecha_hora, double total) {
        this.id_venta = id_venta;
        this.id_usuario = id_usuario;
        this.fecha_hora = fecha_hora;
        this.total = total;
    }
    
    //Get and set

    public int getId_venta() {
        return id_venta;
    }

    public void setId_venta(int id_venta) {
        this.id_venta = id_venta;
    }

    public int getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }

    public String getFecha_hora() {
        return fecha_hora;
    }

    public void setFecha_hora(String fecha_hora) {
        this.fecha_hora = fecha_hora;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
    
    
    //toString

    @Override
    public String toString() {
        return "CabeceraVenta{" + "id_venta=" + id_venta + ", id_usuario=" + id_usuario + ", fecha_hora=" + fecha_hora + ", total=" + total + '}';
    }

    
}
