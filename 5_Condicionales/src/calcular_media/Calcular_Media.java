package calcular_media;
import java.util.*;
public class Calcular_Media {
	/*
		  ------------------------------------------------------------------
		 | 																	|
		 | 						Ejercicio_1									|
		 | 																	|
		  ------------------------------------------------------------------
		 *																	*
		 * -> Realiza un programa que gestione notas, añadiendolas y		*
		 *	mostrando la media                                              *
		 * 																	*
		 -------------------------------------------------------------------- */
	public static void main(String[] args) {
		//Pedir al usuario notas, hacer media y decir si esta aprovado
		boolean salir=false;
		Scanner s=new Scanner(System.in);
		ArrayList<Double>notas=new ArrayList<>();
		while(!salir) {
			mostrarOpciones();
			int opcion=s.nextInt();
			switch(opcion) {
			case 1:
				System.out.println("Agregar nota: ");
				double nota=s.nextDouble();
				notas.add(nota);
				break;
			case 2:
				System.out.println("Calcular Media: ");
				double suma=0;
				for(int i=0;i<notas.size();i++) {
					suma+=notas.get(i);
				}
				double media=suma/notas.size();
				System.out.println(media);
				if(media>=5) {
					System.out.println("Estas aprovado!!!");
					if(media>=6 & media<9) {
						System.out.println("Notable");
					}else if (media>=9){
						System.out.println("Sobresaliente");
					}
				}else {
					if(notas.size()==0) {
						System.out.println("No has introducido notas!!!");
						break;
					}
					System.out.println("No has llegado al minimo, estas suspenso");
				}
				break;
			case 3:
				System.out.println("Saliendo");
				salir=true;
				break;
			default:
				System.out.println("Opcion no valida...");
			}
		}
		s.close();
		

	}

	private static void mostrarOpciones() {
		System.out.println("1.- Agregar Nota");
		System.out.println("2.- Calcular Medias");
		System.out.println("3.- Salir");
		
	}
}
