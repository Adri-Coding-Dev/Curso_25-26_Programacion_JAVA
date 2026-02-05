package strings_tests;
import java.util.*;
public class Ejercicio_8 {

	public static void main(String[] args) {
		/*Cifrado César. Crea un método que tome una cadena y un número como 
		 *argumentos, y aplique el cifrado César a la cadena. El cifrado 
		 *César consiste en mover cada letra en el alfabeto por un número fijo. 
		 *Por ejemplo, con un desplazamiento de 3, "hola" se convierte en "krod"*/
		Scanner s=new Scanner(System.in);
		System.out.println("Introduce una cadena de texto: ");
		String cadena=s.nextLine();
		System.out.println("Introduce el numero de desaplazamiento entre letras:");
		int desplazamiento=s.nextInt();
		String cifrado=cifradoCesar(cadena,desplazamiento);
		System.out.println(cifrado);
	}

	private static String cifradoCesar(String cadena, int desplazamiento) {
		String cifrado="";
		for(int i=0;i<cadena.length();i++) {
			char caracter=cadena.charAt(i);
			if(Character.isLetter(caracter)) {
				if(Character.isLowerCase(caracter)) {
					caracter=(char)('a' + (caracter - 'a' + desplazamiento)% 26);
				}else if(Character.isUpperCase(caracter)) {
					caracter=(char)('A' + (caracter - 'A' + desplazamiento)% 26);
				}
			}
			cifrado+=caracter;
		}
		return cifrado;
	}

}
