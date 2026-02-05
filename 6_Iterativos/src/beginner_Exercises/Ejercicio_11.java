package beginner_Exercises;
import java.util.*;
public class Ejercicio_11 {

	public static void main(String[] args) {
		/*Realiza un ejercicio que pida números al usuario, 
		 *hasta que uno de ellos sea 0 (cero). Al finalizar se 
		 *debe imprimir en pantalla cuantos son positivos y cuantos 
		 *negativos. No debes tener en cuenta el 0 (cero).
		 * */
		Scanner s=new Scanner(System.in);
		boolean salir=false;
		int contadorPos=0,contadorNeg=0;
		while(!salir) {
			System.out.println("Introduce un numero");
			int num=s.nextInt();
			if(num==0) {
				break;
			}else {
				if(num>0) {
					contadorPos++;
				}else {
					contadorNeg++;
				}
			}
		}
		System.out.println("Cantidad de Positivos: "+contadorPos);
		System.out.println("Cantidad de Negativos: "+contadorNeg);
		s.close();
	}

}
