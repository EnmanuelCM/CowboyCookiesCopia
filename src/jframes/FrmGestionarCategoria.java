
package jframes;

import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import conexion.Conexion;
import controlador.ctrlCategoria;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.sql.PreparedStatement;
import javax.swing.JOptionPane;
import javax.swing.table.TableColumnModel;
import static jframes.FrmNuevaVenta.jTable_Productos;
import modelo.Categoria;


public class FrmGestionarCategoria extends javax.swing.JInternalFrame {
    
    private int id_categoria;

    
    public FrmGestionarCategoria() {
       
        initComponents();
        this.setSize(new Dimension(600, 400));
        this.setTitle("Gestionar Categorias");
        
        this.CargarTablaCategorias();
       
    }


   
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable_categoria = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        txt_nombrecategoria = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        btnactualizar = new javax.swing.JButton();
        btneliminar = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txt_descripcion = new javax.swing.JTextArea();
        jLabel_walpaper = new javax.swing.JLabel();

        setClosable(true);
        setIconifiable(true);
        setResizable(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Montserrat", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(95, 47, 35));
        jLabel1.setText("Administar categorías");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 20, -1, -1));

        jPanel1.setBackground(new java.awt.Color(254, 176, 200));
        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jScrollPane1.setFont(new java.awt.Font("Montserrat", 0, 10)); // NOI18N

        jTable_categoria.setFont(new java.awt.Font("Montserrat", 0, 10)); // NOI18N
        jTable_categoria.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(jTable_categoria);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, 330, 240));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 60, 350, 280));

        jPanel4.setBackground(new java.awt.Color(254, 176, 200));
        jPanel4.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel4.setFont(new java.awt.Font("Montserrat", 1, 12)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(95, 47, 35));
        jLabel4.setText("Nombre Categoría:");
        jPanel4.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, -1));

        txt_nombrecategoria.setFont(new java.awt.Font("Montserrat", 0, 12)); // NOI18N
        jPanel4.add(txt_nombrecategoria, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 30, 190, -1));

        getContentPane().add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 140, 210, 70));

        jPanel2.setBackground(new java.awt.Color(254, 176, 200));
        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnactualizar.setBackground(new java.awt.Color(95, 47, 35));
        btnactualizar.setFont(new java.awt.Font("Montserrat", 1, 12)); // NOI18N
        btnactualizar.setForeground(new java.awt.Color(255, 255, 255));
        btnactualizar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/files/actualizar.png"))); // NOI18N
        btnactualizar.setText("Actualizar");
        btnactualizar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnactualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnactualizarActionPerformed(evt);
            }
        });
        jPanel2.add(btnactualizar, new org.netbeans.lib.awtextra.AbsoluteConstraints(45, 15, 120, -1));

        btneliminar.setBackground(new java.awt.Color(95, 47, 35));
        btneliminar.setFont(new java.awt.Font("Montserrat", 1, 12)); // NOI18N
        btneliminar.setForeground(new java.awt.Color(255, 255, 255));
        btneliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/files/cancelar.png"))); // NOI18N
        btneliminar.setText("Eliminar");
        btneliminar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btneliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btneliminarActionPerformed(evt);
            }
        });
        jPanel2.add(btneliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(45, 45, 120, -1));

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 60, 210, 80));

        jPanel3.setBackground(new java.awt.Color(254, 176, 200));
        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Montserrat", 1, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(95, 47, 35));
        jLabel2.setText("Descripción:");
        jPanel3.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, -1));

        txt_descripcion.setColumns(20);
        txt_descripcion.setFont(new java.awt.Font("Montserrat", 0, 11)); // NOI18N
        txt_descripcion.setRows(5);
        jScrollPane2.setViewportView(txt_descripcion);

        jPanel3.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 26, 190, 90));

        getContentPane().add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 210, 210, 130));

        jLabel_walpaper.setIcon(new javax.swing.ImageIcon(getClass().getResource("/backgrounds/Fondo_GestionarCategoria.jpg"))); // NOI18N
        getContentPane().add(jLabel_walpaper, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 590, 370));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnactualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnactualizarActionPerformed
        if (!txt_nombrecategoria.getText().isEmpty() && !txt_descripcion.getText().isEmpty()) {
        Categoria categoria = new Categoria();
        ctrlCategoria controlCategoria = new ctrlCategoria();

        categoria.setNombre_categoria(txt_nombrecategoria.getText().trim());
        categoria.setDescripcion(txt_descripcion.getText().trim());

        if (controlCategoria.actualizar(categoria, id_categoria)) {
            JOptionPane.showMessageDialog(null, "Categoría actualizada exitosamente");
            txt_nombrecategoria.setText("");
            txt_descripcion.setText("");
            this.CargarTablaCategorias();
        } else {
            JOptionPane.showMessageDialog(null, "Error al actualizar la categoría");
        }
    } else {
        JOptionPane.showMessageDialog(null, "Debe seleccionar una categoría");
    }
    }//GEN-LAST:event_btnactualizarActionPerformed

    private void btneliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btneliminarActionPerformed
        if (!txt_nombrecategoria.getText().isEmpty()) {
        Categoria categoria = new Categoria();
        ctrlCategoria controlCategoria = new ctrlCategoria();

        categoria.setDescripcion(txt_descripcion.getText().trim());
        String nombreCategoria = txt_nombrecategoria.getText().trim(); // Agregamos el nombre

        // Ahora llamamos al método eliminar pasando el id y el nombre
        if (controlCategoria.eliminar(id_categoria, nombreCategoria)) {
            JOptionPane.showMessageDialog(null, "Categoria Eliminada");
            txt_nombrecategoria.setText("");
            txt_descripcion.setText("");
            this.CargarTablaCategorias();
        } else {
            JOptionPane.showMessageDialog(null, "Error al Eliminar Categoria");
        }
    } else {
        JOptionPane.showMessageDialog(null, "Seleccione una categoria");
    }
    }//GEN-LAST:event_btneliminarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnactualizar;
    private javax.swing.JButton btneliminar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel_walpaper;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    public static javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    public static javax.swing.JTable jTable_categoria;
    private javax.swing.JTextArea txt_descripcion;
    private javax.swing.JTextField txt_nombrecategoria;
    // End of variables declaration//GEN-END:variables

    private void CargarTablaCategorias() {
        Conexion cn = new Conexion();
        Connection con = cn.getConexion();
        DefaultTableModel model = new DefaultTableModel();
        String sql = "select id_categoria, nombre_categoria, descripcion from categorias";
        Statement st;
        try {
            st = (Statement) con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            FrmGestionarCategoria.jTable_categoria = new JTable(model);
            FrmGestionarCategoria.jScrollPane1.setViewportView(FrmGestionarCategoria.jTable_categoria);

            model.addColumn("ID");
            model.addColumn("Categoria");
            model.addColumn("Descripción");
            
            // Ajustar anchos de columna
        TableColumnModel columnModel = jTable_categoria.getColumnModel();
        columnModel.getColumn(0).setPreferredWidth(30);  // "ID"
        columnModel.getColumn(1).setPreferredWidth(100); // "Nombre" más ancho
        columnModel.getColumn(2).setPreferredWidth(200);  // "Descrpcion"

            while (rs.next()) {
                Object fila[] = new Object[3];
                for (int i = 0; i < 3; i++) {
                    fila[i] = rs.getObject(i + 1);
                }
                model.addRow(fila);
            }
            con.close();
        } catch (SQLException e) {
            System.out.println("Error al llenar la tabla categorias: " + e);
        }
        
        jTable_categoria.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int fila_point = jTable_categoria.rowAtPoint(e.getPoint());
                int columna_point = 0;

                if (fila_point > -1) {
                    id_categoria = (int) model.getValueAt(fila_point, columna_point);
                    EnviarDatosCategoriaSeleccionada(id_categoria);
                    
                    }
            }
        });
    }
    
    private void EnviarDatosCategoriaSeleccionada(int id_categoria) {
        try {
        Conexion cn = new Conexion();
        Connection con = cn.getConexion();
        PreparedStatement pst = con.prepareStatement(
            "select * from categorias where id_categoria = ?");
        pst.setInt(1, id_categoria);
        ResultSet rs = pst.executeQuery();
        if (rs.next()) {
            txt_nombrecategoria.setText(rs.getString("nombre_categoria")); // <- Nombre de la categoría
            txt_descripcion.setText(rs.getString("descripcion")); // <- Descripción
        }
        con.close();
    } catch (SQLException e) {
        System.out.println("Error al seleccionar categoria: " + e);
    }
}
    }
    
                    
                                





    

