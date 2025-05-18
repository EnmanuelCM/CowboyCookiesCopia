/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package jframes;

import conexion.Conexion;
import controlador.ctrlProducto;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.Timer;
import javax.swing.table.TableColumnModel;
import modelo.Producto;

public class FrmGestionarProducto extends javax.swing.JInternalFrame {

    public FrmGestionarProducto() {

        initComponents();
        mostrarProductos();
        
        // Usamos un Timer para ejecutar la verificación después de 2 segundos
    Timer timer = new Timer(1000, new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            // ADVERTENCIA POR STOCK BAJO
        ctrlProducto controlProducto = new ctrlProducto();
        ArrayList<Producto> productosBajoStock = controlProducto.obtenerProductosConBajoStock(20);
        if (!productosBajoStock.isEmpty()) {
            StringBuilder mensaje = new StringBuilder("¡ATENCIÓN! Los siguientes productos tienen bajo stock:\n\n");
            for (Producto p : productosBajoStock) {
                mensaje.append("- ").append(p.getNombre()).append(" (Stock: ").append(p.getStock()).append(")\n");
            }
            JOptionPane.showMessageDialog(null, mensaje.toString(), "Stock Bajo", JOptionPane.WARNING_MESSAGE);
        }
        }
    });
    timer.setRepeats(false);  // Solo lo ejecuta una vez
    timer.start();
        

    
    
        
    
    
        
            
        jTable_producto.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable_productoMouseClicked(evt);
            }
        });
        this.setSize(new Dimension(900, 500));
        this.setTitle("Gestionar Productos");

    }
    
    

    private void limpiarCampos() {
        txt_nombre.setText("");
        txt_stock.setText("");
        txt_precio.setText("");
        txt_descripcion.setText("");
        txt_itbis.setText("");
        txt_stock.setText("");
        jTable_producto.clearSelection();
    }

    void mostrarProductos() {
        // Creamos el modelo de tabla
        DefaultTableModel modelo = new DefaultTableModel();

        // Añadimos las columnas al modelo
        modelo.addColumn("ID");
        modelo.addColumn("Nombre");
        modelo.addColumn("Descripción");
        modelo.addColumn("Precio");
        modelo.addColumn("Stock");
        modelo.addColumn("Categoría");
        modelo.addColumn("ITBIS");

        // Sentencia SQL para obtener los datos
        String sql = "SELECT * FROM productos";

        try {
            // Conexión a la base de datos
            Connection con = Conexion.getConnection(); // Asegúrate que tu clase de conexión se llame así
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);

            // Recorremos los resultados
            while (rs.next()) {
                Object[] fila = new Object[7];
                fila[0] = rs.getInt("id_producto");
                fila[1] = rs.getString("nombre");
                fila[2] = rs.getString("descripcion");
                fila[3] = rs.getDouble("precio");
                fila[4] = rs.getInt("stock");
                fila[5] = rs.getString("categoria");
                fila[6] = rs.getInt("porcentajeitbis");

                modelo.addRow(fila);
            }

            // Asignamos el modelo a la tabla
            jTable_producto.setModel(modelo);
            jTable_producto.setRowHeight(20); // Ajuste del alto de fila

            // Ajustar el ancho de columnas
            jTable_producto.getColumnModel().getColumn(0).setPreferredWidth(30);  // ID
            jTable_producto.getColumnModel().getColumn(1).setPreferredWidth(170); // Nombre
            jTable_producto.getColumnModel().getColumn(2).setPreferredWidth(250); // Descripción
            jTable_producto.getColumnModel().getColumn(3).setPreferredWidth(60);  // Precio
            jTable_producto.getColumnModel().getColumn(4).setPreferredWidth(60);  // Stock
            jTable_producto.getColumnModel().getColumn(5).setPreferredWidth(100); // Categoría
            jTable_producto.getColumnModel().getColumn(6).setPreferredWidth(40);  // ITBIS

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error al mostrar productos: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void jTable_productoMouseClicked(java.awt.event.MouseEvent evt) {
        int filaSeleccionada = jTable_producto.getSelectedRow();

        if (filaSeleccionada >= 0) {

            txt_nombre.setText(jTable_producto.getValueAt(filaSeleccionada, 1).toString());
            txt_descripcion.setText(jTable_producto.getValueAt(filaSeleccionada, 2).toString());
            txt_precio.setText(jTable_producto.getValueAt(filaSeleccionada, 3).toString());
            txt_stock.setText(jTable_producto.getValueAt(filaSeleccionada, 4).toString());

            // Seleccionar categoría en el JComboBox
            String categoria = jTable_producto.getValueAt(filaSeleccionada, 5).toString();
            cbo_categoria.setSelectedItem(categoria);

            txt_itbis.setText(jTable_producto.getValueAt(filaSeleccionada, 6).toString());
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable_producto = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        txt_nombre = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txt_stock = new javax.swing.JTextField();
        txt_precio = new javax.swing.JTextField();
        txt_descripcion = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txt_itbis = new javax.swing.JTextField();
        cbo_categoria = new javax.swing.JComboBox<>();
        jLabel_wallpaper = new javax.swing.JLabel();

        setClosable(true);
        setIconifiable(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Montserrat", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(95, 47, 35));
        jLabel1.setText("Administrar Productos");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 20, -1, -1));

        jPanel1.setBackground(new java.awt.Color(254, 176, 200));
        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jScrollPane1.setFont(new java.awt.Font("Montserrat", 0, 12)); // NOI18N

        jTable_producto.setFont(new java.awt.Font("Montserrat", 0, 12)); // NOI18N
        jTable_producto.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(jTable_producto);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, 690, 230));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, 710, 270));

        jPanel2.setBackground(new java.awt.Color(254, 176, 200));
        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton1.setBackground(new java.awt.Color(95, 47, 35));
        jButton1.setFont(new java.awt.Font("Montserrat", 1, 12)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/files/actualizar.png"))); // NOI18N
        jButton1.setText("Actualizar");
        jButton1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 120, 30));

        jButton2.setBackground(new java.awt.Color(95, 47, 35));
        jButton2.setFont(new java.awt.Font("Montserrat", 1, 12)); // NOI18N
        jButton2.setForeground(new java.awt.Color(255, 255, 255));
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/files/cancelar.png"))); // NOI18N
        jButton2.setText("Eliminar");
        jButton2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, 120, 30));

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 230, 140, 90));

        jPanel3.setBackground(new java.awt.Color(254, 176, 200));
        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Montserrat", 1, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(95, 47, 35));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel2.setText("Nombre:");
        jPanel3.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, -1, -1));

        txt_nombre.setFont(new java.awt.Font("Montserrat", 0, 12)); // NOI18N
        jPanel3.add(txt_nombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 30, 170, -1));

        jLabel3.setFont(new java.awt.Font("Montserrat", 1, 12)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(95, 47, 35));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel3.setText("Stock:");
        jPanel3.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 60, -1, -1));

        jLabel4.setFont(new java.awt.Font("Montserrat", 1, 12)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(95, 47, 35));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel4.setText("Precio:");
        jPanel3.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 30, -1, -1));

        jLabel5.setFont(new java.awt.Font("Montserrat", 1, 12)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(95, 47, 35));
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel5.setText("Descripción:");
        jPanel3.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 60, -1, -1));

        jLabel7.setFont(new java.awt.Font("Montserrat", 1, 12)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(95, 47, 35));
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel7.setText("ITBIS:");
        jPanel3.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 60, -1, -1));

        txt_stock.setEditable(false);
        txt_stock.setFont(new java.awt.Font("Montserrat", 0, 12)); // NOI18N
        txt_stock.setEnabled(false);
        jPanel3.add(txt_stock, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 60, 170, -1));

        txt_precio.setFont(new java.awt.Font("Montserrat", 0, 12)); // NOI18N
        jPanel3.add(txt_precio, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 30, 160, -1));

        txt_descripcion.setFont(new java.awt.Font("Montserrat", 0, 12)); // NOI18N
        jPanel3.add(txt_descripcion, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 60, 160, -1));

        jLabel8.setFont(new java.awt.Font("Montserrat", 1, 12)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(95, 47, 35));
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel8.setText("Categoría:");
        jPanel3.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 30, -1, -1));

        txt_itbis.setFont(new java.awt.Font("Montserrat", 0, 12)); // NOI18N
        jPanel3.add(txt_itbis, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 60, 180, -1));

        cbo_categoria.setFont(new java.awt.Font("Montserrat", 0, 12)); // NOI18N
        cbo_categoria.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccione categoria ", "Galletas", "Postres", "Sandwiches", "Bebidas", "Cafes" }));
        jPanel3.add(cbo_categoria, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 30, 180, -1));

        getContentPane().add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 330, 870, 100));

        jLabel_wallpaper.setIcon(new javax.swing.ImageIcon(getClass().getResource("/backgrounds/Fondo_NuevaVenta.jpg"))); // NOI18N
        getContentPane().add(jLabel_wallpaper, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 890, 470));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        int fila = jTable_producto.getSelectedRow();
    if (fila < 0) {
        JOptionPane.showMessageDialog(null, "Debes seleccionar un producto de la tabla.");
        return;
    }

    try {
        // ID del producto
        int id_producto = Integer.parseInt(jTable_producto.getValueAt(fila, 0).toString());

        // Valores actuales de la tabla
        String nombreTabla = jTable_producto.getValueAt(fila, 1).toString();
        String descripcionTabla = jTable_producto.getValueAt(fila, 2).toString();
        String precioTabla = jTable_producto.getValueAt(fila, 3).toString();
        String stockTabla = jTable_producto.getValueAt(fila, 4).toString();
        String categoriaTabla = jTable_producto.getValueAt(fila, 5).toString();
        String itbisTabla = jTable_producto.getValueAt(fila, 6).toString();

        // Valores del formulario
        String nombreForm = txt_nombre.getText().trim();
        String descripcionForm = txt_descripcion.getText().trim();
        String precioForm = txt_precio.getText().trim();
        String stockForm = txt_stock.getText().trim();
        String categoriaForm = cbo_categoria.getSelectedItem().toString();
        String itbisForm = txt_itbis.getText().trim();

        // Comparar si hay cambios
        if (nombreTabla.equals(nombreForm) &&
            descripcionTabla.equals(descripcionForm) &&
            precioTabla.equals(precioForm) &&
            stockTabla.equals(stockForm) &&
            categoriaTabla.equals(categoriaForm) &&
            itbisTabla.equals(itbisForm)) {

            JOptionPane.showMessageDialog(null, "No se ha hecho ningún cambio en el producto.");
            return;
        }

        // Si hay cambios, se actualiza
        Producto producto = new Producto();
        producto.setNombre(nombreForm);
        producto.setDescripcion(descripcionForm);
        producto.setPrecio(Double.parseDouble(precioForm));
        producto.setStock(Integer.parseInt(stockForm));
        producto.setCategoria(categoriaForm);
        producto.setPorcentajeitbis(Integer.parseInt(itbisForm));

        ctrlProducto controlProducto = new ctrlProducto();
        if (controlProducto.actualizar(producto, id_producto)) {
            JOptionPane.showMessageDialog(null, "Producto actualizado correctamente.");
            mostrarProductos();
            limpiarCampos();
        } else {
            JOptionPane.showMessageDialog(null, "Error al actualizar el producto.");
        }
    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "Error: " + e);
    }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        int fila = jTable_producto.getSelectedRow();
        if (fila < 0) {
            JOptionPane.showMessageDialog(null, "Debes seleccionar un producto para eliminar.");
            return;
        }

        int confirmacion = JOptionPane.showConfirmDialog(null, "¿Estás seguro de eliminar este producto?", "Confirmar", JOptionPane.YES_NO_OPTION);

        if (confirmacion == JOptionPane.YES_OPTION) {
            int id_producto = Integer.parseInt(jTable_producto.getValueAt(fila, 0).toString());

            ctrlProducto controlProducto = new ctrlProducto();
            if (controlProducto.eliminar(id_producto)) {
                JOptionPane.showMessageDialog(null, "Producto eliminado correctamente.");
                mostrarProductos();
                limpiarCampos();
            } else {
                JOptionPane.showMessageDialog(null, "Error al eliminar el producto.");
            }
        }
    }//GEN-LAST:event_jButton2ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> cbo_categoria;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel_wallpaper;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    public static javax.swing.JTable jTable_producto;
    private javax.swing.JTextField txt_descripcion;
    private javax.swing.JTextField txt_itbis;
    private javax.swing.JTextField txt_nombre;
    private javax.swing.JTextField txt_precio;
    private javax.swing.JTextField txt_stock;
    // End of variables declaration//GEN-END:variables
}
