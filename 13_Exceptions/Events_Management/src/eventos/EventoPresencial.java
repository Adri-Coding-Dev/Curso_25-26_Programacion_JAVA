package eventos;

import java.time.LocalDateTime;

/**
 * Evento que se realiza presencialmente (en aula)
 */

public class EventoPresencial extends Evento {

    /**
     * Atributo
     */

    private String aula;

    /**
     * Constructor del evento presencial
     * 
     * @param nombre    -> Nombre del evento
     * @param fechaHora -> Fecha del evento
     * @param aula      -> Codigo/ identificador del aula
     */

    public EventoPresencial(String nombre, LocalDateTime fechaHora, String aula) {
        super(nombre, fechaHora);
        this.aula = aula;
    }

    /**
     * Metodo sobreescrito para mostrar la informacion del evento
     */

    @Override
    public void mostarinfo() {
        System.out.println("=".repeat(40));
        System.out.println("Evento Presencial: " + nombre);
        System.out.println("Aula: " + aula);
        System.out.println("Fecha: " + fechaHora);
        if (isFuture())
            System.out.println("Evento por realizar");
        else
            System.out.println("Evento pasado");
        System.out.println("=".repeat(40));
    }

}
