package arrays_challenge;
import java.util.*;
public class Ejercicio_9 {

	public static void main(String[] args) {
		/* Realiza un ejercicio que inicialice un array de 150 
		 *elementos enteros al azar entre 0 y 100. Debes conseguir 
		 *multiplicar cada uno de los elementos del array por un 
		 *número determinado, pedido al usuario. Debes realizar el 
		 *ejercicio mediante un método que admita el array como
		 *argumento, además del número por el que multiplicar y 
		 *realice la acción pedida. Cabecera del método 'public 
		 *static void multiplicaValoresPorFactor (int a[], int 
		 *factor)'*/
		int min=0,max=100;
		int array[]=new int[150];
		for(int i=0;i<array.length;i++) {
			array[i]=(int)(Math.round(Math.random()*(max-min)+min));
			System.out.println(array[i]);
		}
		Scanner s=new Scanner(System.in);
		System.out.println("Introduce el numero a multiplicar: ");
		int factor=s.nextInt();
		multiplicarValoresPorFactor(array,factor);
	}

	private static void multiplicarValoresPorFactor(int[] array, int factor) {
		for(int i=0;i<array.length;i++) {
			System.out.println(array[i]*factor);
		}	
	}
}
