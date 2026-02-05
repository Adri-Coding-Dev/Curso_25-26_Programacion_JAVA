package ejercicios_condicionales;
import java.util.*;
public class Ejercicio_12 {

	public static void main(String[] args) {
		/*Escribe un programa que lea el importe de una compra y la cantidad recibida y calcule el
		cambio a devolver, teniendo en cuenta que el número de monedas que se devuelven debe
		ser mínimo. Suponer que el sistema monetario utilizado consta de monedas de 100, 50, 25,
		5, y 1 unidad. El precio viene dado en unidades monetarias enteras.*/
		Scanner s=new Scanner(System.in);
		int moneda_100=0,moneda_50=0,moneda_25=0,moneda_5=0,moneda_1=0;
		System.out.println("Introduzca el valor del precio:");
		int precio=s.nextInt();
		System.out.println("Introduzca el total a pagar:");
		int pagaUsu=s.nextInt();
		if(pagaUsu<precio) {
			System.out.println("Falta dinero");
		}else {
			int restante=pagaUsu-precio;
			System.out.println("Precio a devolver: "+restante);
			while(restante!=0) {
				if(restante>100) {
					moneda_100++;
					restante=restante-100;
				}else
				if(restante>50) {
					moneda_50++;
					restante=restante-50;
				}else
				if(restante>25) {
					moneda_25++;
					restante=restante-25;
				}else
				if(restante>5) {
					moneda_5++;
					restante=restante-5;
				}else
				if(restante>=1) {
					moneda_1++;
					restante=restante-1;
				}else {
					restante=0;
				}
			}
			System.out.println("Cambio: "+moneda_100+" de 100, "+moneda_50+" de 50, "+moneda_25+" de 25, "+moneda_5+" de 5, "+(moneda_1)+" de 1");
		}
		s.close();
	}

}
