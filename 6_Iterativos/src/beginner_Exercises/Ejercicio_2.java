package beginner_Exercises;
import java.util.*;
public class Ejercicio_2 {

	public static void main(String[] args) {
		/* Modifica el ejercicio anterior de manera que, 
		 *en lugar de que el programa siempre pida cuatro números,
		 *sea el usuario el que decida cuantos números se van a pedir*/
		Scanner s=new Scanner(System.in);
		System.out.println("Cuantos numeros quieres introducir?");
		int limite=s.nextInt();
		int suma=0;
		for(int i=0;i<limite;i++) {
			System.out.println("Introduce un numero");
			int num=s.nextInt();
			if(num>=10) {
				suma+=num;
			}
		}
		System.out.println("La suma de los mayores de 10 es: "+suma);
		s.close();
	}

}
