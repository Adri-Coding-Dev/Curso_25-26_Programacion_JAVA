package adrian.dev.controller.db;

/**
 * @autor Adrian
 * CLASE DEDICADA A ENTABLAR UNA CONEXION A LA BBDD CON LOS DATOS DE {@link adrian.dev.controller.db.ConfigBD}
 */

//Importamos las librerias necesarias de mysql
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionDDBB {
    //Declaramos la variable para obtener los datos necesarios
    private static ConfigBD datosBBDD = new ConfigBD();

    /**
     * Metodo para obtener una conexion con la Base de Datos
     * @return -> Conexion entablada con la BBDD
     * @throws SQLException -> En caso de que el DriverManager no encuentre la BBDD o no pueda conectarse
     */
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(datosBBDD.getURL(), datosBBDD.getUSER(), datosBBDD.getPASS());
    }

}
