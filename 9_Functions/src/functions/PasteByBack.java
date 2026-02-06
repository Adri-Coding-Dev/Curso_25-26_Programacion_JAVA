package functions;

public class PasteByBack {
    public static int pasteByBack(int number, int digit){
        // Multiplicamos por 10 para crear el espacio y sumamos el dígito
        return (number * 10) + digit;
    }
}
