package postOffice;

public class Ticket {
    private int numeroTicket;
    private String tipoAccion; // "ENVIAR" o "RECOGER"
    private long idPackage; // Solo para recoger
    private Paquete paqueteEnvio; // Solo para enviar

    public Ticket(int numeroTicket, String tipoAccion, long idPackage) {
        this.numeroTicket = numeroTicket;
        this.tipoAccion = tipoAccion;
        this.idPackage = idPackage;
    }

    public Ticket(int numeroTicket, String tipoAccion, Paquete paqueteEnvio) {
        this.numeroTicket = numeroTicket;
        this.tipoAccion = tipoAccion;
        this.paqueteEnvio = paqueteEnvio;
    }

    // Getters
    public int getNumeroTicket() {
        return numeroTicket;
    }

    public String getTipoAccion() {
        return tipoAccion;
    }

    public long getIdPackage() {
        return idPackage;
    }

    public Paquete getPaqueteEnvio() {
        return paqueteEnvio;
    }

    @Override
    public String toString() {
        return "Ticket N° " + numeroTicket + " - Acción: " + tipoAccion +
                (idPackage > 0 ? " - ID Paquete: " + idPackage : "");
    }
}