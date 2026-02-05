package ejercicios_condicionales;

import java.util.Scanner;

public class Ejercicio_8 {

	public static void main(String[] args) {
		/*Realiza un ejercicio igual al anterior, pero al acabar, el programa no debe mostrar la
		suma de los números, sino cuantos han entrado en cada categoría. A esto se llema
		contadores, a diferencia del ejercicio anterior, que reciben el nombre de acumuladore*/
		
		Scanner s=new Scanner(System.in);
		int contador_Negativos=0,contador_Bajos=0,contador_Medios=0,contador_Grandes=0;
		for(int i=0;i<5;i++) {
			System.out.println("Introduce un numero: ");
			int num=s.nextInt();
			if(num<0) {
				contador_Negativos++; //CONTADOR_NEGATIVOS=CONTADOR_NEGATIVOS+1;
			}else if(num>=0&&num<=25) {
				contador_Bajos++;
			}else if(num>25 && num<=250) {
				contador_Medios++;
			}else if(num>=250) {
				contador_Grandes++;
			}
		}
		System.out.println("------------------------CATEGORIA NEGATIVOS---------------------------");
		System.out.println("Cantidad de negativos: "+contador_Negativos);
		System.out.println("------------------------CATEGORIA BAJOS---------------------------");
		System.out.println("Cantidad de bajos: "+contador_Bajos);
		System.out.println("------------------------CATEGORIA MEDIOS---------------------------");
		System.out.println("Cantidad de medios: "+contador_Medios);
		System.out.println("------------------------CATEGORIA GRANDES---------------------------");
		System.out.println("Cantidad de grandes: "+contador_Grandes);
		
		s.close();

	}

}
