package beginner_Exercises;
import java.util.*;
public class Ejercicio_9 {

	public static void main(String[] args) {
		/*Realiza un ejercicio que pida números al usuario hasta 
		 *que este introduzca el 0 (cero). Al finalizar el ejercicio se 
		 *debe imprimir en pantalla el valor mayor y el menor introducido. El 
		 *valor 0 (cero) no debe tenerse en cuenta*/
		Scanner s=new Scanner(System.in);
		boolean salir=false;
		int max=Integer.MIN_VALUE,min=Integer.MAX_VALUE;
		while(!salir) {
			System.out.println("Introduce un numero");
			int num=s.nextInt();
			if(num==0) {
				break;
			}else {
				if(num>max) {
					max=num;
				}
				if(num<min) {
					min=num;
				}
			}
		}
		System.out.println("Numero mayor: "+max);
		System.out.println("Numero menor: "+min);
		s.close();
	}

}
