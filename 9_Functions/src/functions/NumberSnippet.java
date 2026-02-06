package functions;

public class NumberSnippet {
    public static long numberSnippet(long number, int start, int end){
        // Convertimos a String para manipular posiciones fácilmente
        String numStr = String.valueOf(Math.abs(number));

        // Validaciones básicas para evitar StringIndexOutOfBoundsException
        if (start < 0 || end > numStr.length() || start >= end) {
            return -1; // O manejar el error según necesites
        }

        String subStr = numStr.substring(start, end);

        // Convertimos de nuevo a número
        return Long.parseLong(subStr);
    }
}
