
package Conexion;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.DriverManager;

/**
 *
 * @author Ezequiel R
 */
public class Conexion {
    //conexion local

    public static Connection conectar() {
        try {
            Connection cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bd_sistema_facturacion", "root", "12345");
            return cn;
        } catch (SQLException e) {
            System.out.println("Error en la conexion local:");
            return (null);
        }

    }

}
