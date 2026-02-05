package gestion;

import java.util.ArrayList;
import eventos.*;
import excepciones.EventoNoEncontradoException;

/**
 * Gestor de Eventos
 */

public class GestorEventos {
    // Atributos

    private ArrayList<Evento> eventos = new ArrayList<>();

    /**
     * Metodo para agregar eventos al ArrayList de Eventos
     * 
     * @param e
     */
    public void agregarEvento(Evento e) {
        eventos.add(e);
    }

    /**
     * Metodo para mostrar la informacion de todos los eventos creados
     */

    public void listarTodos() {
        if (eventos.isEmpty()) // -> Si no hay ningun evento...
            System.out.println("No hay eventos creados...");
        else {
            for (Evento evento : eventos) {
                evento.mostarinfo();
            }
        }
    }

    /**
     * Metodo para mostrar la informacion de los eventos futuros
     */

    public void listarFuturos() {
        if (eventos.isEmpty()) { // -> Si no hay eventos...
            System.out.println("No hay eventos para mostrar...");
        } else {
            for (Evento evento : eventos) { // For each para recorrer todos los eventos
                if (evento.isFuture())
                    evento.mostarinfo();
            }
        }
    }

    /**
     * Metodo para borrar los eventos
     * 
     * @param nombre -> Nombre del evento
     * @throws EventoNoEncontradoException -> Excepcion en caso de que no hayan
     *                                     eventos en la lista
     */

    public boolean borrarPorNombre(String nombre) throws EventoNoEncontradoException {
        if (eventos.isEmpty()) {
            throw new EventoNoEncontradoException();
        }
        eventos.removeIf(e -> e.getNombre().equalsIgnoreCase(nombre));
        return true;
    }
}
