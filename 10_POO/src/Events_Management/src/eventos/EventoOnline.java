package eventos;

import java.time.LocalDateTime;

public class EventoOnline extends Evento {

    /**
     * Atributos
     */

    private String plataforma;

    /**
     * Constructor del Evento online
     * 
     * @param nombre     -> Nombre del evento
     * @param fechaHora  -> Fecha del evento
     * @param plataforma -> Plataforma en la que se realizará el evento
     */

    public EventoOnline(String nombre, LocalDateTime fechaHora, String plataforma) {
        super(nombre, fechaHora);
        this.plataforma = plataforma;
    }

    /**
     * Metodo sobreescrito para mostrar la informacion del evento
     */

    @Override
    public void mostarinfo() {
        System.out.println("=".repeat(40));
        System.out.println("Evento online: " + nombre);
        System.out.println("Plataforma: " + plataforma);
        System.out.println("Fecha: " + fechaHora);
        if (isFuture())
            System.out.println("Evento por realizar");
        else
            System.out.println("Evento pasado");
        System.out.println("=".repeat(40));
    }

}
