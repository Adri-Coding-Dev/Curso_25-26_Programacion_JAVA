package ejercicios;

public class LibroInfantil extends Libro {
    private int edadRecomendad;

    public LibroInfantil(String titulo, String autor, int paginas, int edadRecomendad) {
        super(titulo, autor, paginas);
        this.edadRecomendad = edadRecomendad;
    }

    @Override
    public String toString() {
        return super.toString() + "LibroInfantil [edadRecomendad=" + edadRecomendad + "]";
    }

    public void mostrarInfo() {
        super.mostrarInfo();
        System.out.println("Edad Recomendada: " + edadRecomendad);
    }

}
