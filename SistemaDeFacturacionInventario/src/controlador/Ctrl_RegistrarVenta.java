package controlador;

import Conexion.Conexion;
import com.mysql.jdbc.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import modelo.CabeceraVenta;
import modelo.DetalleVenta;
import java.sql.ResultSet;

/**
 *
 * @author admin
 */
public class Ctrl_RegistrarVenta {
    // Ultimo id de la cabcera
    
    public static int idCabeceraRegistrada;
    java.math.BigDecimal iDColVar;
    
    /* 
    Metodo para guardar la cabecera de venta
    */
        public boolean guardar(CabeceraVenta objeto) {
        boolean respuesta = false;
        Connection cn = Conexion.conectar();

        try {

            PreparedStatement consulta = cn.prepareStatement("insert into tb_cabecera_venta values (?,?,?,?,?)", 
                    Statement.RETURN_GENERATED_KEYS);
            consulta.setInt(1, 0);//id;
            consulta.setInt(2, objeto.getIdCliente());
            consulta.setDouble(3, objeto.getValorPagar());
            consulta.setString(4, objeto.getFechaVenta());
            consulta.setInt(5, objeto.getEstado());

            if (consulta.executeUpdate() > 0) {
                respuesta = true;
            }
            
            ResultSet rs = consulta.getGeneratedKeys();
            while (rs.next()) {
                iDColVar = rs.getBigDecimal(1);
                idCabeceraRegistrada = iDColVar.intValue();
            }

            cn.close();
        } catch (java.sql.SQLException e) {
            System.out.println("Error al guardar cabecera de venta" + e);
        }

        return respuesta;
    }
        
        /* 
    Metodo para guardar el detalle de venta
    */
    public boolean guardarDetalle(DetalleVenta objeto) {
        boolean respuesta = false;
        Connection cn = Conexion.conectar();

        try {

            PreparedStatement consulta = cn.prepareStatement("insert into tb_detalle_venta values (?,?,?,?,?,?,?,?,?,?)");
            consulta.setInt(1, 0); // ID;
            consulta.setInt(2, idCabeceraRegistrada);
            consulta.setInt(3, objeto.getIdProducto());
            consulta.setInt(4, objeto.getCantidad());
            consulta.setDouble(5, objeto.getPrecioUnitario());
            consulta.setDouble(6, objeto.getSubtotal());
            consulta.setDouble(7, objeto.getDescuento());
            consulta.setDouble(8, objeto.getItbis());
            consulta.setDouble(9, objeto.getTotalPagar());
            consulta.setInt(10, objeto.getEstado());

            if (consulta.executeUpdate() > 0) {
                respuesta = true;
            }

            cn.close();

        } catch (java.sql.SQLException e) {
            System.out.println("Error al guardar cliente" + e);
        }

        return respuesta;

    }
    
    public boolean actualizar(CabeceraVenta objeto, int idCabeceraVenta) {
        boolean respuesta = false;

        Connection cn = Conexion.conectar();

        try {

            PreparedStatement consulta = cn.prepareStatement("update tb_cabecera_venta set idCliente =?, estado=? "
                    + "where idCabeceraVenta = '" + idCabeceraVenta + "'");
            consulta.setInt(1, objeto.getIdCliente());
            
            consulta.setInt(2, objeto.getEstado());

            if (consulta.executeUpdate() > 0) {
                respuesta = true;
            }

            cn.close();

        } catch (java.sql.SQLException e) {
            System.out.println("Error al actualizar cabecera de venta" + e);
        }

        return respuesta;

    }
}
