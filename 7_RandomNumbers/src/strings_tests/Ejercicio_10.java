package strings_tests;
import java.util.*;
public class Ejercicio_10 {

	public static void main(String[] args) {
		/*
		 * */
		Scanner s=new Scanner(System.in);
		System.out.println("Introduce una cadena de Texto: ");
		String cadena=s.nextLine();
		String palabra=contarPalabraMasLarga(cadena);
		System.out.println("La palabra "+palabra+" es la palabra mas larga...");
		System.out.println(palabra+" tiene "+palabra.length()+" letras.");
	}

	private static String contarPalabraMasLarga(String cadena) {
		String palabra="";
		String cadenaMasLarga="";
		int contador=0,maximoLetras=0;
		char caracter=' ';
		for(int i=0;i<=cadena.length();i++) {
			if(i!=cadena.length()) {
				caracter=cadena.charAt(i);
			}
			if(caracter!=' ' && i!=(cadena.length())) {
				contador++;
				palabra+=caracter;
			}else {
				if(contador>maximoLetras) {
					maximoLetras=contador;
					contador=0;
					if(palabra.length()>cadenaMasLarga.length()) {
						cadenaMasLarga=palabra;
						palabra="";
					}else {
						palabra="";
					}
				}else {
					palabra="";
				}
			}
		}
		return cadenaMasLarga;
	}

}
