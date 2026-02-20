package adrian.dev.controller.db;

/**
 * @author Adrian
 * CLASE DEDICADA A GUARDAR LOS DATOS NECESARIOS PARA ENTABLAR UNA CONEXION
 */
public class ConfigBD {
    //Atributos necesarios -> URL de la BBDD | USER propietario de mysql | PASS asociado al usuario
    private final String URL = "jdbc:mysql://localhost:3306/Mi_Aucorsa";
    private final String USER = "root";
    private final String PASS = "root";

    //Getters necesarios para poder acceder a esos datos desde otras clases sin modificar su contenido
    public String getURL() {
        return URL;
    }

    public String getUSER() {
        return USER;
    }

    public String getPASS() {
        return PASS;
    }
}
