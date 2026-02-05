package petshop;
public class Gato extends Animal implements Mascota {
    private String color;
    private String raza;
    private String tipoPelo;
    private int chip;
    private String nombre;
    private Duenio duenio;

    public Gato(String sexo, int edadDias, String ean, String color, String raza, String tipoPelo) {
        super(sexo, edadDias, ean);
        this.color = color;
        this.raza = raza;
        this.tipoPelo = tipoPelo;
    }

    @Override
    public double getPrecio() { return 50; }

    @Override
    public String getTipo() { return "Gato"; }

    @Override
    public String mostrarCaracteristicas() {
        return "Gato [" + color + ", " + raza + ", " + tipoPelo + "]";
    }

    @Override
    public int getChip() { return chip; }

    @Override
    public String getNombre() { return nombre; }

    @Override
    public Duenio getDuenio() { return duenio; }

    @Override
    public void setChip(int chip) { this.chip = chip; }

    @Override
    public void setNombre(String nombre) { this.nombre = nombre; }

    @Override
    public void setDuenio(Duenio duenio) { this.duenio = duenio; }

    public boolean leGustaComida(String comida) {
        return comida.equalsIgnoreCase("pescado") || comida.equalsIgnoreCase("pienso");
    }
}
