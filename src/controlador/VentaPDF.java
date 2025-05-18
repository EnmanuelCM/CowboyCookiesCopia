package controlador;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import conexion.Conexion;
import java.awt.Desktop;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import jframes.FrmNuevaVenta;

public class VentaPDF {

    private String nombreEmpleado;

    private String fechaActual = "";
    private String nombreArchivoPDFVenta = "";

    //metodo para obtener datos del cliente
    public void DatosEmpleado(int UsuarioActual) {
        Connection cn = Conexion.getConnection();
        String sql = "select * from usuarios where id_usuario = '" + UsuarioActual + "'";
        Statement st;
        try {

            st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                nombreEmpleado = rs.getString("nombre") + " " + rs.getString("apellido");
            }
            cn.close();
        } catch (SQLException e) {
            System.out.println("Error al obtener datos del Empleado: " + e);
        }
    }

    //metodo para generar la factura de venta
    public void generarFacturaPDF() {
        try {

            //cargar la fecha actual
            Date date = new Date();
            fechaActual = new SimpleDateFormat("yyyy/MM/dd").format(date);
            //cambiar el formato de la fecha de / a _
            String fechaNueva = "";
            for (int i = 0; i < fechaActual.length(); i++) {
                if (fechaActual.charAt(i) == '/') {
                    fechaNueva = fechaActual.replace("/", "_");
                }
            }

            int numeroFactura = obtenerNumeroFactura();
            nombreArchivoPDFVenta = "Venta_" + nombreEmpleado + "_" + fechaNueva + "_"+ String.format("%03d", numeroFactura) +".pdf";

            FileOutputStream archivo;
            File file = new File("src/pdf/" + nombreArchivoPDFVenta);
            archivo = new FileOutputStream(file);

            Document doc = new Document();
            PdfWriter.getInstance(doc, archivo);
            doc.open();

            BaseFont montserrat = BaseFont.createFont("src/fonts/Montserrat-Regular.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
            Font fuentePersonalizada = new Font(montserrat, 11, Font.NORMAL, new BaseColor(0x5f, 0x2f, 0x23)); // Color: #5f2f23

            BaseFont montserratBold = BaseFont.createFont("src/fonts/Montserrat-Bold.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
            Font Negrita = new Font(montserratBold, 12, Font.NORMAL, new BaseColor(0x5f, 0x2f, 0x23)); // color #5f2f23

            Font montserrat12Negro = new Font(montserrat, 12, Font.NORMAL, BaseColor.BLACK);

            // Crear la imagen del logo y ajustarla
            Image img = Image.getInstance("src/files/PDFventaslogo.png");
            img.scalePercent(6); // 20% del tamaño original
            img.setAlignment(Image.ALIGN_LEFT);

            // Crear el párrafo con la fecha
            Paragraph fecha = new Paragraph("Factura: " + String.format("%03d", numeroFactura) + "\nFecha: " + fechaActual, fuentePersonalizada);

            fecha.setAlignment(Element.ALIGN_RIGHT);

            // Crear tabla del encabezado
            PdfPTable Encabezado = new PdfPTable(4);
            Encabezado.setWidthPercentage(100);
            Encabezado.getDefaultCell().setBorder(0);
            float[] ColumnaEncabezado = new float[]{20f, 5f, 55f, 20f};
            Encabezado.setWidths(ColumnaEncabezado);
            Encabezado.setHorizontalAlignment(Element.ALIGN_LEFT);

            // Celda con imagen
            PdfPCell celdaImg = new PdfPCell(img);
            celdaImg.setBorder(0);
            celdaImg.setRowspan(2);
            Encabezado.addCell(celdaImg);

// Celda vacía para separación
            PdfPCell vacio = new PdfPCell(new Phrase(""));
            vacio.setBorder(0);
            Encabezado.addCell(vacio);

            String nombre = "Cowboy Cookies";
            String telefono = "(829)-975-9719";
            String direccion = "Calle #7 Almanzar, Honduras del Norte";

// Celda con información del negocio
            Paragraph infoNegocio = new Paragraph();
            infoNegocio.add(new Phrase("NOMBRE: ", Negrita));
            infoNegocio.add(new Phrase(nombre + "\n", fuentePersonalizada));
            infoNegocio.add(new Phrase("TELÉFONO: ", Negrita));
            infoNegocio.add(new Phrase(telefono + "\n", fuentePersonalizada));
            infoNegocio.add(new Phrase("DIRECCIÓN: ", Negrita));
            infoNegocio.add(new Phrase(direccion + "\n", fuentePersonalizada));

            PdfPCell celdaInfo = new PdfPCell(infoNegocio);
            celdaInfo.setBorder(0);
            celdaInfo.setPaddingTop(5f); // Espacio superior
            Encabezado.addCell(celdaInfo);

            PdfPCell celdaFecha = new PdfPCell(fecha);
            celdaFecha.setBorder(0);
            celdaFecha.setVerticalAlignment(Element.ALIGN_TOP);
            celdaFecha.setPaddingTop(5f); // Espacio superior
            Encabezado.addCell(celdaFecha);

// Agregar el encabezado al documento
            doc.add(Encabezado);

            Paragraph espacio = new Paragraph();
            espacio.add(Chunk.NEWLINE);
            espacio.add("");
            espacio.setAlignment(Element.ALIGN_CENTER);
            doc.add(espacio);

            // Agregar nombre del empleado que registró la venta
            Paragraph empleado = new Paragraph("Atendido por: " + nombreEmpleado, fuentePersonalizada);
            empleado.add(Chunk.NEWLINE); // nueva línea
            empleado.setAlignment(Paragraph.ALIGN_LEFT);
            doc.add(empleado);

            doc.add(espacio);
            doc.add(espacio);

            //AGREGAR LOS PRODUCTOS
            PdfPTable tablaProducto = new PdfPTable(5);
            tablaProducto.setWidthPercentage(100);
            tablaProducto.getDefaultCell().setBorder(0);
            //tamaño de celdas
            float[] ColumnaProducto = new float[]{15f, 50f, 15f, 15f, 20f};
            tablaProducto.setWidths(ColumnaProducto);
            tablaProducto.setHorizontalAlignment(Element.ALIGN_LEFT);
            PdfPCell producto1 = new PdfPCell(new Phrase("Cantidad: ", Negrita));
            PdfPCell producto2 = new PdfPCell(new Phrase("Descripción: ", Negrita));
            PdfPCell producto3 = new PdfPCell(new Phrase("Precio Unitario: ", Negrita));
            PdfPCell producto4 = new PdfPCell(new Phrase("ITBIS: ", Negrita));
            PdfPCell producto5 = new PdfPCell(new Phrase("Precio Total: ", Negrita));

            //quitar bordes
            producto1.setBorder(0);
            producto2.setBorder(0);
            producto3.setBorder(0);
            producto4.setBorder(0);
            producto5.setBorder(0);
            //agregar color al encabezado del producto
            BaseColor colorCelda = new BaseColor(162, 210, 255); // Color #a2d2ff
            producto1.setBackgroundColor(colorCelda);
            producto2.setBackgroundColor(colorCelda);
            producto3.setBackgroundColor(colorCelda);
            producto4.setBackgroundColor(colorCelda);
            producto5.setBackgroundColor(colorCelda);
            //agg celda a la tabla
            tablaProducto.addCell(producto1);
            tablaProducto.addCell(producto2);
            tablaProducto.addCell(producto3);
            tablaProducto.addCell(producto4);
            tablaProducto.addCell(producto5);

            for (int i = 0; i < FrmNuevaVenta.jTable_Productos.getRowCount(); i++) {
                String producto = FrmNuevaVenta.jTable_Productos.getValueAt(i, 1).toString();
                String cantidad = FrmNuevaVenta.jTable_Productos.getValueAt(i, 2).toString();
                String precio = FrmNuevaVenta.jTable_Productos.getValueAt(i, 3).toString();
                String ITBIS = FrmNuevaVenta.jTable_Productos.getValueAt(i, 5).toString();
                String total = FrmNuevaVenta.jTable_Productos.getValueAt(i, 6).toString();

                PdfPCell celdaCantidad = new PdfPCell(new Phrase(cantidad, montserrat12Negro));
                celdaCantidad.setPaddingTop(5f);
                celdaCantidad.setPaddingBottom(5f);

                PdfPCell celdaProducto = new PdfPCell(new Phrase(producto, montserrat12Negro));
                celdaProducto.setPaddingTop(5f);
                celdaProducto.setPaddingBottom(5f);

                PdfPCell celdaPrecio = new PdfPCell(new Phrase(precio, montserrat12Negro));
                celdaPrecio.setPaddingTop(5f);
                celdaPrecio.setPaddingBottom(5f);
                
                PdfPCell celdaITBIS = new PdfPCell(new Phrase(ITBIS, montserrat12Negro));
                celdaPrecio.setPaddingTop(5f);
                celdaPrecio.setPaddingBottom(5f);

                PdfPCell celdaTotal = new PdfPCell(new Phrase(total, montserrat12Negro));
                celdaTotal.setPaddingTop(5f);
                celdaTotal.setPaddingBottom(5f);

                // Opcional: remover bordes si quieres mantenerlos como en el encabezado
                celdaCantidad.setBorder(0);
                celdaProducto.setBorder(0);
                celdaPrecio.setBorder(0);
                celdaITBIS.setBorder(0);
                celdaTotal.setBorder(0);

                tablaProducto.addCell(celdaCantidad);
                tablaProducto.addCell(celdaProducto);
                tablaProducto.addCell(celdaPrecio);
                tablaProducto.addCell(celdaITBIS);
                tablaProducto.addCell(celdaTotal);

            }

            //agregar al documento
            doc.add(tablaProducto);

            // Separar visualmente el total
            Paragraph espacioEntreTablaYTotal = new Paragraph();
            espacioEntreTablaYTotal.add(Chunk.NEWLINE);
            espacioEntreTablaYTotal.setSpacingAfter(5f);
            doc.add(espacioEntreTablaYTotal);

            // Total en una tabla aparte
            PdfPTable totalTabla = new PdfPTable(1);
            totalTabla.setWidthPercentage(40);
            totalTabla.setHorizontalAlignment(Element.ALIGN_RIGHT);
            PdfPCell totalCelda = new PdfPCell(new Phrase("Total a pagar: " + FrmNuevaVenta.txtTotal.getText(), Negrita));
            totalCelda.setHorizontalAlignment(Element.ALIGN_RIGHT);
            totalCelda.setPadding(10f);
            totalCelda.setBackgroundColor(new BaseColor(255, 243, 209)); // Color suave
            totalCelda.setBorderColor(BaseColor.LIGHT_GRAY);
            totalCelda.setBorderWidth(0.5f);
            totalTabla.addCell(totalCelda);
            doc.add(totalTabla);

            // Mensaje de agradecimiento
            Paragraph mensaje = new Paragraph("¡Gracias por su compra!", Negrita);
            mensaje.setAlignment(Element.ALIGN_CENTER);
            mensaje.setSpacingBefore(20f);
            doc.add(mensaje);

            //cerrar el documento y el archivo
            doc.close();
            archivo.close();

            //abrir el documento al ser generado automaticamente
            Desktop.getDesktop().open(file);

        } catch (DocumentException | IOException e) {
            System.out.println("Error en: " + e);
        }
    }

    private int obtenerNumeroFactura() {
        int numero = 1; // valor por defecto si el archivo no existe
        File archivo = new File("src/files/contador_factura.txt");

        try {
            if (archivo.exists()) {
                BufferedReader reader = new BufferedReader(new FileReader(archivo));
                numero = Integer.parseInt(reader.readLine());
                reader.close();
            }
        } catch (IOException | NumberFormatException e) {
            System.out.println("Error al leer número de factura: " + e.getMessage());
        }

        // guardar el nuevo número para la siguiente factura
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(archivo));
            writer.write(String.valueOf(numero + 1));
            writer.close();
        } catch (IOException e) {
            System.out.println("Error al guardar número de factura: " + e.getMessage());
        }

        return numero;
    }

}
