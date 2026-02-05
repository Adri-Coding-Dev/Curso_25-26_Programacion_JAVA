package strings_tests;
import java.util.*;
public class Ejercicio_2 {

	public static void main(String[] args) {
		/*Desde "main" lee una cadena de caracteres (String) del usuario, 
		 *con Scanner. Envía la cadena de caracteres a un método que cuente 
		 *y muestre en consola cuántas vocales y consonantes hay en el texto. 
		 *En la cadena de caracteres no debe haber vocales con tilde o 'ñ'
		 * */
		Scanner s=new Scanner(System.in);
		System.out.println("Introduce una cadena de Texto (1 palabra): ");
		String cadena=s.nextLine();
		int vocales=contadorVocales(cadena);
		int consonantes=contadorConsonantes(cadena);
		System.out.println("Cantidad de Vocales: "+vocales);
		System.out.println("Cantidad de Consonantes: "+consonantes);
	}

	private static int contadorConsonantes(String cadena) {
		String consonantes="bcdfghjklmnpqrstvwxyz"; //Quitamos la 'ñ'
		int cantidad=0;
		for(int i=0;i<cadena.length();i++) {
			char caracter=cadena.charAt(i);
			for(int j=0;j<consonantes.length();j++) {
				char consonante=consonantes.charAt(j);
				if(consonante==caracter) {
					cantidad++;
				}
			}
		}
		
		return cantidad;
	}

	private static int contadorVocales(String cadena) {
		int vocales=0;
		for(int i=0;i<cadena.length();i++) {
			char caracter=cadena.charAt(i);
			switch(caracter) {
			case 'a','e','i','o','u':
				vocales++;
			}
		}
		return vocales;
	}

}
