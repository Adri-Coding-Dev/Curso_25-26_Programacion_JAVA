package rookie_exercises;
import java.util.*;
public class Ejercicio_2 {

	public static void main(String[] args) {
		/*Crea un programa que compruebe si son primos 
		 *o compuestos todos los números entre un límite 
		 *inferior y uno superior que debes pedir al usuario. 
		 *Tienes prohibido el uso del bucle "for".
		 * */
		Scanner s=new Scanner(System.in);
		System.out.println("Introduce un minimo");
		int minimo=s.nextInt();
		System.out.println("Introduce un maximo");
		int maximo=s.nextInt();
		for(int i=minimo;i<maximo;i++) {
			System.out.println("Numero a bsucar: "+i);
			Ejercicio_1.verificarPrimo(i);
		}
		s.close();
	}
}
