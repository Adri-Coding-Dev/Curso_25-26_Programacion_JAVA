package arrays_challenge;
import java.util.*;
public class Ejercicio_4 {

	public static void main(String[] args) {
		/*Realiza un ejercicio que inicialice un array de 150 
		 *elementos enteros al azar entre 0 y 100. A continuación 
		 *pedirás al usuario un valor y buscarás dicho valor en el 
		 *array. Si lo encuentras, debes indicar al usuario la 
		 *posición del array en que se encuentra. En caso de que no 
		 *encuentres el valor buscado, debes indicárselo también al 
		 *usuario*/
		Scanner s=new Scanner(System.in);
		int min=0,max=100;
		int array[]=new int[150];
		for(int i=0;i<array.length;i++) {
			array[i]=(int)(Math.round(Math.random()*(max-min)+min));
		}
		System.out.println("¿Que numero quieres buscar?");
		int numBuscar=s.nextInt();
		boolean terminado=false;
		for(int i=0;i<array.length;i++) {
			if(array[i]==numBuscar) {
				System.out.println("Numero encontrado en la posicion "+i);
				terminado=true;
			}
		}
		if(!terminado) {
			System.out.println("Su numero no se ha podido encontrar...");
		}
		s.close();
	}

}
