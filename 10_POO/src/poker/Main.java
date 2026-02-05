package poker;
public class Main {

    public static void main(String[] args) {

        Baraja baraja = new Baraja();
        baraja.mezclar();

        Jugador j1 = new Jugador("Jugador 1");
        Jugador j2 = new Jugador("Jugador 2");

        for (int i = 0; i < 5; i++) {
            j1.recibirCarta(baraja.darCarta());
            j2.recibirCarta(baraja.darCarta());
        }

        j1.mostrarMano();
        j2.mostrarMano();

        int r1 = Poker.evaluarMano(j1.getMano());
        int r2 = Poker.evaluarMano(j2.getMano());

        System.out.println("Jugador 1 → " + r1);
        System.out.println("Jugador 2 → " + r2);

        if (r1 > r2) {
            System.out.println("GANA Jugador 1");
        } else if (r2 > r1) {
            System.out.println("GANA Jugador 2");
        } else {
            System.out.println("Empate en ranking, desempate por valores...");

            int desempate = Poker.desempatar(j1.getMano(), j2.getMano());

            if (desempate == 1) System.out.println("GANA Jugador 1");
            else if (desempate == 2) System.out.println("GANA Jugador 2");
            else System.out.println("EMPATE TOTAL");
        }
    }
}
