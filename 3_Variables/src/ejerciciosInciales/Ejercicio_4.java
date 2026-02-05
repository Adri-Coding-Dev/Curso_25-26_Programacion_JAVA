package ejerciciosInciales;
import java.util.*;
public class Ejercicio_4 {

	public static void main(String[] args) {
		/*
		  ------------------------------------------------------------------
		 | 																	|
		 | 						Ejercicio_4									|
		 | 																	|
		  ------------------------------------------------------------------
		 *																	*
		 * -> Realiza un programa que pida al usuario 3 numeros				*
		 * 																	*
		 * -> Muestra y calcula la media de dichos numeros                  *
		 * 																	*
		 -------------------------------------------------------------------- */
		
		//Usamos scanner para poder interactuar con datos del usuario
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
		
		//Hacemos la media utilizando la variable suma y la cantidad de numeros (3)
		double media=(suma/3);
		
		//Mostramos el resultado por pantalla
		System.out.println("La media de tus tres numeros es: "+media);
		
		//Cerramos el Scanner 
		s.close();
	}

}
