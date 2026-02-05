package rookie_exercises;
import java.util.*;
public class Ejercicio_5 {

	public static void main(String[] args) {
		/* Calcula el factorial de un número dado por el usuario. 
		 * Debes usar sólo el bucle while*/
		Scanner s=new Scanner(System.in);
		System.out.println("Introduce un numero");
		int numUsuario=s.nextInt();
		boolean salir=false;
		int iteracion=numUsuario;
		int factorial=1;
		while(!salir) {
			if(iteracion==0) {
				salir=true;
				break;
			}else {
				factorial*=iteracion;
				iteracion--;
			}
		}
		System.out.println("Factorial: "+factorial);
		s.close();
	}

}
