package strings_tests;
import java.util.*;
public class Ejercicio_5 {

	public static void main(String[] args) {
		/*Desde "main" lee dos cadenas de caracteres (String) del usuario,
		 *con Scanner. Envía las cadenas de caracteres a un método que las 
		 *admitirá con los nombres "string1" y "string2". El método debe 
		 *comparar las dos cadenas de forma lexicográfica y devolver: -1 si 
		 *"string1" es menor que "string2"; 1 si "string1" es mayor que 
		 *"string2"; 0 si las dos cadenas son iguales. No uses vocales 
		 *con tilde o 'ñ'*/
		
		Scanner s=new Scanner(System.in );
		System.out.println("Introduce una primera cadena de Texto: ");
		String string1=s.nextLine();
		
		System.out.println("Introduce una segunda cadena de Texto: ");
		String string2=s.nextLine();
		
		int resultado=compararCadenas(string1,string2);
		System.out.println(resultado);
	}

	private static int compararCadenas(String string1, String string2) {
		if(string1.length()>string2.length()) {
			return 1;
		}else if(string1.length()<string2.length()) {
			return -1;
		}else {
			return 0;
		}
		
	}
}
