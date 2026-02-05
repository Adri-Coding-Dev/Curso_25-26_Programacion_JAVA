package ejerciciosInciales;
import java.util.*;
public class Ejercicio_6 {

	public static void main(String[] args) {
		/*
		  ------------------------------------------------------------------
		 | 																	|
		 | 						Ejercicio_6									|
		 | 																	|
		  ------------------------------------------------------------------
		 *																	*
		 * -> Realiza un programa que calcule la cuota mensual				*
		 * 																	*
		 -------------------------------------------------------------------- */
		/*
		 * El enunciado completo nos dice que el usuario debe introducir
		 * un Euribor (float), un Diferencial (float), una Capital (float) y
		 * un numero de plazos a pagar (int)
		 * 
		 * Ademas, nos dice que el interes anual es el Euribor + Diferencial
		 * El interes mensual (Im) es el (interes anual / 12)/100 
		 * */
		//Usamos el metodo Scanner para interactuar con datos del Usuario
		Scanner s=new Scanner(System.in);
		System.out.println("Introduce el Euribor (float): ");
		float Euribor=s.nextFloat();
		System.out.println("Introduce el Diferencial (float): ");
		float Diferencial=s.nextFloat();
		System.out.println("Introduce la Capital (float): ");
		float Capital=s.nextFloat();
		System.out.println("Introduce el Numero de plazas (int): ");
		int Plazas=s.nextInt();
		
		//Primero, vamos a calcular el interes anual y el mensual
		float Interes_Anual=Euribor+Diferencial;
		float Interes_Mensual=(Interes_Anual/12)/100;
		
		//Ahora, para no abrumarnos con tantas operaciones, podemos separar
		//las operaciones de arriba de la division y las de abajo
		
		//Hacemos Uso de la libreria Math, para operaciones con potencias
		double arriba_Div=(Interes_Mensual*((Math.pow(Interes_Mensual+1, Plazas))));
		double abajo_Div=((Math.pow(Interes_Mensual+1, Plazas))-1);
		
		
		//Lo montamos todo 
		double Cuota_Mensual=(Capital*(arriba_Div/abajo_Div));
		
		//Mostramos el resultado
		System.out.println("La cuota mensual es: "+Cuota_Mensual);
		
		//Cerramos el Scanner
		s.close();
	}

}
