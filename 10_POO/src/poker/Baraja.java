package poker;
import java.util.Random;

public class Baraja {

    private Carta[] cartas;
    private int indiceActual = 0;

    public Baraja() {
        this.cartas = new Carta[52];
        inicializarBaraja();
    }

    // A. Inicializar las 52 cartas
    private void inicializarBaraja() {
        String[] palos = {"Corazones", "Diamantes", "Tréboles", "Picas"};
        int index = 0;

        for (String palo : palos) {
            for (int valor = 1; valor <= 13; valor++) {
                cartas[index] = new Carta(valor, palo);
                index++;
            }
        }
    }

    // B. Mostrar baraja
    public void mostrarBaraja() {
        for (Carta c : cartas) {
            c.mostrarInfoCarta();
        }
        System.out.println();
    }

    // C. Pasar carta hacia arriba (rotar)
    public void pasarCartaArriba() {
        Carta primera = cartas[0];
        for (int i = 0; i < cartas.length - 1; i++) {
            cartas[i] = cartas[i + 1];
        }
        cartas[cartas.length - 1] = primera;
    }

    // C2. Pasar carta hacia abajo
    public void pasarCartaAbajo() {
        Carta ultima = cartas[cartas.length - 1];
        for (int i = cartas.length - 1; i > 0; i--) {
            cartas[i] = cartas[i - 1];
        }
        cartas[0] = ultima;
    }

    // D. Buscar carta
    public int buscarCarta(int valor, String palo) {
        for (int i = 0; i < cartas.length; i++) {
            if (cartas[i].getValor() == valor && cartas[i].getPalo().equals(palo)) {
                return i;
            }
        }
        return -1;
    }

    // E. Mezclar baraja
    public void mezclar() {
        Random random = new Random();
        for (int i = 0; i < cartas.length; i++) {
            int randomIndex = random.nextInt(52);
            Carta temp = cartas[i];
            cartas[i] = cartas[randomIndex];
            cartas[randomIndex] = temp;
        }
    }

    // F. Dar carta desde la parte superior
    public Carta darCarta() {
        if (indiceActual >= 52) {
            System.out.println("La baraja está vacía!");
            return null;
        }
        Carta carta = cartas[indiceActual];
        indiceActual++;
        return carta;
    }
}
