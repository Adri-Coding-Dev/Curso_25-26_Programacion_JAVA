package ejercicios_condicionales;

import java.util.ArrayList;
import java.util.Scanner;

public class Ejercicio_5 {

	public static void main(String[] args) {
		/*Pedir al usuario cinco números, e imprimir posteriormente 
		el mayor valor introducido y el menor valor introducido*/
		Scanner s=new Scanner(System.in);
		ArrayList<Integer>numeros=new ArrayList<>();
		for(int i=0;i<5;i++) {
			System.out.println("Introduce un numero: ");
			int numUsuario=s.nextInt();
			numeros.add(numUsuario);
		}
		numeros.sort(null);
		System.out.println("El numero mas pequeño es: "+numeros.getFirst());
		System.out.println("El numero mas grande es: "+numeros.getLast());
		s.close();
		
		//Otro metodo
		int max=Integer.MIN_VALUE,min=Integer.MAX_VALUE;
		for(int i=0;i<5;i++) {
			System.out.println("Introduce un numero: ");
			int numUsuario=s.nextInt();
			if(numUsuario>=max) {
				max=numUsuario;
			}
			if(numUsuario<=min) {
				min=numUsuario;
			}
		}
		System.out.println("El numero mayor es: "+max);
		System.out.println("El numero menor es: "+min);
	}

}
