package ejerciciosInciales;
import java.util.*;
public class Ejercicio_5 {

	public static void main(String[] args) {
		/*
		  ------------------------------------------------------------------
		 | 																	|
		 | 						Ejercicio_5									|
		 | 																	|
		  ------------------------------------------------------------------
		 *																	*
		 * -> Realiza un programa que pida al usuario 2 numeros				*
		 * 																	*
		 * -> Muestra los numeros ANTES y DESPUES de INTERCAMBIAR sus       *
		 * valores                                                          *
		 * 																	*
		 -------------------------------------------------------------------- */
		
		//Usamos Scanner para interactuar con datos del Usuario
		Scanner s=new Scanner(System.in);
		System.out.println("Introduce tu primer numero: ");
		int numUsuario_1=s.nextInt();
		System.out.println("Introduce tu segundo numero: ");
		int numUsuario_2=s.nextInt();
		
		//Mostramos ambos valores antes del intercambio
		System.out.println("Numero 1: "+numUsuario_1);
		System.out.println("Numero 2: "+numUsuario_2);
		
		//Usamos una variable auxiliar para guardar el valor de una de las 
		//Variable y no perder su valor
		
		int var_aux=numUsuario_1;
		numUsuario_1=numUsuario_2;
		numUsuario_2=var_aux;
		
		//De esta forma conservamos el valor de la variable auxiliar sin perderlo
		System.out.println("Numero 1: "+numUsuario_1);
		System.out.println("Numero 2: "+numUsuario_2);
		
		//Cerramos el Scanner
		s.close();
	}

}
