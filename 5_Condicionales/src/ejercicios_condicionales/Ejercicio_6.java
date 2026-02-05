package ejercicios_condicionales;
import java.util.*;
public class Ejercicio_6 {

	public static void main(String[] args) {
		/*Realizar un ejercicio en Java que pida un número al usuario e 
		indique en pantalla si se trata de un número par o impar. 
		Recuerda que un número par es aquel cuyo resto de su división 
		entre dos sea siempre cero, y recuerda que un número impar es 
		aquel cuyo resto de su división entre dos sea siempre uno*/
		Scanner s=new Scanner(System.in);
		System.out.println("Introduce un numero: ");
		int numUsuario=s.nextInt();
		if(numUsuario%2==0) {
			System.out.println("El numero es par");
		}else {
			System.out.println("El numero es impar");
		}
		s.close();
	}

}
