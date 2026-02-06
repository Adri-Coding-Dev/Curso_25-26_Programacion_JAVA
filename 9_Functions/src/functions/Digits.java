package functions;

public class Digits {
    /**
     * Metodo para contar los digitos de un numero
     * @param number -> Numero a calcular
     * @return -> Cantidad de digitos
     */
    public static int digits(int number){
        int contador = 0;
        int temporal = Math.abs(number);

        if (temporal == 0) {
            contador = 1;
        } else {
            while (temporal > 0) {
                temporal /= 10;
                contador++;
            }
        }
        return contador;
    }
}
