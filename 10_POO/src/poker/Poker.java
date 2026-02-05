package poker;
import java.util.Arrays;

public class Poker {

    // Ranking:
    // 10 Escalera Real
    // 9  Escalera de Color
    // 8  Poker
    // 7  Full
    // 6  Color
    // 5  Escalera
    // 4  Trio
    // 3  Doble Pareja
    // 2  Pareja
    // 1  Carta Alta

    public static int evaluarMano(Carta[] mano) {

        int[] v = new int[5];
        String[] p = new String[5];

        for (int i = 0; i < 5; i++) {
            v[i] = mano[i].getValor() == 1 ? 14 : mano[i].getValor();
            p[i] = mano[i].getPalo();
        }

        Arrays.sort(v);

        boolean color = esColor(p);
        boolean escalera = esEscalera(v);

        int[] rep = contarRepeticiones(v);

        boolean pareja = rep[0] == 2;
        boolean doblePareja = rep[0] == 2 && rep[1] == 2;
        boolean trio = rep[0] == 3;
        boolean poker = rep[0] == 4;
        boolean full = rep[0] == 3 && rep[1] == 2;

        if (escalera && color && v[4] == 14) return 10;
        if (escalera && color) return 9;
        if (poker) return 8;
        if (full) return 7;
        if (color) return 6;
        if (escalera) return 5;
        if (trio) return 4;
        if (doblePareja) return 3;
        if (pareja) return 2;

        return 1;
    }

    private static boolean esColor(String[] p) {
        return p[0].equals(p[1]) && p[1].equals(p[2]) &&
               p[2].equals(p[3]) && p[3].equals(p[4]);
    }

    private static boolean esEscalera(int[] v) {
        for (int i = 0; i < 4; i++) {
            if (v[i] + 1 != v[i + 1])
                return false;
        }
        return true;
    }

    private static int[] contarRepeticiones(int[] v) {
        int[] rep = new int[5];
        int idx = 0;

        for (int i = 0; i < 5; ) {
            int count = 1;
            int j = i + 1;

            while (j < 5 && v[j] == v[i]) {
                count++;
                j++;
            }

            rep[idx] = count;
            idx++;
            i = j;
        }

        Arrays.sort(rep);
        return new int[]{rep[4], rep[3]};
    }

    // Comparación avanzada en caso de empate
    public static int desempatar(Carta[] m1, Carta[] m2) {
        int[] v1 = manoOrdenada(m1);
        int[] v2 = manoOrdenada(m2);

        for (int i = 4; i >= 0; i--) {
            if (v1[i] > v2[i]) return 1;
            if (v2[i] > v1[i]) return 2;
        }
        return 0;
    }

    private static int[] manoOrdenada(Carta[] mano) {
        int[] valores = new int[5];
        for (int i = 0; i < 5; i++) {
            valores[i] = mano[i].getValor() == 1 ? 14 : mano[i].getValor();
        }
        Arrays.sort(valores);
        return valores;
    }
}
