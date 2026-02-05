package ejercicios_condicionales;

import java.util.ArrayList;
import java.util.Scanner;

public class Ejercicio_4 {

	public static void main(String[] args) {
		/*Repetir el ejercicio 3º de este bloque de ejercicios, 
		pero mostrando el menor valor de los cinco introducidos por el usuario*/
		Scanner s=new Scanner(System.in);
//		ArrayList<Integer>numeros=new ArrayList<>();
//		for(int i=0;i<5;i++) {
//			System.out.println("Introduce un numero: ");
//			int numUsuario=s.nextInt();
//			numeros.add(numUsuario);
//		}
//		numeros.sort(null);
//		System.out.println("El numero mas pequeño es: "+numeros.getFirst());
//		s.close();
		
		//Otro metodo
		int min=Integer.MAX_VALUE;
		for(int i=0;i<5;i++) {
			System.out.println("Introduce un numero: ");
			int numUsuario=s.nextInt();
			if(numUsuario<=min) {
				min=numUsuario;
			}
		}
		System.out.println("El numero menor es: "+min);
	}

}
