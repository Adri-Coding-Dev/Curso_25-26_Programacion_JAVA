package beginner_Exercises;
import java.util.*;
public class Ejercicio_7 {

	public static void main(String[] args) {
		/*Reforma el ejercicio anterior, para que el 
		 *límite sea también introducido por el usuario*/
		Scanner s=new Scanner(System.in);
		System.out.println("Introduce un numero:");
		int num=s.nextInt();
		System.out.println("Introduce el limite");
		int limite=s.nextInt();
		int multiplo=0;
		for(int i=0;i<100;i++) {
			if(multiplo>=limite) {
				break;
			}else {
				multiplo=num*i;
				System.out.println("Multiplo "+i+": "+multiplo);
			}
		}
		s.close();
	}

}
