package functions;

public class Flip {
    /**
     * Metodo para invertir un numero
     * @param number -> Numero para invertir
     * @return -> numero invertido
     */
    public static int flip(int number){
        int invertido = 0, resto;
        int original = number; // Guardar para mostrar al final

        // Bucle para invertir el número
        while (number != 0) {
            resto = number % 10;                // Obtiene el último dígito
            invertido = invertido * 10 + resto; // Construye el número invertido
            number /= 10;                       // Elimina el último dígito
        }

        return invertido;
    }
}
