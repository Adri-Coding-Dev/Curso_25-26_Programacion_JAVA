package beginner_Exercises;
import java.util.*;
public class Ejercicio_8 {

	public static void main(String[] args) {
		/*Realiza un ejercicio que pida números al usuario. 
		 *El programa debe detenerse cuando el usuario introduzca el 
		 *número 0 (cero), que no debe tenerse en cuenta para ninguna 
		 *operación aritmética, simplemente para salir de la aplicación. 
		 *Cuando el programa haya terminado, se debe sacar en pantalla 
		 *el valor de la suma y de la media de todos los números
		 * */
		Scanner s=new Scanner(System.in);
		boolean salir=false;
		int suma=0;
		int contador=0;
		while(!salir) {
			System.err.println("Introduce un numero: ");
			int num=s.nextInt();
			if(num==0) {
				salir=true;
				break;
			}else{
				suma+=num;
				contador++;
			}
		}
		System.out.println("Suma de todos los numeros: "+suma);
		float media=suma/contador;
		System.out.println("Media total: "+media);
		s.close();
	}

}
