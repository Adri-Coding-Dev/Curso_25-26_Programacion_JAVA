package rookie_exercises;
import java.util.*;
public class Ejercicio_7 {

	public static void main(String[] args) {
		/*Escribe un programa que imprima los primeros n términos de la secuencia de Fibonacci*/
		Scanner s=new Scanner(System.in);
        System.out.print("Introduce cuántos números de Fibonacci quieres: ");
        int n=s.nextInt();
        int anterior=0; // -> Primer Numero
        int actual=1; // -> Segundo Numero
        int posterior; // -> Suma de los dos numeros (Posterior)
        for (int i=1;i<=n;i++) {
            System.out.println(anterior);
            posterior=anterior+actual;
            anterior=actual;
            actual=posterior;
        }
        s.close();
	}
}
