package petshop;
public class Perro extends Animal implements Mascota {
    private String color;
    private String raza;
    private String tipoPelo;
    private boolean pedigree;
    private int chip;
    private String nombre;
    private Duenio duenio;

    public Perro(String sexo, int edadDias, String ean, String color, String raza, String tipoPelo, boolean pedigree) {
        super(sexo, edadDias, ean);
        this.color = color;
        this.raza = raza;
        this.tipoPelo = tipoPelo;
        this.pedigree = pedigree;
    }

    @Override
    public double getPrecio() { return pedigree ? 200 : 100; }

    @Override
    public String getTipo() { return "Perro"; }

    @Override
    public String mostrarCaracteristicas() {
        return "Perro [" + color + ", " + raza + ", " + tipoPelo + ", pedigree=" + pedigree + "]";
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
        return comida.equalsIgnoreCase("carne") || comida.equalsIgnoreCase("huesos") || comida.equalsIgnoreCase("pienso");
    }
}
