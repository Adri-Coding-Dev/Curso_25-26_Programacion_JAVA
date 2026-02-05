package petshop;
public class Rata extends Animal {
    private double peso; // gramos
    private double tamano; // cm

    public Rata(String sexo, int edadDias, String ean, double peso, double tamano) {
        super(sexo, edadDias, ean);
        this.peso = peso;
        this.tamano = tamano;
    }

    @Override
    public double getPrecio() { return 2; }

    @Override
    public String getTipo() { return "Rata"; }

    @Override
    public String mostrarCaracteristicas() {
        return "Rata [" + peso + "gr, " + tamano + "cm]";
    }

    public boolean esVendible() {
        return peso >= 200 && tamano >= 5;
    }
}
