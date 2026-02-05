package strings_tests;
import java.util.*;
public class Ejercicio_1 {

	public static void main(String[] args) {
		/*Desde "main" lee una cadena de caracteres (String) del usuario, 
		 * con Scanner. Envía la cadena de caracteres a un método que 
		 * muestre dicha cadena en orden inverso
		 * */
		Scanner s=new Scanner(System.in);
		System.out.println("Introduce una cadena de Texto (1 palabra): ");
		String cadena=s.nextLine();
		String inverso=invertirCadena(cadena);
		System.out.println("Cadena invertida: "+inverso);
	}

	private static String invertirCadena(String cadena) {
		String inverso="";
		for(int i=cadena.length()-1;i>=0;i--) {
			char caracter=cadena.charAt(i);
			inverso+=caracter;
		}
		return inverso;
		
	}

}
