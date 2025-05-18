package modelo;

public class DetalleVenta {

    private int id_detalle;
    private int id_venta;
    private int id_producto;
    
    private String nombre;
    private int cantidad;
    private double precio_unitario;
    private double subtotal;
    private double itbis;
    private double total;

    //Constructor
    public DetalleVenta() {
        this.id_detalle = 0;
        this.id_venta = 0;
        this.id_producto = 0;
        this.nombre = "";
        this.cantidad = 0;
        this.precio_unitario = 0.0;
        this.subtotal = 0.0;
        this.itbis = 0.0;
        this.total = 0.0;

    }
    
    //Constructor Sobrecargado

    public DetalleVenta(int id_detalle, int id_venta, int id_producto, String nombre, int cantidad, double precio_unitario, double subtotal, double itbis, double total) {
        this.id_detalle = id_detalle;
        this.id_venta = id_venta;
        this.id_producto = id_producto;
        this.nombre = nombre;
        this.cantidad = cantidad;
        this.precio_unitario = precio_unitario;
        this.subtotal = subtotal;
        this.itbis = itbis;
        this.total = total;
    }


    //Setter y Getter

    public int getId_detalle() {
        return id_detalle;
    }

    public void setId_detalle(int id_detalle) {
        this.id_detalle = id_detalle;
    }

    public int getId_venta() {
        return id_venta;
    }

    public void setId_venta(int id_venta) {
        this.id_venta = id_venta;
    }

    public int getId_producto() {
        return id_producto;
    }

    public void setId_producto(int id_producto) {
        this.id_producto = id_producto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getPrecio_unitario() {
        return precio_unitario;
    }

    public void setPrecio_unitario(double precio_unitario) {
        this.precio_unitario = precio_unitario;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }

    public double getItbis() {
        return itbis;
    }

    public void setItbis(double itbis) {
        this.itbis = itbis;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    
    
    //toString

    @Override
    public String toString() {
        return "DetalleVenta{" + "id_detalle=" + id_detalle + ", id_venta=" + id_venta + ", id_producto=" + id_producto + ", nombre=" + nombre + ", cantidad=" + cantidad + ", precio_unitario=" + precio_unitario + ", subtotal=" + subtotal + ", itbis=" + itbis + ", total=" + total + '}';
    }
    

}
