package ejercicios;

public class Libro {
    private String titulo;
    private String autor;
    private int paginas;
    private boolean prestado = false;

    // Constructor
    public Libro(String titulo, String autor, int paginas) {
        this.autor = autor;
        this.paginas = paginas;
        this.titulo = titulo;
    }

    public Libro() {
    }

    public String getTitulo() {
        return titulo;
    }

    public String getAutor() {
        return autor;
    }

    public int getPaginas() {
        return paginas;
    }

    public boolean isPrestado() {
        return prestado;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public void setPaginas(int paginas) {
        this.paginas = paginas;
    }

    public void setPrestado(boolean prestado) {
        this.prestado = prestado;
    }

    @Override
    public String toString() {
        return "Libro [titulo=" + titulo + ", autor=" + autor + ", paginas=" + paginas + ", prestado=" + prestado + "]";
    }

    public void prestar() {
        if (this.prestado)
            System.out.println("No se puede prestar. Ya esta prestado");
        else {
            prestado = true;
            System.out.println("El libro se ha prestado correctamente");
        }
    }

    public void devolver() {
        if (this.prestado) {
            prestado = false;
            System.out.println("Tu libro se ha devuelto correctamente");
        } else {
            System.out.println("Este libro ya estaba disponible");
        }
    }

    public void mostrarInfo() {
        System.out.println("Titulo: " + this.titulo);
        System.out.println("Autor: " + this.autor);
        System.out.println("Paginas: " + this.paginas);
        System.out.println("¿Prestado?: " + this.prestado);
    }

}
