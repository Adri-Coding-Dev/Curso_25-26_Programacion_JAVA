package beginner_Exercises;
import java.util.*;
public class Ejercicio_1 {

	public static void main(String[] args) {
		/*Realiza un programa que pida al usuario cuatro números enteros,
		 *y calcule la suma solo de aquellos números introducidos por 
		 *el usuario, que sean mayores de 10. Es decir, que si el
		 *usuario introduce el 5, el 15, el 6 y el 25, el programa 
		 *debe calcular la suma solo de 15 más 25, ya que 5 y 6 son menores de 10
		 * */
		Scanner s=new Scanner(System.in);
		int suma=0;
		for(int i=0;i<4;i++) {
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
