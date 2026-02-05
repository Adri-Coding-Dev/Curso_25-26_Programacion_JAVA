package poker;
public class Jugador {

    private String nombre;
    private Carta[] mano;
    private int indiceMano;

    public Jugador(String nombre) {
        this.nombre = nombre;
        this.mano = new Carta[5];
        this.indiceMano = 0;
    }

    public void recibirCarta(Carta carta) {
        if (indiceMano < 5) {
            mano[indiceMano] = carta;
            indiceMano++;
        }
    }

    public Carta[] getMano() {
        return mano;
    }

    public String getNombre() {
        return nombre;
    }

    public void mostrarMano() {
        System.out.println("Mano de " + nombre + ":");
        for (Carta c : mano) {
            c.mostrarInfoCarta();
        }
        System.out.println();
    }
}

