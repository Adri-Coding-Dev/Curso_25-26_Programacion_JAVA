package petshop;
public class Ave extends Animal {
    private String color;
    private String tipo;

    public Ave(String sexo, int edadDias, String ean, String color, String tipo) {
        super(sexo, edadDias, ean);
        this.color = color;
        this.tipo = tipo;
    }

    @Override
    public double getPrecio() { return 10; }

    @Override
    public String getTipo() { return "Ave"; }

    @Override
    public String mostrarCaracteristicas() {
        return "Ave [" + color + ", tipo=" + tipo + "]";
    }

    public boolean leGustaComida(String comida) {
        return comida.equalsIgnoreCase("pienso");
    }
}
