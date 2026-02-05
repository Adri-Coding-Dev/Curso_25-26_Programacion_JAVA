package ejerciciosInciales;
import java.util.*;
public class Ejercicio_3 {

	public static void main(String[] args) {
		/*
		  ------------------------------------------------------------------
		 | 																	|
		 | 						Ejercicio_3									|
		 | 																	|
		  ------------------------------------------------------------------
		 *																	*
		 * -> Realiza un programa que pida al usuario 3 numeros				*
		 * 																	*
		 * -> Muestra y calcula la suma de dichos numeros                   *
		 * 																	*
		 -------------------------------------------------------------------- */
		
		//Usamos el metodo Scanner para interactuar con datos del usuario
		Scanner s=new Scanner(System.in);
		System.out.println("Introduce un primer numero: ");
		//Al no especificar nada, voy a usar double por si quiere agregar decimales
		double numUsuario_1=s.nextDouble();
		System.out.println("Introduce un segundo numero: ");
		double numUsuario_2=s.nextDouble();
		System.out.println("Introduce un tercer numero: ");
		double numUsuario_3=s.nextDouble();
		
		//Realizamos la suma, recogiendo dicho valor en una variable
		double suma=(numUsuario_1+numUsuario_2+numUsuario_3);
		
		//Mostramos por pantalla el resultado obtenido
		System.out.println("El resultado de la suma es: "+suma);
		
		//Cerramos el Scanner
		s.close();
	}

}
