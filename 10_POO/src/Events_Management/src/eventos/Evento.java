package eventos;

import java.time.LocalDateTime;

/**
 * Clase abstracta base para crear todos los eventos
 */

public abstract class Evento implements InterfaceEvent {

    /**
     * Atributos esenciales
     */

    protected String nombre;
    protected LocalDateTime fechaHora;

    /**
     * Constructor
     */

    public Evento(String nombre, LocalDateTime fechaHora) {
        this.nombre = nombre;
        this.fechaHora = fechaHora;
    }

    /**
     * Getter para obtener el nombre del evento
     * 
     * @return -> nombre del evento
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Metodo para indicar si el evento no ha ocurrido
     * 
     * @return -> si el evento ha pasado ya
     */
    public boolean isFuture() {
        return fechaHora.isAfter(LocalDateTime.now());
    }

}
