package ejercicios_condicionales;

import java.util.Scanner;

public class Ejercicio_2 {

	public static void main(String[] args) {
		//Repetir el ejercicio anterior para mostrar el número menor de los dos introducidos
		Scanner s=new Scanner(System.in);
		System.out.println("Introduce un numero: ");
		int numUsu1=s.nextInt();
		System.out.println("Introduce otro numero: ");
		int numUsu2=s.nextInt();
		if(numUsu1>numUsu2) {
			System.out.println("El segundo numero es el menor: "+numUsu2);
		}else if(numUsu1==numUsu2) {
			System.out.println("Los numeros son iguales!!!");
		}else {
			System.out.println("El primer numero es el menor: "+numUsu1);
		}
		s.close();

	}

}
