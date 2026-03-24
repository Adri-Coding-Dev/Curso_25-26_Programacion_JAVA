package model;

/**
 * Modelo que representa un Conductor almacenado en la base de datos.
 * Contiene los atributos básicos de identificación del conductor.
 */
public class Conductor {

    // ===== ATRIBUTOS =====
    private int    id_C;      // Identificador único del conductor
    private String nombre;    // Nombre del conductor
    private String apellido;  // Apellido del conductor

    // ===== CONSTRUCTOR =====

    /**
     * Constructor con todos los parámetros.
     *
     * @param id_C     Identificador único del conductor
     * @param nombre   Nombre del conductor
     * @param apellido Apellido del conductor
     */
    public Conductor(int id_C, String nombre, String apellido) {
        this.id_C     = id_C;
        this.nombre   = nombre;
        this.apellido = apellido;
    }

    // ===== GETTERS Y SETTERS =====

    /** Devuelve el identificador único del conductor. */
    public int getId_C() { return id_C; }

    /** Establece el identificador único del conductor. */
    public void setId_C(int id_C) { this.id_C = id_C; }

    /** Devuelve el nombre del conductor. */
    public String getNombre() { return nombre; }

    /** Establece el nombre del conductor. */
    public void setNombre(String nombre) { this.nombre = nombre; }

    /** Devuelve el apellido del conductor. */
    public String getApellido() { return apellido; }

    /** Establece el apellido del conductor. */
    public void setApellido(String apellido) { this.apellido = apellido; }

    // ===== UTILIDADES =====

    /** Representación textual del conductor para depuración y logs. */
    @Override
    public String toString() {
        return "[Id_C: " + id_C + ", Nombre: " + nombre + ", Apellido: " + apellido + "]";
    }
}
