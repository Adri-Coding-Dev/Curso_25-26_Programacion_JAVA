package functions;

public class DigitPosition {
    public static int digitPosition(int number, int target){
            // Convertimos a String y manejamos números negativos usando Math.abs
            String numStr = String.valueOf(Math.abs(number));
            String digitoStr = String.valueOf(target);

            // indexOf devuelve el índice (0-based) o -1 si no lo encuentra
            return numStr.indexOf(digitoStr);
    }
}
