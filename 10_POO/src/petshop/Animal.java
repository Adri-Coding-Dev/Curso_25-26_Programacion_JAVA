package petshop;

public abstract class Animal {
    protected String sexo; // "MACHO" o "HEMBRA"
    protected int edadDias;
    protected String ean;

    public Animal(String sexo, int edadDias, String ean) {
        this.sexo = sexo;
        this.edadDias = edadDias;
        this.ean = ean;
    }

    public String getSexo() { return sexo; }
    public int getEdadDias() { return edadDias; }
    public String getEan() { return ean; }

    public abstract double getPrecio();
    public abstract String getTipo();
    public abstract String mostrarCaracteristicas();

    public static boolean puedenAparearse(Animal a1, Animal a2) {
        if (a1.getTipo().equals(a2.getTipo()) && 
            !a1.getTipo().equalsIgnoreCase("Rata") &&
            !a1.getSexo().equalsIgnoreCase(a2.getSexo())) {
            return true;
        }
        return false;
    }
}
