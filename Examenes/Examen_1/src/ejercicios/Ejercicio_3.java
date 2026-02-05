package ejercicios;
import java.util.*;
public class Ejercicio_3 {

	public static void main(String[] args) {
		Scanner s=new Scanner(System.in);
		System.out.println("Introduce un numero: ");
		int n=s.nextInt();
		boolean perfecto=esCuadradoPerfecto(n);
		System.out.println(perfecto);
	}

	private static boolean esCuadradoPerfecto(int n) {
		int posibleCuadrado;
		for(int i=0;i<=n;i++) {
			posibleCuadrado=i*i;
			if(posibleCuadrado==n) {
				return true;
			}
		}
		return false;
	}

}
