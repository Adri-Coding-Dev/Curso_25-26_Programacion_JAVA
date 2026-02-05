package ejercicios_condicionales;
import java.util.*;
public class Ejercicio_3 {

	public static void main(String[] args) {
		/*Repetir el ejercicio 1º de este bloque de ejercicios, pero pidiendo al usuario cinco
		variables. Es decir, se pedirán al usuario cinco variables y se mostrará el mayor valor en
		pantalla*/
		Scanner s=new Scanner(System.in);
//		ArrayList<Integer>numeros=new ArrayList<>();
//		for(int i=0;i<5;i++) {
//			System.out.println("Introduce un numero: ");
//			int numUsuario=s.nextInt();
//			numeros.add(numUsuario);
//		}
//		numeros.sort(null);
//		System.out.println("El numero mas grande es: "+numeros.getLast());
//		s.close();
		
		//Otro Metodo
		int max=Integer.MIN_VALUE;
		for(int i=0;i<5;i++) {
			System.out.println("Introduce un numero: ");
			int numUsuario2=s.nextInt();
			if(numUsuario2>=max) {
				max=numUsuario2;
			}
		}
		System.out.println("El numero mayor es: "+max);
	}

}
