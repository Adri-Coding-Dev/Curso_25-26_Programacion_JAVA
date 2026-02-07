package connection;

public class ConfigDB {
    /**
     * Atributos de la clase
     * URL -> Enlace de la base de datos(puerto + nombre de la BBDD)
     * USER -> Usuario de la BBDD (root por defecto)
     * PASS -> Contraseña del usuario
     */
    private  final String URL = "jdbc:mysql://localhost:3306/Mi_Aucorsa";
    private  final String USER = "root";
    private  final String PASS = "root";

    /**
    * GETTERS && SETTERS para aplicar encapsulamiento de clases
    * */


    public String getUrl(){
        return this.URL;
    }

    public String getUSER(){
        return this.USER;
    }

    public String getPass(){
        return this.PASS;
    }
}
