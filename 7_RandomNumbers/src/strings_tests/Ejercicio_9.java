package strings_tests;
import java.util.*;
public class Ejercicio_9 {

	public static void main(String[] args) {
		/*Contar ocurrencias .Crea un método que cuente cuántas veces 
		 *aparece una subcadena en una cadena dada*/
		Scanner s=new Scanner(System.in);
		System.out.println("Introduce una cadena de texto: ");
		String cadena=s.nextLine();
		System.out.println("Que subcadena quieres contar?");
		String subcadena=s.nextLine();
		int ocurrencias=verificarOcurrencias(cadena,subcadena);
		System.out.println(ocurrencias);
	}

	private static int verificarOcurrencias(String cadena, String subcadena) {
		int contador = 0;
        int indice = 0;

        //Mientras se encuentre la subcadena dentro del texto
        while ((indice = cadena.indexOf(subcadena, indice)) != -1) {
            contador++;
            indice += subcadena.length();
        }

        return contador;
	}

}
