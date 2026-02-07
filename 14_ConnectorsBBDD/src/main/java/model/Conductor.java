package model;

/**
 * Clase Conductor de la BBDD
 */

public class Conductor {
    //ATRIBUTOS
    private  int id_C;
    private  String nombre;
    private  String apellido;

    //CONSTRUCTOR con parametros (no necesito constructor vacio)
    public Conductor(int id_C,String nombre,String apellido){
        this.id_C = id_C;
        this.nombre = nombre;
        this.apellido = apellido;
    }

    //GETTERS && SETTERS
    public int getId_C() {
        return id_C;
    }

    public void setId_C(int id_C) {
        this.id_C = id_C;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    @Override
    public String toString(){
        return "[Id_C: "+this.id_C+", Nombre: "+this.nombre+", Apellido: "+this.apellido+"]";
    }
}
