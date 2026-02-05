package ejercicios;
import java.util.*;
public class Ejercicio_1 {

	public static void main(String[] args) {
		Scanner s=new Scanner(System.in);
		System.out.println("Introduce el limite menor: ");
		int min=s.nextInt();
		System.out.println("Introduce el limite mayor: ");
		int max=s.nextInt();
		if(min>max) {
			int aux=min;
			min=max;
			max=aux;
		}
		int contadorPares=0,contadorImpares=0,sumaPares=0,sumaImpares=0;
		if(min==max) {
		}else {
		for(int i=min;i<=max;i++) {
			boolean par=verificarPar(i);
			if(par) {
				contadorPares++;
				sumaPares+=i;
			}else {
				contadorImpares++;
				sumaImpares+=i;
			}
		}
		}
		System.out.println("Suma de Pares: "+sumaPares);
		System.out.println("Cantidad de Pares: "+contadorPares);
		
		System.out.println("Suma de Impares: "+sumaImpares);
		System.out.println("Cantidad de Impares: "+contadorImpares);
		if(sumaPares>sumaImpares) {
			System.out.println("Los pares suman mas");
		}else if(sumaPares<sumaImpares){
			System.out.println("Los impares suman mas");
		}else {
			System.out.println("No hay numeros validos en el rango");
		}
	}

	private static boolean verificarPar(int i) {
		if(i%2==0) {
			return true;
		}else {
			return false;
		}
	}

}
