package ejerciciosInciales;
import java.util.*;
public class Ejercicio_1 {

	public static void main(String[] args) {
		/*
		  ------------------------------------------------------------------
		 | 																	|
		 | 						Ejercicio_1									|
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
		 * -> Muestra en pantalla los tres numeros en una sola fila			*
		 * 																	*
		 -------------------------------------------------------------------- */
		//Metodo Scanner para que podamos introducir valores por pantalla
		Scanner s=new Scanner(System.in);
		
		//Preguntamos al usuario para que introduzca un valor
		System.out.println("Introduce un numero entero: ");
		int numEntero=s.nextInt();
		System.out.println("Introduce un numero flotante: ");
		float numFlotante=s.nextFloat();
		System.out.println("Introduce un numero doble: ");
		double numDoble=s.nextDouble();
		//Mostramos todos los valores de las tres variables por pantalla
		System.out.println("Entero: "+numEntero+", Flotante: "+numFlotante+", Double: "+numDoble);
		
		//Para evitar que consuma mas memoria de lo normal, cerramos el scanner
		s.close();
	}

}
