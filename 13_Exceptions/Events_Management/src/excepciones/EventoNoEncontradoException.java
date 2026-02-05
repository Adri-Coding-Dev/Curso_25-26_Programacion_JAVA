package excepciones;

public class EventoNoEncontradoException extends Exception {
    /**
     * Excepcion personalizada en caso de que no se encuentre ningun evento (lista
     * de eventos vacia)
     */

    public EventoNoEncontradoException() {
        super("Evento no encontrado");
    }
}
