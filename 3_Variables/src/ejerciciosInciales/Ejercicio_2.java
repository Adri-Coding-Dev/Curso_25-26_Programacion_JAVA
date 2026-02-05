package ejerciciosInciales;
import java.util.*;
public class Ejercicio_2 {

	public static void main(String[] args) {
		/*
		  ------------------------------------------------------------------
		 | 																	|
		 | 						Ejercicio_2									|
		 | 																	|
		  ------------------------------------------------------------------
		 *																	*
		 * -> Realiza un programa que pida al usuario 3 numeros				*
		 * 																	*
		 * -> 1 de Tipo entero												*
		 * 																	*
		 * -> 1 de Tipo flotante											*
		 * 																	*
		 * -> 1 de Tipo doble												*
		 * 																	*
		 * -> Muestra en pantalla los tres numeros en el momnento           *
		 * 	que haya sido introducido		                                *
		 * 																	*
		 -------------------------------------------------------------------- */
		
		//Usamos el Metodo Scanner para poder recibir datos y mostrarlos
		Scanner s=new Scanner(System.in);
		//Pedimos al usuario que introduzca los numeros
		System.out.println("Introduce un numero entero: ");
		int numEntero=s.nextInt();
		//Mostramos directamente el resultado
		System.out.println("Tu numero entero es: "+numEntero);
		
		System.out.println("Introduce un numero flotante: ");
		float numFlotante=s.nextFloat();
		//Mostramos directamente el resultado
		System.out.println("Tu numero flotante es: "+numFlotante);
		
		System.out.println("Introduce un numero doble: ");
		double numDoble=s.nextDouble();
		//Mostramos directamente el resultado
		System.out.println("Tu numero double es: "+numDoble);
		
		//Cerramos el Scanner
		s.close();
	}

}
