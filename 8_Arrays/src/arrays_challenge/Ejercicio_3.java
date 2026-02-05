package arrays_challenge;
import java.util.*;
public class Ejercicio_3 {

	public static void main(String[] args) {
		/*Realiza un ejercicio que inicialice un array de 150 
		 *elementos enteros al azar entre 0 y 100. Después de 
		 *su inicialización debéis imprimir el array en orden inverso*/
		Scanner s=new Scanner(System.in);
		int min=0,max=100;
		int array[]=new int [150];
		for(int i=0;i<array.length;i++) {
			array[i]=(int)(Math.round(Math.random()*(max-min)+min));
			System.out.println("Elemento "+i+": "+array[i]);
		}
		System.out.println("Array del reves");
		for(int i=array.length-1;i>=0;i--) {
			System.out.println("Elemento "+i+": "+array[i]);
		}
		s.close();	
	}

}
