package rookie_exercises;
import java.util.*;
public class Ejercicio_8 {

	public static void main(String[] args) {
		/*Desarrolla un programa que encuentre y muestre los números perfectos en un rango
		 * dado.*/
		Scanner s=new Scanner(System.in);
		System.out.println("Introduce un minimo");
		int minimo=s.nextInt();
		System.out.println("Introduce un maximo");
		int maximo=s.nextInt();
		for(int num=minimo;num<=maximo;num++) {
			int suma = 0; // Suma de divisores

            // Calcular divisores propios
            for (int i = 1; i < num; i++) {
                if (num % i == 0) {
                    suma += i;
                }
            }

            // Si la suma de divisores es igual al número -> perfecto
            if (suma == num && num != 0) {
                System.out.println("Numero encontrado: "+num);
            }
		}
		s.close();
	}

}
