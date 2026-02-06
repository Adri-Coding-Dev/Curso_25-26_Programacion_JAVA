package functions;

public class IsPrime {

    /**
     * Metodo para averiguar si un numero es primo o no
     * @param numero -> Numero a evaluar
     * @return -> Si es primo o no
     */
    public static boolean esPrimo(int numero) {
        // Los números menores o iguales a 1 no son primos
        if (numero <= 1) {
            return false;
        }
        // 2 y 3 son números primos
        if (numero <= 3) {
            return true;
        }
        // Eliminar pares y múltiplos de 3 para optimizar
        if (numero % 2 == 0 || numero % 3 == 0) {
            return false;
        }
        // Comprobar divisores hasta la raíz cuadrada del número
        for (int i = 5; i * i <= numero; i += 6) {
            if (numero % i == 0 || numero % (i + 2) == 0) {
                return false;
            }
        }
        return true;
    }
}
