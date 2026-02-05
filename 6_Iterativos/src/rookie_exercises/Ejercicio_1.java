package rookie_exercises;
import java.util.*;
public class Ejercicio_1 {

	public static void main(String[] args) {
		/*Crea un programa que compruebe si un número es primo o no.
		 *Debes pedir al usuario un número y después informar de si 
		 *se trata de un número primo o compuesto. Tienes prohibido 
		 *el uso del bucle "for".
		 * */
		Scanner s=new Scanner(System.in);
		System.out.println("Introduce un numero");
		int num=s.nextInt();
		verificarPrimo(num);
		s.close();
	}

	public static void verificarPrimo(int num) {
		boolean salir=false;
		int contador=2;
		int incremento=num-1;
		boolean primo=false;
		while(!salir) {
			int resto=num%(incremento);
			if(resto==0) {
				primo=false;
				salir=true;
			}
				contador++;
				incremento--;
			if(contador==num) {
				primo=true;
				salir=true;
			}
		}
		if(primo){
			System.out.println("Primo");
		}else {
			System.out.println("No Primo");
		}
	}

}
