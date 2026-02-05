package arrays_challenge;
import java.util.*;
public class Ejercicio_2 {

	public static void main(String[] args) {
		/*Realiza un ejercicio que inicialice un array de 150 
		 *elementos enteros al azar entre un límite inferior 
		 *y uno superior que debe especificar el usuario. 
		 *Debes mostrar el array en pantalla para comprobar 
		 *el funcionamiento*/
		Scanner s=new Scanner(System.in);
		System.out.println("Introduce el limite menor: ");
		int limiteMenor=s.nextInt();
		System.out.println("Introduce el limite mayor: ");
		int limiteMayor=s.nextInt();
		int array[]=new int[150];
		for(int i=0;i<array.length;i++) {
			array[i]=(int)(Math.round(Math.random()*(limiteMayor-limiteMenor)+limiteMenor));
			System.out.println(array[i]);
		}
		s.close();
	}

}
