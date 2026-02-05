package petshop;

public interface Mascota {
    int getChip();
    String getNombre();
    Duenio getDuenio();

    void setChip(int chip);
    void setNombre(String nombre);
    void setDuenio(Duenio duenio);
}
