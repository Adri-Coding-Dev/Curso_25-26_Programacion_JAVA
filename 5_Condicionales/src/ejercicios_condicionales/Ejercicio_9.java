package ejercicios_condicionales;
import java.util.*;
public class Ejercicio_9 {

	public static void main(String[] args) {
		/*Realiza un ejercicio que califique la nota obtenida por un alumno/a en un examen. El
		ejercicio pedirá al usuario la nota numérica, y se imprimirá en pantalla su traducción a nota
		de texto: muy deficiente, insuficiente, suficiente, bien, notable y sobresaliente. Debes
		realizar el ejercicio a través de una sentencia switch, no de if anidados*/
		Scanner s=new Scanner(System.in);
		System.out.println("Introduce tu nota: ");
		int nota=s.nextInt();
		switch(nota) {
		case 0,1,2:
			System.out.println("Muy deficiente");
		break;
		case 3,4:
			System.out.println("Deficiente");
		break;
		case 5:
			System.out.println("Suficiente");
			break;
		case 6:
			System.out.println("Bien");
			break;
		case 7,8:
			System.out.println("Notable");
			break;
		case 9,10:
			System.out.println("Sobresaliente");
			break;
		default:
			System.out.println("Error con la nota introucida");
		}
		s.close();
	}
}
