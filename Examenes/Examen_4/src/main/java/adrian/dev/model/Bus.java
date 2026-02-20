package adrian.dev.model;

/**
 * @author Adrian
 * CLASE DEDICADA A DEFINIR EL OBJETO BUS DE LA BASE DE DATOS
 */

public class Bus {
    //Atributos de la clase (coincide con las columas de la tabla)
    private String id_Bus;
    private String tipo;
    private String licencia;

    //Constructor vacío
    public Bus(){}

    //Constructor con parámetros
    public Bus(String id_Bus, String tipo, String licencia){
        this.id_Bus = id_Bus;
        this.licencia = licencia;
        this.tipo = tipo;
    }

    //Getters & Setters
    public String getId_Bus() {
        return id_Bus;
    }

    public void setId_Bus(String id_Bus) {
        this.id_Bus = id_Bus;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getLicencia() {
        return licencia;
    }

    public void setLicencia(String licencia) {
        this.licencia = licencia;
    }

    //ToString
    public String toString(){
        return "[ ID_Bus: " + this.id_Bus + ", Tipo: " + this.tipo + ", Licencia: " + this.licencia + "]";
    }
}
