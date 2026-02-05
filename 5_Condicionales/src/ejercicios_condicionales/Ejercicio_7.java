package ejercicios_condicionales;
import java.util.*;
public class Ejercicio_7 {

	public static void main(String[] args) {
		/*Realiza un ejercicio que acumule cinco números introducidos por el usuario, es decir, que
		sume las cantidades introducidas, pero realizando cuatro categorías: números negativos,
		números bajos (entre 0 y 25, ambos inclusive), números medios (entre 26 y 250, ambos
		inclusive) y números grandes (mayores de 250). Al finalizar el programa, se debe mostrar el
		total de las sumas de los números introducidos en cada una de las categorías*/
		Scanner s=new Scanner(System.in);
		int suma_Neg=0,suma_Baj=0,suma_Med=0,suma_Gra=0;
		for(int i=0;i<5;i++) {
			System.out.println("Introduce un numero: ");
			int num=s.nextInt();
			if(num<0) {
				suma_Neg=+num; //SUMA_NEG=SUMA_NEG+NUM;
			}else if(num>=0&&num<=25) {
				suma_Baj=+num;
			}else if(num>25 && num<=250) {
				suma_Med=+num;
			}else if(num>250) {
				suma_Gra+=num;
			}
		}
		System.out.println("------------------------CATEGORIA NEGATIVOS---------------------------");
		System.out.println("Suma de los numeros: "+suma_Neg);
		System.out.println("------------------------CATEGORIA BAJOS---------------------------");
		System.out.println("Suma de los numeros: "+suma_Baj);
		System.out.println("------------------------CATEGORIA MEDIOS---------------------------");
		System.out.println("Suma de los numeros: "+suma_Med);
		System.out.println("------------------------CATEGORIA GRANDES---------------------------");
		System.out.println("Suma de los numeros: "+suma_Gra);
		s.close();
	}

}
