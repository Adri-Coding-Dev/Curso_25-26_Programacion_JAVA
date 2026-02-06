package functions;

public class RemoveByBehind {
    public static int removeByBehind(int number, int idx){
        // Si n es 0, el número queda igual
        // Si n es mayor o igual a la cantidad de dígitos, el resultado será 0
        int divisor = (int) Math.pow(10, idx);
        return number / divisor;
    }
}
