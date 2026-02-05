package strings_tests;
import java.util.*;
public class Ejercicio_12 {

	public static void main(String[] args) {
		/*Pangramas. Crea un método que compruebe si una cadena de 
		 *caracteres es un pangrama. Un pangrama es una frase que contiene 
		 *todas las letras del alfabeto al menos una vez*/
		Scanner s=new Scanner(System.in);
		System.out.println("Introduce una cadena texto");
		String cadena=s.nextLine();
		boolean panagrama=verificarPanagrama(cadena);
		System.out.println(panagrama);
	}

	private static boolean verificarPanagrama(String cadena) {
		String abecedario="abcdefghijklmnñopqrstuvwxyz";
		cadena=cadena.toLowerCase();
		for(int i=0;i<cadena.length();i++) {
			char caracter=cadena.charAt(i);
			for(int j=0;j<abecedario.length();j++) {
				char letra=abecedario.charAt(j);
				if(caracter==letra) {
					abecedario=abecedario.replace(letra,' ');
					break;
				}
			}
			abecedario=abecedario.replace(" ","");
		}
		if(abecedario.length()!=0) {
			return false;
		}else {
			return true;
		}
	}

}
