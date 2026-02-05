package ejercicios_condicionales;
import java.util.*;

public class Ejercicio_10 {

	public static void main(String[] args) {
		/*Realiza una calculadora avanzada, pero con la particularidad de que las operaciones
		deben ser raices, potencias y el módulo de la división*/
		Scanner s=new Scanner(System.in);
		motrarOpciones();
		int opcion=s.nextInt();
		switch(opcion) {
		case 1:
			System.out.println("Raiz cuadrada");
			System.out.println("Introduce el numero: ");
			int num_Raiz=s.nextInt();
			calcular_Raiz(num_Raiz);
			break;
		case 2:
			System.out.println("Potencias");
			System.out.println("Introduce la Base");
			int base=s.nextInt();
			System.out.println("introduce la potencia");
			int potencia=s.nextInt();
			calcular_Potencia(base,potencia);
			break;
		case 3:
			System.out.println("Modulo de la division");
			System.out.println("Introduce el divisor");
			int divisor=s.nextInt();
			System.out.println("Introduce el dividendo");
			int dividendo=s.nextInt();
			calcular_Modular(divisor,dividendo);
			break;
		default:
			System.out.println("Error, opcion no valida");
		}
		s.close();
	}

	private static void calcular_Modular(int divisor, int dividendo) {
		//Calcular modular
		double modular=(divisor%dividendo);
		System.out.println("Modular: "+modular);
	}

	private static void calcular_Potencia(int base, int potencia) {
		//Calculamos la potencia
		double potencia_Res=(Math.pow(base,potencia));
		System.out.println("Potencia: "+potencia_Res);
		
	}

	private static void calcular_Raiz(int num_Raiz) {
		//Calculamos la raiz cuadrada
		double raiz=(Math.sqrt(num_Raiz));
		System.out.println("Raiz: "+raiz);
		
	}

	private static void motrarOpciones() {
		System.out.println("1.- Raiz cuadrada de un numero");
		System.out.println("2.- Potencias de dos numeros");
		System.out.println("3.- Modulo de la division entre dos numeros");
		
	}

}
