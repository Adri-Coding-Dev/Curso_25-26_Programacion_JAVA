package rookie_exercises;
import java.util.*;
public class Ejercicio_6 {

	public static void main(String[] args) {
		/*Desarrolla un programa que sume los dígitos de un número entero*/
		Scanner s=new Scanner(System.in);
		System.out.println("Introduce un numero");
		int numUsuario=s.nextInt();
		int copia=numUsuario;
		int resto=0;
		int recreacion=0;
		int iteracion=0;
		int suma=0;
		boolean salir=false;
		while(!salir) {
			if(recreacion==numUsuario) {
				salir=true;
				break;
			}else {
				resto=copia%10;
				copia/=10;
				suma+=resto;
				recreacion+=(int)(Math.pow(10, iteracion)*resto);
				iteracion++;
			}
		}
		System.out.println("Suma de los digitos: "+suma);
	}

}
