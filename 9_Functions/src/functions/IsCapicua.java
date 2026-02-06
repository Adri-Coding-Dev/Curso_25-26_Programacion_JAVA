package functions;

import java.util.Scanner;

public class IsCapicua {
    /**
     * Metodo para averiguar si un numero es capicua o no
     * @param num -> Numero a verificar
     * @return -> Si es capicua o no
     */
    public static boolean isCapicua(int num) {
        int original = num;
        int invertido = 0;
        int resto;

        // Proceso para invertir el número
        while (num > 0) {
            resto = num % 10;
            invertido = invertido * 10 + resto;
            num = num / 10;
        }

        // Compara el original con el invertido
        return original == invertido;
    }
}
