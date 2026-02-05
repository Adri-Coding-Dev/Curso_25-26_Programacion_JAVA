package beginner_Exercises;
import java.util.*;
public class Ejercicio_3 {

	public static void main(String[] args) {
		/*Realiza un ejercicio que pida números al usuario, tantos 
		 *como indique el usuario. al final debe aparecer cuantos 
		 *números positivos y negativos se han introducido*/
		Scanner s=new Scanner(System.in);
		System.out.println("Cuantos numeros quiers introducir?");
		int limite=s.nextInt();
		int contadorPos=0,contadorNeg=0;
		for(int i=0;i<limite;i++) {
			System.out.println("Introduce un numero");
			int num=s.nextInt();
			if(num>=0) {
				contadorPos++;
			}else {
				contadorNeg++;
			}
		}
		System.out.println("Cantidad de Positivos: "+contadorPos);
		System.out.println("Cantidad de Negativos: "+contadorNeg);
		s.close();
	}

}
