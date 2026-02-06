package functions;

public class NumbersTogether {
    public static long numbersTogether(int number1, int number2){
        String reusltado = String.valueOf((number1) + String.valueOf(number2));
        return Long.parseLong(reusltado);
    }
}
