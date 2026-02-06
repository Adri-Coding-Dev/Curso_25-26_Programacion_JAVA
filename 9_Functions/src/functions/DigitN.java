package functions;

public class DigitN {
    public static int digitN(int number, int idx){
        //Convertimos el numero de cadena de Texto
        String temp = Integer.toString(number);

        //En caso de que el indice sea mayor a la longitud del numero
        if (idx < 0 || idx >= temp.length()) {
            throw new IllegalArgumentException("Position out of bounds");
        }

        //Obtenemos el caracter y convertirmos la cadena a numero
        return Character.getNumericValue(temp.charAt(idx));
    }
}
