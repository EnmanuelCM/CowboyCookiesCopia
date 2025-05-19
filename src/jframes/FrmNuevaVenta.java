package jframes;

import conexion.Conexion;
import controlador.VentaPDF;
import controlador.ctrlCupon;
import controlador.ctrlProducto;
import controlador.ctrlVentas;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import modelo.UsuarioActual;
import modelo.CabeceraVenta;
import modelo.Cupon;
import modelo.DetalleVenta;
import modelo.Producto;

public class FrmNuevaVenta extends javax.swing.JInternalFrame {

    //Modelo de los datos
    private DefaultTableModel modeloDatosProductos;
    //Lista para ver el detalle de venta de los productos
    ArrayList<DetalleVenta> listaProductos = new ArrayList<>();
    private DetalleVenta producto;

    private int id_producto = 0;
    private String nombre = "";
    private int cantidadProductoBBDD = 0;
    private double precioUnitario = 0.0;
    private int porcentajeITBIS = 0;

    private int cantidad = 0;//Cantidad de productos a comprar
    private double subtotal = 0.0;//Cantidad por precio
    private double ITBIS = 0.0;
    private double descuento = 0.0;
    private double totalPagar = 0.0;

    //Variables para calculos globales
    private double subtotalGeneral = 0.0;
    private double ITBISGeneral = 0.0;
    private double totalPagarGeneral = 0.0;
    //Fin de las variables para calculos globales

    private int auxIdDetalle = 1;

    public FrmNuevaVenta() {
        initComponents();
        this.setSize(new Dimension(1000, 600));
        this.setTitle("Facturacion");

        lblEmpleado.setText(UsuarioActual.getNombreUsuario());

        this.CargarComboProductos();
        this.InicializarTablaProducto();

        txtEfectivo.setEnabled(false);
        btnCalcularCambio.setEnabled(false);

        txtSubtotal.setText("0.0");
        txtITBIS.setText("0.0");
        txtTotal.setText("0.0");
    }

    private void InicializarTablaProducto() {
        modeloDatosProductos = new DefaultTableModel();
        // agregar columnas
        modeloDatosProductos.addColumn("N");
        modeloDatosProductos.addColumn("Nombre");
        modeloDatosProductos.addColumn("Cantidad");
        modeloDatosProductos.addColumn("P. Unitario");
        modeloDatosProductos.addColumn("Subtotal");
        modeloDatosProductos.addColumn("ITBIS");
        modeloDatosProductos.addColumn("Descuento"); // Nueva columna
        modeloDatosProductos.addColumn("Total Pagar");
        modeloDatosProductos.addColumn("Accion");

        // Agregar los datos del modelo a la tabla
        this.jTable_Productos.setModel(modeloDatosProductos);
        jTable_Productos.setRowHeight(20); // Ajuste del alto de fila

        // Ajustar anchos de columna
        TableColumnModel columnModel = jTable_Productos.getColumnModel();
        columnModel.getColumn(0).setPreferredWidth(30);  // "N"
        columnModel.getColumn(1).setPreferredWidth(200); // "Nombre"
        columnModel.getColumn(2).setPreferredWidth(70);  // "Cantidad"
        columnModel.getColumn(3).setPreferredWidth(90);  // "P. Unitario"
        columnModel.getColumn(4).setPreferredWidth(90);  // "Subtotal"
        columnModel.getColumn(5).setPreferredWidth(70);  // "ITBIS"
        columnModel.getColumn(6).setPreferredWidth(80);  // "Descuento"
        columnModel.getColumn(7).setPreferredWidth(100); // "Total Pagar"
        columnModel.getColumn(8).setPreferredWidth(60);  // "Accion"
    }

    //Metodo para presentar la informacion de la tabla DetalleVenta
    private void ListaTablaProductos() {
        this.modeloDatosProductos.setRowCount(listaProductos.size());
        for (int i = 0; i < listaProductos.size(); i++) {
            this.modeloDatosProductos.setValueAt(i + 1, i, 0);
            this.modeloDatosProductos.setValueAt(listaProductos.get(i).getNombre(), i, 1);
            this.modeloDatosProductos.setValueAt(listaProductos.get(i).getCantidad(), i, 2);
            this.modeloDatosProductos.setValueAt(listaProductos.get(i).getPrecio_unitario(), i, 3);
            this.modeloDatosProductos.setValueAt(listaProductos.get(i).getSubtotal(), i, 4);
            this.modeloDatosProductos.setValueAt(listaProductos.get(i).getItbis(), i, 5);
            this.modeloDatosProductos.setValueAt(listaProductos.get(i).getDescuento(), i, 6); // Nuevo dato
            this.modeloDatosProductos.setValueAt(listaProductos.get(i).getTotal(), i, 7);
            this.modeloDatosProductos.setValueAt("Eliminar", i, 8);
        }
        // Actualizar JTable
        jTable_Productos.setModel(modeloDatosProductos);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnAplicarCupon = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        txtCupon = new javax.swing.JTextField();
        txtCantidad = new javax.swing.JTextField();
        txt_idproducto = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        lblEmpleado = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        cbxProductos = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        btnAgregarProd = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable_Productos = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        txtEfectivo = new javax.swing.JTextField();
        txtSubtotal = new javax.swing.JTextField();
        txtITBIS = new javax.swing.JTextField();
        txtCambio = new javax.swing.JTextField();
        txtTotal = new javax.swing.JTextField();
        btnCalcularCambio = new javax.swing.JButton();
        btnRegistrarVenta = new javax.swing.JButton();
        lbl_wallpaper = new javax.swing.JLabel();

        setClosable(true);
        setIconifiable(true);
        setPreferredSize(new java.awt.Dimension(1000, 602));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnAplicarCupon.setBackground(new java.awt.Color(95, 47, 35));
        btnAplicarCupon.setFont(new java.awt.Font("Montserrat", 1, 12)); // NOI18N
        btnAplicarCupon.setForeground(new java.awt.Color(255, 255, 255));
        btnAplicarCupon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/files/agregar.png"))); // NOI18N
        btnAplicarCupon.setText("Aplicar Cupón");
        btnAplicarCupon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAplicarCuponActionPerformed(evt);
            }
        });
        getContentPane().add(btnAplicarCupon, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 390, -1, -1));

        jLabel2.setFont(new java.awt.Font("Montserrat SemiBold", 0, 12)); // NOI18N
        jLabel2.setText("Usar Cupón:");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 390, -1, -1));

        jLabel10.setFont(new java.awt.Font("Montserrat SemiBold", 0, 14)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(95, 47, 35));
        jLabel10.setText("Buscar por código:");
        getContentPane().add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 70, -1, -1));
        getContentPane().add(txtCupon, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 390, 100, -1));

        txtCantidad.setFont(new java.awt.Font("Montserrat", 0, 12)); // NOI18N
        getContentPane().add(txtCantidad, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 90, 70, -1));

        txt_idproducto.setFont(new java.awt.Font("Montserrat", 0, 12)); // NOI18N
        txt_idproducto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_idproductoKeyPressed(evt);
            }
        });
        getContentPane().add(txt_idproducto, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 90, 140, -1));

        jLabel3.setFont(new java.awt.Font("Montserrat SemiBold", 0, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(95, 47, 35));
        jLabel3.setText("Cantidad:");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 90, -1, -1));

        lblEmpleado.setFont(new java.awt.Font("Montserrat SemiBold", 0, 18)); // NOI18N
        lblEmpleado.setForeground(new java.awt.Color(95, 47, 35));
        lblEmpleado.setText("Usuario:");
        getContentPane().add(lblEmpleado, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 50, -1, -1));

        jLabel1.setFont(new java.awt.Font("Montserrat", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(95, 47, 35));
        jLabel1.setText("Facturación");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 10, -1, -1));

        cbxProductos.setFont(new java.awt.Font("Montserrat", 0, 12)); // NOI18N
        cbxProductos.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccione Producto:" }));
        getContentPane().add(cbxProductos, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 90, 270, -1));

        jLabel4.setFont(new java.awt.Font("Montserrat SemiBold", 0, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(95, 47, 35));
        jLabel4.setText("Producto:");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 90, -1, -1));

        btnAgregarProd.setBackground(new java.awt.Color(95, 47, 35));
        btnAgregarProd.setFont(new java.awt.Font("Montserrat", 1, 12)); // NOI18N
        btnAgregarProd.setForeground(new java.awt.Color(255, 255, 255));
        btnAgregarProd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/files/anadir-al-carrito.png"))); // NOI18N
        btnAgregarProd.setText("Agregar Producto");
        btnAgregarProd.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnAgregarProd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarProdActionPerformed(evt);
            }
        });
        getContentPane().add(btnAgregarProd, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 83, -1, 30));

        jPanel1.setBackground(new java.awt.Color(254, 176, 200));
        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jScrollPane1.setFont(new java.awt.Font("Montserrat", 0, 12)); // NOI18N

        jTable_Productos.setFont(new java.awt.Font("Montserrat", 0, 12)); // NOI18N
        jTable_Productos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jTable_Productos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable_ProductosMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable_Productos);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 930, 190));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 130, 960, 210));

        jPanel2.setBackground(new java.awt.Color(254, 176, 200));
        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel2.setForeground(new java.awt.Color(255, 255, 255));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel5.setFont(new java.awt.Font("Montserrat SemiBold", 0, 12)); // NOI18N
        jLabel5.setText("ITBIS:");
        jPanel2.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 70, -1, -1));

        jLabel6.setFont(new java.awt.Font("Montserrat SemiBold", 0, 12)); // NOI18N
        jLabel6.setText("Total a pagar:");
        jPanel2.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 100, -1, -1));

        jLabel7.setFont(new java.awt.Font("Montserrat SemiBold", 0, 12)); // NOI18N
        jLabel7.setText("Efectivo:");
        jPanel2.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 150, -1, -1));

        jLabel8.setFont(new java.awt.Font("Montserrat SemiBold", 0, 12)); // NOI18N
        jLabel8.setText("Cambio:");
        jPanel2.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 180, -1, -1));

        jLabel9.setFont(new java.awt.Font("Montserrat SemiBold", 0, 12)); // NOI18N
        jLabel9.setText("Subtotal:");
        jPanel2.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 30, -1, -1));

        txtEfectivo.setFont(new java.awt.Font("Montserrat", 0, 12)); // NOI18N
        jPanel2.add(txtEfectivo, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 150, 100, -1));

        txtSubtotal.setFont(new java.awt.Font("Montserrat", 0, 12)); // NOI18N
        txtSubtotal.setEnabled(false);
        jPanel2.add(txtSubtotal, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 30, 100, -1));

        txtITBIS.setFont(new java.awt.Font("Montserrat", 0, 12)); // NOI18N
        txtITBIS.setEnabled(false);
        jPanel2.add(txtITBIS, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 60, 100, -1));

        txtCambio.setFont(new java.awt.Font("Montserrat", 0, 12)); // NOI18N
        txtCambio.setEnabled(false);
        jPanel2.add(txtCambio, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 180, 100, -1));

        txtTotal.setFont(new java.awt.Font("Montserrat", 0, 12)); // NOI18N
        txtTotal.setEnabled(false);
        jPanel2.add(txtTotal, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 100, 100, -1));

        btnCalcularCambio.setBackground(new java.awt.Color(95, 47, 35));
        btnCalcularCambio.setFont(new java.awt.Font("Montserrat", 1, 14)); // NOI18N
        btnCalcularCambio.setForeground(new java.awt.Color(255, 255, 255));
        btnCalcularCambio.setIcon(new javax.swing.ImageIcon(getClass().getResource("/files/calculadora.png"))); // NOI18N
        btnCalcularCambio.setText("Calcular Cambio");
        btnCalcularCambio.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnCalcularCambio.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnCalcularCambio.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnCalcularCambio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCalcularCambioActionPerformed(evt);
            }
        });
        jPanel2.add(btnCalcularCambio, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 140, 160, 70));

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 340, 420, 220));

        btnRegistrarVenta.setBackground(new java.awt.Color(95, 47, 35));
        btnRegistrarVenta.setFont(new java.awt.Font("Montserrat", 1, 14)); // NOI18N
        btnRegistrarVenta.setForeground(new java.awt.Color(255, 255, 255));
        btnRegistrarVenta.setIcon(new javax.swing.ImageIcon(getClass().getResource("/files/cajero-automatico.png"))); // NOI18N
        btnRegistrarVenta.setText("Registrar Venta");
        btnRegistrarVenta.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnRegistrarVenta.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnRegistrarVenta.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnRegistrarVenta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegistrarVentaActionPerformed(evt);
            }
        });
        getContentPane().add(btnRegistrarVenta, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 370, 150, 110));

        lbl_wallpaper.setIcon(new javax.swing.ImageIcon(getClass().getResource("/backgrounds/Fondo_NuevaVenta.jpg"))); // NOI18N
        getContentPane().add(lbl_wallpaper, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, -4, 990, 570));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAgregarProdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarProdActionPerformed

        String combo = this.cbxProductos.getSelectedItem().toString();

        //Validar que seleccione un producto
        if (combo.equalsIgnoreCase("Seleccione producto:")) {
            JOptionPane.showMessageDialog(null, "Seleccione un producto");
        } else {

            //Validar que ingrese una Cantidad
            if (txtCantidad.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Ingresa la cantidad de productos");
            } else {
                //Vaildar que el usuario no ingrese datos no numericos
                boolean validacion = validar(txtCantidad.getText());
                if (validacion == true) {
                    //Validar que la cantidad sea mayor a 0
                    if (Integer.parseInt(txtCantidad.getText()) > 0) {
                        cantidad = Integer.parseInt(txtCantidad.getText());
                        //Ejecutar metodo para mostrar los productos
                        this.DatosDelProducto();
                        //Validar que la cantidad de productos seleccionados no sea mayor al Stock
                        if (cantidad <= cantidadProductoBBDD) {

                            subtotal = precioUnitario * cantidad;
                            totalPagar = subtotal + ITBIS;

                            //Redondear decimales
                            subtotal = (double) Math.round(subtotal * 100) / 100;
                            ITBIS = (double) Math.round(ITBIS * 100) / 100;
                            totalPagar = (double) Math.round(totalPagar * 100) / 100;

                            // Se crea un nuevo producto
                            producto = new DetalleVenta(
                                    auxIdDetalle,
                                    1, // idCabecera
                                    id_producto,
                                    nombre,
                                    Integer.parseInt(txtCantidad.getText()),
                                    precioUnitario,
                                    subtotal,
                                    ITBIS,
                                    descuento, // asegúrate de tener esta variable calculada antes
                                    totalPagar
                            );

                            //Agregar a la lista
                            listaProductos.add(producto);
                            auxIdDetalle++;
                            txtCantidad.setText("");//Limpiar campo
                            //Volver a cargar combo productos
                            this.CargarComboProductos();
                            this.CalcularTotalPagar();
                            txtEfectivo.setEnabled(true);
                            btnCalcularCambio.setEnabled(true);

                        } else {
                            JOptionPane.showMessageDialog(null, "La cantidad supera el Stock");
                        }

                    } else {
                        JOptionPane.showMessageDialog(null, "La cantidad no puede ser cero (0), ni negativa");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "En la cantidad no se admiten datos no numericos");
                }
            }
        }
        //Llamar al metodo
        this.ListaTablaProductos();
    }//GEN-LAST:event_btnAgregarProdActionPerformed

    private void btnCalcularCambioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCalcularCambioActionPerformed
        if (!txtEfectivo.getText().isEmpty()) {
            //validamos que el usuario no ingrese otros caracteres no numericos 
            boolean validacion = validarDouble(txtEfectivo.getText());
            if (validacion == true) {
                //validar que el efectivo sea mayor a cero
                double efc = Double.parseDouble(txtEfectivo.getText().trim());
                double top = Double.parseDouble(txtTotal.getText().trim());

                if (efc < top) {
                    JOptionPane.showMessageDialog(null, "El Dinero en efectivo no es suficiente");
                } else {
                    double cambio = (efc - top);
                    double cambi = (double) Math.round(cambio * 100d) / 100;
                    String camb = String.valueOf(cambi);
                    txtCambio.setText(camb);
                }
            } else {
                JOptionPane.showMessageDialog(null, "No de admiten caracteres no numericos");
            }

        } else {
            JOptionPane.showMessageDialog(null, "Ingrese el dinero en efectivo para calcular cambio");
        }
    }//GEN-LAST:event_btnCalcularCambioActionPerformed
    int idArrayList = 0;
    private void jTable_ProductosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable_ProductosMouseClicked
        int fila_point = jTable_Productos.rowAtPoint(evt.getPoint());
        int columna_point = 0;
        if (fila_point > -1) {
            idArrayList = (int) modeloDatosProductos.getValueAt(fila_point, columna_point);
        }
        int opcion = JOptionPane.showConfirmDialog(null, "¿Eliminar Producto?");
        //opciones de confir dialog - (si = 0; no = 1; cancel = 2; close = -1)
        switch (opcion) {
            case 0: //presione si
                listaProductos.remove(idArrayList - 1);
                this.CalcularTotalPagar();
                this.ListaTablaProductos();
                break;
            case 1: //presione no
                break;
            default://sea que presione cancel (2) o close (-1)
                break;
        }
    }//GEN-LAST:event_jTable_ProductosMouseClicked

    private void btnRegistrarVentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegistrarVentaActionPerformed
        CabeceraVenta cabeceraVenta = new CabeceraVenta();
        DetalleVenta detalleVenta = new DetalleVenta();
        ctrlVentas controlVenta = new ctrlVentas();

        if (listaProductos.size() > 0) {

            // registrar cabecera
            cabeceraVenta.setId_usuario(UsuarioActual.getIdUsuario());
            cabeceraVenta.setFecha_hora(getFechaHoraActual());
            cabeceraVenta.setTotal(Double.parseDouble(txtTotal.getText()));

            if (controlVenta.guardar(cabeceraVenta)) {
                JOptionPane.showMessageDialog(null, "¡Venta Registrada!");

                // Generar la factura de venta
                VentaPDF pdf = new VentaPDF();
                pdf.DatosEmpleado(UsuarioActual.getIdUsuario());
                pdf.generarFacturaPDF();

                // guardar detalle
                for (DetalleVenta elemento : listaProductos) {
                    detalleVenta.setId_detalle(0);
                    detalleVenta.setId_venta(0);
                    detalleVenta.setId_producto(elemento.getId_producto());
                    detalleVenta.setCantidad(elemento.getCantidad());
                    detalleVenta.setPrecio_unitario(elemento.getPrecio_unitario());
                    detalleVenta.setSubtotal(elemento.getSubtotal());
                    detalleVenta.setItbis(elemento.getItbis());
                    detalleVenta.setDescuento(elemento.getDescuento());
                    detalleVenta.setTotal(elemento.getTotal());

                    if (controlVenta.guardarDetalle(detalleVenta)) {
                        txtSubtotal.setText("0.0");
                        txtITBIS.setText("0.0");
                        txtTotal.setText("0.0");
                        txtEfectivo.setText("");
                        txtCambio.setText("0.0");
                        auxIdDetalle = 1;

                        this.RestarStockProductos(elemento.getId_producto(), elemento.getCantidad());

                    } else {
                        JOptionPane.showMessageDialog(null, "¡Error al guardar detalle de venta!");
                    }
                }

                // vaciamos la lista
                listaProductos.clear();
                ListaTablaProductos();

            } else {
                JOptionPane.showMessageDialog(null, "¡Error al guardar cabecera de venta!");
            }
        } else {
            JOptionPane.showMessageDialog(null, "¡Seleccione un producto!");
        }

    }//GEN-LAST:event_btnRegistrarVentaActionPerformed

    private void txt_idproductoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_idproductoKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            String idTexto = txt_idproducto.getText().trim();
            if (!idTexto.isEmpty()) {
                try {
                    int codigoBuscar = Integer.parseInt(idTexto);
                    String sql = "SELECT * FROM productos WHERE codigo = " + codigoBuscar;
                    Connection cn = Conexion.getConnection();
                    Statement st = cn.createStatement();
                    ResultSet rs = st.executeQuery(sql);

                    if (rs.next()) {
                        // Extraer datos del producto
                        id_producto = rs.getInt("id_producto");
                        nombre = rs.getString("nombre");
                        cantidadProductoBBDD = rs.getInt("stock");
                        precioUnitario = rs.getDouble("precio");
                        porcentajeITBIS = rs.getInt("porcentajeitbis");

                        // Por defecto, cantidad 1 y descuento 0
                        int cantidad = 1;
                        double descuento = 0;

                        // Validar stock disponible
                        if (cantidad > cantidadProductoBBDD) {
                            JOptionPane.showMessageDialog(this, "Stock insuficiente.");
                            return;
                        }

                        // Calcular subtotal, ITBIS y total
                        double subtotal = cantidad * precioUnitario;
                        double ITBIS = (subtotal * porcentajeITBIS) / 100;
                        double totalPagar = subtotal + ITBIS - descuento;

                        // Redondear
                        subtotal = Math.round(subtotal * 100.0) / 100.0;
                        ITBIS = Math.round(ITBIS * 100.0) / 100.0;
                        totalPagar = Math.round(totalPagar * 100.0) / 100.0;

                        // Crear objeto DetalleVenta
                        DetalleVenta producto = new DetalleVenta(
                                auxIdDetalle++, // Asignar ID incremental
                                1, // ID cabecera fija
                                id_producto,
                                nombre,
                                cantidad,
                                precioUnitario,
                                subtotal,
                                ITBIS,
                                descuento,
                                totalPagar
                        );

                        // Agregar a la lista y actualizar tabla
                        listaProductos.add(producto);
                        ListaTablaProductos();
                        CalcularTotalPagar();
                        txtEfectivo.setEnabled(true);
                        btnCalcularCambio.setEnabled(true);

                        txt_idproducto.setText(""); // Limpiar campo
                    } else {
                        JOptionPane.showMessageDialog(this, "Producto no encontrado.");
                    }

                    rs.close();
                    st.close();
                    cn.close();

                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(this, "Ingrese un código numérico válido.");
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(this, "Error al buscar el producto: " + ex.getMessage());
                }
            }
        }
    }//GEN-LAST:event_txt_idproductoKeyPressed

    private void btnAplicarCuponActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAplicarCuponActionPerformed
        
        btnAplicarCupon.addActionListener(new ActionListener() {
    public void actionPerformed(ActionEvent evt) {
        AplicarCupon();
    }
});
        
     
    }//GEN-LAST:event_btnAplicarCuponActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAgregarProd;
    private javax.swing.JButton btnAplicarCupon;
    private javax.swing.JButton btnCalcularCambio;
    private javax.swing.JButton btnRegistrarVenta;
    private javax.swing.JComboBox<String> cbxProductos;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    public static javax.swing.JScrollPane jScrollPane1;
    public static javax.swing.JTable jTable_Productos;
    private javax.swing.JLabel lblEmpleado;
    private javax.swing.JLabel lbl_wallpaper;
    private javax.swing.JTextField txtCambio;
    private javax.swing.JTextField txtCantidad;
    private javax.swing.JTextField txtCupon;
    private javax.swing.JTextField txtEfectivo;
    private javax.swing.JTextField txtITBIS;
    private javax.swing.JTextField txtSubtotal;
    public static javax.swing.JTextField txtTotal;
    private javax.swing.JTextField txt_idproducto;
    // End of variables declaration//GEN-END:variables

//combobox Productos
    private void CargarComboProductos() {
        Conexion con = new Conexion();
        Connection cn = con.getConnection();
        String sql = "select * from productos";
        Statement st;

        try {
            st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            cbxProductos.removeAllItems();
            cbxProductos.addItem("Seleccione producto:");
            while (rs.next()) {
                cbxProductos.addItem(rs.getString("nombre"));
            }
            cn.close();
        } catch (SQLException e) {
            System.out.println("Error al cargar productos" + e);
        }
    }

    //Metodo para que el usuario no ingrese datos no numericos
    private boolean validar(String valor) {
        try {
            int num = Integer.parseInt(valor);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    //Metodo para que el usuario no ingrese datos no numericos
    private boolean validarDouble(String valor) {
        try {
            double num = Double.parseDouble(valor);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    //Metodo para mostrar los datos del producto seleccionado
    private void DatosDelProducto() {
        try {
            String sql = "select * from productos where nombre = '" + this.cbxProductos.getSelectedItem() + "'";
            Connection cn = Conexion.getConnection();
            Statement st;
            st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                id_producto = rs.getInt("id_producto");
                nombre = rs.getString("nombre");
                cantidadProductoBBDD = rs.getInt("stock");
                precioUnitario = rs.getDouble("precio");
                porcentajeITBIS = rs.getInt("porcentajeitbis");
                this.CalcularITBIS(precioUnitario, porcentajeITBIS);
            }

        } catch (SQLException e) {

        }
    }

    //Metodo para calcular itbis
    private double CalcularITBIS(double precio, int porcentajeITBIS) {
        int p_itbis = porcentajeITBIS;

        switch (p_itbis) {

            case 0:
                ITBIS = 0.0;
                break;
            case 8:
                ITBIS = (precio * cantidad) * 0.8;
                break;
            case 10:
                ITBIS = (precio * cantidad) * 0.10;
                break;
            case 16:
                ITBIS = (precio * cantidad) * 0.16;
                break;
            case 18:
                ITBIS = (precio * cantidad) * 0.18;
                break;
        }
        return ITBIS;
    }

    //Metodo para calcular el total a pagar de todos los productos
    private void CalcularTotalPagar() {
        subtotalGeneral = 0;
        ITBISGeneral = 0;
        totalPagarGeneral = 0;

        for (DetalleVenta elemento : listaProductos) {
            subtotalGeneral += elemento.getSubtotal();
            ITBISGeneral += elemento.getItbis();
            totalPagarGeneral += elemento.getTotal();
        }
        //Redondear decimales
        subtotalGeneral = (double) Math.round(subtotalGeneral * 100) / 100;
        ITBISGeneral = (double) Math.round(ITBISGeneral * 100) / 100;
        totalPagarGeneral = (double) Math.round(totalPagarGeneral * 100) / 100;

        //Enviar a la vista
        txtSubtotal.setText(String.valueOf(subtotalGeneral));
        txtITBIS.setText(String.valueOf(ITBISGeneral));
        txtTotal.setText(String.valueOf(totalPagarGeneral));

    }
    //metodo para restar la cantidad (stock) de los productos vendidos

    private void RestarStockProductos(int idProducto, int cantidad) {
        int cantidadProductosBaseDeDatos = 0;
        try {
            Connection cn = Conexion.getConnection();
            String sql = "select id_producto, stock from productos where id_producto = '" + idProducto + "'";
            Statement st;
            st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                cantidadProductosBaseDeDatos = rs.getInt("stock");
            }
            cn.close();
        } catch (SQLException e) {
            System.out.println("Error al restar cantidad 1, " + e);
        }

        try {
            Connection cn = Conexion.getConnection();
            PreparedStatement consulta = cn.prepareStatement("update productos set stock=? where id_producto = '" + idProducto + "'");
            int cantidadNueva = cantidadProductosBaseDeDatos - cantidad;
            consulta.setInt(1, cantidadNueva);
            if (consulta.executeUpdate() > 0) {
                //System.out.println("Todo bien");
            }
            cn.close();
        } catch (SQLException e) {
            System.out.println("Error al restar cantidad 2, " + e);
        }
    }

    public String getFechaHoraActual() {
        java.util.Date fecha = new java.util.Date();
        java.text.SimpleDateFormat formato = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return formato.format(fecha);
    }
    
    private void AplicarCupon() {
    String codigoIngresado = txtCupon.getText().trim();

    if (codigoIngresado.isEmpty()) {
        JOptionPane.showMessageDialog(this, "Por favor, ingrese un código de cupón.");
        return;
    }

    Cupon cupon = ctrlCupon.buscarCupon(codigoIngresado);

    if (cupon == null) {
        JOptionPane.showMessageDialog(this, "El cupón no existe.");
        return;
    }

    if (cupon.isUsado()) {
        JOptionPane.showMessageDialog(this, "Este cupón ya fue utilizado. 🥲");
        return;
    }

    BigDecimal bdSubtotal = BigDecimal.valueOf(subtotal).setScale(2, RoundingMode.HALF_UP);
    BigDecimal descuento = BigDecimal.ZERO;

    if (cupon.getTipoDescuento().equalsIgnoreCase("porcentaje")) {
        BigDecimal porcentaje = BigDecimal.valueOf(cupon.getValorDescuento())
                                   .divide(BigDecimal.valueOf(100), 4, RoundingMode.HALF_UP);
        descuento = bdSubtotal.multiply(porcentaje).setScale(2, RoundingMode.HALF_UP);
    } else if (cupon.getTipoDescuento().equalsIgnoreCase("monto")) {
        descuento = BigDecimal.valueOf(cupon.getValorDescuento()).setScale(2, RoundingMode.HALF_UP);
        if (descuento.compareTo(bdSubtotal) > 0) {
            descuento = bdSubtotal;
        }
    }

    BigDecimal totalFinal = bdSubtotal.subtract(descuento).setScale(2, RoundingMode.HALF_UP);

    txtTotal.setText( totalFinal.toPlainString());

    JOptionPane.showMessageDialog(this,
        "Cupón aplicado: " + cupon.getDescripcion() +
        "\nDescuento: RD$ " + descuento +
        "\nTotal a pagar: RD$ " + totalFinal);

    
}
    }
    
    
