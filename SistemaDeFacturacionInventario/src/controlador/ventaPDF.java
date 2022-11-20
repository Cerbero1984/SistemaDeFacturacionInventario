/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
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
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import Conexion.Conexion;
import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import vista.InterFacturacion;

/**
 *
 * @author cerbe
 */
public class ventaPDF {

    private String nombreCliente;
    private String cedulaCliente;
    private String telefonoCliente;
    private String direccionCliente;

    private String fechaActual = "";

    private String nombreArchivoPDFventa = "";

    //metodo para obtener datos de clientes
    public void DatosCliente(int idCliente) {

        Connection cn = Conexion.conectar();
        String sql = "select * from tb_cliente where idCliente = '" + idCliente + "'";
        Statement st;

        try {

            st = (Statement) cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                nombreCliente = rs.getString("nombre") + " " + rs.getString("apellido");
                cedulaCliente = rs.getString("cedula");
                telefonoCliente = rs.getString("telefono");
                direccionCliente = rs.getString("direccion");

            }
            cn.close();

        } catch (SQLException e) {

            System.out.println("Error al obtener datos del cliente:" + e);
        }

    }
    //metodo para generar factura

    public void generarFacturaPDF() {

        try {
            // cargar la fecha actual    
            Date date = new Date();
            fechaActual = new SimpleDateFormat("dd/MM/yyyy").format(date);
            // cambiar el formato de la fecha de / a _    
            String fechaNueva = "";
            for (int i = 0; i < fechaActual.length(); i++) {
                if (fechaActual.charAt(i) == '/') {
                    fechaNueva = fechaActual.replace("/", "_");

                }

            }

            nombreArchivoPDFventa = "venta_" + nombreCliente + "_" + fechaNueva + ".pdf";
            FileOutputStream archivo;
            File file = new File("src/pdf/" + nombreArchivoPDFventa);
            archivo = new FileOutputStream(file);

            Document doc = new Document();
            PdfWriter.getInstance(doc, archivo);
            doc.open();

            Image img = Image.getInstance("src/img/ventas.png");
            Paragraph fecha = new Paragraph();
            Font negrita= new Font(Font.FontFamily.TIMES_ROMAN,12,Font.BOLD, BaseColor.BLUE);
            fecha.add(Chunk.NEWLINE);//agregar nueva linea
            fecha.add("Factura: 001" + "\nFecha:" + fechaActual + "\n\n" );
            
            
            //encabezado
            
            PdfPTable Encabezado = new PdfPTable(4);
            Encabezado.setWidthPercentage(100);
            Encabezado.getDefaultCell().setBorder(0);// quitar borde de la tabla
            
            //tamaño de las celdas
            
            float[] ColumnaEncabezado = new float[]{20f,30f,70f,40f};
            Encabezado.setWidths(ColumnaEncabezado);
            Encabezado.setHorizontalAlignment(Element.ALIGN_LEFT);
            
            
            //agregar celdas
            
            Encabezado.addCell(img);
            
            String rnc = "01030587954";
            String nombre = "ProComsa";
            String telefono = "8091234567";
            String direccion = "c/ 27 de Febrero esq. Linconl";
            String razon = "La lealtad del servicios en nuestras manos.";
            
            Encabezado.addCell("");//celda vacia
            Encabezado.addCell("RNC:" + rnc + "\nNOMBRE:" + nombre + "\nTELEFONO:" + telefono + "\nDIRECCION:"+ direccion + "\nRAZON:" + razon);
            Encabezado.addCell(fecha); 
            doc.add((Element) Encabezado);
            
            //cuerpo
            
            Paragraph cliente = new Paragraph();
            cliente.add(Chunk.NEWLINE);// nueva linea
            cliente.add("Datos del cliente:" + "\n\n");
            doc.add(cliente);
            
                    
          //datos del cliente
          PdfPTable tablaCliente = new PdfPTable(4);
          tablaCliente.setWidthPercentage(100);
          tablaCliente.getDefaultCell().setBorder(0);//quitar bordes
          
          //tamaño de las celdas
          float[] ColumnaCliente = new float[]{25f, 45f, 30f, 40f};
          tablaCliente.setWidths(ColumnaCliente);
          tablaCliente.setHorizontalAlignment(Element.ALIGN_LEFT);
          PdfPCell cliente1= new PdfPCell(new Phrase("Cedula/RNC;", negrita));
          PdfPCell cliente2= new PdfPCell(new Phrase("Nombre;", negrita));
          PdfPCell cliente3= new PdfPCell(new Phrase("Telefono;", negrita));
          PdfPCell cliente4= new PdfPCell(new Phrase("Direccion;", negrita));
          
          //quitar bordes
          cliente1.setBorder(0);
          cliente2.setBorder(0);
          cliente3.setBorder(0);
          cliente4.setBorder(0);
          
          //agregar celda a la tabla
          
          tablaCliente.addCell(cliente1);
          tablaCliente.addCell(cliente2);
          tablaCliente.addCell(cliente3);
          tablaCliente.addCell(cliente4);
          tablaCliente.addCell(cedulaCliente);
          tablaCliente.addCell(nombreCliente);
          tablaCliente.addCell(telefonoCliente);
          tablaCliente.addCell(direccionCliente);
         
          //agregar al documento
          doc.add((Element) tablaCliente);
          
          //espacio en blanco
          Paragraph espacio = new Paragraph();
          espacio.add(Chunk.NEWLINE);
          espacio.add("");
          espacio.setAlignment(Element.ALIGN_CENTER);
          doc.add(espacio);
          
          //agregar producto
          
          PdfPTable tablaProducto = new PdfPTable(4);
          tablaProducto.setWidthPercentage(100);
          tablaProducto.getDefaultCell().setBorder(0);
          //tamaño de celdas
          
           float[] ColumnaProducto = new float[]{15f, 50f, 15f, 20f};
          tablaProducto.setWidths(ColumnaProducto);
          tablaProducto.setHorizontalAlignment(Element.ALIGN_LEFT);
          PdfPCell producto1= new PdfPCell(new Phrase("Cantidad;", negrita));
          PdfPCell Producto2= new PdfPCell(new Phrase("Descripcion;", negrita));
          PdfPCell producto3= new PdfPCell(new Phrase("Precio Unitario;", negrita));
          PdfPCell producto4= new PdfPCell(new Phrase("Precio Total;", negrita));
          
          //quitar bordes
          
          producto1.setBorder(0);
          Producto2.setBorder(0);
          producto3.setBorder(0);
          producto4.setBorder(0);
             
          //agregar color al cabezal del producto
          
          producto1.setBackgroundColor(BaseColor.LIGHT_GRAY);
          Producto2.setBackgroundColor(BaseColor.LIGHT_GRAY);
          producto3.setBackgroundColor(BaseColor.LIGHT_GRAY);
          producto4.setBackgroundColor(BaseColor.LIGHT_GRAY);
          
          //agregar celda a la tabla
          
          tablaProducto.addCell(producto1);
          tablaProducto.addCell(Producto2);
          tablaProducto.addCell(producto3);
          tablaProducto.addCell(producto4);
          
          for(int i =0; i < InterFacturacion.jTable_productos.getRowCount();i++){
          String producto = InterFacturacion.jTable_productos.getValueAt(i, 1).toString();
          String cantidad = InterFacturacion.jTable_productos.getValueAt(i, 2).toString();
          String precio = InterFacturacion.jTable_productos.getValueAt(i, 3).toString();
          String total = InterFacturacion.jTable_productos.getValueAt(i, 7).toString();
             
          tablaProducto.addCell(cantidad);
          tablaProducto.addCell(producto);
          tablaProducto.addCell(precio);
          tablaProducto.addCell(total);
                     
       }
          
          //agregar al documento
          doc.add((Element) tablaProducto);
          
            
          //Total a pagar
          Paragraph info=new Paragraph();
          info.add(Chunk.NEWLINE);
          info.add("Total a pagar:" + InterFacturacion.txt_total_pagar.getText());
          info.setAlignment(Element.ALIGN_RIGHT);
          doc.add(info);
          
          //firma
          
          
          Paragraph firma =new Paragraph();
          firma.add(Chunk.NEWLINE);
          firma.add("Cancelacion y firma\n\n" );
          firma.add("________________________" );
          firma.setAlignment(Element.ALIGN_CENTER);
          doc.add(firma);
          
          //Mensaje
          Paragraph mensaje =new Paragraph();
          mensaje.add(Chunk.NEWLINE);
          mensaje.add("!Gracias por su Compra!, Feliz dia" );
          mensaje.setAlignment(Element.ALIGN_CENTER);
          doc.add(mensaje);
          
          //cerrar el documento
          
          doc.close();
          archivo.close();
          
          //abrir el documento al ser generado auto.
          
          Desktop.getDesktop().open(file);
          

        } catch (DocumentException | IOException e) {

            System.out.println("Error en:" + e);

        }

    }

}
