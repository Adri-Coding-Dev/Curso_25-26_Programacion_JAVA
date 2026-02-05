package excepciones;

public class FechaPasada extends FechaNoValidaException {
    /**
     * Excepcion del tipo FechaNoValidaException en caso de que la fecha del evento
     * sea anterior a la fecha actual (no se pueden crear eventos pasados)
     */

    public FechaPasada() {
        super("- La fecha no puede ser anterior a la fecha actual");
    }

}
