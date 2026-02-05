package beginner_Exercises;
import java.util.*;
public class Ejercicio_5 {

	public static void main(String[] args) {
		/*Mayor y menor de una cantidad de números dada por el usuario.
		 *El usuario introducirá una cantidad de números, tras lo cual 
		 *se le deben pedir tantos como este haya indicado. Al final se 
		 *debe imprimir el mayor y el menor de todos los introducidos*/
		Scanner s=new Scanner(System.in);
		int min=Integer.MAX_VALUE,max=Integer.MIN_VALUE;
		System.out.println("Introduce la cantidad de numeros a insertar");
		int limite=s.nextInt();
		for(int i=0;i<limite;i++) {
			System.out.println("Introduce un numero");
			int num=s.nextInt();
			if(num>max) {
				max=num;
			}
			if(num<min) {
				min=num;
			}
		}
		System.out.println("Numero mayor: "+max);
		System.out.println("Numero menor: "+min);
		s.close();
	}

}
