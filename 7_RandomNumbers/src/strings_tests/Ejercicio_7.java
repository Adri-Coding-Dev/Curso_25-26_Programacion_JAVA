package strings_tests;
import java.util.*;
public class Ejercicio_7 {

	public static void main(String[] args) {
		/*Eliminar espacios en blanco en una cadena. Crea un método que 
		 *tome una cadena y elimine todos los espacios en blanco*/
		Scanner s=new Scanner(System.in);
		System.out.println("Introduce una cadena de texto: ");
		String cadena=s.nextLine();
		String sinEspacio=eliminarEspacios(cadena);
		System.out.println(sinEspacio);
	}

	private static String eliminarEspacios(String cadena) {
		cadena=cadena.replace(" ","");
		String sinEspacios=cadena;
		return sinEspacios;
	}

}
