package model;

/**
 * Clase Bus de la BBDD
 */
public class Bus {
    //ATRIBUTOS
    private  String id_bus;
    private  String tipo;
    private  String licencia;

    //CONSTRUCTOR con parametros (no necesito constructor vacio)
    public Bus(String id_bus,String tipo,String licencia){
        this.id_bus = id_bus;
        this.licencia = licencia;
        this.tipo = tipo;
    }

    //GETTERS && SETTERS
    public String getId_bus(){
        return this.id_bus;
    }

    public String getTipo(){
        return this.tipo;
    }

    public String getLicencia(){
        return this.licencia;
    }

    @Override
    public String toString(){
        return "[Id_Bus: "+this.id_bus+", Tipo: "+this.tipo+", Licencia: "+this.licencia+"]";
    }
}
