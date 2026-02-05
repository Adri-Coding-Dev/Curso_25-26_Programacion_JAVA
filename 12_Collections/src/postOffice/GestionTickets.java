package postOffice;

import java.util.LinkedList;
import java.util.Queue;

public class GestionTickets {
    private Queue<Ticket> colaTickets;
    private int contadorTickets;

    public GestionTickets() {
        colaTickets = new LinkedList<>();
        contadorTickets = 1;
    }

    public Ticket crearTicketEnvio(Paquete paquete) {
        Ticket ticket = new Ticket(contadorTickets++, "ENVIAR", paquete);
        colaTickets.add(ticket);
        return ticket;
    }

    public Ticket crearTicketRecogida(long idPackage) {
        Ticket ticket = new Ticket(contadorTickets++, "RECOGER", idPackage);
        colaTickets.add(ticket);
        return ticket;
    }

    public Ticket atenderSiguienteCliente() {
        return colaTickets.poll(); // Devuelve y elimina el primero
    }

    public Ticket verSiguienteCliente() {
        return colaTickets.peek(); // Solo mira el primero sin eliminarlo
    }

    public Queue<Ticket> getColaTickets() {
        return new LinkedList<>(colaTickets); // Devuelve una copia
    }

    public int getTamanioCola() {
        return colaTickets.size();
    }

    public boolean hayClientesEnEspera() {
        return !colaTickets.isEmpty();
    }

    public void reiniciarContador() {
        contadorTickets = 1;
    }
}