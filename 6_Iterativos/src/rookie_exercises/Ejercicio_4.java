package rookie_exercises;
import java.util.*;
public class Ejercicio_4 {

	public static void main(String[] args) {
		/*Crea un programa que pida al usuario 
		 *un número e imprima ese número en binario.
		 *Tienes prohibido el uso del bucle "for".
		 * */
		Scanner s=new Scanner(System.in);
		System.out.println("Introduzca un numero");
		Integer num=s.nextInt();
		System.out.println(num.toBinaryString(num));
		s.close();
	}

}
