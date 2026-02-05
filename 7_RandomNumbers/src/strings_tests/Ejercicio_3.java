package strings_tests;
import java.util.*;
public class Ejercicio_3 {

	public static void main(String[] args) {
		/*Desde "main" lee una cadena de caracteres (String) del usuario, 
		 *con Scanner. Envía la cadena de caracteres a un método que devuelva 
		 *un valor booleano, indicando si la cadena de caracteres se trata 
		 *de un palíndromo (se lee igual al derecho que al revés, como "radar".
		 *No escribas tildes.*/
		Scanner s=new Scanner(System.in);
		System.out.println("Introduce una cadena de Texto (1 palabra): ");
		String cadena=s.nextLine();
		boolean palindromo=esPalindromo(cadena);
		System.out.println(palindromo);
	}

	private static boolean esPalindromo(String cadena) {
		String inverso="";
		for(int i=cadena.length()-1;i>=0;i--) {
			char caracter=cadena.charAt(i);
			inverso+=caracter;
		}
		System.out.println(inverso);
		if(inverso.equals(cadena)) {
			return true;
		}else return false;
	}

}
