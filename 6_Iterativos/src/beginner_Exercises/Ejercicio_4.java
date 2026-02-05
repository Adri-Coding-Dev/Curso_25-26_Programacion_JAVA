package beginner_Exercises;
import java.util.*;
public class Ejercicio_4 {

	public static void main(String[] args) {
		/*Media de una cantidad de números introducidos por el usuario. 
		 *El usuario especificará una cantidad de números, tras lo cual 
		 *se le deben pedir tantos como este haya indicado. Al final se 
		 *debe imprimir la media
		 * */
		Scanner s=new Scanner(System.in);
		System.out.println("Introduce la cantidad de numeros a insertar");
		int limite=s.nextInt();
		int suma=0;
		for(int i=0;i<limite;i++){
			System.out.println("Introduce un numero");
			int num=s.nextInt();
			suma+=num;
		}
		float media=suma/limite;
		System.out.println("Media de todos los numeros: "+media);
		s.close();
	}

}
