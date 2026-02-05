package arrays_challenge;
import java.util.*;
public class Ejercicio_1 {

	public static void main(String[] args) {
		/*Realiza un ejercicio que inicialice un array de 150 
		 *elementos enteros al azar entre 0 y 100. Debes mostrar 
		 *el array en pantalla y obtener la suma, la media, el 
		 *menor y el mayor de todos los números que aparecen en 
		 *el array*/
		Scanner s=new Scanner(System.in);
		int min=0,max=100;
		int array[]=new int[150];
		int suma=0;
		float media=0;
		int numMayor=Integer.MIN_VALUE,numMenor=Integer.MAX_VALUE;
		for(int i=0;i<array.length;i++) {
			array[i]=(int)(Math.round(Math.random()*(max-min)+min));
			System.out.println(array[i]);
			suma+=array[i];
			if(array[i]>numMayor) {
				numMayor=array[i];
			}
			if(array[i]<numMenor) {
				numMenor=array[i];
			}
		}
		media=suma/array.length;
		System.out.println("Suma de los numeros: "+suma);
		System.out.println("Media de los numeros: "+media);
		System.out.println("Numero Mayor: "+numMayor);
		System.out.println("Numero Menor: "+numMenor);
		s.close();
	}
}
