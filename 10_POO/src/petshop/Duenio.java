package petshop;

import java.io.Serializable;

public class Duenio implements Serializable {
    private String nombre;
    private String apellido;
    private String dni;
    private int numCompras;

    public Duenio(String nombre, String apellido, String dni) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
        this.numCompras = 0;
    }

    public String getNombre() { return nombre; }
    public String getApellido() { return apellido; }
    public String getDni() { return dni; }
    public int getNumCompras() { return numCompras; }

    public void incrementarCompras() { numCompras++; }

    public boolean esClienteHabitual() { return numCompras >= 4; }

    @Override
    public String toString() {
        String texto = nombre + " " + apellido + " (DNI: " + dni + ", compras: " + numCompras + ")";
        if (esClienteHabitual()) {
            // Amarillo
            return "\u001B[33m" + texto + " ⭐ (Habitual)\u001B[0m";
        }
        return texto;
    }
}
