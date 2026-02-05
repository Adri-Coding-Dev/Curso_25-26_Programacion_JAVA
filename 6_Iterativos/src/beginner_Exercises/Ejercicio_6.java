package beginner_Exercises;
import java.util.*;
public class Ejercicio_6 {

	public static void main(String[] args) {
		/*Ejercicio que obtenga todos los múltiplos de un número 
		 *introducido por el usuario. Se imprimirán todos los múltiplos 
		 *menores de 100, este será el límite.
		 * */
		Scanner s=new Scanner(System.in);
		System.out.println("Introduce un numero:");
		int num=s.nextInt();
		int multiplo=0;
		for(int i=0;i<100;i++) {
			if(multiplo>=100) {
				break;
			}else {
				multiplo=num*i;
				System.out.println("Multiplo "+i+": "+multiplo);
			}
		}
		s.close();
	}

}
