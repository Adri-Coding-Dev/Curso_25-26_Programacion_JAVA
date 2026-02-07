package connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Clase encargada de tramitar una conexion con la BBDD
 */


public class ConnexionBBDD {
    static ConfigDB configDB = new ConfigDB(); // Clase de configuracion para acceder al USER, la PASS y la URL

    /**
     * Metodo para tramitar una conexion con la BBDD
     * @return -> La conexion con la BBDD
     * @throws SQLException -> En caso de que algun parametro falle o no carge el Driver
     */
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(configDB.getUrl(),configDB.getUSER(),configDB.getPass());
    }
}
