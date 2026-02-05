package ejercicios_condicionales;
import java.util.*;
public class Ejercicio_1 {

	public static void main(String[] args) {
		/*Realiza un ejercicio que pida dos variables 
		al usuario, tras lo cual, muestre en pantalla el
		número mayor de los dos introducidos*/
		Scanner s=new Scanner(System.in);
		System.out.println("Introduce un numero: ");
		int numUsu1=s.nextInt();
		System.out.println("Introduce otro numero: ");
		int numUsu2=s.nextInt();
		if(numUsu1>numUsu2) {
			System.out.println("El primer numero es el mayor: "+numUsu1);
		}else if(numUsu1==numUsu2) {
			System.out.println("Los numeros son iguales!!!");
		}else {
			System.out.println("El segundo numero es el mayor: "+numUsu2);
		}
		s.close();

	}

}
