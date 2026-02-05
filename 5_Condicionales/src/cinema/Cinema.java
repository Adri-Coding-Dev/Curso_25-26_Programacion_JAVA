package cinema;
import java.util.*;
import java.time.*;
public class Cinema {
	public static void main(String[] args) {
		/*
		  ------------------------------------------------------------------
		 | 																	|
		 | 						Ejercicio_1									|
		 | 																	|
		  ------------------------------------------------------------------
		 *																	*
		 * Make a program that calculates the price of movie tickets based	*
		 *	on the number of people and the day of the week. The base price *
		 *  of a ticket is 8 euros. On Wednesday (spectator's day), the base*
		 *  price is 5 euros. Thursdays are the couple's day, so admission  *
		 *  for two costs 11 euros. With the Student card you get a 10%     *
		 *  discount														*
		 *------------------------------------------------------------------*/

		Scanner s=new Scanner(System.in);
		double base_price=8;
		LocalDateTime dia_Semana=LocalDateTime.now(); //Cogemos la fecha actual
		DayOfWeek dia_semana=dia_Semana.getDayOfWeek(); //Sacamos el dia de la semana
		String dia;
		dia=String.valueOf(dia_semana);
		System.out.println(dia);
		System.out.println("¿Cuantas personas sois?");
		int personas=s.nextInt();
		System.out.println("¿Teneis carnet estudiante?");
		boolean carnet=s.nextBoolean();
		if(carnet) {
			base_price=base_price-(base_price*0.1);
		}
		System.out.println("Precio por persona: "+base_price);
		double precio_final;
		switch(dia) {
		case "MONDAY","THURSDAY","FRIDAY":
			base_price=8;
			if(carnet) {
				base_price=base_price-(base_price*0.1);
			}
			precio_final=base_price*personas;
			System.out.println("Total a pagar: "+precio_final);
			break;
		case "WEDNESDAY":
			base_price=5;
			if(carnet) {
				base_price=base_price-(base_price*0.1);
			}
			precio_final=base_price*personas;
			System.out.println("Total a pagar: "+precio_final);
			break;
		case "TUESDAY":
			System.out.println("¿Sois parejas?");
			boolean pareja=s.nextBoolean();
			if(pareja) {
				int parejas=personas/2;
				base_price=11;
				if(carnet) {
					base_price=base_price-(base_price*0.1);
				}
				precio_final=base_price*parejas;
				System.out.println("Total a pagar: "+precio_final);
				
			}else {
				base_price=8;
				if(carnet) {
					base_price=base_price-(base_price*0.1);
				}
				precio_final=base_price*personas;
				System.out.println("Total a pagar: "+precio_final);
			}
			break;
		}
		s.close();
	}
}
