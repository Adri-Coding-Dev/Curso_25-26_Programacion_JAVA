package model;

/**
 * Clase Lugar de la BBDD
 */
public class Lugar {
    //ATRIBUTOS
    private int id_L;
    private String cod_Post;
    private String ciudad;
    private String ubicacion;

    //CONSTRUCTRO con parametros (no necesito constructor vacio)
    public Lugar(int id_L,String cod_Post,String ciudad,String ubicacion){
        this.id_L = id_L;
        this.cod_Post = cod_Post;
        this.ciudad = ciudad;
        this.ubicacion = ubicacion;
    }

    //GETTERS && SETTERS
    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getCod_Post() {
        return cod_Post;
    }

    public void setCod_Post(String cod_Post) {
        this.cod_Post = cod_Post;
    }

    public int getId_L() {
        return id_L;
    }

    public void setId_L(int id_L) {
        this.id_L = id_L;
    }

    @Override
    public String toString(){
        return "[ID_Lugar: "+this.id_L+", Codigo_Postal: "+this.cod_Post+", Ciudad: "+this.ciudad+", Ubicacion: "+this.ubicacion+"]";
    }
}
