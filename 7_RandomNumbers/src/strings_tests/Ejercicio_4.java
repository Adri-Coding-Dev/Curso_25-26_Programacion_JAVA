package strings_tests;
import java.util.*;
public class Ejercicio_4 {

	public static void main(String[] args) {
		/*Desde "main" lee una cadena de caracteres (String) del usuario, 
		 *con Scanner. Envía la cadena de caracteres a un método que devuelva 
		 *el número de palabras que contiene la cadena de caracteres. Piensa 
		 *en que una palabra puede estar separada de otra por una cantidad 
		 *indeterminada de espacios en blanco.*/
		Scanner s=new Scanner(System.in);
		System.out.println("Introduce una frase: ");
		String cadena=s.nextLine();
		int palabras=contarPalabras(cadena);
		System.out.println("Cantidad de palabras: "+(palabras+1));
	}

	private static int contarPalabras(String cadena) {
		int palabras=0;
		cadena=cadena.replace(" ","_");
		for(int i=0;i<cadena.length();i++) {
			char caracter=cadena.charAt(i);
			if(caracter=='_') {
				if(siguienteCaracterLetra(caracter,i,cadena))palabras++;
			}
		}
		return palabras;
	}
	private static boolean siguienteCaracterLetra(char caracter,int posicion, String cadena) {
		boolean letra=false;
		for(int i=0;i<cadena.length();) {
			if(cadena.charAt(posicion+1)!='_') {
				letra= true;
				break;
			}else {
				letra= false;
				break;
			}
		}
		return letra;
	}
}
