package strings_tests;
import java.util.*;
public class Ejercicio_11 {

	public static void main(String[] args) {
		/*Invertir contenido de palabras. Crea un método que tome una 
		 *cadena y devuelva una nueva cadena donde las palabras están 
		 *invertidas. Por ejemplo, "Hola Mundo" se convierte en "aloH odnuM".*/
		
		Scanner s=new Scanner(System.in);
		System.out.println("Introduce una cadena de Texto");
		String cadena=s.nextLine();
		String copia="";
		String inverso="";
		char caracter=' ';
		for(int i=0;i<=cadena.length();i++) {
			if(i!=cadena.length()) {
				caracter=cadena.charAt(i);
			}
			if(caracter!=' ' && i!=cadena.length()) {
				copia+=caracter;
			}else {
				for(int j=copia.length()-1;j>=0;j--) {
					char caracterInverso=copia.charAt(j);
					inverso+=caracterInverso;
				}
				System.out.print(inverso+" ");
				copia="";
				inverso="";
			}
		}
	}

}
