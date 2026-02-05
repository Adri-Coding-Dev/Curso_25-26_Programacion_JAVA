package beginner_Exercises;
import java.util.*;
public class Ejercicio_10 {

	public static void main(String[] args) {
		/*Realiza un ejercicio que pida números al usuario, 
		 *hasta que uno de ellos sea 0 (cero). Para cada número 
		 *introducido se debe mostrar en pantalla su tabla de multiplicar, 
		 *desde el 0 (cero) hasta el 10.
		 * */
		Scanner s=new Scanner(System.in);
		boolean salir=false;
		while(!salir) {
			System.out.println("Introduce un numero");
			int num=s.nextInt();
			if(num==0) {
				break;
			}else{
				System.out.println("________Tabla del "+num+"_________");
				for(int i=0;i<10;i++) {
					System.out.println(i+"*"+num+": "+(i*num));
				}
			}
		}
		s.close();
	}

}
