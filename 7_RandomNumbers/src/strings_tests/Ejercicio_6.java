package strings_tests;
import java.util.*;
public class Ejercicio_6 {

	public static void main(String[] args) {
		/*Desde "main" lee una cadena de caracteres (String) del usuario, 
		 *con Scanner. Envía la cadena de caracteres a un método que devuelva
		 *otra cadena de caracteres, igual a la recibida pero en la que todas 
		 *las letras mayúsculas se conviertan en minúsculas. No debes usar 
		 *vocales con tilde o 'ñ'*/
		Scanner s=new Scanner(System.in);
		System.out.println("Introduce una cadena de texto: ");
		String cadena=s.nextLine();
		String minuscula=convertirMinusculas(cadena);
		System.out.println(minuscula);
	}

	private static String convertirMinusculas(String cadena) {
		String minuscula=cadena.toLowerCase();
		return minuscula;
		
	}

}
