
package modelo;

public class Producto {
    
    private int id_producto;
    private String nombre;
    private String descripcion;
    private double precio;
    private int stock;
    private String categoria;
    private int porcentajeitbis; 
 
    
    public Producto(){
        this.id_producto = 0;
        this.nombre = "";
        this.descripcion = "";
        this.precio = 0.0;
        this.stock = 0;
        this.categoria = "";
        this.porcentajeitbis = 0;
        
       } 

    public Producto(int id_producto, String nombre, String descripcion, double precio, int stock, String categoria, int porcentajeitbis) {
        this.id_producto = id_producto;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.stock = stock;
        this.categoria = categoria;
        this.porcentajeitbis = porcentajeitbis;
    }

    public int getId_producto() {
        return id_producto;
    }

    public void setId_producto(int id_producto) {
        this.id_producto = id_producto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public int getPorcentajeitbis() {
        return porcentajeitbis;
    }

    public void setPorcentajeitbis(int porcentajeitbis) {
        this.porcentajeitbis = porcentajeitbis;
    }
    }
    
    






    
    
    
    
    
    
    
    