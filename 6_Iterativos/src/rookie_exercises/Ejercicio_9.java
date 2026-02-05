package rookie_exercises;
import java.util.*;
public class Ejercicio_9 {

	public static void main(String[] args) {
		/*Haz un juego donde el programa elige un número aleatorio y 
		 *el usuario debe adivinarlo con pistas de "mayor" o "menor".*/
		Scanner s=new Scanner(System.in);
		int max=Integer.MAX_VALUE,min=Integer.MIN_VALUE;
		int numero_Objetivo=(int)(Math.round(Math.random()*(max-min)+1));
		boolean completado=false;
		int intentos=0;
		while(!completado) {
			System.out.println("Introduce un numero");
			int numUsuario=s.nextInt();
			if(numUsuario==numero_Objetivo) {
				System.out.println("Felicidades!!! Has encontrador el numero");
				System.out.println("Numero de intentos: "+intentos);
				completado=true;
				break;
			}else {
				if(numUsuario>numero_Objetivo) {
					System.out.println("Todavia no, el numero es mas pequeño");
					intentos++;
				}else {
					System.out.println("Todavia no, el numero es mas grande");
					intentos++;
				}
			}
		}
	}
}