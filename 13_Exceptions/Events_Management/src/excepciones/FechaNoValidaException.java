package excepciones;

public class FechaNoValidaException extends Exception {

    /**
     * Excepcion en caso de que el evento no tenga una fecha valida dentro del los
     * parseos permitidos
     * 
     * @param mensaje -> String de error que se lanza junto a su fillInStackTrace
     *                (ubicacion del archivo)
     */

    public FechaNoValidaException(String mensaje) {
        super("Error al crear el evento: \n" + mensaje);
        System.err.println(super.fillInStackTrace());
    }
}
